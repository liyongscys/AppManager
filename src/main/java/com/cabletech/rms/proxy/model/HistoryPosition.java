package com.cabletech.rms.proxy.model;

import java.io.Serializable;
import java.util.Date;

public class HistoryPosition implements Serializable {
	/**
	 * keyId
	 */
	private String keyId;
	/**
	 * simId
	 */
	private String simId;
	/**
	 * 区域
	 */
	private String regionId;
	/**
	 * 操作
	 */
	private String operate;
	/**
	 * 活动时间
	 */
	private Date activeTime;
	/**
	 * gps时间
	 */
	private Date gpsTime;
	/**
	 * 坐标
	 */
	private String coordinate;
	/**
	 * ct_x
	 */
	private double ctX;
	/**
	 * ct_y
	 */
	private double ctY;
	/**
	 * 速度
	 */
	private double speed;
	/**
	 * 方向
	 */
	private double course;
	/**
	 * 任务Id
	 */
	private String taskId;
	/**
	 * 距离
	 */
	private String distance;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 业务类型
	 */
	private String businessType;
	/**
	 * 巡检人员
	 */
	private String patrolmanId;
	/**
	 * lon
	 */
	private double lon;
	/**
	 * lat
	 */
	private double lat;
	/**
	 * @return the keyId
	 */
	public String getKeyId() {
		return keyId;
	}
	/**
	 * @param keyId the keyId to set
	 */
	public void setKeyId(String keyId) {
		this.keyId = keyId;
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
	 * @return the gpsTime
	 */
	public Date getGpsTime() {
		return gpsTime;
	}
	/**
	 * @param gpsTime the gpsTime to set
	 */
	public void setGpsTime(Date gpsTime) {
		this.gpsTime = gpsTime;
	}
	/**
	 * @return the coordinate
	 */
	public String getCoordinate() {
		return coordinate;
	}
	/**
	 * @param coordinate the coordinate to set
	 */
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	/**
	 * @return the ctX
	 */
	public double getCtX() {
		return ctX;
	}
	/**
	 * @param ctX the ctX to set
	 */
	public void setCtX(double ctX) {
		this.ctX = ctX;
	}
	/**
	 * @return the ctY
	 */
	public double getCtY() {
		return ctY;
	}
	/**
	 * @param ctY the ctY to set
	 */
	public void setCtY(double ctY) {
		this.ctY = ctY;
	}
	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	/**
	 * @return the course
	 */
	public double getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(double course) {
		this.course = course;
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
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
