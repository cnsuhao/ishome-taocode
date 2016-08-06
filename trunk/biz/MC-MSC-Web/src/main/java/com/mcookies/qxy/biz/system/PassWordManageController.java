package com.mcookies.qxy.biz.system;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.security.value.MD5SecurityHelper;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.User.UserDBO;
import com.mcookies.qxy.common.User.UserService;

@Controller
/** 密码管理*/
public class PassWordManageController extends MyControllerSupport {
	@Resource
	protected UserService UserService_;

	/**
	 * 密码修改提交接口
	 * 
	 * @param sid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/uppassword", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean uppasswrodPUT(@RequestBody String jsonparam) {
		RESTResultBean result = new RESTResultBean();
		try {
			JSONObject param = JSONObject.parseObject(jsonparam);
			String token = (String) param.get("token");
			if (doCheckToken(token) == false) {
				return tokenFail();
			}
			String userId = getLoginer().getUserId();
			String oldpwd = param.getString("oldpassword");
			String newpwd = param.getString("newpassword");
			if(oldpwd.equals(newpwd)){
				result.setInfo("修改密码失败，新密码与旧密码一致");
				result.setStatus(3);
				return result;
			}
			//验证旧密码
			UserDBO user = new UserDBO();
			user.setUid(Long.valueOf(userId));
			user.setPassword(MD5SecurityHelper.encrypt(oldpwd));
			List<UserDBO> relist = (List<UserDBO>)UserService_.doSelectData(user);
			if(relist!=null&&relist.size()>0){
				user.setPassword(MD5SecurityHelper.encrypt(newpwd));
				UserService_.doUpdate(user);
			}else{
				result.setInfo("修改密码失败，旧密码不正确");
				result.setStatus(2);
				return result;
			}
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

}
