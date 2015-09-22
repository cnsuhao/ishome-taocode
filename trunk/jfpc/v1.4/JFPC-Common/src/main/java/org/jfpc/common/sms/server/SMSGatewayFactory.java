package org.jfpc.common.sms.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jfpc.beans.common.MS3C1.MS3C1DBO;
import org.jfpc.beans.common.MS3C1.MS3C1Service;
import org.jfpc.framework.bean.FrameworkDataBean;

/**
 * 多短信网关配置
 * @author Spook
 * @since 0.0.10
 * @version 0.1.0 2014/9/4
 */
public class SMSGatewayFactory {
	//网关配置信息
	ArrayList<SMSConfig> list = new ArrayList<SMSConfig>();
	/**
	 * 默认网关
	 */
	SMSConfig conf;
	public SMSConfig getConf() {
		return conf;
	}

	public void setConf(SMSConfig conf) {
		this.conf = conf;
	}

	@Resource
	MS3C1Service netWorkService;
	
	/**
	 * 初始化
	 */
	public void init(){
		if(conf!=null)
			list.add(conf);
		//从数据库里面获得网关配置
		List<FrameworkDataBean> netWorkList = netWorkService.doSelectPage(new MS3C1DBO(), false);
		
		for (FrameworkDataBean netWorkDbo : netWorkList) {
			SMSConfig sp = new SMSConfig();
			sp.setSmsType(((MS3C1DBO)netWorkDbo).getF08_kgzt());//服务状态(0开启1关闭)
			sp.setSmsId(((MS3C1DBO)netWorkDbo).getK01_wgywzh());
			sp.setSmsPassword(((MS3C1DBO)netWorkDbo).getK02_wgaqmm());
			sp.setSmsUrl(((MS3C1DBO)netWorkDbo).getF04_wgfwdz());
			sp.setClassName(((MS3C1DBO)netWorkDbo).getF05_wgsxlmc());
			list.add(sp);
		}
	}	
	
	/**
	 * 获得一个正在使用的网关，如果没有返回空
	 * @return
	 */
	public SMSConfig getSMSGateway(){
		if(list.size()==0)init();
		SMSConfig sp = null;
		int num = list.size();
		if(num==1){
			sp = list.get(0);
		}else{
			int index=(int) (Math.random()*list.size());
			sp = list.get(index);
		}
		
		return sp;
	}

	/**
	 * 设定网关服务使用状态(0开启1关闭)
	 * @param smsId 网关ID
	 * @param smsType (0开启1关闭)
	 * @return 关闭数目
	 */
	public int setSMSGatewayType(String smsId, String smsType) {
		if(list.size()==0)
			return 0;
		//关闭缓存中的网关
		for(SMSConfig sms : list){
			if(smsId.equals(sms.getSmsId())){
				sms.setSmsType(smsType);
			}
		}
		//关闭数据库的网关
		MS3C1DBO networkDbo=new MS3C1DBO();
		networkDbo.setPuk(smsId);
		networkDbo.setF08_kgzt(smsType);
		int res=netWorkService.doUpdate(networkDbo);
		return res;
	}
}
