package org.jfpc.base.file;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jfpc.base.bean.RESTResultBean;
import org.jfpc.base.support.MyFrameworkSupport;
import org.jfpc.base.utils.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 域名访问默认登陆页面
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
@Controller
public class UploadApi extends MyFrameworkSupport{
	private static final Logger logger = LoggerFactory.getLogger(UploadApi.class);
	
	@Resource
	FTPUtil FTPUtil_;
	
	/**
	 * 文件上传
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/888", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m00000000POST(@RequestParam MultipartFile file) {
		logger.debug(file.toString());
		RESTResultBean tb = new RESTResultBean();
		tb.setResult(FTPUtil_.uploadFile(file));
		if (StringUtils.isEmpty(tb.getResult().toString())) {
			tb.setResultcode("1");
		} 
		logger.debug(tb.toString());
		return tb;
	}
}
