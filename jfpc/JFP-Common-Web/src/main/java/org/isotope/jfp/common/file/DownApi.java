package org.isotope.jfp.common.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.isotope.jfp.framework.support.MyContentTypeSupport;
import org.isotope.jfp.framework.utils.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//在jsp页面中加入:
//<%out.clear(); 
//out = pageContext.pushBody(); %>
//即可

/**
 * 文件下载
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/6/10
 */
@Controller
public class DownApi extends MyContentTypeSupport {
//	protected static final Logger logger = LoggerFactory.getLogger(DownApi.class);
//
//	// 在jsp页面中加入:
//	// <%out.clear();
//	// out = pageContext.pushBody(); %>
//	// 即可
//	/**
//	 * 文件下载
//	 * 
//	 * @param request
//	 * @param response
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/00003020/{id}", method = RequestMethod.GET)
//	public void m00003020GET(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doDown(id, request, response);
//	}
//
//	/**
//	 * 文件下载
//	 * 
//	 * @param request
//	 * @param response
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/00003020/{id}/{token}", method = RequestMethod.GET)
//	public void m00003020GETToken(@PathVariable String id, @PathVariable String token, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO TOKEN CHECK
//
//		doDown(id, request, response);
//	}
//
//	@Resource
//	FTPUtil FTPUtil_;
//
//	/**
//	 * 文件下载
//	 * 
//	 * @param id
//	 * @param request
//	 * @param response
//	 */
//	public void doDown(String id, HttpServletRequest request, HttpServletResponse response) {
//		try {
//			getResponseContextDownType(response);
//			OutputStream out = response.getOutputStream();
//			FileInputStream in = new FileInputStream(FTPUtil_.getFileUri(id));
//			byte[] buffer = new byte[1024];
//			int n = 0;
//			while ((n = in.read(buffer)) != -1) {
//				out.write(buffer, 0, n);
//			}
//			out.flush();
//			out.close();
//			in.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
