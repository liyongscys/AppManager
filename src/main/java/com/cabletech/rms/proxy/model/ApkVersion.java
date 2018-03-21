package com.cabletech.rms.proxy.model;

/**
 * Created by liyong on 2018/3/19.
 */
public class ApkVersion {
    public final static String NOT_NEW_VERSION = "0";
    public final static String NEW_VERSION = "1";
    public final static String FORCE_UPDATE = "2";
    private long versionCode;
    private String versionName;
    private String isNeedUpdate;
    private String releaseLog;
    private long apkSize;
    private String apkDownloadUrl;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(long versionCode) {
        this.versionCode = versionCode;
    }

    public String getIsNeedUpdate() {
        return isNeedUpdate;
    }

    public void setIsNeedUpdate(String isNeedUpdate) {
        this.isNeedUpdate = isNeedUpdate;
    }

    public String getReleaseLog() {
        return releaseLog;
    }

    public void setReleaseLog(String releaseLog) {
        this.releaseLog = releaseLog;
    }

    public long getApkSize() {
        return apkSize;
    }

    public void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }

    public String getApkDownloadUrl() {
        return apkDownloadUrl;
    }

    public void setApkDownloadUrl(String apkDownloadUrl) {
        this.apkDownloadUrl = apkDownloadUrl;
    }
}
