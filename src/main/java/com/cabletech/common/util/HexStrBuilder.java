package com.cabletech.common.util;

/**
 * 十六制字符串构造器
 * 
 * @author YuLeyuan
 * 
 */
public class HexStrBuilder {
	/**
	 * 分隔符
	 */
	private String separator;
	/**
	 * 存储String的StringBuffer;
	 */
	private StringBuffer sb;

	/**
	 * 构造函数
	 * 
	 * @param separator
	 *            分隔符
	 */
	public HexStrBuilder(String separator) {
		this.separator = separator;
		sb = new StringBuffer();
	}

	/**
	 * 构造函数
	 */
	public HexStrBuilder() {
		this(null);
	}

	/**
	 * 扩展
	 * 
	 * @param b
	 *            　字节数组
	 */
	public void append(byte[] b) {
		if (separator != null && sb.length() > 0) {
			sb.append(separator);
		}
		sb.append(ByteUtilities.asUppercaseHexString(b, separator));
	}

	/**
	 * 扩展
	 * 
	 * @param b
	 *            　字节
	 */
	public void append(byte b) {
		if (separator != null && sb.length() > 0) {
			sb.append(separator);
		}
		sb.append(ByteUtilities.asUppercaseHexString(b));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return sb.toString();
	}

}
