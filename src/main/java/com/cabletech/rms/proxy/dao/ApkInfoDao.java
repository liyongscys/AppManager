package com.cabletech.rms.proxy.dao;

import java.util.List;

import com.cabletech.rms.proxy.model.ApkInfo;
import com.cabletech.rms.proxy.model.PagedResult;
import com.cabletech.rms.proxy.model.Paging;

/**
 * @author YuLeyuan
 *
 */
public interface ApkInfoDao {
	/**
	 * checkTable
	 */
	//void checkTable();
	/**
	 * insert
	 * @param apk apk
	 */
    void insert(ApkInfo apk);
    /**
     * delete
     * @param apk apk
     */
    void delete(ApkInfo apk);
    /**
     * get
     * @param id id
     * @return
     */
    ApkInfo get(int id);
    /**
     * getLastApk
     * @param param 过滤条件
     * @return
     */
    ApkInfo getLastApk(ApkInfo param);
    /**
     * list
     * @param apk apk
     * @return
     */
    List<ApkInfo> list(ApkInfo apk);
    /**
     * pagingList
     * @param apk apk
     * @param paging paging
     * @return
     */
    PagedResult<ApkInfo> pagingList(ApkInfo apk,Paging paging);
}
