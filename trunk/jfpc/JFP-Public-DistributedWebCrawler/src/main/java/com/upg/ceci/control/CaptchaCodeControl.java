package com.upg.ceci.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.dwc.biz.JobConfig;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.HttpRequestHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.upg.ceci.biz.captcha.CaptchaCodeImpl;

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
				String realDirPath= request.getServletContext().getRealPath(path);
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

	@RequestMapping(value = "/C310/011", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean addCodeInJobList2(HttpServletRequest request, @RequestParam String jobid, @RequestParam byte[] img) {
		RESTResultBean rrb = new RESTResultBean();
		if (img != null && img.length > 0) {
			try {
				// 文件存储
				String path = config.getFileSavePath() + DateHelper.currentDate3() + "/";
				File f = new File(path);
				// 创建目录
				if (f.exists() == false)
					f.mkdirs();
				// 保存文件
				String fileName = jobid + ".jpg";
				FileOutputStream fs = new FileOutputStream(path + fileName);
				fs.write(img);
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
			rrb.setMessage("FAIL");
		}
		return rrb;
	}

	@RequestMapping(value = "/C310/020", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean getRestltWithJob(HttpServletRequest request, String jobid, String result) {
		RESTResultBean rrb = new RESTResultBean();
		rrb.setResult(CaptchaCode_.getRestltWithJob(jobid));
		return rrb;
	}

	@RequestMapping(value = "/C310/030", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean putRestltWithJob(HttpServletRequest request, String jobid, String result) {
		RESTResultBean rrb = new RESTResultBean();
		CaptchaCode_.putRestltWithJob(jobid, result);
		rrb.setMessage("OK");
		return rrb;
	}

	@RequestMapping(value = "/C310/040", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean lockJobOnList(HttpServletRequest request, String jobid) {
		RESTResultBean rrb = new RESTResultBean();
		CaptchaCode_.lockJobOnList(jobid, HttpRequestHelper.getIpAddr(request));
		rrb.setMessage("OK");
		return rrb;
	}

	@RequestMapping(value = "/C310/050", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean loadJobWithList() {
		RESTResultBean rrb = new RESTResultBean();
		rrb.setResult(CaptchaCode_.loadJobWithList());
		return rrb;
	}

}
