package com.cabletech.common.util;

import java.lang.reflect.Type;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * @author YuLeyuan
 *
 */
public class GsonUtils {
	public static final Gson GSON_BUILDER = createGson();
	/**
	 * 创建Gson
	 * @return
	 */
	public static Gson createGson(){
		GsonBuilder gsonBuilder = new GsonBuilder(); 
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
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
	/**
	 * 通过资源模型获取资源的json串
	 * @param propertys 资源模型
	 * @return
	 */
	public static String getPropertysJsonStr(String propertys){
			StringBuffer jsonStr=new StringBuffer();
			jsonStr.append("'{'");
			for (String propertyStr : propertys.split(",")) {
				String[] property = propertyStr.split(";");
				if(StringUtils.upperCase(property[0]).equals("SHAPE")||StringUtils.upperCase(property[0]).equals("OBJECTID")){
					continue;
				}
					jsonStr.append("||'\""+StringUtils.upperCase(property[0])+"\":'");
					String propertyType = property[4];
					if(("1".equals(propertyType)&&"1".equals(property[5]))||"11".equals(propertyType)){
						jsonStr.append("||nvl("+StringUtils.upperCase(property[0])+",0)||','");
					}else if("12".equals(propertyType)){
						jsonStr.append("||'\"'||to_char("+StringUtils.upperCase(property[0])+",'YYYY-MM-dd hh:mi:ss')||'\"'||','");
					}else{
						jsonStr.append("||'\"'||"+StringUtils.upperCase(property[0])+"||'\"'||','");
					}
					if ("2".equals(propertyType)) {
						jsonStr.append("||'\""+StringUtils.upperCase(property[0])+"_NAME\":'");
						jsonStr.append("||'\"'||fn_getnamebycode("+StringUtils.upperCase(property[0])+",'')||'\"'||','");
					} else if ("3".equals(propertyType) || "4".equals(propertyType)
							|| "5".equals(propertyType) || "6".equals(propertyType)) {
						jsonStr.append("||'\""+StringUtils.upperCase(property[0])+"_NAME\":'");
						jsonStr.append("||'\"'||fn_getresname("+StringUtils.upperCase(property[0])+")||'\"'||','");
					} else if ("7".equals(propertyType)) {
						jsonStr.append("||'\""+StringUtils.upperCase(property[0])+"_NAME\":'");
						jsonStr.append("||'\"'||fn_getregionname("+StringUtils.upperCase(property[0])+")||'\"'||','");
					} else if ("8".equals(propertyType)) {
						jsonStr.append("||'\""+StringUtils.upperCase(property[0])+"_NAME\":'");
						jsonStr.append("||'\"'||fn_getprojectname("+StringUtils.upperCase(property[0])+")||'\"'||','");
					} else if ("9".equals(propertyType)) {
						jsonStr.append("||'\""+StringUtils.upperCase(property[0])+"_NAME\":'");
						jsonStr.append("||'\"'||fn_getcjdwname("+StringUtils.upperCase(property[0])+")||'\"'||','");
					} else if ("10".equals(propertyType)) {
						jsonStr.append("||'\""+StringUtils.upperCase(property[0])+"_NAME\":'");
						jsonStr.append("||'\"'||fn_getdaiwei("+StringUtils.upperCase(property[0])+")||'\"'||','");
					}
			}
			return jsonStr.toString().subSequence(0, jsonStr.toString().length()-5)+"||'}'";
		}

	/**
	 * 获取all_index表的json串
	 * 
	 * @return
	 */
	public static String getAllIndexGsonStr() {
		String gsonStr = "'{\"REGION_ID\":\"'||region_id||'\",'||(case when lat is not null and lon is not null then '\"LAT\":'||lat||',\"LON\":'||lon||',' else '' end)||'\"SPATIAL_TYPE\":1,\"RES_NAME\":\"'||res_name||'\",\"CREATEDATE\":\"'||to_char(createdate,'yyyy-MM-dd hh:mi:ss')||'\",\"RES_TYPE\":\"'||res_type||'\",\"ADDRESS\":\"'||address||'\",\"RES_THIRDPARTY_ID\":\"'||RES_THIRDPARTY_ID||'\",\"RES_ID\":\"' ||res_id||'\",\"KEYID\":\"' ||keyid||'\",\"PARENT_ID\":\"'||parent_id||'\"}' ";
		return gsonStr;
	}
}
