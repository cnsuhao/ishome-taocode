package org.isotope.jfp.mcws.client.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.isotope.jfp.dwc.config.JobConfig;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.HttpRequestHelper;
import org.isotope.jfp.mcws.captcha.impl.CaptchaCodeImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 抓取验证码
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class CaptchaCodeControl {
	@Resource
	protected JobConfig config;
	@Resource
	protected CaptchaCodeImpl CaptchaCode_;

	/**
	 * 默认页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/C310", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean index() throws Exception {
		RESTResultBean rrb = new RESTResultBean();
		rrb.setMessage("抓取验证码服务接口");
		return rrb;
	}

	/**
	 * 网页提交（验证码图片）
	 * 
	 * @param jobid
	 * @param codes
	 * @return
	 */
	@RequestMapping(value = "/C310/010", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean addCodeInJobList(HttpServletRequest request, @RequestParam String jobid, @RequestParam MultipartFile img) {
		RESTResultBean rrb = new RESTResultBean();
		if (!img.isEmpty()) {
			try {
				// 文件存储
				String path = config.getFileSavePath() + DateHelper.currentDate3() + "/";
				String realDirPath = request.getServletContext().getRealPath(path);
				File f = new File(realDirPath);
				// 创建目录
				if (f.exists() == false)
					f.mkdirs();
				// 保存文件
				InputStream stream = img.getInputStream();
				String fileName = jobid + ".jpg";
				FileOutputStream fs = new FileOutputStream(realDirPath + "/" + fileName);
				byte[] buffer = new byte[1024 * 1024];
				int byteread = 0;
				while ((byteread = stream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
					fs.flush();
				}
				fs.close();
				stream.close();
				// 设定返回值
				rrb.setMessage("OK");
				// 缓存到队列
				CaptchaCode_.addCodeInJobList(jobid, path + fileName);
			} catch (Exception e) {
				e.printStackTrace();
				rrb.setMessage("FAIL");
			}
		} else {
			rrb.setMessage("FAIL");
		}
		return rrb;
	}

	/**
	 * 提交验证码
	 * 
	 * @param request
	 * @param jobid
	 * @param img
	 * @return
	 */
	@RequestMapping(value = "/C310/011", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean addCodeInJobList2(HttpServletRequest request, @RequestParam String jobid, @RequestParam String img) {
		RESTResultBean rrb = new RESTResultBean();
		if (EmptyHelper.isNotEmpty(img)) {
			try {
				// 文件存储
				String path = config.getFileSavePath() + DateHelper.currentDate3() + "/";
				String realDirPath = request.getServletContext().getRealPath(path);
				File f = new File(realDirPath);
				// 创建目录
				if (f.exists() == false)
					f.mkdirs();
				// 保存文件
				String fileName = jobid + ".jpg";
				FileOutputStream fs = new FileOutputStream(realDirPath + "/" + fileName);
				fs.write(Base64.decodeBase64(img));
				fs.close();
				// 设定返回值
				rrb.setMessage("OK");
				// 缓存到队列
				CaptchaCode_.addCodeInJobList(jobid, path + fileName);
			} catch (Exception e) {
				e.printStackTrace();
				rrb.setMessage("FAIL");
			}
		} else {
			rrb.setMessage("EMPTY");
		}
		return rrb;
	}

	/**
	 * 客户端回传结果
	 * 
	 * @param request
	 * @param jobid
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/C310/020", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean getRestltWithJob(HttpServletRequest request, String jobid) {
		RESTResultBean rrb = new RESTResultBean();
		rrb.setResult(CaptchaCode_.getRestltWithJob(jobid));
		return rrb;
	}

	/**
	 * 客户端回传结果
	 * 
	 * @param request
	 * @param jobid
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/C310/030", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean putRestltWithJob(HttpServletRequest request, String jobid, String result) {
		RESTResultBean rrb = new RESTResultBean();
		CaptchaCode_.putRestltWithJob(jobid, result);
		rrb.setMessage("OK");
		return rrb;
	}

	/**
	 * 锁定一个任务
	 * 
	 * @param request
	 * @param jobid
	 * @return
	 */
	@RequestMapping(value = "/C310/040", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean lockJobOnList(HttpServletRequest request, String jobid) {
		RESTResultBean rrb = new RESTResultBean();
		CaptchaCode_.lockJobOnList(jobid, HttpRequestHelper.getIpAddr(request));
		rrb.setMessage("OK");
		return rrb;
	}

	/**
	 * 获得一个待打码的任务
	 * 
	 * @return
	 */
	@RequestMapping(value = "/C310/050", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean loadJobWithList() {
		RESTResultBean rrb = new RESTResultBean();
		rrb.setResult(CaptchaCode_.loadJobWithList());
		return rrb;
	}

}
