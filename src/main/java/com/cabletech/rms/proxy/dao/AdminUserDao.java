package com.cabletech.rms.proxy.dao;

import com.cabletech.rms.proxy.model.AdminUser;

/**
 * @author YuLeyuan
 *
 */
public interface AdminUserDao {
	public AdminUser findByName(String name);
	public void insert(AdminUser user);
	public void update(AdminUser user);
	public void delete(AdminUser user);
}
