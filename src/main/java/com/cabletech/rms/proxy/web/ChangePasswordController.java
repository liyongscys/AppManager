package com.cabletech.rms.proxy.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cabletech.rms.proxy.dao.AdminUserDao;
import com.cabletech.rms.proxy.model.AdminUser;
import com.cabletech.rms.proxy.security.PasswordEncrypt;
import com.google.gson.Gson;

/**
 * 修改密码
 * @author YuLeyuan
 *
 */
@Controller
public class ChangePasswordController {	

	@Autowired
	private AdminUserDao adminUserDao;
    
	/**
	 * 确认旧的密码是否正确
	 * @param oldPlainPassword 旧密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkPassword.do", method = RequestMethod.POST)
	@ResponseBody
	public final String check(String oldPlainPassword) throws Exception {
		String currentUsername=getCurrentUserName();
		if(checkPassword(currentUsername,oldPlainPassword)){
			return "true";
		}else{
			return "false";
		}
	}
	


	/**
	 * @param oldPlainPassword 旧密码
	 * @param newPlainPassword 新密码
	 * @param newPlanPasswordConfirm 确认新密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changePassword.do", method = RequestMethod.POST)
	@ResponseBody
	public final String change(String oldPlainPassword,
			String newPlainPassword, String newPlanPasswordConfirm)
			throws Exception {
		//2. 获取当前Subject：
	
		Gson gson = new Gson();
		JsonResponse resp = new JsonResponse();
		resp.setSuccess(false);
		List<String> msgList = new ArrayList<String>();
		if (StringUtils.isBlank(oldPlainPassword)) {
			msgList.add("旧密码不能为空");
		}
        if (StringUtils.isBlank(newPlainPassword)) {
			msgList.add("新密码不能为空");
		}
        if (!newPlainPassword.equals(newPlanPasswordConfirm)) {
			msgList.add("两次输入的密码不一致");
		}
        String currentUsername=getCurrentUserName();
		if (!checkPassword(currentUsername,oldPlainPassword)) {
			msgList.add("旧密码不正确");
		}
		
		if(msgList.size()>0){
			resp.setMessage(msgList.get(0));
		}else{
			savePassword(currentUsername,newPlainPassword);
			resp.setSuccess(true);
			resp.setMessage("密码修改成功");
		}
		return gson.toJson(resp);
	}
	/**
	 * 获取当前用户的名称
	 * @return
	 */
	private String getCurrentUserName(){
		Subject currentUser = SecurityUtils.getSubject();		
		String username=(String)currentUser.getPrincipal();
		return username;
	}
	/**
	 * 
	 * @param plainPassword
	 * @return
	 */
	private boolean checkPassword(String username,String plainPassword){
		String inputPassword=PasswordEncrypt.encrypt(username,plainPassword);
		
		AdminUser found=adminUserDao.findByName(username);
		String password=found.getPassword();
		return password.equals(inputPassword);
	}
	
	/**
	 * 保存密码
	 * @param newPlainPassword
	 */
	@Transactional("h2TransactionManager")
	private void savePassword(String username,String newPlainPassword){
		String password=PasswordEncrypt.encrypt(username,newPlainPassword);
		AdminUser user=new AdminUser();
		user.setName(username);
		user.setPassword(password);		
		adminUserDao.update(user);
	}
}
