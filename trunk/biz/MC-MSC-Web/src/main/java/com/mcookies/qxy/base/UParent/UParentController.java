package com.mcookies.qxy.base.UParent;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 家长表*/
public class UParentController extends MyControllerSupport {

	@Resource
    protected UParentService UParentService_;


}
