package com.cabletech.rms.proxy.dao;

import com.cabletech.rms.proxy.model.AppInfo;
import com.cabletech.rms.proxy.model.PagedResult;
import com.cabletech.rms.proxy.model.Paging;

import java.util.List;

/**
 * @author YuLeyuan
 *
 */
public interface AppInfoDao {
	/**
	 * checkTable
	 */
	//void checkTable();
	/**
	 * insert
	 * @param app app
	 */
    void insert(AppInfo app);
    /**
     * delete
     * @param app app
     */
    void delete(AppInfo app);
    /**
     * get
     * @param id id
     * @return
     */
    AppInfo get(int id);
    /**
     * getLastapp
     * @param param 过滤条件
     * @return
     */
    AppInfo getLastapp(AppInfo param);
    /**
     * list
     * @param app app
     * @return
     */
    List<AppInfo> list(AppInfo app);
    /**
     * pagingList
     * @param app app
     * @param paging paging
     * @return
     */
    PagedResult<AppInfo> pagingList(AppInfo app, Paging paging);
}
