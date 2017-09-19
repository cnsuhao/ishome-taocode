package org.isotope.jfp.framework.beans.common;

import java.util.ArrayList;
import java.util.List;

import org.isotope.jfp.framework.beans.ObjectBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.PKHelper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 接口数据返回主体
 * 
 * @author Spook
 * @version 0.1
 * @since 0.2.0.0
 */

public class RESTResultBean extends ObjectBean implements ISFrameworkConstants {

	protected String token = null;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	protected String jobId = null;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * 返回结果(0成功1失败)
	 */
	protected String code = ZERO;// 对接返回代码 -1:无数据 0:正确 其他：对应对接方错误码

	/**
	 * 提示信息
	 */
	protected String message = MESSAGE_PROC_WAITING;// 对接返回信息 空:正确 其他：对应对接方错误描述

	/**
	 * 返回结果
	 */
	protected Object data = null;

	public String getCode() {
		return code;
	}

	public void setCode(String resultCode) {
		this.code = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String resultMsg) {
		this.message = resultMsg;
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
		{
			RESTResultBean result = new RESTResultBean();
			result.setJobId(PKHelper.creatPUKey());
			result.setCode(SYSTEM_TOKEN_ERROR_CODE);
			result.setMessage(MESSAGE_TOKEN_ERROR);// 登录失效，请重新登录
			// 转换成文本
			String json = JSONObject.toJSONString(result);
			System.out.println(json);
		}
		{
			// RESTResultBean深层对象解析示例
			// 定义个深层对象
			RESTResultBean rs = new RESTResultBean();
			List<RESTResultBean> ls = new ArrayList<RESTResultBean>();
			ls.add(new RESTResultBean());
			rs.setData(ls);
			// rs.setResult(new RESTResultBean());
			// 转换成文本
			String json = JSONObject.toJSONString(rs);
			System.out.println(json);
			// 开始解析
			JSONObject jsons = JSONObject.parseObject(json);
			{
				// 直接获得深层对象List
				JSONArray ja = (JSONArray) jsons.get("result");
				//
				System.out.println(JSONArray.parseArray(JSONArray.toJSONString(rs.getData()), RESTResultBean.class));
				// 直接解析数据项目得到一个数据
				System.out.println(JSONObject.toJavaObject((JSONObject) ja.get(0), RESTResultBean.class));
			}
		}
	}
}
