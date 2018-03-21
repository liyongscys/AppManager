package com.cabletech.rms.proxy.security;

import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * @author YuLeyuan
 *
 */
public class PasswordEncrypt {
	/**
	 * 
	 * @param salt username
	 * @param password password
	 * @return
	 */	
	public static String encrypt(String salt,String password){
		return sha256HashPassword(salt,password);
	}
	
	/**
	 * 
	 * @param salt username
	 * @param password password
	 * @return
	 */
	private static String sha256HashPassword(String salt,String password){
    	return new Sha256Hash(password, salt, 1024).toHex();
	}
	/**
	 * 测试方法
	 * @param args 参数
	 */
	public static void main(String[] args){
		String encrpt=PasswordEncrypt.encrypt("admin","admin123");
	}
}
