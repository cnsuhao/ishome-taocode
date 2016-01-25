package org.ishome.jfp.common.Hospital;

import java.util.HashMap;

import org.ishome.jfp.beans.HospitalCloudAccessMonitor.HospitalCloudAccessMonitorDBO;
import org.ishome.jfp.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleDBO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * 医院对接配置参数
 * 
 * @author Spook
 * 
 */
public class HospitalSyncConfigBean extends HospitalCloudAccessMonitorDBO {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HospitalSyncConfigBean vs = new HospitalSyncConfigBean();
		vs.setBizJsonConfigs("aaa");
		System.out.println(JSON.toJSON(vs));
		
		
		try {
			//Object a= JSON.parseObject(values[1], Class.forName(values[0]));
			//System.out.println(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 安全令牌
	 */
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 安全测试数据
	 */
	private boolean secyrityTestFlag;

	public boolean getSecyrityTestFlag() {
		return secyrityTestFlag;
	}

	public void setSecyrityTestFlag(boolean secyrityTestFlag) {
		this.secyrityTestFlag = secyrityTestFlag;
	}
	
	private String bizJsonConfigs;

	public String getBizJsonConfigs() {
		return bizJsonConfigs;
	}

	public void setBizJsonConfigs(String bizJsonConfigs) {
		this.bizJsonConfigs = bizJsonConfigs;
	}

	// 业务对接配置信息
	public HashMap<String, HospitalCloudAccessRuleDBO> getBizConfigs() {
		HashMap<String, HospitalCloudAccessRuleDBO> bizConfigs = new HashMap<String, HospitalCloudAccessRuleDBO>();
		try{
			JSONArray array =  JSONArray.parseArray(bizJsonConfigs);
			HospitalCloudAccessRuleDBO hcard ;
			for(int i=0;i<array.size();i++){
				hcard = JSON.parseObject((String)array.get(i), HospitalCloudAccessRuleDBO.class);
				bizConfigs.put(hcard.getModuleType(), hcard);
			}
		}catch(Exception e){}
		return bizConfigs;
	}
	// 业务对接配置信息
	public void setBizConfig(HospitalCloudAccessRuleDBO hcard) {
		try{
			HashMap<String, HospitalCloudAccessRuleDBO> bizConfigs = getBizConfigs();
			if(hcard!=null)
				bizConfigs.put(hcard.getModuleType(), hcard);
		}catch(Exception e){}
	}
	public void setBizConfigs(HashMap<String, HospitalCloudAccessRuleDBO> bizConfigs) {
		bizJsonConfigs = JSON.toJSONString(bizConfigs);
	}
}
