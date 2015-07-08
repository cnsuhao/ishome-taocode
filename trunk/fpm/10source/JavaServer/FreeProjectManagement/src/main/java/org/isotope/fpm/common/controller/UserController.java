package org.isotope.fpm.common.controller;

import java.util.Locale;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.isotope.fpm.common.bean.FromDateBean;
import org.isotope.fpm.common.bean.RESTResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 默认控制器 <br>
 * 访问路径：/工程名/业务ID/登录者识别ID/操作编码
 * 
 * @author Spook
 * @since 0.1
 * @version 0.1
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	protected SqlSession mySqlSession;
	/**
	 * 非法访问返回
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean login(FromDateBean fdb, Locale locale, Model model) throws Exception {
		logger.info("FromDateBean===>>>" + fdb);
		//TokenUtil
		
		
		RESTResultBean rrb = new RESTResultBean();
		
		return rrb;
	}
}
