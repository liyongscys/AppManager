package com.cabletech.rms.proxy;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author YuLeyuan
 *
 */
public class RmsAppPath {
	/** logger */
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String DB="db";
	private static final String TEMP="temp";
	private static final String APK="apk";
	private static final String LOG="log";
    /** 加密的密码文件名  */
    public static final String CREDENTIALS_FILE_NAME="credentials.properties";
    /** 配置项文件名  */
    public static final String LOCAL_DB_FILE_NAME="rms-db";
	
	public final String basePath;
	public final String uploadTempDir;
	public final String apkDir;
	public final String dbDir;
	public final String logDir;
	public final String credentialsFile;
	public final String localDbFile;
	
	public RmsAppPath(String appWorkPath){
		basePath=FilenameUtils.normalize(appWorkPath);
		dbDir=FilenameUtils.concat(basePath, DB);
		uploadTempDir=FilenameUtils.concat(basePath, TEMP);
		apkDir=FilenameUtils.concat(basePath, APK);
		logDir=FilenameUtils.concat(basePath, LOG);
		credentialsFile=FilenameUtils.concat(dbDir, CREDENTIALS_FILE_NAME);	
		localDbFile=FilenameUtils.concat(dbDir, LOCAL_DB_FILE_NAME);	
		
		Thread createDirThread = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					createAppDirs();
				} catch (IOException e) {
					logger.error("创建目录出错", e);
					throw new IllegalStateException("创建目录出错",e);
				}
				
			}
		});
		
		createDirThread.start(); 
	
	}
	
	public void printAppPathInfo(){
		StringBuffer sb=new StringBuffer();
		sb.append("\n  basePath:").append(basePath);
		sb.append("\n  dbDir:").append(dbDir);
		sb.append("\n  uploadTempDir:").append(uploadTempDir);
		sb.append("\n  apkDir:").append(apkDir);
		sb.append("\n  logDir:").append(logDir);
		sb.append("\n  credentialsFile:").append(credentialsFile);
		sb.append("\n  localDbFile:").append(localDbFile);
		logger.info("RmsAppPath info :{}", sb.toString());
	}
	
	private void createAppDirs() throws IOException {
		FileUtils.forceMkdir(new File(dbDir));
		FileUtils.forceMkdir(new File(uploadTempDir));
		FileUtils.forceMkdir(new File(apkDir));
		File credentials=new File(credentialsFile);
		
		if(credentials.exists()&&credentials.isDirectory()){
			FileUtils.forceDelete(credentials);
		}
		
		if(!credentials.exists()){
			credentials.createNewFile();
		}
	}
	
}
