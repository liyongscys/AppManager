package com.cabletech.rms.proxy.web;


import com.cabletech.common.util.GetPostUtil;
import com.cabletech.common.util.GsonUtils;
import com.cabletech.rms.proxy.model.ApnNotification;
import com.cabletech.rms.proxy.model.ApnUser;
import com.cabletech.rms.proxy.model.PagedResult;
import com.cabletech.rms.proxy.model.Paging;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YuLeyuan
 *
 */
@Controller
public class ApnController {
	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(ApnController.class);
	/**
	 * TempResp
	 * @author yuleyuan
	 *
	 */
	class TempResp{
		public List<String> data;
	}
	
	/**
	 * @param response response
	 * @param paging paging
	 * @return
	 */
	@RequestMapping(value = "/apn/listuser.do", method = RequestMethod.GET)
	@ResponseBody
	public final String listUser(HttpServletResponse response,Paging paging) {
		String url = "http://127.0.0.1:7070/";
		
		PagedResult<ApnUser> result=new PagedResult<ApnUser>(paging);
		result.setTotal(0);
		List<ApnUser> rows=new ArrayList<ApnUser>();
		

		// 1.获取从指定时间开始失效的设备号
		// from: 开始时间，格式如：2012-04-12 15:04:55
		// online: 0表示获取失效用户; 1表示获取在线用户
		// 返回结果为字符串，如：{"data":["9ab043eb976c414cb371743fbe9a1900", "ab9e9846279c4269bf5a0c75849d77fb", "acdc92733a444a53a4c9131c16f18be2"]}
		String resp = GetPostUtil.sendGet(
				url + "user_api.do",
				"from=2012-04-13%2016:33:34&online=0");
		logger.info("GET:" + response);

		if(response!=null){
			TempResp tempResp=GsonUtils.fromJson(resp, TempResp.class);
			ApnUser user;
	        if(tempResp!=null&&tempResp.data!=null){
	            result.setTotal(tempResp.data.size());
	            if(result.getTotal()>0){
	            	int fromIndex=result.getPageNumber()*(result.getPageSize()-1);
	            	int toIndex=result.getPageNumber()*result.getPageSize();
	            	if(toIndex>=result.getTotal()){
	            		toIndex=result.getTotal();
	            	}
	            	List<String> sub=tempResp.data.subList(fromIndex, toIndex);
	            	for(String username:sub){
		        		user=new ApnUser();
		        		user.setUsername(username);
		        		rows.add(user);
		        	}
	            }
	        	
	        }
		}
		result.setRows(rows);
		Type typeOfSrc = new TypeToken<PagedResult<ApnUser>>() {}.getType();
		String json= GsonUtils.toJson(result, typeOfSrc);
		logger.info(json);
		return json;
	}
	
	/**
	 * @param response response
	 * @param notification notification
	 * @return
	 */
	@RequestMapping(value = "/apn/notification.do", method = RequestMethod.POST)
	@ResponseBody
	public final String notification(HttpServletResponse response,ApnNotification notification) {
		String url = "http://127.0.0.1:7070/";
		
		// 3.用POST方式发送
		/*--拼接POST字符串--*/
		StringBuffer sb = new StringBuffer();
		sb.append("action=send");
		// 单条发送这里要设成N,若设成Y则广播,全部收到,后面参数无效
		sb.append("&broadcast=").append(notification.getBroadcast());
		sb.append("&username=").append(notification.getUsername()); 
		
		sb.append("&title=").append(notification.getTitle());
		sb.append("&message=").append(notification.getMessage());
		sb.append("&uri=").append(notification.getUri());
		/*--End--*/
		 String resp = GetPostUtil.send("POST", url + "notification_api.do", sb.toString());
		 logger.info("response:" + resp);
		 return "success:true";
	}
		
}
