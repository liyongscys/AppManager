package com.cabletech.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
/**
 * 文件处理工具类
 * @author zhangyh
 *
 */
public class FileUtils {
	/**
 	 * 删除文件
 	 * @param filepath
 	 */
	public static void delFile(String filepath){
        File file=new File(filepath);
        if(file.exists()&&file.isFile())
            file.delete();
    }
	/**
	 * 文件打成zip包
	 * @param sourceDir 文件原路径
	 * @param zipFilePath zip文件路径
	 * @return
	 * @throws IOException
	 */
	public static File doZip(String sourceDir, String zipFilePath) throws IOException {
		File file = new File(sourceDir);
		File zipFile = new File(zipFilePath);
		ZipOutputStream zos = null;
		try {
			// 创建写出流操作
			OutputStream os = new FileOutputStream(zipFile);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			zos = new ZipOutputStream(bos);
			String basePath = null;
			// 获取目录
			if (file.isDirectory()) {
				basePath = file.getPath();
			} else {
				basePath = file.getParent();
			}
			zos.setEncoding("UTF-8");
			zipFile(file, basePath, zos);
		} finally {
			if (zos != null) {
				zos.closeEntry();
				zos.close();
			}
		}
		return zipFile;
	}

	/**
	 * @param source
	 *            源文件
	 * @param basePath 基础路径
	 * @param zos zip输出流
	 */
	private static void zipFile(File source, String basePath, ZipOutputStream zos)
			throws IOException {
		File[] files = null;
		if (source.isDirectory()) {
			files = source.listFiles();
		} else {
			files = new File[1];
			files[0] = source;
		}
		InputStream is = null;
		String pathName;
		byte[] buf = new byte[1024];
		int length = 0;
		try {
			for (File file : files) {
				if (file.isDirectory()) {
					pathName = file.getPath().substring(basePath.length() + 1)
							+ "/";
					zos.putNextEntry(new ZipEntry(pathName));
					zipFile(file, basePath, zos);
				} else {
					pathName = file.getPath().substring(basePath.length() + 1);
					is = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(is);
					zos.putNextEntry(new ZipEntry(pathName));
					while ((length = bis.read(buf)) > 0) {
						zos.write(buf, 0, length);
					}
				}
			}
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
}
