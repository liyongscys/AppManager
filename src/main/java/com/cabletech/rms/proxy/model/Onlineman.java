package com.cabletech.rms.proxy.model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Date;

/**
 * 在线人员
 * @author YuLeyuan
 *
 */
public class Onlineman implements Serializable{
	/** serialVersionUID */
	private static final long serialVersionUID = -3908053909576211206L;
	/** 设备所使用的SIM卡号 */
    private String simId;
    /** 所属区域 */
    private String regionId;
    /** 巡检人ID */
    private String patrolmanId;
    /** 巡检组ID */
    private String patrolGroupId;
    /** 终端ID */
    private String terminalId;
    /** 活动时间 */
    private Date activeTime;
    /** 专业类型 */
    private String businessType;
    /** 计划ID */
    private String taskId;
    /** 当前操作 */
    private String operate;
    /** x */
    private double x;
    /** y */
    private double y;
    /** lon */
    private double lon;
    /** lat */
    private double lat;
    /**
     * 设置xy的值
     * @param p Point2D对象
     */
    public void setXy(Point2D p){
    	this.x=p.getX();
    	this.y=p.getY();
    }
    
	/**
	 * @return the simId
	 */
	public String getSimId() {
		return simId;
	}
	/**
	 * @param simId the simId to set
	 */
	public void setSimId(String simId) {
		this.simId = simId;
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

	/**
	 * @return the patrolmanId
	 */
	public String getPatrolmanId() {
		return patrolmanId;
	}
	/**
	 * @param patrolmanId the patrolmanId to set
	 */
	public void setPatrolmanId(String patrolmanId) {
		this.patrolmanId = patrolmanId;
	}
	
	/**
	 * @return the patrolGroupId
	 */
	public String getPatrolGroupId() {
		return patrolGroupId;
	}

	/**
	 * @param patrolGroupId the patrolGroupId to set
	 */
	public void setPatrolGroupId(String patrolGroupId) {
		this.patrolGroupId = patrolGroupId;
	}

	/**
	 * @return the terminalId
	 */
	public String getTerminalId() {
		return terminalId;
	}
	/**
	 * @param terminalId the terminalId to set
	 */
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	/**
	 * @return the activeTime
	 */
	public Date getActiveTime() {
		return activeTime;
	}
	/**
	 * @param activeTime the activeTime to set
	 */
	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	/**
	 * @return the businessType
	 */
	public String getBusinessType() {
		return businessType;
	}
	/**
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the operate
	 */
	public String getOperate() {
		return operate;
	}
	/**
	 * @param operate the operate to set
	 */
	public void setOperate(String operate) {
		this.operate = operate;
	}
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
    
    
}
