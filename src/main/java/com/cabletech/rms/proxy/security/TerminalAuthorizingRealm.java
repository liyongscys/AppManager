package com.cabletech.rms.proxy.security;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
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
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;

/**
 * @author YuLeyuan
 *
 */
public class TerminalAuthorizingRealm extends AuthorizingRealm {
	/**logger*/
	final Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	/**
	 * 
	 */
	public TerminalAuthorizingRealm() {

		setCachingEnabled(false);
	}

	/**
	 * @param name name
	 */
	public TerminalAuthorizingRealm(String name) {
		this();
		setName(name);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String imei=token.getUsername();
		String loginName=new String(token.getPassword());	
		if(imei!=null){
			Subject currentSubject = SecurityUtils.getSubject();  
	    	Session session = currentSubject.getSession();    	
			return new SimpleAuthenticationInfo(imei, loginName, getName());
		}else{
			throw new UnknownAccountException("登录失败，系统中找不到IMEI="+imei+"的设备");
		}

	}

	/**
	 * 授权实现
	 * @param principals principals
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
	    //null usernames are invalid
	    if (principals == null) {
	      throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
	    }

	    String imei = (String) principals.fromRealm(getName()).iterator().next();
		
		Set<String> roleNames;

	    if (imei == null) {
	      roleNames = ImmutableSet.of();
	    } else {
	      roleNames = ImmutableSet.of("patrolTerminal", "adminTerminal");
	    }

	    SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo(roleNames);
	    
	    String domains="0x00,0x10,0x13,0x15,0x16,0x1A,0x21,0x22";
		authorization.addStringPermission(domains+":execute");
		
		return authorization;
	}

}
