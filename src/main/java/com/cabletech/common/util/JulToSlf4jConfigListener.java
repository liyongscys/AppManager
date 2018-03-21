package com.cabletech.common.util;

import java.util.logging.Handler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener
 * @author YuLeyuan
 *
 */
public class JulToSlf4jConfigListener implements ServletContextListener {
	private Handler[] originalHandlers;
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	/**
	 * contextInitialized
	 * @param event event
	 */
	public void contextInitialized(ServletContextEvent event) {
		originalHandlers =JulToSlf4jBridgeHelper.getJulRootLogger().getHandlers();
		JulToSlf4jBridgeHelper.install();  
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		JulToSlf4jBridgeHelper.uninstall(originalHandlers);
	}
}
