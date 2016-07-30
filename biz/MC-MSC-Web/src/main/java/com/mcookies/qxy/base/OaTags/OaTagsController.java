package com.mcookies.qxy.base.OaTags;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** OA标签表*/
public class OaTagsController extends MyControllerSupport {

	@Resource
    protected OaTagsService OaTagsService_;


}
