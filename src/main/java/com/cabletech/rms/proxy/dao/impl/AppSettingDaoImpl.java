package com.cabletech.rms.proxy.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cabletech.rms.proxy.model.Option;
import com.cabletech.rms.proxy.dao.AppSettingDao;
import com.cabletech.rms.proxy.model.SettingItem;
import org.springframework.stereotype.Service;

/**
 * @author YuLeyuan
 *
 */
@Service
public class AppSettingDaoImpl extends CtBaseDao implements AppSettingDao {
	
	private static final String PRE = AppSettingDao.class.getName() + ".";
	/** sql名 */
	private static final String GET = PRE + "get";
	/** sql名 */
	private static final String DELETE = PRE + "delete";
	/** sql名 */
	private static final String INSERT = PRE + "insert";
	/** sql名 */
	private static final String UPDATE = PRE + "update";
	/** sql名 */
	private static final String LIST = PRE + "list";
	

	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AppSettingDao#insert(com.cabletech.rms.proxy.model.SettingItem)
	 */
	@Override
	public void insert(SettingItem setting) {
		getSqlSession().insert(INSERT, setting);
		
	}

	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AppSettingDao#update(com.cabletech.rms.proxy.model.SettingItem)
	 */
	@Override
	public void update(SettingItem setting) {
		getSqlSession().update(UPDATE, setting);
	}

	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AppSettingDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String name) {
		getSqlSession().delete(DELETE, name);
		
	}

	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AppSettingDao#find(java.lang.String)
	 */
	@Override
	public SettingItem find(String name) {
		return super.find(GET, name);
	}

	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AppSettingDao#list()
	 */
	@Override
	public List<SettingItem> list() {
		return getSqlSession().selectList(LIST);
	}

	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AppSettingDao#listAsMap()
	 */
	@Override
	public Map<String, SettingItem> listAsMap() {
		List<SettingItem> list=list();
		Map<String, SettingItem> settings=new HashMap<String, SettingItem>();
		for(SettingItem item:list){
			settings.put(item.getName(), item);
		}
		return settings;
	}

	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AppSettingDao#getValue(java.lang.String)
	 */
	@Override
	public String getValue(String name) {
		SettingItem item=find(name);
		if(item!=null){
			return item.getValue();
		}else{
			return null;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.cabletech.rms.proxy.dao.AppSettingDao#getValue(java.lang.String)
	 */
	@Override
	public List<Option> getOptions(String name) {
		List<Option> list=new ArrayList<Option>();
		SettingItem item=find(name);
		if(item==null){
			return list;
		}
		String codestr=item.getOptCode();
		String descstr=item.getOptDesc();
		if(StringUtils.isBlank(codestr)
				||StringUtils.isBlank(descstr)){
			return list;
		}
		String[] codes=codestr.split("|");
		String[] descs=codestr.split("|");
		
		int length=codes.length;
		if(length>0&&length>=descs.length){
			for(int i=0;i<length;i++){
				list.add(new Option(codes[i],descs[i]));
			}
		}
		return list;
		
	}

}
