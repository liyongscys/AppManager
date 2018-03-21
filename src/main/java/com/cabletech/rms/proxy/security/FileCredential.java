package com.cabletech.rms.proxy.security;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cabletech.common.util.PropertiesUtils;

/**
 * @author YuLeyuan
 *
 */
public class FileCredential {
	/** logger */
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String KEY_USERNAME="username";
	private static final String KEY_PASSWORD="password";
	
	private static final String DEFAULT_USERNAME="admin";
	private static final String DEFAULT_PASSWORD=PasswordEncrypt.encrypt(DEFAULT_USERNAME,"admin123");
	
	private String filePath;
	private Properties props;
	/**
	 * 构造方法
	 * @param filePath 文件路径
	 */
	public FileCredential(String filePath){
		this.filePath=filePath;		
	}
	/**
	 * 获取参数
	 * @return
	 */
	private Properties getProps(){
		if(props==null){
			refresh();
		}
		return props;
	}
	/**
	 * 刷新
	 */
	public void refresh(){
		props=PropertiesUtils.getProperties(filePath);
	}
	
	public String getUsername(){
		return getProps().getProperty(KEY_USERNAME, DEFAULT_USERNAME);
	}
	
	public String getPassword(){
		return getProps().getProperty(KEY_PASSWORD, DEFAULT_PASSWORD);
	}
	
	private String generateComment(){
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer comment=new StringBuffer("updated ");
		comment.append(fmt.format(new Date()));
		return comment.toString();
	}
	/**
	 * 保存凭据
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public boolean saveCredential(String username,String password){
		Properties saveProps=new Properties();
		saveProps.setProperty(KEY_USERNAME, username);
		saveProps.setProperty(KEY_PASSWORD, password);
		/*
		Properties saveProps=getProps();
		saveProps.setProperty(KEY_USERNAME, username);
		saveProps.setProperty(KEY_PASSWORD, password);	
		*/
		PropertiesUtils.writeProperties(filePath, saveProps, generateComment());
		logger.info("save username={} password={******}",username);
		return true;
	}

}
