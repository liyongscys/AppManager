package com.cabletech.rms.proxy.handler;

/**
 * 消息处理接口
 * @author YuLeyuan
 *
 */
public interface Handler {	
	/**
	 * 处理消息
	 * @param deviceId 设备ID
	 * @param cmdId 输入指令ID
	 * @param json  输入指令的json内容
	 * @return BaseCmd 输出指令，返回json字符串，或是对象 
	 */
	Object handle(String deviceId, String cmdId, String json);
}
