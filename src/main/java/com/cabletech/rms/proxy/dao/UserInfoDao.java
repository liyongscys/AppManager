package com.cabletech.rms.proxy.dao;

import java.util.List;

import com.cabletech.rms.proxy.model.UserInfo;

/**
 * @author YuLeyuan
 *
 */
public interface UserInfoDao {
	
    /**
     * 根据用户登录名查找用户信息
     * @param loginName loginName
     * * @param appName appName
     * @return
     */
    UserInfo getByLoginName(String loginName,String appName);
    
    /**
     * 根据用户ID查找用户信息
     * @param id 用户id
     * @return
     */
    UserInfo getById(String id);
    
    /**
     * 根据工号查找用户信息
     * @param employeeNum employeeNum
     * @return
     */
    UserInfo getEmployeeNum(String employeeNum);
    /**
     * 通过部门id获取部门下所有用户的信息
     * @param deptId
     * @return
     */
    List<UserInfo> getByDeptId(String deptId);
}
