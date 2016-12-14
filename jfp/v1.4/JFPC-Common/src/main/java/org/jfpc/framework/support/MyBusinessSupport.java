package org.jfpc.framework.support;

import javax.annotation.Resource;

import org.jfpc.common.login.LoginService;
import org.jfpc.common.message.MessageModelUtils;
import org.jfpc.framework.page.PageVOSupport;

/**
 * 数据业务操作接口定义超类<br>
 * 定义通用8个操作方法<br>
 * 缓存参考SSM配置
 * @author Spook
 * @since 0.1.0 2013-8-21
 * @version 0.1.0
 * @see ISServiceSupport
 */
public class MyBusinessSupport extends MyFrameworkSupport {

	//用户分页
	@Resource
	protected PageVOSupport pageModel;
	//信息模版
	@Resource
	protected MessageModelUtils messageModel;
	//登录相关
	@Resource
	protected LoginService loginService;
	
	
}
