package org.ishome.jfp.pdp.service.biz.check;

import org.ishome.jfp.framework.constants.ISJobConstants;
import org.ishome.jfp.pdp.service.biz.AHospitalProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 检查详细信息
 * 
 * @author Spook
 * 
 */
@Service("CheckSheetViewBusinessImpl")
public class CheckSheetViewBusinessImpl extends AHospitalProcessService {
	private static final Logger logger = LoggerFactory.getLogger(CheckSheetViewBusinessImpl.class);
	public CheckSheetViewBusinessImpl() {
		setBizName("CheckSheetView");
	}
	
	//CheckSheetViewHAO param;
	//ArrayList<SheetItemInfoBean> ret = new ArrayList<SheetItemInfoBean>();
	
	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(message.toString());
		//param = (CheckSheetViewHAO) message;
		//super.setPuk(param.getPuk());
		// 检测医院网络状态
		return true;
	}
	
	/**
	 * 完整性校验
	 */
	@Override
	public boolean doCheck() throws Exception {
		// 这里做数据转换
		// 医院HIS系统数据状态
		// message
		return true;
	}

	/**
	 * 涓氬姟澶勭悊
	 */
	@Override
	public boolean doProcess() throws Exception {
		logger.debug("开始取检查报告详情。。。");
		setReturnObject("");
		setReturnCode(ISJobConstants.ACCESSS_RTN_CODE_ERROR_NODATA);
        setReturnMessage(ISJobConstants.ACCESSS_RTN_MESSAGE_ERROR_NODATA);
        logger.debug("取检查报告详情结束。。。");
		return true;
	}
}
