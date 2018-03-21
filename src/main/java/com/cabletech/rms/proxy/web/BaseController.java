package com.cabletech.rms.proxy.web;

import org.springframework.http.MediaType;

/**
 * Spring MVC控制器的基类
 * @author YuLeyuan
 *
 */
public class BaseController {
	/**
	 * APPLICATION_JSON
	 */
	protected static final String APPLICATION_JSON=MediaType.APPLICATION_JSON_VALUE;
	/**
	 * 字符集
	 */
	protected static final String CHARSET="UTF-8";
	/**
	 * RESP_CONTENT_TYPE
	 */
	protected static final String RESP_CONTENT_TYPE=APPLICATION_JSON+"; charset="+CHARSET;

}
