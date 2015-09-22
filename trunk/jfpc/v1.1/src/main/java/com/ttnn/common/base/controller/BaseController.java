package com.ttnn.common.base.controller;

import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ttnn.common.base.service.IService;
import com.ttnn.common.base.service.ServiceManager;
import com.ttnn.common.util.DateUtil;
import com.ttnn.common.util.ServerTools;
import com.ttnn.framework.bean.FrameworkDataBean;

/**
 * 默认控制器 <br>
 * 访问路径：/工程名/业务ID/登录者识别ID/操作编码
 * 
 * @author Spook
 * @since 0.1
 * @version 0.1
 */
@Controller
@RequestMapping("/base")
public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 非法访问返回
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@RequestMapping(value = "/{BID}/{UID}/{CID}", method = RequestMethod.GET)
	public String errorController(@PathVariable String BID,// 业务ID
	        @PathVariable String UID,// 登录者识别ID
	        @PathVariable int CID,// 操作编码
	        FrameworkDataBean fdb, Locale locale, Model model) throws Exception {
		logger.error("错误的用户操作：" + UID);

		model.addAttribute("serverTime", DateUtil.currentTimeMillis2());
		return "home";
	}

	@Resource
	protected ServiceManager myController;
	
	protected FrameworkDataBean returnFromDateBean;
	
	/**
	 * 非法访问返回
	 * 
	 * @throws FoundException
	 */
	@RequestMapping(value = "/{BID}/{CID}", method = RequestMethod.POST)
	public String defaultController(
			@PathVariable String BID,// 业务ID
	        @PathVariable String UID,// 登录者识别ID
	        @PathVariable int CID,// 操作编码
	        FrameworkDataBean fdb, Locale locale, Model model) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		// 返回数据
//		FrameworkDataBean returnFromDateBean = new FrameworkDataBean();
		//对象池~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//UID
		
		
		// 定义业务控制器
		IService ic = null;
		{
			//设定用户ID
			fdb.setPuk(UID);
			
		}
		
		// 获得控制器
		{
			ic = myController.getService(BID);
		}
		//处理基本业务逻辑
		{
			switch (CID) {
			case 0:// 一览
				break;
			case 10:// 明细
				break;
			case 20:// 新建
				break;
			case 30:// 插入
				returnFromDateBean = ic.batchInsert(fdb);
				break;
			case 40:// 修改
				break;
			case 50:// 删除
				break;
			case 60:// 清空
				returnFromDateBean = ic.batchDelete(fdb);
				break;
			default:// 其他操作99
				returnFromDateBean = ic.doProcess(fdb);
				break;
			}
		}
		// 打印日志并设定返回
		logger.debug(returnFromDateBean.toString());
		ServerTools.putData(returnFromDateBean, model);
		// 系统时间
		model.addAttribute("serverTime", DateUtil.currentTimeMillis2());
		return "home";
	}

	/**
	 * 非法访问返回
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is " + locale.toString());

		model.addAttribute("serverTime", DateUtil.currentTimeMillis2());
		return "home";
	}

}
