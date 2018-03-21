package com.cabletech.common.util;
/**
 * LittleEndian工具类.
 * @author YuLeyuan
 *
 */
public class LittleEndianUtils {

	/**
	 * @param value 整形值
	 * @return　字节数组
	 */
	public static final byte[] asBytes(int value) {
		byte[] w = new byte[4];
		w[0] = (byte) value;
		w[1] = (byte) (value >> 8);
		w[2] = (byte) (value >> 16);
		w[3] = (byte) (value >> 24);
		return w;
	}
	
    
	/**
	 * @param w
	 *            　4字节数组
	 * @return 整形值　
	 */
	public static final int asInt(byte[] w) {
		return 
				  (w[3] & 0xff) << 24 
				| (w[2] & 0xff) << 16 
				| (w[1] & 0xff) << 8
				| (w[0] & 0xff);
		
	}
	
}
