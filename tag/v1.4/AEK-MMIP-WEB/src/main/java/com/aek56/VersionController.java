package com.aek56;

import org.jfpc.framework.bean.RESTResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 三证管理
 * @author Spook
 *
 */
@Controller
public class VersionController {
	
	String Version ="v0.0.1 at 2014.8.21";
	
	/**
	 * 默认登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/00000", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean m00000GET() {
		RESTResultBean rs = new RESTResultBean();
		rs.setMessage("三证管理");
		rs.setResult(Version);
		return rs;
	}
	
}
