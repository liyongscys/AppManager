package com.cabletech.common.util;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

/**
 * @author YuLeyuan
 * 用于给mybatis查询使用的参数
 */
public class ParamMap extends HashMap<String,Object> {	
	/** serialVersionUID */
	private static final long serialVersionUID = 7398351104797617251L;
    
	/**
	 * @param key 键
	 * @param value 值
	 */
	public void putIgnoreBlank(String key,Object value){
		if(key==null||value==null){
			return;
		}
		if(value instanceof String){
    		String s=(String)value;
    		if(StringUtils.isNotBlank(s)){
    			put(key, value);
    		}
    	}else{
    		put(key, value);
    	}
	}

}
