
package org.isotope.fpm.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FTPUtil {
	/**
	* Description: 向FTP服务器上传文件
	* @param url FTP服务器hostname
	* @param port FTP服务器端口
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param path FTP服务器保存目录
	* @param filename 上传到FTP服务器上的文件名
	* @param input    输入流
	* @return 成功返回true，否则返回false
	*/
	public boolean uploadFile(String url, int port, String username,
	    String password, String path, String filename, InputStream input) {
	   // 初始表示上传失败
	   boolean success = false;
	   // 创建FTPClient对象
	   FTPClient ftp = new FTPClient();
	   try {
	    int reply;
	    // 连接FTP服务器
	    // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
	    
	    ftp.connect(url, port);
	    
	    
	    // 登录ftp
	     boolean is=ftp.login(username, password);
	    // 看返回的值是不是230，如果是，表示登陆成功
	     System.out.println(is);
	    reply = ftp.getReplyCode();
	    // 以2开头的返回值就会为真
	    if (!FTPReply.isPositiveCompletion(reply)) {
	     ftp.disconnect();
	     return success;
	    }
	    ftp.enterLocalPassiveMode();
	    // 转到指定上传目录
	    ftp.setBufferSize(2*1024);
	    ftp.changeWorkingDirectory(path);
	    
	    ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	    BufferedInputStream bi=new BufferedInputStream(input);
	    ftp.setDataTimeout(5000);
	  
	    // 将上传文件存储到指定目录
	    boolean b=ftp.storeFile(filename, bi);
        
	    // 关闭输入流
	    input.close();
	    // 退出ftp
	    ftp.logout();
	    // 表示上传成功
	    success = b;
	   } catch (IOException e) {
	    e.printStackTrace();

	   } finally {
	    if (ftp.isConnected()) {
	     try {
	      ftp.disconnect();
	     } catch (IOException ioe) {

	     }
	    }
	   }
	   return success;
	}  
	
public static void main(String agrs[]) {
	
	FTPUtil ftp=new FTPUtil();
		boolean isok;
		try {
			//isok = ftp.uploadFile("114.80.201.48", 21, "mkq", "mkq_2012", "/cb", "bb.wma",new FileInputStream("c:\\1.wma"));
			//isok = ftp.uploadFile("60.191.103.70", 21, "administrator", "hz2009", "/cb", "bb.wma",new FileInputStream("c:\\1.wma"));
			isok = ftp.uploadFile("183.129.160.242", 21, "hzlh", "hzlhqwer!@#$", "/a", "zz2.mp3",new FileInputStream("F:/FTP/20130104（正常）.mp3"));
//			URL url=new URL("http://183.129.160.242:1080/uploads/news/audios/test.mp3");
//			HttpURLConnection  con=(HttpURLConnection) url.openConnection();
//			isok = ftp.uploadFile("183.129.160.242", 21, "hzlh", "hzlhqwer!@#$", "/test", "F:/FTP/20130103（正常）.mp3",con.getInputStream());
			System.out.println(isok);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}






