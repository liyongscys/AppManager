package com.cabletech.rms.proxy.model;

import java.util.Date;

/**
 * apk下载记录
 * Created by liyong on 2018/3/20.
 */
public class ApkDownloadReord {
    private String appKey;
    private String apkId;
    private Date downloadTime;
    private String downloadIp;
    private String downloadIMEI;

    public String getApkId() {
        return apkId;
    }

    public void setApkId(String apkId) {
        this.apkId = apkId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }

    public String getDownloadIp() {
        return downloadIp;
    }

    public void setDownloadIp(String downloadIp) {
        this.downloadIp = downloadIp;
    }

    public String getDownloadIMEI() {
        return downloadIMEI;
    }

    public void setDownloadIMEI(String downloadIMEI) {
        this.downloadIMEI = downloadIMEI;
    }
}
