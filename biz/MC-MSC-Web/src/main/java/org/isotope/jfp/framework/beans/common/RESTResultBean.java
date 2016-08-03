package org.isotope.jfp.framework.beans.common;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.isotope.jfp.framework.beans.ObjectBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 接口数据返回主体
 * 
 * @author Spook
 * @version 0.1
 * @since 0.2.0.0
 */
@Named
public class RESTResultBean extends TokenBean implements ISFrameworkConstants {

	/**
	 * 返回结果(0成功1失败) 11->token is invalid 12->token is timeout 13->Illegal
	 * interface calls, not power use interface
	 */
	protected int status = 0;

	/**
	 * 提示信息
	 */
	protected String info = "OK";// 对接返回信息 空:正确 其他：对应对接方错误描述

	/**
	 * 返回结果
	 */
	protected Object data = EMPTY;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// RESTResultBean深层对象解析示例
		// 定义个深层对象
		RESTResultBean rs = new RESTResultBean();
		List<RESTResultBean> ls = new ArrayList<RESTResultBean>();
		ls.add(new RESTResultBean());
		rs.setData(ls);
		// rs.setResult(new RESTResultBean());
		// 转换成文本
		String json = JSONObject.toJSONString(rs);
		// 开始解析
		JSONObject jsons = JSONObject.parseObject(json);
		{
			// 直接获得深层对象List
			JSONArray ja = (JSONArray) jsons.get("result");
			// System.out.println(JSONArray.parseArray(JSONArray.toJSONString(rs.getResult()),
			// RESTResultBean.class));
			// 直接解析数据项目得到一个数据
			// System.out.println(JSONObject.toJavaObject((JSONObject)
			// ja.get(0), RESTResultBean.class));
		}
	}
}
