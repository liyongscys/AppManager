package com.cabletech.rms.proxy.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Set;

/**
 * Created by liyong on 2018/3/19.
 */
public class UserInfo {
    /**
     * 数据库主键对应view_userinfo的sid字段
     */
    private String id;
    /**
     * 数据库主键对应view_userinfo的id字段，
     * 主要用于查询BASE_USERROLE表，该表的userid字段与rid关联
     */
    private String rid;
    /**
     * 登录名
     */
    private String name;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 显示名
     */
    private String displayName;
    /**
     * 所属区域ID
     */
    private String regionId;
    /**
     * 所属区域名
     */
    private String regionName;
    /**
     * 所属区域级别 1:全国;2:省级;3:地市级
     */
    private int regionLevel;
    /**
     * 巡检组ID
     */
    private String groupId;
    /**
     * 巡检组名
     */
    private String groupName;
    /**
     * 部门ID
     */
    private String departmentId;
    /**
     * 部门名
     */
    private String departmentName;
    /**
     * 工号
     */
    private String employeeNum;
    /**
     * 组织ID
     */
    private String orgId;

    /**
     * 是移动用户还是代维用户:
     * "1"是移动用户，"2"是代维用户
     * 对于阜阳的工程项目
     * "1"是移动项目经理
     * "3"是监理方
     * "4"是 施工方
     **/
    private String orgType;

    /**
     * 角色
     * "PRJ_ROLE_MOBILE"				移动项目经理
     * "PRJ_ROLE_CONSTRUCTION"			施工方单位项目经理
     * "PRJ_ROLE_SUPERVISION"			监理
     * <p>
     * "RMS_ROLE_ON_THE_SPOT_CHECK"		现场检查
     * "RMS_ROLE_WORK_ORDER"			工单管理
     * "RMS_ROLE_PATROL_TASK"			巡检任务
     * "RMS_ROLE_HIDDEN_REPORT"	隐患上报
     * "RMS_ROLE_NOTICE_RECEPTION"		接收系统公告
     * "RMS_ROLE_DATA_VIEW"				数据查看
     * "RMS_ROLE_MAP"					地图
     * "RMS_ROLE_APPLY_FOR_CAR"			车辆申请
     * "RMS_ROLE_TOOLS"					工具包
     * "RMS_ROLE_ADDRESS_BOOK"			通讯录
     * <p>
     * "RMS_ROLE_SIGNIN"			          终端签到功能
     * "RMS_ROLE_PROBLEM_REPORT"		投诉工单处理
     * 使用说明
     * 设置
     */
    private Set<String> roles;


    /**
     * 创建UserInfo对象.
     */
    public UserInfo() {

    }

    /**
     * 创建UserInfo对象.
     *
     * @param id       主键
     * @param name     登录名
     * @param password 密码
     * @param display  显示名
     * @param regionId 区域ID
     */
    public UserInfo(String id, String name, String password
            , String display, String regionId) {
        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException("用户名不能为空！");
        }
        this.id = id;
        this.name = name;
        this.password = password;
        this.displayName = display;
        this.regionId = regionId;
    }

    /**
     * 创建UserInfo对象.
     *
     * @param name     登录名
     * @param password 密码
     */
    public UserInfo(String name, String password) {
        this(null, name, password, name, "440600");
    }

    /**
     * 获取主键
     *
     * @return 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }


    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户真实姓名
     *
     * @return 用户真实姓名
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 设置用户真实姓名
     *
     * @param displayName 用户真实姓名
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 获取登录密码
     *
     * @return 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取区域ID 所属区域级别 1:全国;2:省级;3:地市级
     *
     * @return 返回区域ID
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * 设置区域ID 所属区域级别 1:全国;2:省级;3:地市级
     *
     * @param regionId 区域ID
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    /**
     * @return the regionClass
     */
    public int getRegionLevel() {
        return regionLevel;
    }

    /**
     * @param regionLevel the regionLevel to set
     */
    public void setRegionLevel(int regionLevel) {
        this.regionLevel = regionLevel;
    }

    /**
     * 获取区域名称
     *
     * @return 返回区域名称
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * 设置区域名称
     *
     * @param regionName 区域名称
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * 获取巡检组ID
     *
     * @return 返回巡检组ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置巡检组ID
     *
     * @param groupId 巡检组ID
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取巡检组名
     *
     * @return 返回巡检组名
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置巡检组名
     *
     * @param groupName 巡检组名
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取部门ID
     *
     * @return 返回部门ID
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置部门ID
     *
     * @param departmentId 部门ID
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }


    /**
     * @return the orgType
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType the orgType to set
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * 获取部门名称
     *
     * @return 返回部门名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置部门名称.
     *
     * @param departmentName 部门名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    /**
     * @return the employeeNum
     */
    public String getEmployeeNum() {
        return employeeNum;
    }

    /**
     * @param employeeNum the employeeNum to set
     */
    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    /**
     * @return the roles
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


    /**
     * @return the contractorId
     */
    public String getContractorId() {
        return this.getDepartmentId();
    }


    /**
     * @return the orgId
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * @param orgId the orgId to set
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("rid", rid)
                .append("name ", name)
                .append("password ", "******")
                .append("displayName", displayName)
                .append("regionId", regionId)
                .append("regionName", regionName)
                .append("groupId", groupId)
                .append("groupName", groupName)
                .append("departmentId", departmentId)
                .append("departmentName", departmentName)
                .append("employeeNum", employeeNum)
                .append("orgId", orgId)
                .append("roles", roles)
                .toString();
    }
}
