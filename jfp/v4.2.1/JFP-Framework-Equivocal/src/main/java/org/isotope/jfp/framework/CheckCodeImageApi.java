package org.isotope.jfp.framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyContentTypeSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 图片显示
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/6/10
 * 
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CheckCodeImageApi extends MyContentTypeSupport {
	/**
	 * 验证码校验
	 * 
	 * @param id
	 * @param token
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/99993039", method = RequestMethod.GET)
	@ResponseBody
	public RESTResultBean do99993039GET(HttpServletRequest request, HttpServletResponse response, HttpSession session, String SecurityCode) throws ServletException, IOException {
		RESTResultBean result = new RESTResultBean();
		result.setMessage("OK");
		if (checkSecurityCode(request, response) == false) {
			result.setCode(ONE);
			result.setMessage("验证码输入错误，请刷新后再试。");
		}

		return result;
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
	@RequestMapping(value = "/99993030", method = RequestMethod.GET)
	public void do99993030GET(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		// int width = 65;// 设置图片的宽度
		// int height = 22;// 设置图片的高度
		do99993031GET(65, 22, request, response, session);
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
	@RequestMapping(value = "/99993031/{width}/{height}", method = RequestMethod.GET)
	public void do99993031GET(@PathVariable int width, @PathVariable int height, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		do99993032GET(width, height, "", "", request, response, session);
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
	@RequestMapping(value = "/99993032/{width}/{height}/{background}/{fontground}", method = RequestMethod.GET)
	public void do99993032GET(@PathVariable int width, @PathVariable int height, @PathVariable String background, @PathVariable String fontground, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {

		getResponseContextImageType(response);

		ServletOutputStream sos = response.getOutputStream();

		// 定义画板
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		// 定义背景颜色
		Color backgroundColor = Color.BLACK;
		if (EmptyHelper.isNotEmpty(background)) {
			backgroundColor = loadColor(background);
		}
		drawBackground(g, backgroundColor, width, height);// 绘制背景

		// 生成随机数
		String code = loadSecurityCode(request, response);
		char[] rands = code.toCharArray();

		// 定义字体颜色
		Color fontgroundColor = Color.WHITE;
		if (EmptyHelper.isNotEmpty(fontground)) {
			fontgroundColor = loadColor(fontground);
		}
		drawRands(g, fontgroundColor, rands, width, height);// 绘制文字

		g.dispose();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length);
		sos.write(buf);
		bos.close();
		sos.close();
	}

	private Color loadColor(String color) {
		BigInteger r = new BigInteger(color.substring(0, 2), 16);
		BigInteger g = new BigInteger(color.substring(2, 4), 16);
		BigInteger b = new BigInteger(color.substring(4, 6), 16);

		return new Color(r.intValue(), g.intValue(), b.intValue());
	}

	private void drawBackground(Graphics g, Color background, int width, int height) {

		g.setColor(background);
		g.fillRect(0, 0, width, height);
		//
		// for (int i = 0; i < 120; i++) {
		// int x = (int) (Math.random() * width);
		// int y = (int) (Math.random() * height);
		// int red = (int) (Math.random() * 255);
		// int green = (int) (Math.random() * 255);
		// int blue = (int) (Math.random() * 255);
		//// g.setColor(new Color(red, green, blue));
		//// g.drawOval(x, y, 1, 0);
		// }
	}

	private void drawRands(Graphics g, Color fontground, char[] rands, int width, int height) {
		g.setColor(fontground);
		int size = (int) (height * 0.7);
		g.setFont(new Font(null, Font.BOLD, size));

		BigDecimal baseX = BigDecimal.valueOf(width).divide(BigDecimal.valueOf(10));
		BigDecimal baseY = BigDecimal.valueOf(height);
		BigDecimal x = BigDecimal.valueOf(width).divide(BigDecimal.valueOf(5));

		g.drawString("" + rands[0], x.multiply(BigDecimal.valueOf(0)).add(baseX).intValue(), baseY.multiply(BigDecimal.valueOf(0.6)).intValue());
		g.drawString("" + rands[1], x.multiply(BigDecimal.valueOf(1)).add(baseX).intValue(), baseY.multiply(BigDecimal.valueOf(0.85)).intValue());
		g.drawString("" + rands[2], x.multiply(BigDecimal.valueOf(2)).add(baseX).intValue(), baseY.multiply(BigDecimal.valueOf(0.75)).intValue());
		g.drawString("" + rands[3], x.multiply(BigDecimal.valueOf(3)).add(baseX).intValue(), baseY.multiply(BigDecimal.valueOf(0.65)).intValue());
	}

}
