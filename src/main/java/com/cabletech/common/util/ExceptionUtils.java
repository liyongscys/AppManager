package com.cabletech.common.util;

/**
 * 将CheckedException转换为UnCheckedException.
 * 
 * @author YuLeyuan
 * 
 */
public class ExceptionUtils {

	/**
	 * 将CheckedException转换为UnCheckedException.
	 * 
	 * @param e
	 *            　CheckedException。
	 * @return　UnCheckedException.
	 */
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException(e.getMessage(), e);
	}
}
