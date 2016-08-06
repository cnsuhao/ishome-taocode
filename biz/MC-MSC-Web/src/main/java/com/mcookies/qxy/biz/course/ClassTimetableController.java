package com.mcookies.qxy.biz.course;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.User.UserService;
/**
 * 班级课表查询
 * @author macBookTang
 *
 */
@Controller
public class ClassTimetableController extends MyControllerSupport {
	@Resource
	protected UserService UserService_;


}
