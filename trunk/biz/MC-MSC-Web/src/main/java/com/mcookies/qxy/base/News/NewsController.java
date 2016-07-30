package com.mcookies.qxy.base.News;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 新闻表*/
public class NewsController extends MyControllerSupport {
    @Resource
    protected NewsService NewsService_;

}
