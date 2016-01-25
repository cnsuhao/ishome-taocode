package com.aek56.atm.master.md;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfpc.framework.helper.EmptyHelper;
import org.springframework.stereotype.Service;

import com.aek56.utils.MasterDataServiceSupport;

@Service
public class MasterDataService extends MasterDataServiceSupport {

	public MasterDataDao getDao() {
		return getMySqlSession().getMapper(MasterDataDao.class);
	}
/////////////////////////////////////////////////////////////////////
	public Map<String, Object> getMasterCount() {
		return getDao().getMasterCount(new HashMap<String, Object>());
	}
	// ////////////////////////////////////////////////////////////////
	public Map<String, Object> getMasterData(int type,int page,int limit) {
		Map<String, Object> p = new HashMap<String, Object>();
		String tablename = changeTable(type);
		if (EmptyHelper.isEmpty(tablename))
			return new HashMap<String, Object>();
		// 设定企业ID
		p.put("tablename", tablename);
		p.put("page", (page - 1) * limit);
		p.put("limit", limit);
		
		return getDao().getMasterData(p);
	}

	public Map<String, Object> getMaster(int type, String id) {
		Map<String, Object> p = new HashMap<String, Object>();
		String tablename = changeTable(type);
		if (EmptyHelper.isEmpty(tablename))
			return new HashMap<String, Object>();
		// 设定企业ID
		p.put("tablename", tablename);
		p.put("puk", id);

		return getDao().getMaster(p);
	}
	
	/**
	 * 无条件获得表内数据
	 * @param type
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Map<String, Object>> getMasterAll(int type,int page,int limit) {
		Map<String, Object> p = new HashMap<String, Object>();
		String tablename = "";
		switch (type) {
		// 企业经营范围
		case 00:
			tablename = "* FROM md1_qyjyfw_copy";
			break;
		// 供应商信息 11
		case 11:
			tablename = "* FROM md2_gysxx_copy";
			break;
		// 医院信息 12
		case 12:
			tablename = "* FROM md3_yyxx_copy";
			break;
		// 厂商信息 13
		case 13:
			tablename = "* FROM md4_csxx_copy";
			break;
		// 配送商信息 14
		// case 14:
		// tablename="* FROM ";
		// break;
		// 行政机构单位 10
		case 10:
			tablename = "* FROM md5_xzjgdw_copy";
			break;
		// 产品信息 无价格 40
		case 40:
			tablename = "* FROM md6_cpxx_copy";
			break;
		// 产品规格信息 31
		case 31:
			tablename = "* FROM md7_cpggxx_copy";
			break;
		// 产品注册证信息 32
		// case 32:
		// tablename="* FROM md8_cpzczxx";
		// break;
		// 产品产品线信息 33
		// case 33:
		// tablename="* FROM md9_cpcpxxx";
		// break;
		// 产品线信息 20
		case 20:
			tablename = "* FROM mda_cpxxx_copy";
			break;
		// 产品线分类信息 21
		case 21:
			tablename = "* FROM mdb_cpxflxx_copy";
			break;
		// 品牌信息 50
		case 50:
			tablename = "* FROM mdc_ppxx_copy";
			break;
		// 品牌关联供应商信息 18
		// case 18:
		// tablename="* FROM mdd_ppgysxx";
		// break;
		// 医生信息 16
		case 16:
			tablename = "* FROM mde_ysxx_copy";
			break;
		// 患者信息 17
		case 17:
			tablename = "* FROM mdf_hzxx_copy";
			break;
		// 条码规则 60
		// case 60:
		// tablename="* FROM ";
		// break;
		// 注册证信息 30
		case 30:
			tablename = "* FROM mdg_zczxx_copy";
			break;
		default:
			return new ArrayList<Map<String, Object>>();
		}
		// 设定企业ID
		p.put("tablename", tablename);
		p.put("page", (page - 1) * limit);
		p.put("limit", limit);
		return getDao().getMasterAll(p);
	}
	// ///////////////////////////////////////////////////////////////
	/**
	 * 获得主数据检索全部内容
	 * 
	 * @param value
	 * @param type
	 *            数据类型
	 * @param limit
	 *            返回记录数
	 * @return
	 */
	public List<Map<String, Object>> getTop101(String value, int type, int limit) {
		Map<String, Object> p = new HashMap<String, Object>();
		String tablename = "";
		switch (type) {
		// 企业经营范围
		case 00:
			tablename = "f01_zdmc as text,puk as id FROM md1_qyjyfw";
			break;
		// 供应商信息 11
		case 11:
			tablename = "f01_qyqc as text,puk as id FROM md2_gysxx";
			break;
		// 医院信息 12
		case 12:
			tablename = "f01_qyqc as text,puk as id FROM md3_yyxx";
			break;
		// 厂商信息 13
		case 13:
			tablename = "f01_qyqc as text,puk as id FROM md4_csxx";
			break;
		// 配送商信息 14
		// case 14:
		// tablename="f01_qyqc as text,puk as id FROM ";
		// break;
		// 行政机构单位 10
		case 10:
			tablename = "f01_jgqc as text,puk as id FROM md5_xzjgdw";
			break;
		// 产品信息 无价格 40
		case 40:
			tablename = "f01_cpqc as text,puk as id FROM md6_cpxx";
			break;
		// 产品规格信息 31
		case 31:
			tablename = "f01_ggqc as text,puk as id FROM md7_cpggxx";
			break;
		// 产品注册证信息 32
		// case 32:
		// tablename="* FROM md8_cpzczxx";
		// break;
		// 产品产品线信息 33
		// case 33:
		// tablename="* FROM md9_cpcpxxx";
		// break;
		// 产品线信息 20
		case 20:
			tablename = "f01_zwmc as text,puk as id FROM mda_cpxxx";
			break;
		// 产品线分类信息 21
		case 21:
			tablename = "f01_flmc as text,puk as id FROM mdb_cpxflxx";
			break;
		// 品牌信息 50
		case 50:
			tablename = "f01_ppqc as text,puk as id FROM mdc_ppxx";
			break;
		// 品牌关联供应商信息 18
		// case 18:
		// tablename="* FROM mdd_ppgysxx";
		// break;
		// 医生信息 16
		case 16:
			tablename = "f01_yyxm as text,puk as id FROM mde_ysxx";
			break;
		// 患者信息 17
		case 17:
			tablename = "f01_hzxm as text,puk as id FROM mdf_hzxx";
			break;
		// 条码规则 60
		// case 60:
		// tablename="* FROM ";
		// break;
		// 注册证信息 30
		case 30:
			tablename = "f01_zczzwmc as text,puk as id FROM mdg_zczxx";
			break;
		default:
			return new ArrayList<Map<String, Object>>();
		}
		// 设定企业ID
		p.put("tablename", tablename);
		p.put("ggg", value);
		p.put("limit", limit);

		return getDao().getTop10(p);
	}

	/**
	 * 获得主数据检索内容简拼
	 * 
	 * @param value
	 * @param type
	 *            数据类型
	 * @param limit
	 *            返回记录数
	 * @return
	 */
	public List<Map<String, Object>> getTop10(String value, int type, int limit) {
		Map<String, Object> p = new HashMap<String, Object>();
		String tablename = changeTable(type);
		if (EmptyHelper.isEmpty(tablename))
			return new ArrayList<Map<String, Object>>();
		// 设定企业ID
		p.put("tablename", tablename);
		p.put("ggg", value);
		p.put("limit", limit);

		return getDao().getTop10(p);
	}

	// ////////////////////////////////////
	/**
	 * 数据库分表
	 * 
	 * @param data
	 */
	public String changeTable(int type) {
		String tablename = "";
		switch (type) {
		// 企业经营范围
		case 00:
			tablename = "* FROM md1_qyjyfw";
			break;
		// 供应商信息 11
		case 11:
			tablename = "*, puk AS id, f01_qyqc AS text FROM md2_gysxx";
			break;
		// 医院信息 12
		case 12:
			tablename = "*, puk AS id, f01_qyqc AS text FROM md3_yyxx";
			break;
		// 厂商信息 13
		case 13:
			tablename = "*, puk AS id, f01_qyqc AS text FROM md4_csxx";
			break;
		// 配送商信息 14
		// case 14:
		// tablename="* FROM ";
		// break;
		// 行政机构单位 10
		case 10:
			tablename = "* FROM md5_xzjgdw";
			break;
		// 产品信息 无价格 40
		case 40:
			tablename = "* FROM md6_cpxx";
			break;
		// 产品规格信息 31
		case 31:
			tablename = "* FROM md7_cpggxx";
			break;
		// 产品注册证信息 32
		case 32:
			tablename = "* FROM md8_cpzczxx";
			break;
		// 产品产品线信息 33
		case 33:
			tablename = "* FROM md9_cpcpxxx";
			break;
		// 产品线信息 20
		case 20:
			tablename = "* FROM mda_cpxxx";
			break;
		// 产品线分类信息 21
		case 21:
			tablename = "* FROM mdb_cpxflxx";
			break;
		// 品牌信息 50
		case 50:
			tablename = "* FROM mdc_ppxx";
			break;
		// 品牌关联供应商信息 18
		case 18:
			tablename = "* FROM mdd_ppgysxx";
			break;
		// 医生信息 16
		case 16:
			tablename = "* FROM mde_ysxx";
			break;
		// 患者信息 17
		case 17:
			tablename = "* FROM mdf_hzxx";
			break;
		// 条码规则 60
		// case 60:
		// tablename="* FROM ";
		// break;
		// 注册证信息 30
		case 30:
			tablename = "*,f01_zczzwmc as text,puk as id FROM mdg_zczxx";
			break;
		default:
			break;
		}

		return tablename;
	}
}
