package org.ishome.jfp.mobile.beans.Demo;

import java.util.List;

import org.ishome.jfp.framework.beands.FrameworkDataBean;
import org.ishome.jfp.framework.support.MyDataBaseObjectSupport;
import org.ishome.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 业务处理类实体
 * 
 * @author Spook
 * 
 */
@Service
public class DemoService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(DemoService.class);

	public DemoDao getDao() {
		return getMySqlSession().getMapper(DemoDao.class);
	}

	/**
	 * 查询数据
	 * 
	 * @param formParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doSelectDemo(MyDataBaseObjectSupport formParamBean) {
		return super.doSelectData(formParamBean);
	}
}
