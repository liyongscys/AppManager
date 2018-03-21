package com.cabletech.common.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrTokenizer;

/**
 * 字符串分隔工具类
 * @author YuLeyuan
 *
 */
public class StringSplitter {

	/**
	 * 使用分隔符，将字符串分隔成List
	 * 
	 * @param s
	 *            要分隔的字符串
	 * @param delim
	 *            分隔符
	 * @return 返回List
	 */
	public static List<String> splitAsList(String s, char delim) {
		return splitAsList(s, delim, false);
	}

	/**
	 * 使用分隔符，将字符串分隔成数组
	 * 
	 * @param s
	 *            要分隔的字符串
	 * @param delim
	 *            分隔符
	 * @return　返回数组
	 */
	public static String[] splitAsArray(String s, char delim) {
		return splitAsArray(s, delim, false);
	}

	/**
	 * 使用分隔符，将字符串分隔成List
	 * 
	 * @param s 要分隔的字符串
	 * @param delim 分隔符
	 * @param emptyAsNull 为空的替换值
	 * @return　返回List
	 */
	@SuppressWarnings("unchecked")
	public static List<String> splitAsList(String s, char delim,
			boolean emptyAsNull) {
		return generatorSplitTokenizer(s, delim, emptyAsNull).getTokenList();
	}

	/**
	 * 使用分隔符，将字符串分隔成数组
	 * 
	 * @param s 待分隔的字符串
	 * @param delim 分隔符
	 * @param emptyAsNull 如果为空的替换值
	 * @return 返回数组
	 */
	public static String[] splitAsArray(String s, char delim,
			boolean emptyAsNull) {
		return generatorSplitTokenizer(s, delim, emptyAsNull).getTokenArray();
	}

	/**
	 * 生成StrTokenizer
	 * @param input 待分隔的字符串
	 * @param delim 分隔符
	 * @param emptyAsNull 如果为空的替换值
	 * @return 返回StrTokenizer
	 */
	private static StrTokenizer generatorSplitTokenizer(String input,
			char delim, boolean emptyAsNull) {
		StrTokenizer tokenizer = new StrTokenizer(input, delim);
		if (emptyAsNull) {
			tokenizer.setEmptyTokenAsNull(true);
		}
		tokenizer.setIgnoreEmptyTokens(false);
		return tokenizer;
	}
	/**
	 * 测试用方法
	 * @param args 参数
	 */
	public static void main(String[] args){
		String[] s=StringUtils.splitPreserveAllTokens("ssss", 's');
		
	}

}
