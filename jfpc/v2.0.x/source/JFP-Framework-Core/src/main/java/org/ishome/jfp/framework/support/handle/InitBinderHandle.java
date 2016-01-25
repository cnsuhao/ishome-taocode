package org.ishome.jfp.framework.support.handle;

import org.ishome.jfp.framework.support.SuperControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;


//@ControllerAdvice
public class InitBinderHandle {
	protected static final Logger logger = LoggerFactory.getLogger(SuperControllerSupport.class);
	 
//	/**
//	 * 解析request中的参数并通过date bind机制与handler method中的参数做绑定。
//	 * @param binder
//	 */
//	@InitBinder
//	//此处的参数也可以是ServletRequestDataBinder类型
//	public void initBinder(WebDataBinder binder) throws Exception {
//		//注册自定义的属性编辑器
//		//1、日期
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
//		//表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
//		binder.registerCustomEditor(Date.class, dateEditor);	
//		//自定义的电话号码编辑器(和【4.16.1、数据类型转换】一样)
//		binder.registerCustomEditor(PhoneNumberModel.class, new PhoneNumberEditor());
//	}

}
