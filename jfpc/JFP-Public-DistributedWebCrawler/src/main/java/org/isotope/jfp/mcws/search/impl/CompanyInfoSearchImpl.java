package org.isotope.jfp.mcws.search.impl;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.mcws.search.ISCompanyInfoSearch;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 多源检索
 * 
 * @author 001745
 *
 */
@Service
public class CompanyInfoSearchImpl implements ISCompanyInfoSearch, ISFrameworkConstants {

	public static final String COMPANY_URL = "COMPANY:URL:";
	@Resource
	protected ICacheService mq;

	/**
	 * jobId + SEMICOLON + areaCode + SEMICOLON + companyName
	 */
	@Override
	public Object getCompanyName() {
		// jobId + SEMICOLON + areaCode + SEMICOLON + companyName
		// mq.offerObjectInList(COMPANY_INFO,"123;310000;aaa",false);
		String company = (String) mq.pollFirstObjectInList(COMPANY_CAP,false);
		if (EmptyHelper.isEmpty(company))
			return EMPTY;
		String[] cs = company.split(SEMICOLON);
		Object url = getAreaUrl(cs[1]);
		if (EmptyHelper.isEmpty(url))
			return EMPTY;
		StringBuffer res = new StringBuffer(200);
		res.append(company);
		res.append(SEMICOLON);
		res.append(url);
		return res.toString();
	}

	@Override
	public boolean saveCompanyInfo(String jobId, String html) {
		return mq.putObject(jobId, html, 30, false);
	}

	@Override
	public Object getAreaUrl(String areaCode) {
		return mq.getObject(COMPANY_URL + areaCode, false);
	}

	@Override
	public boolean addCompanyName(String jobId, String areaCode, String companyName) {
		return mq.offerObjectInList(COMPANY_CAP, jobId + SEMICOLON + areaCode + SEMICOLON + companyName, false);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 检索列表待抓取企业信息
	 */
	final static String TASK_KEY = "JOB_LIST";
	public ForCodeCompany getCompanyNameByCap() {
		ForCodeCompany res = new ForCodeCompany();
		try {
			// 1.获得一个企业名称
			// jobId + SEMICOLON + areaCode + SEMICOLON + companyName + SEMICOLON + num
			String company = (String) mq.pollFirstObjectInList(TASK_KEY,false);
			if (EmptyHelper.isEmpty(company))
				return res;
			JSONObject cmp = JSONObject.parseObject(company);
			res.setAreaCode(cmp.getString("res_date"));
			res.setCorpName(cmp.getString("corp_name"));
			String failCount = cmp.getString("fail_count");
			if (EmptyHelper.isNotEmpty(failCount))
				res.setFailCount(Integer.parseInt(failCount)+1);
			
			// 2.判断是否已经存在（当日索引更新队列）

			// 3.插入到当日索引更新队列
		} catch (Exception e) {
		}finally{
			ErrorCompanyThread ect = new ErrorCompanyThread();
			ect.setCacheService(mq);
			ect.start();
		}

		// 4.返回数据
		return res;
	}
	
	/**
	 * 失败企业迁移到正常队列，并增加失败次数
	 * @author 001745
	 *
	 */
	public class ErrorCompanyThread extends Thread {
		protected ICacheService cacheService;
		public ICacheService getCacheService() {
			return cacheService;
		}
		public void setCacheService(ICacheService cacheService) {
			this.cacheService = cacheService;
		}
		public void run(){
			for(int i=0;i<10;i++){
				String company = (String) mq.pollFirstObjectInList(ForCodeCompany.FOR_CODE_FAILD,false);
				if (EmptyHelper.isEmpty(company))
					return;
				JSONObject cmp = new JSONObject();
				JSONObject fcc = JSONObject.parseObject(company);
				cmp.put("res_date",fcc.getString("areaCode"));
				cmp.put("corp_name",fcc.getString("corpName"));
				cmp.put("fail_count",fcc.getString("failCount"));
				
				mq.offerObjectInList(TASK_KEY,cmp.toJSONString(),false);
			}
		}
	}
	
	/**
	 * Redis数据内容
	 * @author 001745
	 *
	 */
	public class ForCodeCompany {
		//企业失败队列
		public static final String FOR_CODE_FAILD = "FOR:CODE:FAILD";

		// 企业名称
		private String corpName;
		// 区域码
		private String areaCode;
		// 失败次数
		private int failCount;

		public String getCorpName() {
			return corpName;
		}

		public void setCorpName(String corpName) {
			this.corpName = corpName;
		}

		public String getAreaCode() {
			return areaCode;
		}

		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}

		public int getFailCount() {
			return failCount;
		}

		public void setFailCount(int failCount) {
			this.failCount = failCount;
		}
	}
}
