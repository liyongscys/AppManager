package com.cabletech.common.ftp;


import java.io.FileInputStream;   
import java.io.IOException;   
import java.io.InputStream;    
import java.io.PrintWriter;   
import java.io.UnsupportedEncodingException;
  
import com.cabletech.common.ftp.UploadStatus;
  
import org.apache.commons.net.PrintCommandListener;   
import org.apache.commons.net.ftp.FTP;   
import org.apache.commons.net.ftp.FTPClient;   
import org.apache.commons.net.ftp.FTPFile;   
import org.apache.commons.net.ftp.FTPReply;  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**   
 * 支持断点续传的FTP实用类  
 * @author BenZhou  http://www.bt285.cn
 * @version 0.1 实现基本断点上传下载  
 * @version 0.2 实现上传下载进度汇报  
 * @version 0.3 实现中文目录创建及中文文件创建，添加对于中文的支持  
 */
public class Uploader {
	private static final Logger logger = LoggerFactory.getLogger(Uploader.class);
    /**
     * ftp client
     */
    public FTPClient ftpClient = new FTPClient();   
    
    /**
     * 构造函数
     */
    public Uploader(){   
        //设置将过程中使用到的命令输出到控制台   
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));   
    }   
       
    /** *//**  
     * 连接到FTP服务器  
     * @param hostname 主机名  
     * @param port 端口  
     * @param username 用户名  
     * @param password 密码  
     * @return 是否连接成功  
     * @throws IOException  
     */  
    public boolean connect(String hostname,int port,String username,String password) throws IOException{   
        ftpClient.connect(hostname, port);   
        ftpClient.setControlEncoding("GBK");   
        if(FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){   
            if(ftpClient.login(username, password)){   
                return true;   
            }   
        }   
        disconnect();   
        return false;   
    }  
    
    /**
     * 连接ftp服务器
     * @param info ftp信息
     * @return
     * @throws IOException
     */
    public boolean connect(FtpInfo info) throws IOException{   
        
        return connect(info.getHost(),info.getPort(),
        		info.getUserName(),info.getUserPassword());   
    }
       
       
    /**   
     * 上传文件到FTP服务器，支持断点续传  
     * @param local 本地文件名称，绝对路径  
     * @param remote 远程文件路径，使用/home/directory1/subdirectory/file.ext或是 http://www.guihua.org /subdirectory/file.ext 按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构  
     * @return 上传结果  
     * @throws IOException  
     */  
    public UploadStatus upload(String local,String remote) throws IOException{   
        //设置PassiveMode传输   
        ftpClient.enterLocalPassiveMode();   
        //设置以二进制流的方式传输   
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);   
        ftpClient.setControlEncoding("GBK");   
        UploadStatus result;   
        //对远程目录的处理   
        String remoteFileName = remote;   
        if(remote.contains("/")){   
            remoteFileName = remote.substring(remote.lastIndexOf("/")+1);   
            //创建服务器远程目录结构，创建失败直接返回   
            if(createDirectory(remote)==UploadStatus.Create_Directory_Fail){   
                return UploadStatus.Create_Directory_Fail;   
            }   
        }   
           
        //检查远程是否存在文件   
        FTPFile[] files = ftpClient.listFiles(toGbkString(remoteFileName));   
        if(files.length == 1){   
            if(!ftpClient.deleteFile(remoteFileName)){   
                return UploadStatus.Delete_Remote_Faild;   
            }   
        }
        
        InputStream input=new FileInputStream(local);
        boolean success=ftpClient.storeFile(remoteFileName, input);	
        
        if(success){
        	result= UploadStatus.Upload_New_File_Success;
        }else{
        	result= UploadStatus.Upload_New_File_Failed;
        }
		return result;
	
    }
    
    /** 
     * 断开与远程服务器的连接  
     * @throws IOException  
     */  
    public void disconnect() throws IOException{   
        if(ftpClient.isConnected()){   
            ftpClient.disconnect();   
        }   
    }  
    
    /**
     * 将字符串转化为GBK编码，防止乱码
     * @param s 
     * @return
     */
    private static String toGbkString(String s){
    	try {
			return new String(s.getBytes("GBK"),"iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			return null;
		}
    }
       
    /**   
     * 递归创建远程服务器目录  
     * @param remote 远程服务器文件绝对路径  
     * @return 目录创建是否成功  
     * @throws IOException  
     */  
    public UploadStatus createDirectory(String remote) throws IOException{ 
    	//FTPClient ftpClient
        UploadStatus status = UploadStatus.Create_Directory_Success;   
        String directory = remote.substring(0,remote.lastIndexOf("/")+1); 
        
        if(!directory.equals("/")&&!ftpClient.changeWorkingDirectory(toGbkString(directory))){   
            //如果远程目录不存在，则递归创建远程服务器目录   
            int start=0;   
            int end = 0;   
            if(directory.startsWith("/")){   
                start = 1;   
            }else{   
                start = 0;   
            }   
            end = directory.indexOf("/",start);   
            while(true){   
                String subDirectory = toGbkString(remote.substring(start,end));   
                if(!ftpClient.changeWorkingDirectory(subDirectory)){   
                    if(ftpClient.makeDirectory(subDirectory)){   
                        ftpClient.changeWorkingDirectory(subDirectory);   
                    }else {   
                    	logger.error("创建目录失败");
                        return UploadStatus.Create_Directory_Fail;   
                    }   
                }   
                   
                start = end + 1;   
                end = directory.indexOf("/",start);   
                   
                //检查所有目录是否创建完毕   
                if(end <= start){   
                    break;   
                }   
            }   
        }   
        return status;   
    } 

}
