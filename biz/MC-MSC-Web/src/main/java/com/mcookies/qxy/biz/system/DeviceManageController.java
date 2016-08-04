package com.mcookies.qxy.biz.system;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.DeviceTag.DeviceTagDBO;
import com.mcookies.qxy.common.DeviceTag.DeviceTagPVO;
import com.mcookies.qxy.common.DeviceTag.DeviceTagService;

@Controller
/** 设备管理*/
public class DeviceManageController extends MyControllerSupport {
	@Resource
	protected DeviceTagService DeviceTagService_;

	/**
	 * 设备查询接口
	 * 
	 * @param sid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/deviceinfo", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean deviceinfoGET(String sid, String token) {
		RESTResultBean result = new RESTResultBean();
		// 权限校验
		DeviceTagDBO device = new DeviceTagDBO();
/*		pageModel.setFormParamBean(device);
		pageModel.setPageCurrent(1);
		pageModel.setPageLimit(20);
		DeviceTagService_.doSelectPage(pageModel);
		List<DeviceTagDBO> xx = (List<DeviceTagDBO>)pageModel.getPageListData();*/
		return result;
	}

	/**
	 * 设备新增接口
	 * 
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "/deviceinfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean deviceinfoPOST(@RequestBody DeviceTagPVO device) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(device.getToken()) == false) {
				return tokenFail();
			}
			// 校验是否已经存在
			DeviceTagDBO devicetag = new DeviceTagDBO();
			devicetag.setDeviceId(device.getDeviceId());
			devicetag.setSid(device.getSid());
			List<DeviceTagDBO> relist = (List<DeviceTagDBO>) DeviceTagService_.doSelectData(devicetag);
			if (relist != null && relist.size() > 0) {
				result.setInfo("新增失败，该设备已存在");
				result.setStatus(2);
			} else {
				// 新增
				String currentTime = DateHelper.currentTimeMillisCN1();
				device.setIsUse(0);
				device.setCreateTime(currentTime);
				device.setLastLoginTime(currentTime);
				device.setUpdateTime(currentTime);
				device.setCreator(Long.parseLong(super.getLoginer().getUserId()));
				device.setUpdator(Long.parseLong(super.getLoginer().getUserId()));
				DeviceTagService_.doInsert(device);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 设备修改接口
	 * 
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "/deviceinfo", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean deviceinfoPUT(@RequestBody DeviceTagPVO device) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(device.getToken()) == false) {
				return tokenFail();
			}
			// 校验是否已经存在
			DeviceTagDBO devicetag = new DeviceTagDBO();
			devicetag.setDeviceId(device.getDeviceId());
			devicetag.setSid(device.getSid());
			List<DeviceTagDBO> relist = (List<DeviceTagDBO>) DeviceTagService_.doSelectData(devicetag);
			if (relist != null && relist.size() > 0) {
				for (DeviceTagDBO temp : relist) {
					if (temp.getId() != device.getId()) {
						result.setInfo("更新失败，该设备已存在");
						result.setStatus(2);
						return result;
					}
				}
			}
			// 修改
			String currentTime = DateHelper.currentTimeMillisCN1();
			device.setUpdateTime(currentTime);
			device.setUpdator(Long.parseLong(super.getLoginer().getUserId()));
			DeviceTagService_.doUpdate(device);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

	/**
	 * 设备删除接口
	 * 
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "/deviceinfo", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean deviceinfoDELETE(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		JSONObject param = JSONObject.parseObject(jsonparam);
		String token = (String)param.get("token");
		
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			JSONArray devices = param.getJSONArray("device");
			for(Object device:devices){
				JSONObject temp = (JSONObject)device;
				//乐观锁操作
				DeviceTagDBO dbo = new DeviceTagDBO();
				dbo.setId(temp.getLong("id"));
				dbo = (DeviceTagDBO)DeviceTagService_.doRead(dbo);
				int flag = DeviceTagService_.doDelete(dbo);
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}

}
