package org.isotope.jfp.framework.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.springframework.web.multipart.MultipartFile;

/**
 * FTP工具
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.2 2014/11/3
 * @version 0.1 2014/2/8
 */
public class FTPUtil implements ISFrameworkConstants {

	/**
	 * 文件上传
	 *
	 * @param file
	 * @return
	 */
	public String uploadFile(MultipartFile file) {
		String[] filePath = FilePathHelper.makeFilePath(file.getOriginalFilename());
		try {
			if (uploadFile(filePath[1], filePath[2], file.getInputStream()) == true)
				return filePath[0];
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 文件上传
	 * 
	 * @param file
	 * @return
	 */
	public boolean uploadFile(String path, String filename, InputStream input) {
		return uploadFile(serverIp, serverPort, serverUser, serverUserPassword, path, filename, input);
	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public boolean uploadFile(String url, int port, String username, String password, String path, String filename,
			InputStream input) {
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
			boolean is = ftp.login(username, password);
			// 看返回的值是不是230，如果是，表示登陆成功
			if (is == false)
				return success;
			//System.out.println(is);
			reply = ftp.getReplyCode();
			// 以2开头的返回值就会为真
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.enterLocalPassiveMode();
			// 转到指定上传目录
			ftp.setBufferSize(2 * 1024);

			for (String p : path.split(BACKSLASH)) {
				ftp.makeDirectory(p);
				ftp.changeWorkingDirectory(p);
			}

			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			BufferedInputStream bi = new BufferedInputStream(input);
			ftp.setDataTimeout(5000);

			// 将上传文件存储到指定目录
			boolean b = ftp.storeFile(filename, bi);

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

	//////////属性定义/////////////
	/**
	 * 文件访问前缀地址
	 */
	private String fileUri = "";
	/**
	 * FTP服务器IP
	 */
	private String serverIp = "";
	/**
	 * FTP服务器端口
	 */
	private int serverPort = 21;
	/**
	 * FTP服务器用户名
	 */
	private String serverUser = "";
	/**
	 * FTP服务器密码
	 */
	private String serverUserPassword = "";

	public String getFileUri() {
		return fileUri;
	}

	public String getFileUri(String fileId) {
		return fileUri + FilePathHelper.getFilePath(fileId, false)[0];
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerUser() {
		return serverUser;
	}

	public void setServerUser(String serverUser) {
		this.serverUser = serverUser;
	}

	public String getServerUserPassword() {
		return serverUserPassword;
	}

	public void setServerUserPassword(String serverUserPassword) {
		this.serverUserPassword = serverUserPassword;
	}

}
