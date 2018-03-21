package com.cabletech.rms.proxy.dao.impl;

import com.cabletech.rms.proxy.dao.AdminUserDao;
import com.cabletech.rms.proxy.model.AdminUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YuLeyuan
 *
 */
@Service("adminUserDao")
public class AdminUserDaoImpl extends CtBaseDao implements AdminUserDao {
	private static final String PRE = AdminUserDao.class.getName() + ".";
	/** sql名 */
	private static final String FIND = PRE + "findByName";
	/** sql名 */
	private static final String DELETE = PRE + "delete";
	/** sql名 */
	private static final String INSERT = PRE + "insert";
	/** sql名 */
	private static final String UPDATE = PRE + "update";
	
	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AdminUserDao#findByName(java.lang.String)
	 */
	@Override
	public AdminUser findByName(String name) {
		List<AdminUser> users=getSqlSession().selectList(FIND,name);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AdminUserDao#insert(com.cabletech.rms.proxy.security.AdminUser)
	 */
	@Override
	public void insert(AdminUser user) {
		getSqlSession().insert(INSERT, user);
		
	}

	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AdminUserDao#update(com.cabletech.rms.proxy.security.AdminUser)
	 */
	@Override
	public void update(AdminUser user) {
		getSqlSession().update(UPDATE, user);
		
	}
	
	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AdminUserDao#delete(com.cabletech.rms.proxy.security.AdminUser)
	 */
	@Override
	public void delete(AdminUser user) {
		getSqlSession().delete(DELETE, user);		
	}

}
