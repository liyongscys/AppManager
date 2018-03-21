package com.cabletech.common.util;

import java.util.Date;
/**
 * 位置信息
 * @author YuLeyuan
 *
 */
public class Position {
	
	/**
	 * INVALID
	 */
	public static final Position INVALID=new Position();
	
	/**
	 * 当位置捕获到时的时间戳
	 */
	private Date timestamp;	
	/**
	 * 纬度，单位：度 
	 */
	private double latitude;
	/**
	 * 经度，单位：度 
	 */
	private double longitude;	
	/**
	 * 海拔，单位：米 
	 */
	private double altitude;
	/**
	 * 航向,设备正北顺时针前进的方位where 0° ≤ heading < 360°
	 */
	private double heading;
	/**
	 * 航速,移动速度,单位：米/秒 meters per second
	 */
	private double speed;
	

	/**
	 * @param lat 纬度
	 * @param lng 经度
	 */
	public void setLatlng(double lat,double lng){
		latitude=lat;
		longitude=lng;
	}


	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}


	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}


	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}


	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	/**
	 * @return the altitude
	 */
	public double getAltitude() {
		return altitude;
	}


	/**
	 * @param altitude the altitude to set
	 */
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}


	/**
	 * @return the heading
	 */
	public double getHeading() {
		return heading;
	}


	/**
	 * @param heading the heading to set
	 */
	public void setHeading(double heading) {
		this.heading = heading;
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
	

	
}
