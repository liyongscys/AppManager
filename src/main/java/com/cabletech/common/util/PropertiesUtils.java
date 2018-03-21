package com.cabletech.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PropertiesUtils
 * @author YuLeyuan
 *
 */
public class PropertiesUtils {
	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	
	/** 
     * 读取properties的全部信息
     * @param filePath 文件路径，即文件所在包的路径，例如：java/util/config.properties 
     * @return Properties
     */  
    public static Properties getProperties(String filePath) {
        Properties properties=null; 
        File file = new File(filePath);
        try {  
        	InputStream in = FileUtils.openInputStream(file);
            properties=new Properties();
            properties.load(in); 
            IOUtils.closeQuietly(in);  
            return properties;  
        } catch (Exception e) {  
        	logger.error("Load {} error",filePath);  
            return null;  
        }  
    } 
    
    /** 
     * 保存，并加入注释  
     * @param filePath 文件路径，即文件所在包的路径，例如：java/util/config.properties 
     * @param properties properties
     * @param comments 注释  
     */  
    public static void writeProperties(String filePath, Properties properties,String comments) { 
        try {
            OutputStream output = new FileOutputStream(filePath);
            properties.store(output, comments);  
            IOUtils.closeQuietly(output);  
        } catch (IOException e) {  
        	logger.error("Visit {} for updating  value error",filePath);  
        }  
    }
}
