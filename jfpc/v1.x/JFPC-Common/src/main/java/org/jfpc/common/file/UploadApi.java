package org.jfpc.common.file;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.support.MyFrameworkSupport;
import org.jfpc.framework.utils.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/6/10
 */
@Controller
public class UploadApi extends MyFrameworkSupport{
	protected static final Logger logger = LoggerFactory.getLogger(UploadApi.class);
	
	@Resource
	FTPUtil FTPUtil_;
	
	/**
	 * 文件上传
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/00003010/{token}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m00003010POST(@PathVariable String token,@RequestParam MultipartFile file) {
		logger.debug(file.getOriginalFilename());
		RESTResultBean tb = new RESTResultBean();
		tb.setResult(FTPUtil_.uploadFile(file));
		if (StringUtils.isEmpty(tb.getResult().toString())) {
			tb.setCode("1");
		} 
		logger.debug(tb.toString());
		return tb;
	}
}
