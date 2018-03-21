package com.cabletech.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * 公用加密解密工具
 * @ClassName: CryptUtils 
 * @Description: 
 * @author yangyj 
 * @date 2014年10月28日
 * @version V1.0
 */
public class CryptUtils {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(CryptUtils.class);
	
	
	private static CryptUtils instance = null;
	/**
	 * 默认密钥，至少24位
	 */
	private static final String DEFAULT_KEY = "xgx055165327532CabletechInfo";
	private static final String DEFAULT_IV = "20161001";
	private Cipher ENCRYPT_MODE_CIPHER;// 加密
	private Cipher DECRYPT_MODE_CIPHER;// 解密

	
	/**
	 * 默认的构造函数
	 * @param key 字符串
	 */
	private CryptUtils(String key) {
		SecureRandom sr = new SecureRandom();
		//从原始密匙数据创建一个DESKeySpec对象
		DESedeKeySpec dks;
		try {
			dks = new DESedeKeySpec(key.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey skey = keyFactory.generateSecret(dks);
			// Cipher对象实际完成加密操作
			ENCRYPT_MODE_CIPHER = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			DECRYPT_MODE_CIPHER = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			// 用密匙初始化Cipher对象
			IvParameterSpec iv = new IvParameterSpec(DEFAULT_IV.getBytes());
			ENCRYPT_MODE_CIPHER.init(Cipher.ENCRYPT_MODE, skey, iv, sr);
			DECRYPT_MODE_CIPHER.init(Cipher.DECRYPT_MODE, skey, iv, sr);
		} catch (InvalidKeyException e) {
			logger.info("初始化加密解密工具出错：",e);
		} catch (NoSuchAlgorithmException e) {
			logger.info("初始化加密解密工具出错：",e);
		} catch (InvalidKeySpecException e) {
			logger.info("初始化加密解密工具出错：",e);
		} catch (NoSuchPaddingException e) {
			logger.info("初始化加密解密工具出错：",e);
		} catch (InvalidAlgorithmParameterException e) {
			logger.info("初始化加密解密工具出错：",e);
		}
	}


	/**
	 * 获得加密解密实例
	 * @return
	 */
	public static CryptUtils getInstance() {
		/*
		String s = null;
		if (key == null || key[0] == null || key[0].length() < 24) {
			s = DEFAULT_KEY;
		} else {
			s = key[0];
		}
		*/
		if(instance==null) {
			instance = new CryptUtils(DEFAULT_KEY);
		}
		return instance;
	}

	/**
	 * 加密明文 
	 * @param data 明文
	 * @return 密文
	 * @throws Exception
	 */
	public synchronized String encode(String data) {
		if (data == null) {
			data = "";
		}
		byte[] pasByte = null;
		try {
			pasByte = ENCRYPT_MODE_CIPHER.doFinal(data.getBytes("utf-8"));
		} catch (Exception e) {
			logger.info("加密解密工具加密出错：", e);
		}
		return Base64.encode(pasByte);
	}

	/**
	 * 解密密文 
	 * @param data 密文
	 * @return 明文
	 * @throws Exception
	 */
	public synchronized String decode(String data) {
		if (data == null || "".equals(data.trim())) {
			return "";
		}
		byte[] pasByte = null;
		String txt = null;
		try {
			pasByte = DECRYPT_MODE_CIPHER.doFinal(Base64.decode(data));
			txt = new String(pasByte, "UTF-8");
		} catch (Exception e) {
			logger.info("加密解密工具解密出错：", e);
		}
		return txt;
	}

	/**
	 * 将明文按base64进行编码
	 * @param data 明文
	 * @return 将明文按base64进行编码后的密文
	 */
	@SuppressWarnings("unused")
	private String base64Encode(String data) {
		if (data == null) {
			return "";
		}
		return Base64.encode(data.getBytes());
	}

	/**
	 * 将明文按base64进行编码的密文转换为明文 
	 * @param data base64密文
	 * @return 明文
	 */
	@SuppressWarnings("unused")
	private String base64Decode(String data) {
		if (data == null) {
			return "";
		}
		byte[] dByte = null;
		try {
			dByte = Base64.decode(data);
		} catch (IOException e) {
			logger.info("加密解密工具解密出错：", e);
		}
		return new String(dByte);
	}
	public static void main(String[] args) {
		String txt="kkkkkkkkkk";
		String encode = CryptUtils.getInstance().encode(txt);
		CryptUtils.getInstance().decode(encode);

	}

}
