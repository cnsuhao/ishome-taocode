package org.isotope.jfp.common.file.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import org.isotope.jfp.common.file.ImageMarkLogoHelper;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.cache.redis.RedisCacheHelper;
import org.isotope.jfp.framework.support.MyContentTypeSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.FTPUtil;
import org.isotope.jfp.framework.utils.token.UserCacheHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * 文件业务操作
 * 
 * @author 001745
 *
 */
public class FileImageControl extends MyContentTypeSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 默认图片
	 */
	private String defaultPic = "";

	public void setDefaultPic(String defaultPic) {
		this.defaultPic = defaultPic;
	}

	/**
	 * 开启水印
	 */
	private boolean doMark = false;
	/**
	 * 开启压缩
	 */
	private boolean doCompress = false;
	/**
	 * 水印类别
	 */
	private int mark = 0;
	/**
	 * 压缩后的图片尺寸比例
	 */
	public float ratio = 1.0F;
	/**
	 * 百分比
	 */
	public float per = 0.3F;
	/**
	 * 水印图片
	 */
	private Resource markPic = null;

	@Autowired
	FTPUtil FTPUtil_;

	public static int MARK_NONE = 0;
	public static int MARK_IMAGE = 1;
	public static int MARK_TEXT = 2;

	/**
	 * 文件上传
	 * 
	 * @param productId
	 * @param markType
	 *            MARK_NONE：0（无水印），MARK_IMAGE：1（图片水印），MARK_TEXT：2（文字水印）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/file/{token}/{markType}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean m00003010POST(@PathVariable String token, @RequestParam MultipartFile file, @PathVariable int markType,String savingType) throws Exception {
		logger.debug(file.getOriginalFilename());
		RESTResultBean tb = new RESTResultBean();
		InputStream input = file.getInputStream();
		// 压缩
		if (doCompress) {
			// input = ImageCompress.Tosmallerpic(input, ratio, per);
		}
		// 水印
		if (doMark) {
			if (mark == MARK_TEXT) {
				input = ImageMarkLogoHelper.markImageByIcon(input, new ImageIcon(markPic.getURL()));
			} else if (mark == MARK_IMAGE) {
				input = ImageMarkLogoHelper.markImageByIcon(input, new ImageIcon(markPic.getURL()));
			} else {

			}
		}
		String filePath = FTPUtil_.uploadFile(file.getOriginalFilename(), input, savingType);
		if (EmptyHelper.isEmpty(filePath)) {
			tb.setCode("1");
		} else {
			tb.setResult(filePath);
		}
		logger.debug(tb.toString());
		return tb;
	}

	/**
	 * 显示默认图片
	 * 
	 * @param token
	 * @param request
	 * @param response
	 * @param session
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/file/image/default", method = RequestMethod.GET)
	public void defaultImageGET(String token, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {

		getResponseContextImageType(response);
		try {
			OutputStream out = response.getOutputStream();
			FileInputStream in;

			in = new FileInputStream(defaultPic);

			byte[] buffer = new byte[1024];
			int n = 0;
			while ((n = in.read(buffer)) != -1) {
				out.write(buffer, 0, n);
			}
			out.flush();
			out.close();
			in.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	// http://127.0.0.1:8800/00003030/123/123
	/**
	 * 显示图片服务器上一张图片
	 * 
	 * @param id
	 * @param token
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/file/image/show", method = RequestMethod.GET)
	public void imageShowGET(String id, String token, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		if (UserCacheHelper.checkUser(token) == null) {
			OutputStream out = response.getOutputStream();
			out.write("用户登录失败".getBytes("UTF-8"));
			out.flush();
			out.close();
			return;
		}
		try {
			OutputStream out = response.getOutputStream();
			FileInputStream in;
			if (EmptyHelper.isEmpty(id)) {
				getResponseContextImageType(response);
				in = new FileInputStream(defaultPic);
			} else {
				try {
					in = new FileInputStream(FTPUtil_.getFileUri(id));
					getResponseContextType(response, id);
				} catch (Exception e) {
					in = new FileInputStream(defaultPic);
				}
			}
			byte[] buffer = new byte[1024];
			int n = 0;
			while ((n = in.read(buffer)) != -1) {
				out.write(buffer, 0, n);
			}
			out.flush();
			out.close();
			in.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	/**
	 * 显示一张验证码图片
	 * 
	 * @param id
	 * @param token
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/file/image/security", method = RequestMethod.GET)
	public void imageSecurityGET(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {

		getResponseContextImageType(response);

		ServletOutputStream sos = response.getOutputStream();

		int WIDTH = 65;// 设置图片的宽度
		int HEIGHT = 22;// 设置图片的高度
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		char[] rands = generateCheckCode(30, 4, session.getId());
		drawBackground(g, WIDTH, HEIGHT);
		drawRands(g, rands, WIDTH, HEIGHT);
		g.dispose();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length);
		sos.write(buf);
		bos.close();
		sos.close();
	}

	/**
	 * 自定义高度显示一张验证码图片
	 * 
	 * @param width
	 * @param height
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/file/image/security/{width}/{height}", method = RequestMethod.GET)
	public void imageSecurityGET1(@PathVariable String width, @PathVariable String height, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {

		getResponseContextImageType(response);

		ServletOutputStream sos = response.getOutputStream();

		int WIDTH = Integer.parseInt(width);// 设置图片的宽度
		int HEIGHT = Integer.parseInt(height);// 设置图片的高度

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		char[] rands = generateCheckCode(30, 4, session.getId());
		drawBackground(g, WIDTH, HEIGHT);
		drawRands(g, rands, WIDTH, HEIGHT);
		g.dispose();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length);
		sos.write(buf);
		bos.close();
		sos.close();
	}

	/**
	 * 文件上传
	 * 
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/file/uploadPost", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean fileUploadPOST(@RequestParam String token, @RequestParam MultipartFile file,String savingType, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RESTResultBean tb = new RESTResultBean();
		logger.debug(token);
		if (UserCacheHelper.checkUser(token) == null) {
			OutputStream out = response.getOutputStream();
			tb.setMessage("用户登录失败");
			out.write(JSON.toJSONString(tb).getBytes("UTF-8"));
			out.flush();
			out.close();
			return null;
		}
		logger.debug(file.getOriginalFilename());
		// file.transferTo(new File(file.getOriginalFilename()));
		String filePath = FTPUtil_.uploadFile(file,savingType);
		if (EmptyHelper.isEmpty(filePath)) {
			tb.setCode("1");
		} else {
			tb.setResult(filePath);
		}
		logger.debug(tb.toString());
		return tb;
	}		
	/**
	 * 
	 * @param file ftp文件上传。
	 * @param type "0"表示需 要cdn加速,"1表示不需要加速的文件"
	 * @return  /file/upload?type=[type]&token=[token]
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean fileUploadFtp(@RequestParam("file") MultipartFile file,String token, String type, HttpServletRequest request, HttpServletResponse response) throws Exception{
		RESTResultBean tb = new RESTResultBean();
		logger.debug(token);
		if (UserCacheHelper.checkUser(token) == null) {
			OutputStream out = response.getOutputStream();
			tb.setMessage("用户登录失败");
			out.write(JSON.toJSONString(tb).getBytes("UTF-8"));
			out.flush();
			out.close();
			return null;
		}
		logger.debug(file.getOriginalFilename());
		// file.transferTo(new File(file.getOriginalFilename()));
		String filePath = FTPUtil_.uploadFile(file,type);
		if (EmptyHelper.isEmpty(filePath)) {
			tb.setCode("1");
		} else {
			tb.setResult(filePath);
		}
		logger.debug(tb.toString());
		return tb;
	}

	/**
	 * 文件下载
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/file/down", method = RequestMethod.GET)
	public void fileDownGET(String id, String token, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (UserCacheHelper.checkUser(token) == null) {
			OutputStream out = response.getOutputStream();
			out.write("用户登录失败".getBytes("UTF-8"));
			out.flush();
			out.close();
			return;
		}
		doDown(id, request, response);
	}

	/**
	 * 文件下载
	 *
	 * @param id
	 * @param request
	 * @param response
	 */
	private void doDown(String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			getResponseContextDownType(response);
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
			e.printStackTrace();
		}
	}

	private void drawBackground(Graphics g, int width, int height) {
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		for (int i = 0; i < 120; i++) {
			int x = (int) (Math.random() * width);
			int y = (int) (Math.random() * height);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 1, 0);
		}
	}

	private void drawRands(Graphics g, char[] rands, int width, int height) {
		// g.setColor(Color.BLUE);
		Random random = new Random();
		int red = random.nextInt(110);
		int green = random.nextInt(50);
		int blue = random.nextInt(50);
		g.setColor(new Color(red, green, blue));
		int size = (int) (height * 0.7);
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, size));
		g.drawString("" + rands[0], 0 * width / 4 + 1, size);
		g.drawString("" + rands[1], 1 * width / 4 + 1, size - 1);
		g.drawString("" + rands[2], 2 * width / 4 + 1, size);
		g.drawString("" + rands[3], 3 * width / 4 + 1, size + 1);
	}

	/**
	 * 获得图片验证码（大写英字）
	 * 
	 * @param session
	 * @return
	 */
	private char[] generateCheckCode(int second, int length, String jobid) {
		String chars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
		char[] rands = new char[length];
		for (int i = 0; i < length; i++) {
			int rand = (int) (Math.random() * (chars.length() - 1));
			rands[i] = chars.charAt(rand);
		}
		// 缓存
		RedisCacheHelper.setSessionAttribute(1, second, jobid, new String(rands));
		return rands;
	}
}
