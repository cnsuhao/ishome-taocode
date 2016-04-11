package com.upg.biz.search.control;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.upg.biz.search.beans.CeciCorpBase.CeciCorpBaseDBO;
import com.upg.biz.search.beans.CeciCorpBase.CeciCorpBaseService;

/**
 * qiyejiansuo
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class CorpBaseControl {
	@Resource
	protected ICacheService mq;
	@Resource
	protected CeciCorpBaseService CeciCorpBase;

	@RequestMapping(value = "/CB", method = RequestMethod.POST)
	@ResponseBody
	public String queryCompanyByName(@RequestParam(required = true) String corp_name, @RequestParam(required = true) int maxNum, String clientType) throws Exception {
		Assert.notNull(corp_name);
		Assert.hasText(corp_name, "搜索名称不能为空！");

		// 本地库查询
		CeciCorpBaseDBO condation = new CeciCorpBaseDBO();
		condation.setCorpName(corp_name);
		condation.setLimit(maxNum == 0 ? 5 : maxNum);
		return JSON.toJSONString(CeciCorpBase.doSelectData(condation));
	}
}
