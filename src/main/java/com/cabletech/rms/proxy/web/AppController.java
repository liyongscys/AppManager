package com.cabletech.rms.proxy.web;


import com.cabletech.common.util.GsonUtils;
import com.cabletech.rms.proxy.dao.AppInfoDao;
import com.cabletech.rms.proxy.model.AppInfo;
import com.cabletech.rms.proxy.model.PagedResult;
import com.cabletech.rms.proxy.model.Paging;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * app管理
 *
 */
@Controller
public class AppController extends BaseController {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    /** */
    /**
     * Dao
     */
    @Autowired(required = false)
    private AppInfoDao appInfoDao;

    @RequestMapping(value = "/saveApp.do", method = RequestMethod.POST)
    @ResponseBody
    public String saveApp(AppInfo appinfo) {
        appinfo.setCreateTime(new Date());
        appInfoDao.insert(appinfo);
        return "success:true";
    }


    /**
     * 列出所有最app新版本
     *
     * @param response response
     * @param paging   paging
     * @return
     */
    @RequestMapping(value = "/listApp.do", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public final String listApp(HttpServletResponse response, Paging paging) {
        AppInfo app = new AppInfo();
        paging.setPageNumber(1);
        paging.setPageSize(10);
        PagedResult<AppInfo> pagedResult = appInfoDao.pagingList(app, paging);
        Type typeOfSrc = new TypeToken<PagedResult<AppInfo>>() {
        }.getType();
        String json = GsonUtils.toJson(pagedResult, typeOfSrc);
        logger.info(json);
        return json;
    }

    /**
     * 删除app
     *
     * @param response response
     * @param id       id
     * @return
     */
    @RequestMapping(value = "/deleteApp.do", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public final String deleteApp(HttpServletResponse response, int id) {
        AppInfo app = appInfoDao.get(id);
        if (app != null) {
            appInfoDao.delete(app);
            return "success:true";
        } else {
            return "success:false";
        }

    }


}
