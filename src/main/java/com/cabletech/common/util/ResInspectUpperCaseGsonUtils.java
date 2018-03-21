package com.cabletech.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * @author YuLeyuan
 *
 */
public class ResInspectUpperCaseGsonUtils {
	public static final Gson GSON_BUILDER = createGson();
	/**
	 * 创建Gson
	 * @return
	 */
	public static Gson createGson(){
		GsonBuilder gsonBuilder = new GsonBuilder(); 
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gsonBuilder.serializeNulls();
		NamingStrategy namingStrategy=new NamingStrategy();
		gsonBuilder.setFieldNamingStrategy(namingStrategy);
		return gsonBuilder.create();
	}
	
	/**
	 * 转化为Json字符串
	 * @param src src
	 * @return
	 */
	public static String toJson(Object src){
		 return GSON_BUILDER.toJson(src);
	}
	
	/**
	 * @param src src
	 * @param typeOfSrc typeOfSrc
	 * @return
	 */
	public static String toJson(Object src, Type typeOfSrc){
		return GSON_BUILDER.toJson(src,typeOfSrc);
	}
	
	/**
	 * @param json json
	 * @param classOfT classOfT
	 * @param <T> t
	 * @return
	 * @throws JsonSyntaxException
	 */
	public static <T> T fromJson(String json, Class<T> classOfT)
			throws JsonSyntaxException {
		return GSON_BUILDER.fromJson(json, classOfT);
	}
	
	/**
	 * @param json json
	 * @param typeOfT typeOfT
	 * @param <T> t
	 * @return
	 * @throws JsonSyntaxException
	 */
	public static <T> T fromJson(String json, Type typeOfT)
			throws JsonSyntaxException {
		return GSON_BUILDER.fromJson(json, typeOfT);
	}
	static class NamingStrategy implements FieldNamingStrategy{

		@Override
		public String translateName(Field arg0) {
			return arg0.getName().toString().toUpperCase();
		}
	}
}
