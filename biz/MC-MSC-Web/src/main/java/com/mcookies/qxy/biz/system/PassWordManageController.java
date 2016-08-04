package com.mcookies.qxy.biz.system;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcookies.qxy.common.User.UserPVO;
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
	@RequestMapping(value = "/uppassword", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean deviceinfoGET(UserPVO user) {
		RESTResultBean result = new RESTResultBean();
		try {
			if (doCheckToken(user.getToken()) == false) {
				return tokenFail();
			}

			String userId = getLoginer().getUserId();

			result.setInfo("欢迎访问千校云平台：" + userId + "," + user.getAccount());
		} catch (Exception e) {
			result.setInfo("访问失败");
			result.setStatus(1);
		}

		return result;
	}

}
