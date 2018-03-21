package com.cabletech.rms.proxy;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cabletech.common.util.SpringContextHolder;
import com.cabletech.rms.proxy.dao.ApkInfoDao;


/**
 * 应用的全局变量类
 * @author YuLeyuan
 *
 */
public class RmsApplication extends HttpServlet {
	/** serialVersionUID */
	private static final long serialVersionUID = 3424851164885006968L;
	/** logger */
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/** Web的Root路径 */
    private static String webRootPath;
    public static String baseSpatialTableName="RES_ZDXX";

	/**
	 * @return the webRootPath
	 */
	public static String getWebRootPath() {
		return webRootPath;
	}

	/**
	 * @param webRootPath the webRootPath to set
	 */
	public static void setWebRootPath(String webRootPath) {
		RmsApplication.webRootPath = webRootPath;
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = getServletContext();
	    
		String webRootPath=servletContext.getRealPath("/"); 
		logger.info("app web root path:{}",webRootPath);
		setWebRootPath(webRootPath);
		Properties props=SpringContextHolder.getBean("configProperties");
		String tbl=props.getProperty("base.spatial.table.name", "RES_ZDXX");
		baseSpatialTableName=tbl.toUpperCase();
		
//		runCheckApkTable();

	}
	/**
	 * 用线程来跑防止太耗时
	 */
	private void runCheckApkTable(){
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					logger.info("Rms Check table and sequence...........");
					ApkInfoDao apkInfoDao=SpringContextHolder.getBean(ApkInfoDao.class);
					//apkInfoDao.checkTable();
					
					logger.info("Rms Started............................");
				} catch (Exception e) {
					logger.error("apk表创建出错", e);
				}
				
			}
		});
		
		thread.start(); 
	}
	
}
