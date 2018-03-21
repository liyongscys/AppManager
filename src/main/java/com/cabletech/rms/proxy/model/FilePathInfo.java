package com.cabletech.rms.proxy.model;

/**
 * 上传文件信息
 * @author YuLeyuan
 *
 */
public class FilePathInfo {
    /**
     * 主键
     */
    private String id;
    /**
     * 存储路径
     */
    private String savePath;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件字节数
     */
    private long fileSize=0;
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件说明
     */
    private String description;
    /**
     * 分类
     */
    private String catlog;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the savePath
	 */
	public String getSavePath() {
		return savePath;
	}
	/**
	 * @param savePath the savePath to set
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the catlog
	 */
	public String getCatlog() {
		return catlog;
	}
	/**
	 * @param catlog the catlog to set
	 */
	public void setCatlog(String catlog) {
		this.catlog = catlog;
	}
    
}
