package org.jfpc.framework.helper;

import net.sf.json.JSONObject;

import org.jfpc.constants.ISFrameworkConstants;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.springframework.ui.Model;


/**
 * 数据安全
 * @author Spook
 *
 */
public class ServiceHelper implements ISFrameworkConstants {

	/**
	 * 
	 * @param gameDate
	 * @param model
	 */
    public static FrameworkDataBean getData(FrameworkDataBean fdb){
	
		return fdb;
//		return (FromDateBean)JSONObject.toBean(
//				JSONObject.fromObject(
//						decryption(gameDate.getPuk(),gameDate.getF01())),
//						FromDateBean.class);
	}
	
	/**
	 * 数据加密返回
	 * @param fdb
	 * @param model
	 */
	public static void putData(FrameworkDataBean fdb, Model model){
		model.addAttribute("serverback", 
				encryption(Public_Key_Cryptography, //数据加密
						JSONObject.fromObject( fdb ).toString()) );
	}
	
	/**
	 * 数据解密
	 * @param UID
	 * @param date
	 * @return
	 */
	public static String decryption(String key,String date)
	{
		return date;		
	}
	
	/**
	 * 数据加密
	 * @param UID
	 * @param date
	 * @return
	 */
	public static String encryption(String key,String date)
	{
		return date;		
	}
}
