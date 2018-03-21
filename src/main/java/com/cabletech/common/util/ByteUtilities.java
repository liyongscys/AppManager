package com.cabletech.common.util;

import org.apache.commons.lang.text.StrBuilder;
/**
 * ByteUtilities
 * @author yuleyuan
 *
 */
public class ByteUtilities {
	/**
	 * 十六进制小写字符
	 */
	private static char[] LOWCASE_HEX_CHARS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	/**
	 * 十六进制大写字符
	 */
	private static char[] UPPERCASE_HEX_CHARS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * Convert a byte array to a hex string.
	 * 
	 * @param bytes
	 *            字节数组
	 * @param hexChars
	 *            十六进制字符
	 * @param separator
	 *            分隔符
	 * @return 返回转换后的十六进制字符串
	 */
	private static String toHexString(byte[] bytes, char[] hexChars,
			String separator) {
		StrBuilder sb;
		if (separator != null) {
			sb = new StrBuilder(bytes.length * (2 + separator.length()));
		} else {
			sb = new StrBuilder(bytes.length * 2);
		}

		for (int i = 0; i < bytes.length; i++) {

			// look up high nibble char
			sb.append(hexChars[(bytes[i] & 0xf0) >>> 4]);

			// look up low nibble char
			sb.append(hexChars[bytes[i] & 0x0f]);
			if (separator != null && i < bytes.length - 1) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}

	/**
	 * @param b
	 *            字节
	 * @param hexChars
	 *            十六进制字符
	 * @return 返回转换后的十六进制字符串
	 */
	private static String toHexString(byte b, char[] hexChars) {
		StringBuilder sb = new StringBuilder(2);
		// look up high nibble char
		sb.append(hexChars[(b & 0xf0) >>> 4]);
		// look up low nibble char
		sb.append(hexChars[b & 0x0f]);
		return sb.toString();
	}

	/**
	 * @param b
	 *            字节
	 * @param separator
	 *            分隔符
	 * @return 返回转换后的十六进制字符串
	 */
	public static String asLowcaseHexString(byte[] b, String separator) {
		return toHexString(b, LOWCASE_HEX_CHARS, separator);
	}

	/**
	 * @param b
	 *            字节
	 * @param separator
	 *            分隔符
	 * @return 返回转换后的十六进制字符串
	 */
	public static String asUppercaseHexString(byte[] b, String separator) {
		return toHexString(b, UPPERCASE_HEX_CHARS, separator);
	}

	/**
	 * @param b
	 *            字节
	 * @return 返回转换后的十六进制字符串
	 */
	public static String asLowcaseHexString(byte b) {
		return toHexString(b, LOWCASE_HEX_CHARS);
	}

	/**
	 * @param b
	 *            字节
	 * @return 返回转换后的十六进制字符串
	 */
	public static String asUppercaseHexString(byte b) {
		return toHexString(b, UPPERCASE_HEX_CHARS);
	}

}
