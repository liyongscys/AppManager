package com.cabletech.rms.proxy.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.cabletech.rms.proxy.dao.autoMapper.ApkDownloadHisMapper;
import com.cabletech.rms.proxy.model.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cabletech.common.util.GsonUtils;
import com.cabletech.rms.proxy.RmsAppPath;
import com.cabletech.rms.proxy.dao.ApkInfoDao;
import com.google.gson.reflect.TypeToken;

/**
 * APK管理
 *
 * @author YuLeyuan
 */
@Controller
public class ApkController extends BaseController {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ApkController.class);
    /** */
    private static final String TIME_FMT = "yyyyMMddHHmmss";
    /**
     * Dao
     */
    @Autowired(required = false)
    private ApkInfoDao apkInfoDao;
    @Autowired
    private ApkDownloadHisMapper apkDownloadHisMapper;

    /**
     * 注入应用程序路径对象
     */
    @Autowired
    private RmsAppPath appPath;

    /**
     * 列出所有最APK新版本
     *
     * @param response response
     * @param paging   paging
     * @return
     */
    @RequestMapping(value = "/listapk.do", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public final String listApk(HttpServletResponse response, Paging paging) {
        ApkInfo apk = new ApkInfo();
        PagedResult<ApkInfo> pagedResult = apkInfoDao.pagingList(apk, paging);
        Type typeOfSrc = new TypeToken<PagedResult<ApkInfo>>() {
        }.getType();
        String json = GsonUtils.toJson(pagedResult, typeOfSrc);
        logger.info(json);
        return json;
    }

    /**
     * 删除APK
     *
     * @param response response
     * @param id       id
     * @return
     */
    @RequestMapping(value = "/deleteapk.do", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public final String deleteApk(HttpServletResponse response, int id) {
        ApkInfo apk = apkInfoDao.get(id);
        if (apk != null) {
            apkInfoDao.delete(apk);
            String fullPath = appPath.apkDir + apk.getFilePath();
            File file = new File(fullPath);
            if (file.exists()) {
                file.delete();
                logger.info("apk " + file.getAbsolutePath()
                        + " delete success.");
            } else {
                logger.info("apk " + file.getAbsolutePath() + " not exists.");
            }
            return "success:true";
        } else {
            return "success:false";
        }

    }


    protected String encodeFilenameEx(String filename) throws UnsupportedEncodingException {
        return new String(filename.getBytes("utf-8"), "ISO8859-1");
    }

    /**
     * 上传APK
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse对象
     * @param file     file
     * @param apkInfo  apk对象
     * @return 返回json格式
     * @throws Exception 异常
     */
    @RequestMapping(value = "/uploadapk.do", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public final String uploadapk(HttpServletRequest request,
                                  HttpServletResponse response, @RequestParam MultipartFile file,
                                  ApkInfo apkInfo) throws Exception {
        response.setContentType(RESP_CONTENT_TYPE);

        String originalName = file.getOriginalFilename();
        logger.info("ApkName={}", originalName);
        logger.info("ApkSize={}", file.getSize());

        String time = new SimpleDateFormat(TIME_FMT).format(new Date());
        String newName = "F" + time + "_" + originalName;

        String localPath = appPath.apkDir + "/" + newName;
        localPath = FilenameUtils.normalize(localPath, true);

        ApkInfo apk = new ApkInfo();
        apk.setAppKey(apkInfo.getAppKey());
        apk.setVersionCode(apkInfo.getVersionCode());
        apk.setVersionName(apkInfo.getVersionName());
        apk.setDesc(apkInfo.getDesc());
        apk.setFileName(originalName);
        apk.setFilePath("/" + newName);
        apk.setFileSize(file.getSize());
        Subject currentUser = SecurityUtils.getSubject();

        apk.setUploader((String) currentUser.getPrincipal());
        apk.setUploadTime(new Date());
        apk.setForceUpdate(apkInfo.getForceUpdate());
        apkInfoDao.insert(apk);

        File tempFile = new File(localPath);
        FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);

        logger.info("Apk save to {}", localPath);

        return "success:'true'";

    }

    @RequestMapping(value = "/versionCheck.do", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public String apkVersionCheck(@Valid ApkInfo apkInfo, BindingResult result, HttpServletRequest request) {
        ApkInfo apk = apkInfoDao.getLastApk(apkInfo);
        final ApkVersion apkVersion = new ApkVersion();
        apkVersion.setIsNeedUpdate(ApkVersion.NOT_NEW_VERSION);
        if (apk != null) {
            apkVersion.setVersionCode(apk.getVersionCode());
            apkVersion.setApkSize(apk.getFileSize());
            apkVersion.setReleaseLog(apk.getDesc());
            apkVersion.setVersionName(apk.getVersionName());
            if (apk.getForceUpdate()) {
                apkVersion.setIsNeedUpdate(ApkVersion.FORCE_UPDATE);
            } else {
                apkVersion.setIsNeedUpdate(ApkVersion.NEW_VERSION);
            }
            apkVersion.setApkDownloadUrl(this.getDownloadUrl(request, apk.getId()));
            final StringBuffer requestURL = request.getRequestURL();
            logger.info(requestURL.toString() + request.getContextPath());
        }


        /*if (br.hasErrors()) {
            return GsonUtils.toJson(br.getAllErrors());
        }*/
        return GsonUtils.toJson(apkVersion);
    }

    @RequestMapping(value = "/test.do", method = RequestMethod.GET)
    @ResponseBody
    public String test(@Valid ApkInfo apkInfo) {
        ApkInfo apk = apkInfoDao.getLastApk(apkInfo);
        return GsonUtils.toJson(apk);
    }


    private String getDownloadUrl(HttpServletRequest request, int apkId) {
        StringBuilder url = new StringBuilder(128);
        url.append(request.getScheme());
        url.append("://");
        url.append(request.getServerName());
        url.append(":");
        url.append(request.getServerPort());
        url.append(request.getContextPath());
        url.append("/downloadapk.do?id=");
        url.append(apkId);
        return url.toString();
    }

    /**
     * 下载APK
     *
     * @param id       id
     * @param response response
     * @throws Exception
     */
    @RequestMapping(value = "/downloadapk.do", method = RequestMethod.GET)
    public void download(@RequestParam int id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        String fullPath = "";
        ApkInfo apk = apkInfoDao.get(id);
        String relativePath = apk.getFilePath();
        fullPath = appPath.apkDir + "/" + relativePath;
        String fileName = apk.getFileName();
        final ApkDownloadHis apkDownloadHis = new ApkDownloadHis();
        apkDownloadHis.setApkId(String.valueOf(apk.getId()));
        apkDownloadHis.setAppKey(apk.getAppKey());
        apkDownloadHis.setDownloadIp(request.getRemoteAddr());
        apkDownloadHis.setDownloadTime(new Date());
        apkDownloadHisMapper.insert(apkDownloadHis);
        executeDownload(fullPath, fileName, response);
    }


    /**
     * 执行下载动作
     *
     * @param fullPath        文件所处的完整路径，包括文件名
     * @param defaultSaveName 文件下载保存的默认文件名
     * @param response        response
     * @throws Exception
     */
    private void executeDownload(String fullPath, String defaultSaveName, HttpServletResponse response) throws Exception {
        logger.info("ApkName={}", defaultSaveName);
        response.setContentType("text/html;charset=utf-8");
        InputStream input = null;
        OutputStream output = null;
        logger.info(fullPath);
        try {
            long fileLength = new File(fullPath).length();
            String encodedName = encodeFilename(defaultSaveName);
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + encodedName);
            response.setHeader("Content-Length", String.valueOf(fileLength));
            input = new FileInputStream(fullPath);
            output = response.getOutputStream();
            IOUtils.copy(input, output);

        } catch (Exception e) {
            logger.error("下载过程出错", e);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }

    protected String encodeFilename(String filename) throws UnsupportedEncodingException {
        String encodedName = URLEncoder.encode(filename, "utf-8");
        encodedName = filename.replaceAll("\\+", "%20");
        return encodedName;
    }
}
