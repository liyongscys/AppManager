package com.cabletech.rms.proxy.model;

/**
 * @author YuLeyuan
 * 控制台登录用户
 */
public class AdminUser {
	//用户登录名
    private String name;
    //用户登录密码
    private String password;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
    
}
