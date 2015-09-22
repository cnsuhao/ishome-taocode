package org.jfpc.common.dict;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jfpc.beans.common.MS0A1.MS0A1Service;
import org.jfpc.beans.common.MS0M1.MS0M1DBO;
import org.jfpc.constants.ISFrameworkConstants;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.helper.EmptyHelper;

/**
 * 字典数据持久层
 * 
 * @author Spook
 * @since 0.2.1
 * @version 0.2.1 2014/12/1
 */
public class DictionaryService implements DictionaryConstants, ISFrameworkConstants {

	@Resource
	MS0A1Service MS0A1Service_;

	public void loadDictionary() {
		MS0M1DBO dictionaryDBO = new MS0M1DBO();
		dictionaryDBO.setDdd(ZERO);
		List<FrameworkDataBean> dictionaryaList = MS0A1Service_.doSelectPage(dictionaryDBO, false);
		for(FrameworkDataBean fdb :dictionaryaList){
			if(DICT_COUNTRY.equals(fdb.getGgg()))
				countryList.add((MS0M1DBO) fdb);
			else if(DICT_PROVINCE.equals(fdb.getGgg()))
				cityList.add((MS0M1DBO) fdb);
			else if(DICT_CITY.equals(fdb.getGgg()))
				cityList.add((MS0M1DBO) fdb);
			else if(DICT_AREA.equals(fdb.getGgg()))
				cityList.add((MS0M1DBO) fdb);
			else// if("".equals(fdb.getGgg()))
				dictionaryList.add((MS0M1DBO) fdb);
		}		
	}
	private List<MS0M1DBO> countryList;//字典数据
	public void getCountry(String value, StringBuilder sb, boolean b) {
		// <option value="">一次性耗材用品</option>
		for (MS0M1DBO md : countryList) {
			sb.append("<option");
			sb.append(" value=\"").append(md.getPuk()).append("\"");
			if(value.equals(md.getPuk()))
				sb.append(" selected");
			sb.append(">");
			sb.append(md.getF01_zdmc());
			sb.append("</option>");
		}
	}
	///////////////////////////////////////////////////////
	private List<MS0M1DBO> provinceList;//字典数据
	private List<MS0M1DBO> cityList;//字典数据
	private List<MS0M1DBO> areaList;//字典数据
	public void getProvince(
			String province, String provinceId, 
			StringBuilder sb) {
		// <option value="">一次性耗材用品</option>
		for (MS0M1DBO md : provinceList) {
			sb.append("<option");
			sb.append(" value=\"").append(md.getPuk()).append("\"");
			if(province.equals(md.getPuk()))
				sb.append(" selected");
			sb.append(">");
			sb.append(md.getF01_zdmc());
			sb.append("</option>");
		}
	}
	public void getCity(
			String province, String provinceId, 
			String city, String cityId, 
			StringBuilder sb) {
		// <option value="">一次性耗材用品</option>
		for (MS0M1DBO md : cityList) {
			if(province.equals(md.getPuk())){
				sb.append("<option");
				sb.append(" value=\"").append(md.getPuk()).append("\"");
				if(city.equals(md.getPuk()))
					sb.append(" selected");
				sb.append(">");
				sb.append(md.getF01_zdmc());
				sb.append("</option>");
			}
		}
	}
	public void getArea(
			String province, String provinceId, 
			String city, String cityId, 
			String area, String areaId, 
			StringBuilder sb) {
		// <option value="">一次性耗材用品</option>
		for (MS0M1DBO md : areaList) {
			if(province.equals(md.getPuk())&&city.equals(md.getPuk())){
				sb.append("<option");
				sb.append(" value=\"").append(md.getPuk()).append("\"");
				if(area.equals(md.getPuk()))
					sb.append(" selected");
				sb.append(">");
				sb.append(md.getF01_zdmc());
				sb.append("</option>");
			}
		}
	}
/////////////////////////////////////////////////////////////////
	private List<MS0M1DBO> dictionaryList;//字典数据
	/**
	 * 根据字典分类标识获得数据字典内容
	 * @param dictTypeId
	 */
	public List<MS0M1DBO> getDictionary(
			String dictTypeId,
			String value) {
		return getDictionary(dictTypeId, EMPTY, value);
	}
	
	public List<MS0M1DBO> getDictionary(
			String dictTypeId, 
			String bigTypeId,
			String value) {
		return getDictionary(dictTypeId, bigTypeId, EMPTY, value);
	}

	public List<MS0M1DBO> getDictionary(
			String dictTypeId,
			String bigTypeId, 
			String middleTypeId,
			String value) {
		return getDictionary(dictTypeId, bigTypeId, middleTypeId, EMPTY, value);

	}

	public List<MS0M1DBO> getDictionary(
			String dictTypeId,
			String bigTypeId, 
			String middleTypeId, 
			String smallTypeId,
			String value) {
		return getDictionary(dictTypeId, bigTypeId, middleTypeId, smallTypeId, value, null, false);
	}

	/**
	 * 获得数据字典
	 * @param dictTypeId 字典分类标识
	 * @param bigTypeId 大分类
	 * @param middleTypeId 中分类
	 * @param smallTypeId 小分类
	 * @param sb
	 * @param html
	 * @return
	 */
	public List<MS0M1DBO> getDictionary(
			String dictTypeId, 
			String bigTypeId, 
			String middleTypeId, 
			String smallTypeId, 
			String value, 
			StringBuilder sb, 
			boolean html) {
		List<MS0M1DBO> rs = new ArrayList<MS0M1DBO>();
		if (EmptyHelper.isEmpty(bigTypeId)) {
			return rs;
		}
		if (dictionaryList == null || dictionaryList.size() == 0)
			loadDictionary();
		boolean add = false;
		for (MS0M1DBO md : dictionaryList) {
			if (bigTypeId.equals(md.getK01_dflid())) {
				if (EmptyHelper.isEmpty(middleTypeId)) {
					add = true;
				} else if (middleTypeId.equals(md.getK02_zflid())) {
					if (EmptyHelper.isEmpty(smallTypeId)) {
						add = true;
					} else if (smallTypeId.equals(md.getK03_xflid())) {
						add = true;
					} else {
						add = false;
					}
				} else {
					add = false;
				}
				// 判断是否为目标数据
				if (add) {
					// 创建HTML内容
					if (html) {
						// <option value="">一次性耗材用品</option>
						sb.append("<option");
						sb.append(" value=\"").append(md.getPuk()).append("\"");
						if(value.equals(md.getPuk()))
							sb.append(" selected");
						sb.append(">");
						sb.append(md.getF01_zdmc());
						sb.append("</option>");
					}
					rs.add(md);
				}
			}
		}

		return rs;
	}

	/**
	 * 获得字典名称
	 */
	public String getDictionaryName(String dbPuk) {
		if (EmptyHelper.isEmpty(dbPuk)) {
			return EMPTY;
		}
		MS0M1DBO md;
		for (FrameworkDataBean db : dictionaryList) {
			md = (MS0M1DBO) db;
			if (dbPuk.equals(md.getPuk())) {
				return md.getF01_zdmc();
			}
		}
		return EMPTY;
	}
}
