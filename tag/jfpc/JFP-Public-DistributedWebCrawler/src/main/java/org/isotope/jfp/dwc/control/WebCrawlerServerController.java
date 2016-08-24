package org.isotope.jfp.dwc.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.dwc.config.JobConfig;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.DateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 网页结果保存服务端 
 * Distributed Web Crawler
 * 
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
public class WebCrawlerServerController {
	public static String WEB_KEY = "HTML_RESULT";
	@Resource
	protected ICacheService mq;
	@Resource
	protected JobConfig config;

	@RequestMapping(value = "/09001000", method = RequestMethod.POST)
	public ModelAndView capUpload(HttpServletRequest request, @RequestParam String fileName, @RequestParam MultipartFile file) {
		ModelAndView model = new ModelAndView("DWC/09001000");
		if (!file.isEmpty()) {
			try {
				// 文件存储
				String path = config.getFileSavePath()+DateHelper.currentDate3()+"/";
				File f = new File(path);
				if(f.exists()==false)
					f.mkdirs();
				SaveFileFromInputStream(file.getInputStream(), path , fileName + ".html");
				model.addObject(WEB_KEY, "OK");
				// Redis存储
				// model.addObject("FILE_VALUE", new String(bytes, "UTF-8"));
			} catch (Exception e) {
				e.printStackTrace();
				model.addObject(WEB_KEY, "FAIL");
			}
		} else {
			model.addObject(WEB_KEY, "FAIL");
		}
		return model;
	}

	public void SaveFileFromInputStream(InputStream stream, String path, String fileName) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + fileName);
		byte[] buffer = new byte[1024 * 1024];
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}

}
