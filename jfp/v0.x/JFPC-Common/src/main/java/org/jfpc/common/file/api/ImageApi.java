package org.jfpc.common.file.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfpc.base.utils.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 图片显示
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/6/10
 * 
 */
@Controller
public class ImageApi {
	protected static final Logger logger = LoggerFactory.getLogger(ImageApi.class);

	@RequestMapping(value = "/00003030/{id}/{token}", method = RequestMethod.GET)
	public void do00003030GetToken(@PathVariable String id,@PathVariable String token, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO TOKEN CHECK
		doImage(id,request, response);
	}
	
	@Resource
	FTPUtil FTPUtil_;
	public void doImage(String id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/*"); // 设置返回的文件类型
		response.setHeader("Content-Disposition", "attachment; filename = 1.jpg");// 保存文件名字
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			OutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(FTPUtil_.getFileUri(id));
			byte[] buffer = new byte[1024];
			int n = 0;
			while ((n = in.read(buffer)) != -1) {
				out.write(buffer, 0, n);
			}
			out.flush();
			out.close();
			in.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
}
