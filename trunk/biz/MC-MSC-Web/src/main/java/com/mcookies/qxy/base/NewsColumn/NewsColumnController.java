package com.mcookies.qxy.base.NewsColumn;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 栏目表*/
public class NewsColumnController extends MyControllerSupport {
    @Resource
    protected NewsColumnService NewsColumnService_;

}
