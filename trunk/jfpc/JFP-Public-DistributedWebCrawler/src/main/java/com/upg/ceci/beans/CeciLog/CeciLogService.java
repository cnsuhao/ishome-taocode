package com.upg.ceci.beans.CeciLog;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.isotope.jfp.framework.utils.PKHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 抓取结果日志 */
@Service("CeciLogService")
public class CeciLogService extends MyServiceSupport {

	public static final String JOB_OK = "JOB_OK";
	public static final String JOB_ERR = "JOB_ERR";
	public static final String JOB_COUNT = "JOB_COUNT";

	protected static final Logger logger = LoggerFactory.getLogger(CeciLogService.class);

	public CeciLogDao getDao() {
		return getMySqlSession().getMapper(CeciLogDao.class);
	}

	/**
	 * 整理上次运行作业数据
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void prepareLastJobData() {
		// 生成统计结果
		CeciLogDBO cld = new CeciLogDBO();
		List<CeciLogDBO> rs = (List<CeciLogDBO>) getDao().doSelectPage(cld);
		// 清空运行日志
		cld.setInfoType(JOB_OK);
		doDelete(cld);
		for (int i = 0; i < rs.size(); i++) {
			cld = rs.get(i);
			cld.setId(PKHelper.creatBarCodeKey());
			cld.setInfoType(CeciLogService.JOB_COUNT);
			doInsert(cld);
		}
	}

	/**
	 * 读取数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public FrameworkDataBean doReadLast(MyDataBaseObjectSupport formParamBean) {
		changeTable(formParamBean, DB_SELECT);
		return getDao().doReadLast(formParamBean);
	}
}
