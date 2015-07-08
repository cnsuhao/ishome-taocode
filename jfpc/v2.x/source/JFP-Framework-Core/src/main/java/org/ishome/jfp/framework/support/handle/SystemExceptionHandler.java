package org.ishome.jfp.framework.support.handle;

import org.ishome.jfp.framework.support.SuperControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;


/**
 * 系统异常拦截器
 * 
 * @author Spook
 * 
 */
//@ControllerAdvice
public class SystemExceptionHandler {
	protected static final Logger logger = LoggerFactory.getLogger(SuperControllerSupport.class);

//	/**
//	 * 异常页面控制
//	 * 
//	 * @param runtimeException
//	 * @return
//	 */
//	@ExceptionHandler(RuntimeException.class)
//	@ResponseStatus(HttpStatus.UNAUTHORIZED)//返回一个指定的http response状态码。 
//	@ResponseBody
//	public Map<String, Object> runtimeExceptionHandler(Exception exception,HttpServletRequest request, HttpServletResponse response) {
//		logger.error(runtimeException.getLocalizedMessage());
//
//		Map model = new TreeMap();
//		model.put("status", false);
//		return model;
//	}

//	/**
//	 * 异常页面控制
//	 * 
//	 * @param runtimeException
//	 * @return
//	 */
//	@ExceptionHandler(RuntimeException.class)
//	public String runtimeExceptionHandler(RuntimeException runtimeException, ModelMap modelMap) {
//		logger.error(runtimeException.getLocalizedMessage());
//
//		modelMap.put("status", false);
//		return "exception";
//	}

}
