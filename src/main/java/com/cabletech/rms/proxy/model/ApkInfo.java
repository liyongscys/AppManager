package com.cabletech.rms.proxy.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by liyong on 2018/3/19.
 */
public class ApkInfo {
    /**
     * 主键
     */
    private int id;
    /**
     * 应用名
     */
    @NotNull(message = "{error.appKey.empty}")
    @Size(min = 4, max = 64, message = "{error.appKey.length}")
    private String appKey;
    /**
     * APK版本号(内部版本号)
     */
    @Min(value = 1, message = "{error.versionCode.min}")
    private int versionCode;
    /**
     * APK版本号
     */
    private String versionName;
    /**
     * 是否強制更新
     */
    private boolean forceUpdate;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件大小
     */
    private long fileSize;
    /**
     * 发布说明
     */
    private String desc;
    /**
     * 上传人
     */
    private String uploader;
    /**
     * 上传时间
     */
    private Date uploadTime;

    public boolean getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the appName
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * @param appKey the appName to set
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * @return the versionCode
     */
    public int getVersionCode() {
        return versionCode;
    }

    /**
     * @param versionCode the versionCode to set
     */
    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * @return the versionName
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * @param versionName the versionName to set
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the uploader
     */
    public String getUploader() {
        return uploader;
    }

    /**
     * @param uploader the uploader to set
     */
    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    /**
     * @return the uploadTime
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * @param date the uploadTime to set
     */
    public void setUploadTime(Date date) {
        this.uploadTime = date;
    }

}
