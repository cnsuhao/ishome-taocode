package org.isotope.jfp.common.file;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyContentTypeSupport;
import org.isotope.jfp.framework.utils.FTPUtil;
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
 * @version 0.2 2014/11/3
 * @version 0.1 2014/10/8
 * @since 0.1.0
 */
@Controller
public class UploadApi extends MyContentTypeSupport {
	protected static final Logger logger = LoggerFactory.getLogger(UploadApi.class);

	@Resource
	FTPUtil FTPUtil_;

	/**
	 * 文件上传
	 * 
	 * @param productId
	 * @param markType MARK_NONE：0（无水印），MARK_IMAGE：1（图片水印），MARK_TEXT：2（文字水印）
	 * @return
	 * @throws Exception  
	 */
	@RequestMapping(value = "/00003010/{token}/{markType}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m00003010POST(
			@PathVariable String token,
			@RequestParam MultipartFile file,
			@PathVariable int markType) throws Exception {
		logger.debug(file.getOriginalFilename());
		RESTResultBean tb = new RESTResultBean();
		//file.transferTo(new File(file.getOriginalFilename()));
//		String[] filePath = FTPUtil_.uploadFile(file,markType);
//		if (filePath.length<2||StringUtils.isEmpty(filePath[0])) {
//			tb.setCode("1");
//		}else{
//			tb.setResult(filePath[0]);
//			tb.setMessage(filePath[3]);
//		}
//		logger.debug(tb.toString());
		return tb;
	}
	
	/**
	 * 文件上传
	 * 
	 * @param productId
	 * @return
	 * @throws Exception  
	 */
	@RequestMapping(value = "/00003010/{token}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m00003010MarkPOST(@PathVariable String token, @RequestParam MultipartFile file) throws Exception {
		logger.debug(file.getOriginalFilename());
		RESTResultBean tb = new RESTResultBean();
//		//file.transferTo(new File(file.getOriginalFilename()));
//		String[] filePath = FTPUtil_.uploadFile(file,FTPUtil.MARK_IMAGE);
//		if (filePath.length<2||StringUtils.isEmpty(filePath[0])) {
//			tb.setCode("1");
//		}else{
//			tb.setResult(filePath[0]);
//			tb.setMessage(filePath[3]);
//		}
//		logger.debug(tb.toString());
		return tb;
	}
	
	/**
	 * 文件上传
	 * 
	 * @param productId
	 * @return
	 * @throws Exception  
	 */
	@RequestMapping(value = "/00003010", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m00003010POST(String token, @RequestParam MultipartFile file) throws Exception {
		logger.debug(file.getOriginalFilename());
		RESTResultBean tb = new RESTResultBean();
		//file.transferTo(new File(file.getOriginalFilename()));
//		String[] filePath = FTPUtil_.uploadFile(file,FTPUtil.MARK_IMAGE);
//		if (filePath.length<2||StringUtils.isEmpty(filePath[0])) {
//			tb.setCode("1");
//		}else{
//			tb.setResult(filePath[0]);
//			tb.setMessage(filePath[3]);
//		}
//		logger.debug(tb.toString());
		return tb;
	}
}
