package com.cabletech.rms.proxy.web;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cabletech.rms.proxy.dao.AppSettingDao;
import com.cabletech.rms.proxy.model.SettingItem;
import com.cabletech.rms.proxy.web.edit.GridData;
import com.cabletech.rms.proxy.web.edit.PropertyItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author YuLeyuan
 *
 */
@Controller
public class SettingsController {
	/** logger */
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/** Dao */
	@Autowired
	private AppSettingDao appSettingDao;
	
	@Autowired
	Properties configProperties;
	
	private String[][] ss=new String[][]{
			{"jdbc.driverClass","OracleJdbc驱动类",""},
			{"jdbc.url","数据库连接","综合代维数据库"},
			{"jdbc.username","数据库用户名","综合代维数据库"},
			{"jdbc.password","数据库密码","综合代维数据库"},
			{"ctbaseinfo.jdbc.url","数据库连接","通用权限"},
			{"ctbaseinfo.jdbc.username","数据库用户名","通用权限"},
			{"ctbaseinfo.jdbc.password","数据库密码","通用权限"},
			{"patrol.jdbc.url","数据库连接","传输线路巡检"},
			{"patrol.jdbc.username","数据库用户名","传输线路巡检"},
			{"patrol.jdbc.password","数据库密码","传输线路巡检"},
			{"gprs.ct1200message.port","CT1200端口",""},
			
			{"app.work.dir","工作路径",""},			
			{"ftp.host","FTP服务器地址","FTP"},
			{"ftp.port","FTP服务器端口","FTP"},
			{"ftp.user.name","FTP用户名","FTP"},
			{"ftp.user.password","FTP密码","FTP"},
			{"ftp.root.path","FTP路径","FTP"},
			
			{"dwjk.url","内部接口URL地址",""},
			{"signin.radius","签到匹配半径",""},

			//AAC40,the day,AAC41,plan,AAC42,denforbidden
			{"patrol.item.save.mode","终端本地保存巡检项有效期",""},
			//0 full or 1 part
			{"patrol.item.submit.mode","终端巡检项提交模式",""}
	};
	
	private static final String SPCODE="spcode";
	
	private static final String PATROL_ITEM_SAVE_MODE="patrol.item.save.mode";
	private static final String PATROL_ITEM_SUBMIT_MODE="patrol.item.submit.mode";
	
	private static final String SIGNIN_RADIUS="signin.radius";
	

	
	private GridData<PropertyItem> generateSettings(){
		GridData<PropertyItem> settings=new GridData<PropertyItem>();
		List<PropertyItem> rows=new ArrayList<PropertyItem>();
		PropertyItem prop;
		String key;
		for(String[] s:ss){
			key=s[0];
			prop=new PropertyItem();
			prop.setField(key);
			prop.setName(s[1]);
			prop.setGroup(s[2]);
			prop.setValue(configProperties.getProperty(key, ""));
			prop.setEditor(null);
			rows.add(prop);
		}
		
		
		SettingItem found=appSettingDao.find(SPCODE);
		prop=new PropertyItem();
		prop.setField(found.getName());
		prop.setName(found.getDesc());
		prop.setGroup("");
		prop.setValue(found.getValue());
		rows.add(prop);
		
		settings.setRows(rows);
		return settings;
	}
	
	/**
	 * 加载配置项
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadSettings.do")
	@ResponseBody
	public final String loadSettings() throws Exception {
		GridData<PropertyItem> settings=generateSettings();
		Gson gson=new Gson();
		
		Type type=new TypeToken<GridData<PropertyItem>>(){}.getType();
		return gson.toJson(settings,type);
	}
	
	/**
	 * 更新配置项
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateSettings.do", method = RequestMethod.POST)
	@ResponseBody
	public final String updateSettings(HttpServletRequest request) throws Exception {
		Enumeration<String> keys=request.getParameterNames();		
		SettingItem item;
		while(keys.hasMoreElements()){
			item=new SettingItem(); 
			item.setName(keys.nextElement());
			item.setValue(request.getParameter(item.getName()));
			appSettingDao.update(item);
		}
		
		return "{'success':true}";
	}
	
	
}
