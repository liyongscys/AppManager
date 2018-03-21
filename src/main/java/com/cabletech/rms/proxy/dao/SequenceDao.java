package com.cabletech.rms.proxy.dao;

/**
 * @author YuLeyuan
 *
 */
public interface SequenceDao {
	/**
	 * 生成序列号
	 * 
	 * @return 资源的序列号
	 */
	String generateFileSeq();
	/**
	 * 生成资源序列号
	 * @return 
	 */
	String generateResIdSeq();
}
