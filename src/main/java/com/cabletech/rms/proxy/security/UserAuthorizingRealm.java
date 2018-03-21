package com.cabletech.rms.proxy.security;

import java.util.Set;

import com.cabletech.rms.proxy.model.AdminUser;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cabletech.rms.proxy.dao.AdminUserDao;
import com.google.common.collect.ImmutableSet;

/**
 * 用户认证
 *
 * @author YuLeyuan
 */
public class UserAuthorizingRealm extends AuthorizingRealm {
    /**
     * logger
     */
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdminUserDao adminUserDao;

    /* (non-Javadoc)
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {

        UsernamePasswordToken loginToken = (UsernamePasswordToken) authcToken;
        String username = loginToken.getUsername();
        //String loginPassword=new String(loginToken.getPassword());
        logger.info("用户  {} 登录", username);

        if (username == null) {
            throw new AccountException("登录用户名不能为空。");
        }
        AdminUser found = null;
        try {
            found = adminUserDao.findByName(username);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (found == null) {
            throw new UnknownAccountException("用户帐号[" + username + "]不存在");
        }
        return generateAuthenticationInfo(found);
    }

    private SimpleAuthenticationInfo generateAuthenticationInfo(AdminUser user) {
        String username = user.getName();
        char[] password = user.getPassword().toCharArray();
        String salt = username;
        SimpleAuthenticationInfo info = null;
        info = new SimpleAuthenticationInfo(username, password, getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(salt));
        return info;
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        // null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException(
                    "PrincipalCollection method argument cannot be null.");
        }

        String name = (String) principals.fromRealm(getName()).iterator()
                .next();

        Set<String> roleNames;

        if (name == null) {
            roleNames = ImmutableSet.of();
        } else {
            roleNames = ImmutableSet.of("admin", "user");
        }

        SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo(
                roleNames);

        String domains = "smmo,smmt,gprsmo,gprsmt";
        authorization.addStringPermission(domains + ":list");

        return authorization;
    }

}