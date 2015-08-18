package org.ishome.jfp.framework.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.springframework.core.io.Resource;
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
	 * @deprecated
	 */
	 public String uploadFile(MultipartFile file) {
	 String[] filePath =
	 FilePathHelper.makeFilePath(file.getOriginalFilename());
	 try {
	 if (uploadFile(filePath[1], filePath[2], file.getInputStream()) == true)
	 return filePath[0];
	 } catch (IOException e) {
	 e.printStackTrace();
	 }
	 return "";
	 }

	public static int MARK_NONE = 0;
	public static int MARK_IMAGE = 1;
	public static int MARK_TEXT = 2;
	/**
	 * 文件上传
	 * 
	 * @param file
	 * @param mark
	 *            是否使用水印
	 * @return
	 */
	public String[] uploadFile(MultipartFile file, int mark) {
		String[] filePath = FilePathHelper.makeFilePath(file.getOriginalFilename());
		try {
			InputStream input = file.getInputStream();			
			if(FilePathHelper.IMAG_TYPE.indexOf(filePath[3])>=0){
				//压缩
				if(doCompress){
					input = ImageCompress.Tosmallerpic(input,ratio,per);
				}
				//水印
				if(doMark){
					if (mark == MARK_TEXT) {
						input = ImageMarkLogoHelper.markImageByIcon(input, new ImageIcon(markPic.getURL()));
					} else if (mark == MARK_IMAGE) {
						input = ImageMarkLogoHelper.markImageByIcon(input, new ImageIcon(markPic.getURL()));
					} else {
		
					}
				}
			}
			
			if (uploadFile(filePath[1], filePath[2], input) == true)
				return filePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String[]{};
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
	public boolean uploadFile(String url, int port, String username, String password, String path, String filename, InputStream input) {
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
			// System.out.println(is);
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

	// //////////////////属性定义/////////////////////////
	/**
	 * 开启水印
	 */
	private boolean doMark = false;
	/**
	 * 开启压缩
	 */
	private boolean doCompress = false;
	
	/**
	 * 水印图片
	 */
	private Resource markPic = null;
	/**
	 * 默认图片
	 */
	private String defaultPic = "";
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

    /**
     * 压缩后的图片尺寸比例 
     */
	public float ratio=1.0F;
	/**
	 * 百分比 
	 */
	public float per=0.3F;
	
	public boolean isDoMark() {
		return doMark;
	}

	public void setDoMark(boolean doMark) {
		this.doMark = doMark;
	}

	public boolean isDoCompress() {
		return doCompress;
	}

	public void setDoCompress(boolean doCompress) {
		this.doCompress = doCompress;
	}

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

	public float getPer() {
		return per;
	}

	public void setPer(float per) {
		this.per = per;
	}

	public Resource getMarkPic() {
		return markPic;
	}

	public void setMarkPic(Resource markPic) {
		this.markPic = markPic;
	}

	public String getDefaultPic() {
		return defaultPic;
	}

	public void setDefaultPic(String defaultPic) {
		this.defaultPic = defaultPic;
	}

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
