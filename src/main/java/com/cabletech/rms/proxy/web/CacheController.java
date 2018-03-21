package com.cabletech.rms.proxy.web;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author YuLeyuan
 *
 */
@Controller
public class CacheController {
	/** logger */
	final Logger logger = LoggerFactory.getLogger(getClass());
	/*
	private static final String TERMINAL_CACHE="terminalCache";
	private static final String USER_CACHE="userCache";
	private static final String SDEINFO_CACHE="sdeInfoCache";
	private static final String RES_GPRSRECORD_CACHE="resGprsRecordCache";
	
	private static final String WPLAN_PATROL_TEMPLATE_CACHE="wplanPatrolTemplateCache";
	
	private static final String WIRELESS_PATROLPLAN_RESOURCE_CACHE=
			"wirelessPatrolPlanResourceCache";	
	*/
	
	@Autowired
	private CacheManager cacheManager;

	/**
	 * @param cacheName cacheName
	 * @param key key
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cache/clear.do", method = RequestMethod.POST)
	@ResponseBody
	public final String clear(@RequestParam(required = true) String cacheName,
			@RequestParam(required = false) String key) throws Exception {
		Cache cache=cacheManager.getCache(cacheName);		
		if(StringUtils.isNotBlank(key)){
			cache.evict(key.trim());
			logger.info("移除缓存{}中的key={} 的值", cacheName, key);
		}else{
			cache.clear();
			logger.info("清空缓存{}", cacheName);
		}
        return "success:'true'";
	}
}
