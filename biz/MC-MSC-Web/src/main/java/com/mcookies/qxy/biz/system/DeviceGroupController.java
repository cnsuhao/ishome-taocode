package com.mcookies.qxy.biz.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.DeviceAlarm.DeviceAlarmDBO;
import com.mcookies.qxy.common.DeviceGroup.DeviceGroupDBO;
import com.mcookies.qxy.common.DeviceGroup.DeviceGroupPVO;
import com.mcookies.qxy.common.DeviceGroup.DeviceGroupService;
import com.mcookies.qxy.common.DeviceTag.DeviceTagDBO;
import com.mcookies.qxy.common.DeviceTag.DeviceTagService;

import com.mcookies.qxy.common.School.SchoolDBO;
import com.mcookies.qxy.common.School.SchoolService;
@Controller
/** 设备分组管理*/
public class DeviceGroupController extends MyControllerSupport{
	@Resource
	protected DeviceGroupService DeviceGroupService;
	@Resource
	protected SchoolService SchoolService;
	@Resource
	protected DeviceTagService DeviceTagService_;
	
	/**
	 * 设备分组查询接口
	 * 
	 * @param sid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/device/group", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dgroupinfoGET(Long sid, String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			DeviceGroupDBO model = new DeviceGroupDBO();
			model.setIsUse(1);
			if(sid !=null && sid>0 ){
				model.setSid(sid);				
			}else{
				sid= getLoginer().getSchoolId();
				model.setSid(sid);
			}
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("sid", sid);
			List<DeviceGroupPVO> relist = (List<DeviceGroupPVO>) DeviceGroupService.doSelectDeviceGroup(param);
			if(relist.size() == 0){
				result.setInfo("你还没给设备进行分组");
				result.setStatus(2);
				return result;
			}
			JSONObject json = new JSONObject();
			SchoolDBO school = new SchoolDBO();
			school.setSid(sid);
			school = (SchoolDBO) SchoolService.doRead(school);
			json.put("sid", sid);
			json.put("schoolName", school.getSchoolName());
			json.put("dgroup", relist);
			result.setData(json);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		return result;
	}
	
	
	
	/**
	 * 设备分组新增接口
	 * 
	 * @RequestBody DgroupDBO dgroup
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/device/group", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dgroupinfoPOST(@RequestBody DeviceGroupDBO dgroup, String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			SchoolDBO school = new SchoolDBO();
			if(dgroup.getSid() !=null && dgroup.getSid()>0 ){
				school.setSid(dgroup.getSid());				
			}
			school = (SchoolDBO) SchoolService.doRead(school);
			if(school == null){
				result.setInfo("添加失败，该学校不存在");
				result.setStatus(2);
				return result;
			}else{
				DeviceGroupDBO test = new DeviceGroupDBO();
				test.setSid(dgroup.getSid());
				test.setDgroupName(dgroup.getDgroupName());
				if(dgroup.getSid() == null || dgroup.getSid() == 0){
					Long sid = getLoginer().getSchoolId();
					dgroup.setSid(sid);
					test.setSid(sid);
				}
				
				List<DeviceGroupDBO> dgroups = (List<DeviceGroupDBO>) DeviceGroupService.doSelectData(test);
				if(dgroups.size() > 0){
					result.setInfo("添加失败，该设备分组已经存在");
					result.setStatus(3);
					return result;
				}
				dgroup.setIsUse(1);
				DeviceGroupService.doInsert(dgroup);
			}		
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		
		return result;
	}
	
	
	/**
	 * 设备分组修改接口
	 * 
	 * @RequestBody DgroupDBO dgroup
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/device/group", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dgroupinfoPUT(@RequestBody DeviceGroupDBO dgroup, String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(dgroup.getDgroupId() == null || dgroup.getDgroupId() == 0){
				throw new IllegalArgumentException("设备分组id不能为空");
			}
			DeviceGroupService.doUpdate(dgroup);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("info", "ok");				
			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		
		return result;
	}
	
	
	/**
	 * 设备分组删除接口
	 * 
	 * @RequestBody DgroupDBO dgroup
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/device/group", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean dgroupinfoDELETE(@RequestBody DeviceGroupDBO dgroup, String token) {
		RESTResultBean result = new RESTResultBean();
		try {
			// token校验
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			if(dgroup.getDgroupId() == null || dgroup.getDgroupId() == 0){
				throw new IllegalArgumentException("设备分组id不能为空");
			}
			DeviceTagDBO  device = new DeviceTagDBO();
			device.setDgroupId(dgroup.getDgroupId());
			List<DeviceTagDBO> dlist = (List<DeviceTagDBO>) DeviceTagService_.doSelectData(device);
			Map<String, Object> data = new HashMap<String, Object>();
			if(dlist.size() == 0){
				dgroup = (DeviceGroupDBO) DeviceGroupService.doRead(dgroup);
				DeviceGroupService.doDelete(dgroup);
				data.put("info", "ok");
			}else{
				result.setStatus(2);
				data.put("info", "该设备分组正在使用，请核对后再操作");
			}

			result.setData(data);
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}
		
		return result;
	}
	
	
	
	
	
	

}
