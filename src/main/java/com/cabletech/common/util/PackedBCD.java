package com.cabletech.common.util;

/**
 * 压缩BCD码生成工具
 * @author YuLeyuan
 *
 */
public class PackedBCD {
	/**
	 * Encodes a string containing just decimal digis 0-9 into a backed binary
	 * coded decimal array. For instance the decimal string "1234" will be
	 * encoded into two bytes 0x12 0x34.
	 * 
	 * @param digitStr
	 *            The input string
	 * @return the BCD encoded binary array
	 */
	public static byte[] encode(String digitStr) {
		
		String decimalStr;
		if ((digitStr.length() % 2) == 0){
			decimalStr=digitStr;
		}else{
			decimalStr="0"+digitStr;
		}
		int half=decimalStr.length()/2;
		byte[] b=new byte[half];
		for(int i=0;i<half;i++){			
			b[i]=(byte) ((((decimalStr.charAt(i*2) - '0') << 4) | (decimalStr.charAt(i*2+1) - '0')));
		}
		
		return b;
	}

	/**
	 * Decodes a BCD encoded array back into a String containing decimal digits.
	 * For instance 0x34 will be decoded as the string "34".
	 * 
	 * @param b
	 *            the input BCD encoded array
	 * @return The decoded, original string
	 *  
	 */
	public static String decode(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; ++i) {
			sb.append((char) (((b[i] & 0xf0) >> 4) + '0'));
			if ((i != b.length) && ((b[i] & 0xf) != 0x0A)) // if not pad char
				sb.append((char) ((b[i] & 0x0f) + '0'));
		}
		return sb.toString();
	}
	

}
