package org.isotope.jfp.framework.beands.pub;

/**
 * 网络代理配置
 * 
 * @author fucy
 * @version 2.4.2.2015/11/25
 * @since 2.4.2.2015/11/25
 */
public class ProxyBean {

	/**
	 * 代理服务器地址
	 */
	private String ipAdress;
	/**
	 * 代理端口
	 */
	private int port;
	/**
	 * 服务类型（Http,Https，Socket）
	 */
	private String type;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String passWord;
	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
