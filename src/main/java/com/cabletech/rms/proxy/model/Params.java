package com.cabletech.rms.proxy.model;

import java.util.HashMap;

/**
 * @author YuLeyuan
 * mybatis的查询参数封装类
 */
public class Params extends HashMap<String,Object>{
    /** serialVersionUID */
	private static final long serialVersionUID = -1915694027959112248L;
	
	public static Params create(String[] keys,Object[] values){
		if(keys==null||keys.length<1){
			return null;
		}
		Params params=new Params();
		for(int i=0;i<keys.length;i++){
			params.putIfNotNull(keys[i], values[i]);
		}
		return params;		
	}
	
	public static Params create(String key,Object value){
		Params params=new Params();
		params.putIfNotNull(key, value);
		return params;
	}

	public void putIfNotNull(String key,Object value){
		if(value!=null){
			put(key, value);
		}
	}
}
