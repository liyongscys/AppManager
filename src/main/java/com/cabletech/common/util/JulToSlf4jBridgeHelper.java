package com.cabletech.common.util;

import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.LogManager;

import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * 将java.util.loggin的日志转换到slf4j中的工具类
 * 
 * @author YuLeyuan
 * 
 */
public class JulToSlf4jBridgeHelper {
	
	/**
	 * @return
	 */
	public static Logger getJulRootLogger(){
		return LogManager.getLogManager().getLogger("");
	}
	

	/**
	 * 设置Bridge handler
	 * 
	 * @param cleanOldJulHandlers
	 *            是否消除jul的输出
	 */
	public static void install(boolean cleanOldJulHandlers) {
		if (cleanOldJulHandlers) {
			Logger julLogger = getJulRootLogger();
			for (Handler handler : julLogger.getHandlers()) {
				julLogger.removeHandler(handler);
			}
		}
		SLF4JBridgeHandler.install();
	}

	/**
	 * 设置Bridge handler
	 */
	public static void install() {
		JulToSlf4jBridgeHelper.install(true);
	}
	
	/**
	 * 移除Bridge handler
	 */
	public static void uninstall() {
		SLF4JBridgeHandler.uninstall();
	}
	
	/**
	 * 移除Bridge handler
	 * @param originalHandlers originalHandlers
	 */
	public static void uninstall(Handler[] originalHandlers) {
		JulToSlf4jBridgeHelper.uninstall();
		if(originalHandlers==null){
			return;
		}
		Logger julLogger = getJulRootLogger();
		for (Handler handler : originalHandlers) {
			julLogger.addHandler(handler);
		}
	}
}
