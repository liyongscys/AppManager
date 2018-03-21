package com.cabletech.rms.proxy.model;

/**
 * @author YuLeyuan
 *
 */
public class ApnNotification {
	/** broadcast: Y表示群发,N表示发送给指定用户 */
	private String broadcast;
	/** username: 指定用户的username,如:feddfa7ca6f14d649a36c74e5e7062b4 */
	private String username;
	/** title: 推送消息的标题 */
	private String title;
	/** message: 推送消息的内容 */
	private String message;
	/** uri: 隐式传递的参数，如推送消息的id */
	private String uri;
	
	/**
	 * @return the broadcast
	 */
	public String getBroadcast() {
		return broadcast;
	}
	/**
	 * @param broadcast the broadcast to set
	 */
	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
}
