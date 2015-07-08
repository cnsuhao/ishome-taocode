package org.ishome.jfp.bds.utils;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;


/**
 * 对接数据校验
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 *
 */
public class ValidateHelper implements ISFrameworkConstants {

//	/**
//	 * 校验排班信息
//	 * 
//	 * @param scheduling
//	 * @return
//	 */
//	public static boolean checkScheduling(SchedulingDBO scheduling) {
//		// 医生Id不为空
//		//if (StringUtils.isEmpty(scheduling.getDocId()))
//			//return false;
//		// 总号不为0
//		//if (StringUtils.isEmpty(scheduling.getResNo()))
//		//	return false;
//		// 科室Id不为空
//		if (StringUtils.isEmpty(scheduling.getDeptId()))
//			return false;
//		// 排班对应日期不为空
//		if (StringUtils.isEmpty(scheduling.getAccessSchTime()))
//			return false;
//		return true;
//	}
//
//	/**
//	 * 校验医生信息
//	 * 
//	 * @param dDBO
//	 * @return
//	 */
//
//	public static String[] checkDoctor(DoctorDBO dDBO) {
//		String[] ret = { ZERO, null };
//		// 医生Id不为空
//		StringBuffer sb = new StringBuffer(200);
//		sb.append("【医生同步对接校验失败】，医院：").append(dDBO.getHosId()).append(",医生ID：").append(dDBO.getDocId()).append(",问题原因：");
//		// 1.未对接上access_doc_id为空
//		// 2.已对接上,但医生介绍、头像为空
//		if (StringUtils.isEmpty(dDBO.getAccessDocId())) {
//			ret[0] = ONE;
//			sb.append("access_doc_id为空");
//		}
//		if (StringUtils.isEmpty(dDBO.getResume())) {
//			ret[0] = ONE;
//			sb.append("医生介绍为空");
//		}
//		if (StringUtils.isEmpty(dDBO.getHeadPhoto())) {
//			ret[0] = ONE;
//			sb.append("医生头像为空");
//		}
//
//		ret[1] = sb.toString();
//		return ret;
//	}
}
