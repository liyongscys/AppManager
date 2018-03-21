package com.cabletech.common.ftp;

import java.util.Properties;
/**
 * ftp的相关参数信息
 * @author YuLeyuan
 *
 */
public class FtpInfo {
	/**
	 * ftp服务器地址
	 */
	private String host;
	/**
	 * ftp端口号
	 */
	private int port;
	/**
	 * ftp用户名
	 */
	private String userName;
	/**
	 * ftp登录密码
	 */
	private String userPassword;
	/**
	 * ftp的根路径
	 */
	private String rootPath;
	
	/**
	 * 空的构造方法
	 */
	public FtpInfo(){
		
	}
	
	/**
	 * 构造方法
	 * @param properties properties
	 */
	public FtpInfo(Properties properties){
        host=properties.getProperty("ftp.host");
        port=Integer.parseInt(properties.getProperty("ftp.port","21"));
        userName=properties.getProperty("ftp.user.name");
        userPassword=properties.getProperty("ftp.user.password");
        //rootPath=properties.getProperty("ftp.root.path");
		
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the rootPath
	 */
	public String getRootPath() {
		return rootPath;
	}

	/**
	 * @param rootPath the rootPath to set
	 */
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	
}
