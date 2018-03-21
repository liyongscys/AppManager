package com.cabletech.rms.proxy.model;

import java.io.Serializable;
/**
 * 组织对象
 * @author zyh
 *
 */
public class OrgInfo  implements Serializable{
	/**
	 * id
	 */
	private String id;
	/**
	 * 组织名称
	 */
	private String name;
	/**
	 * 父类组织ID
	 */
	private String parentId;
	/**
	 * 组织类型
	 */
	private String orgType;
	/**
	 * 所属区域
	 */
	private String regionId;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	 * @return the regionId
	 */
	public String getRegionId() {
		return regionId;
	}
	/**
	 * @param regionId the regionId to set
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
}
