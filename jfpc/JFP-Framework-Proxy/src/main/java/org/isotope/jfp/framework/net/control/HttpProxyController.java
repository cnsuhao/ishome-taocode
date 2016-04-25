package org.isotope.jfp.framework.net.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.net.HttpProxyBean;
import org.isotope.jfp.framework.net.proxy.HttpProxyServiceImpl;
import org.isotope.jfp.framework.utils.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 网络代理管理
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class HttpProxyController {
	private Logger logger = LoggerFactory.getLogger(HttpProxyController.class);

	@Resource
	HttpProxyServiceImpl HttpProxyServiceImpl_;

	@RequestMapping(value = "/HTP", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView("index");
		model.addObject("DDD", "网络代理管理" + DateHelper.currentTimeMillis2());
		return model;
	}

	@RequestMapping(value = "/HTP/010", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request, @RequestParam String jobid, @RequestParam MultipartFile file) throws Exception {
		ModelAndView model = new ModelAndView("index");
		logger.debug(file.getName());
		long line = 0;
		if (!file.isEmpty()) {
			// 开始整理文件
			BufferedReader reader = null;
			try {
				logger.debug("以行为单位读取文件内容，一次读一整行：");
				reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String proxy = null;
				// 一次读入一行，直到读入null为文件结束
				while ((proxy = reader.readLine()) != null) {
					line++;
					// 显示行号
					logger.debug("line " + line + ": " + proxy);
					HttpProxyServiceImpl_.addHttpProxy(proxy);
				}
				reader.close();
				model.addObject("DDD", "成功读取" + line + "行");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
					}
				}
			}
		} else {
		}
		return model;
	}

	@RequestMapping(value = "/HTP/020", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean get(HttpServletRequest request) throws Exception {
		RESTResultBean rs = new RESTResultBean();
		rs.setResult(HttpProxyServiceImpl_.getHttpProxy());
		return rs;
	}

	@RequestMapping(value = "/HTP/030", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean add(HttpServletRequest request, String proxy) throws Exception {
		RESTResultBean rs = new RESTResultBean();
		HttpProxyServiceImpl_.addHttpProxy(proxy);
		rs.setMessage("OK");
		return rs;
	}

	@RequestMapping(value = "/HTP/040", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean add(HttpServletRequest request, HttpProxyBean proxy) throws Exception {
		RESTResultBean rs = new RESTResultBean();
		HttpProxyServiceImpl_.addHttpProxy(proxy);
		rs.setMessage("OK");
		return rs;
	}

	@RequestMapping(value = "/HTP/050", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean del(HttpServletRequest request, HttpProxyBean proxy) throws Exception {
		RESTResultBean rs = new RESTResultBean();
		rs.setResult(HttpProxyServiceImpl_.getHttpProxy());
		rs.setMessage("OK");
		return rs;
	}
}
