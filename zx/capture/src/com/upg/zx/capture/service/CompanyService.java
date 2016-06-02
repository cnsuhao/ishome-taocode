package com.upg.zx.capture.service;

import java.util.Map;

/**
 * @deprecated
 * @author 001745
 *
 */
public class CompanyService {
	private Enterprisecaptruer enterprise = new Enterprisecaptruer();

	public Enterprisecaptruer getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprisecaptruer enterprise) {
		this.enterprise = enterprise;
	}

	/**
	 * 抓取公司信息
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getCorpInfoById(final String id) {
		try {

			final Map<String, Object> map_info = enterprise.getCompanyInfo_(id, 1, 100);
			// 获取备案信息
			Map map = enterprise.getregisterInfo(id);
			if (map.get("fzjgxx") != null) {
				map_info.put("branchList", map.get("fzjgxx"));
			}

			if (map.get("ryxx") != null) {
				map_info.put("disectorList", map.get("ryxx"));
			}
			return map_info;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
