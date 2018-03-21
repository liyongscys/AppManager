package com.cabletech.common.ftp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件上传线程
 * @author YuLeyuan
 *
 */
public class UploaderThread extends Thread{
	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(UploaderThread.class);
	/** ftp连接信息 */
	private FtpInfo ftpInfo;
	/** 本地文件路径 */
	private String localPath;
	/** ftp服务器上的文件路径 */
	private String remotePath;
	
	
	/**
	 * 构造函数
	 * @param ftpInfo ftpInfo
	 * @param localPath localPath
	 * @param remotePath remotePath
	 */
	public UploaderThread(FtpInfo ftpInfo,String localPath,String remotePath){
		this.ftpInfo=ftpInfo;
		this.localPath=localPath;
		this.remotePath=remotePath;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Uploader uploader=new Uploader();
        try {
			uploader.connect(ftpInfo);
			UploadStatus status=uploader.upload(localPath, remotePath);
			if(status==UploadStatus.Upload_New_File_Success){
				//删除本地文件
				File localFile=new File(localPath);
				FileUtils.deleteQuietly(localFile);
				logger.info("本地文件[{}]删除成功",localPath);
			}else{
				logger.error("上传文件失败");
			}				
		} catch (IOException e) {
			logger.error("upload error:", e);
		}
	}
	
}
