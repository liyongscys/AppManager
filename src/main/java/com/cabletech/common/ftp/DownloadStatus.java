package com.cabletech.common.ftp;
/**
 * 下载状态
 * @author YuLeyuan
 *
 */
public enum DownloadStatus {
	/**
	 * 远程文件不存在
	 */
	Remote_File_Noexist,
	/**
	 * 下载文件成功
	 */
	Download_New_Success,
	/**
	 * 下载文件失败
	 */
	Download_New_Failed,
	/**
	 * 本地文件大于远程文件
	 */
	Local_Bigger_Remote,
	/**
	 * 断点续传成功
	 */
	Download_From_Break_Success,
	/**
	 * 断点续传失败
	 */
	Download_From_Break_Failed;
}
