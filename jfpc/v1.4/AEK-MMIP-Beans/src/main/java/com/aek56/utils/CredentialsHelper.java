package com.aek56.utils;

import org.jfpc.constants.ISFrameworkConstants;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;

import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZDBO;
import com.aek56.atm.credentials.MGTA_CJYLQXZCZ.MGTA_CJYLQXZCZDBO;
import com.aek56.atm.master.MDG_ZCZXX.MDG_ZCZXXDBO;

/**
 * 注册证帮助类
 * @author Administrator
 *
 */
public class CredentialsHelper {
	private final static String code68="68";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println(get68Code("国食药监械（进）字2012第2404286号"));
System.out.println(get123Type("国食药监械（进）字2012第2404286号"));
	}
	
	/**
	 * 根据注册证号返回68码
	 */
	public static void getCode(MyDataBaseObjectSupport data) {
		//主数据
		if(data instanceof MDG_ZCZXXDBO){
			MDG_ZCZXXDBO md = (MDG_ZCZXXDBO)data;
			//68码拦截
			if(EmptyHelper.isEmpty(md.getK02_zj68bh()))
			{
				md.setK02_zj68bh(get68Code(md.getF01_zczzwmc()));
			}
			//123类
			if(EmptyHelper.isEmpty(md.getK03_zj123lb()))
			{
				md.setK03_zj123lb(get68Code(md.getF01_zczzwmc()));
			}
		}
		//厂家医疗器械注册证(供应商)
		else if(data instanceof MGAAC_YLQXZCZDBO){
			MGAAC_YLQXZCZDBO md = (MGAAC_YLQXZCZDBO)data;
			//68码拦截
			if(EmptyHelper.isEmpty(md.getK02_zj68bh()))
			{
				md.setK02_zj68bh(get68Code(md.getF01_zczzwmc()));
			}
			//123类
			if(EmptyHelper.isEmpty(md.getK03_zj123lb()))
			{
				md.setK03_zj123lb(get68Code(md.getF01_zczzwmc()));
			}
		}
		//供应商提供医院厂家医疗器械注册证(医院)
		else if(data instanceof MGTA_CJYLQXZCZDBO){
			MGTA_CJYLQXZCZDBO md = (MGTA_CJYLQXZCZDBO)data;
			//68码拦截
			if(EmptyHelper.isEmpty(md.getK02_zj68bh()))
			{
				md.setK02_zj68bh(get68Code(md.getF01_zczzwmc()));
			}
			//123类
			if(EmptyHelper.isEmpty(md.getK03_zj123lb()))
			{
				md.setK03_zj123lb(get68Code(md.getF01_zczzwmc()));
			}
		}
	}
	/**
	 * 根据注册证号返回68码
	 */
	public static String get68Code(String code) {
		//国食药监械（进）字2012第2404286号
		if(code.getBytes().length<25){
			return ISFrameworkConstants.EMPTY;
		}
		//System.out.println(code.indexOf("第"));
		//System.out.println(code.substring(code.indexOf("第")+2, code.indexOf("第")+4));
		return code68+code.substring(code.indexOf("第")+2, code.indexOf("第")+4);
	}
	
	/**
	 * 根据注册证号返回I/II/III类别
	 */
	public static String get123Type(String code) {
		if(code.getBytes().length<25){
			return ISFrameworkConstants.EMPTY;
		}
		return code.substring(code.indexOf("第")+1, code.indexOf("第")+2);
	}
}
