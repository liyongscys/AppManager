package com.cabletech.rms.proxy.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cabletech.rms.proxy.model.ApkInfo;
import com.cabletech.rms.proxy.model.PagedResult;
import com.cabletech.rms.proxy.model.Paging;
import com.cabletech.rms.proxy.dao.ApkInfoDao;
import org.springframework.stereotype.Service;

/**
 * @author YuLeyuan
 *
 */
@Service
public class ApkInfoDaoImpl extends CtBaseDao implements ApkInfoDao {
	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(ApkInfoDaoImpl.class);
	
	private static final String PRE = ApkInfoDao.class.getName() + ".";
	/** sql名 */
	private static final String GET = PRE + "get";
	/** sql名 */
	private static final String GET_LAST_APK = PRE + "getLastApk";
	/** sql名 */
	private static final String DELETE = PRE + "delete";
	/** sql名 */
	private static final String INSERT = PRE + "insert";
	/** sql名 */
	private static final String COUNT = PRE + "count";
	/** sql名 */
	private static final String LIST = PRE + "list";

	@Override
	public void insert(ApkInfo apk) {
		
		getSqlSession().insert(INSERT,apk);		
	}



	@Override
	public ApkInfo get(int id) {
		List<ApkInfo> list= getSqlSession().selectList(GET,id);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public ApkInfo getLastApk(ApkInfo param) {
		List<ApkInfo> list= getSqlSession().selectList(GET_LAST_APK,param);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}


	@Override
	public PagedResult<ApkInfo> pagingList(ApkInfo apk,Paging paging) {
		return paging(COUNT, LIST, apk, paging);
	}


	@Override
	public List<ApkInfo> list(ApkInfo apk) {
		return getSqlSession().selectList(LIST,apk);
	}


	@Override
	public void delete(ApkInfo apk) {
		getSqlSession().delete(DELETE,apk);		
		
	}


    
}
