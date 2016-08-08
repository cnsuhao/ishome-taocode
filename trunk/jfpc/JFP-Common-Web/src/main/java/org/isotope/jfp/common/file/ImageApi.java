package org.isotope.jfp.common.file;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.isotope.jfp.framework.support.MyContentTypeSupport;
import org.isotope.jfp.framework.utils.FTPUtil;
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
public class ImageApi extends MyContentTypeSupport {
	protected static final Logger logger = LoggerFactory.getLogger(ImageApi.class);
//	@Resource
//	FTPUtil FTPUtil_;

	/**
	 * 显示一张图片
	 * 
	 * @param id
	 * @param token
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/00003030/{token}", method = RequestMethod.GET)
	public void do00003030GetToken(@PathVariable String token, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {

//		getResponseContextImageType(response);
//		try {
//			OutputStream out = response.getOutputStream();
//			FileInputStream in;
//
//			in = new FileInputStream(FTPUtil_.getDefaultPic());
//
//			byte[] buffer = new byte[1024];
//			int n = 0;
//			while ((n = in.read(buffer)) != -1) {
//				out.write(buffer, 0, n);
//			}
//			out.flush();
//			out.close();
//			in.close();
//		} catch (Exception e) {
//			// e.printStackTrace();
//		}
	}

	// http://127.0.0.1:8800/00003030/123/123
	/**
	 * 显示一张图片
	 * 
	 * @param id
	 * @param token
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/00003030/{id}/{token}", method = RequestMethod.GET)
	public void do00003030GetToken(@PathVariable String id, @PathVariable String token, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
//		try {
//			OutputStream out = response.getOutputStream();
//			FileInputStream in;
//			if (StringUtils.isEmpty(id)) {
//				getResponseContextImageType(response);
//				in = new FileInputStream(FTPUtil_.getDefaultPic());
//			} else {
//				try {
//					in = new FileInputStream(FTPUtil_.getFileUri(id));
//					getResponseContextType(response, id);
//				} catch (Exception e) {
//					in = new FileInputStream(FTPUtil_.getDefaultPic());
//				}
//			}
//			byte[] buffer = new byte[1024];
//			int n = 0;
//			while ((n = in.read(buffer)) != -1) {
//				out.write(buffer, 0, n);
//			}
//			out.flush();
//			out.close();
//			in.close();
//		} catch (Exception e) {
//			// e.printStackTrace();
//		}
	}

	/**
	 * 显示一张图片
	 * 
	 * @param id
	 * @param token
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/00003030", method = RequestMethod.GET)
	public void doImage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {

		getResponseContextImageType(response);

		ServletOutputStream sos = response.getOutputStream();

		int WIDTH = 65;// 设置图片的宽度
		int HEIGHT = 22;// 设置图片的高度
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		char[] rands = generateCheckCode(session);
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
	 * 自定义高度
	 * 
	 * @param width
	 * @param height
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/00003031/{width}/{height}", method = RequestMethod.GET)
	public void m00003031GET(@PathVariable String width, @PathVariable String height, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {

		getResponseContextImageType(response);

		ServletOutputStream sos = response.getOutputStream();

		int WIDTH = Integer.parseInt(width);// 设置图片的宽度
		int HEIGHT = Integer.parseInt(height);// 设置图片的高度

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		char[] rands = generateCheckCode(session);
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

	private char[] generateCheckCode(HttpSession session) {
		String chars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
		char[] rands = new char[4];
		for (int i = 0; i < 4; i++) {
			int rand = (int) (Math.random() * (chars.length() - 1));
			rands[i] = chars.charAt(rand);
		}
		// 缓存
		session.setAttribute(RANDOM_CODE, new String(rands));
		return rands;
	}
}
