package com.mcookies.qxy.biz.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mcookies.qxy.common.DeviceAlarm.DeviceAlarmPVO;
import com.mcookies.qxy.common.DeviceAlarm.DeviceAlarmService;
import com.mcookies.qxy.common.DeviceTag.DeviceTagDBO;
import com.mcookies.qxy.common.DeviceTag.DeviceTagPVO;
import com.mcookies.qxy.common.DeviceTag.DeviceTagService;

@Controller
/** 设备管理*/
public class DeviceManageController extends MyControllerSupport {
	@Resource
	protected DeviceTagService DeviceTagService_;
	@Resource
	protected DeviceAlarmService DeviceAlarmService_;

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
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("sid", sid);
			List<DeviceTagPVO> relist = DeviceTagService_.doSelectDevice(param);
			if(relist!=null){
				JSONObject json = new JSONObject();
				json.put("count", relist.size());
				JSONArray array = new JSONArray();
				for(DeviceTagPVO pvo:relist){
					JSONObject device = new JSONObject();
					device.put("device_id", pvo.getDeviceId()+"");
					device.put("device_name", pvo.getDeviceName());
					device.put("school_name", pvo.getSchoolName());
					device.put("username", pvo.getUsername());
					device.put("password", pvo.getPassword());
					array.add(device);
				}
				json.put("device", array);
				result.setData(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
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
			List<DeviceTagDBO> relist = (List<DeviceTagDBO>) DeviceTagService_.doSelectData(devicetag);
			if (relist != null && relist.size() > 0) {
				result.setInfo("新增失败，该设备已存在");
				result.setStatus(2);
			} else {
				// 新增
				String currentTime = DateHelper.currentTimeMillis2();
				device.setIsUse(0);
				device.setLastLoginTime(currentTime);
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
			String currentTime = DateHelper.currentTimeMillis2();
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
	
	/**
	 * 设备报警查询接口
	 * @param sid
	 * @param token
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/devicealarm", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean devicealarmGET(String deviceId, String token,Integer page,Integer size) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(size==null||size==0){
				size = 12;
			}
			if(page==null||page==0){
				page = 1;
				size = 99999;
			}
			//获取设备信息
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("deviceId", deviceId);
			List<DeviceTagPVO> relist = DeviceTagService_.doSelectDevice(param);
			if(relist!=null&&relist.size()>0)
			{
				DeviceTagPVO device = relist.get(0);
				JSONObject json = new JSONObject();
				json.put("device_id", device.getDeviceId()+"");
				json.put("device_name", device.getDeviceName());
				json.put("school_name", device.getSchoolName());
				json.put("username", device.getUsername());
				json.put("password", device.getPassword());
				//分页查询告警列表
				DeviceAlarmPVO alarm = new DeviceAlarmPVO();
				alarm.setDeviceId(device.getDeviceId());
				pageModel.setPageCurrent(page);
				pageModel.setPageLimit(size);
				pageModel.setFormParamBean(alarm);
				DeviceAlarmService_.doSelectPageDeviceAlarm(pageModel);
				List<DeviceAlarmPVO> alarmlist = (List<DeviceAlarmPVO>)pageModel.getPageListData();
				json.put("page", page);
				json.put("count", pageModel.getResultCount());
				JSONArray array = new JSONArray();
				if(alarmlist!=null){
					for(DeviceAlarmPVO alarmpvo:alarmlist){
						JSONObject alarmjson = new JSONObject();
						alarmjson.put("id", alarmpvo.getId()+"");
						alarmjson.put("send_time", alarmpvo.getSendTime().getTime()+"");
						alarmjson.put("alarm_information", alarmpvo.getAlarmInformation());
						alarmjson.put("alarm_time", alarmpvo.getAlarmTime());
						array.add(alarmjson);
					}
				}
				json.put("devicealarm", array);
				result.setData(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
}
