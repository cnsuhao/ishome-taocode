package org.ishome.jfp.mobile.biz.Demo;

import org.ishome.jfp.framework.biz.custom.ISCheckCustomBusiness;
import org.ishome.jfp.framework.biz.custom.ISInitCustomBusiness;
import org.ishome.jfp.framework.biz.custom.ISProcessCustomBusiness;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.mobile.biz.MobileDubboBusinessInitSupport;
import org.springframework.stereotype.Service;


/**
 * Dubbo框架下面的业务处理
 * 
 * @author Spook
 * @version 2.3.1 2015/6/23
 * @since 2.3.1 2015/6/23
 */
@Service("DemoDubboBusinessImpl")
public class DemoDubboBusinessImpl extends MobileDubboBusinessInitSupport
implements ISInitCustomBusiness, ISCheckCustomBusiness, ISProcessCustomBusiness, ISFrameworkConstants {

	// com.hundsun.med.access.hao.sync.DepartmentHAO//{"type":"waiting"}
	public DemoDubboBusinessImpl() {
		setBizName("Demo");
	}

	//DepartmentHAO param;

	@Override
	public boolean beforeProcess() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean doProcess() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean afterProcess() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean beforeCheck() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean doCheck() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean afterCheck() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean beforeInit() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean doInit() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean afterInit() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
