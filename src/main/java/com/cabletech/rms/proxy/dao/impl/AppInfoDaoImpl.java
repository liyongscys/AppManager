package com.cabletech.rms.proxy.dao.impl;

import com.cabletech.rms.proxy.dao.AppInfoDao;
import com.cabletech.rms.proxy.model.AppInfo;
import com.cabletech.rms.proxy.model.PagedResult;
import com.cabletech.rms.proxy.model.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YuLeyuan
 */
@Service
public class AppInfoDaoImpl extends CtBaseDao implements AppInfoDao {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AppInfoDaoImpl.class);

    private static final String PRE = AppInfoDao.class.getName() + ".";
    /**
     * sql名
     */
    private static final String GET = PRE + "get";
    /**
     * sql名
     */
    private static final String GET_LAST_APP = PRE + "getLastApp";
    /**
     * sql名
     */
    private static final String DELETE = PRE + "delete";
    /**
     * sql名
     */
    private static final String INSERT = PRE + "insert";
    /**
     * sql名
     */
    private static final String COUNT = PRE + "count";
    /**
     * sql名
     */
    private static final String LIST = PRE + "list";

    @Override
    @Transactional
    public void insert(AppInfo app) {

        getSqlSession().insert(INSERT, app);
    }


    @Override
    public AppInfo get(int id) {
        List<AppInfo> list = getSqlSession().selectList(GET, id);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public AppInfo getLastapp(AppInfo param) {
        List<AppInfo> list = getSqlSession().selectList(GET_LAST_APP, param);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }


    @Override
    public PagedResult<AppInfo> pagingList(AppInfo app, Paging paging) {
        return paging(COUNT, LIST, app, paging);
    }


    @Override
    public List<AppInfo> list(AppInfo app) {
        return getSqlSession().selectList(LIST, app);
    }


    @Override
    public void delete(AppInfo app) {
        getSqlSession().delete(DELETE, app);

    }


}
