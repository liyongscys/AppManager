package com.cabletech.common.ftp;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

/**
 * @author YuLeyuan
 *
 */
public class TestUploader {
	/**
	 * testUpload
	 */
	@Test
	@Rollback(false)
	public void testUpload() {
		Uploader myFtp = new Uploader();   
        try {   
            boolean result=myFtp.connect("192.168.1.213", 21, "ftpuser", "ftpuser"); 
            assertTrue(result);
            String path="/广东/测试三";
            String localFilePath="D:\\Temp\\P120809155901.jpg"; 
            UploadStatus status=myFtp.upload(localFilePath,path+"/F000000004275.jpg");
            assertEquals(UploadStatus.Upload_New_File_Success,status);
            myFtp.disconnect();   
        } catch (IOException e) { 
            fail(e.toString());
        } 
	}
}
