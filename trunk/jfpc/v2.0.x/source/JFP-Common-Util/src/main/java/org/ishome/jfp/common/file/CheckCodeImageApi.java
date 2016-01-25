package org.ishome.jfp.common.file;

import org.ishome.jfp.framework.support.MyContentTypeSupport;

/**
 * 图片显示
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/6/10
 * 
 */
//@Controller
public class CheckCodeImageApi extends MyContentTypeSupport {
//	protected static final Logger logger = LoggerFactory.getLogger(CheckCodeImageApi.class);
//
//	/**
//	 * 显示一张图片
//	 * 
//	 * @param id
//	 * @param token
//	 * @param request
//	 * @param response
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/00003030", method = RequestMethod.GET)
//	public void doImage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
//
//		getResponseContextImageType(response);
//
//		ServletOutputStream sos = response.getOutputStream();
//
//		int WIDTH = 65;// 设置图片的宽度
//		int HEIGHT = 22;// 设置图片的高度
//		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
//		Graphics g = image.getGraphics();
//
//		char[] rands = sendRandomCode(4).toCharArray();
//		drawBackground(g, WIDTH, HEIGHT);
//		drawRands(g, rands, WIDTH, HEIGHT);
//		g.dispose();
//
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		ImageIO.write(image, "JPEG", bos);
//		byte[] buf = bos.toByteArray();
//		response.setContentLength(buf.length);
//		sos.write(buf);
//		bos.close();
//		sos.close();
//	}
//
//	/**
//	 * 自定义高度
//	 * 
//	 * @param width
//	 * @param height
//	 * @param request
//	 * @param response
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/00003031/{width}/{height}", method = RequestMethod.GET)
//	public void m00003031GET(@PathVariable String width, @PathVariable String height, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
//
//		getResponseContextImageType(response);
//
//		ServletOutputStream sos = response.getOutputStream();
//
//		int WIDTH = Integer.parseInt(width);// 设置图片的宽度
//		int HEIGHT = Integer.parseInt(height);// 设置图片的高度
//
//		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
//		Graphics g = image.getGraphics();
//
//		char[] rands = sendRandomCode(4).toCharArray();
//		drawBackground(g, WIDTH, HEIGHT);
//		drawRands(g, rands, WIDTH, HEIGHT);
//		g.dispose();
//
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		ImageIO.write(image, "JPEG", bos);
//		byte[] buf = bos.toByteArray();
//		response.setContentLength(buf.length);
//		sos.write(buf);
//		bos.close();
//		sos.close();
//	}
//
//	private void drawBackground(Graphics g, int width, int height) {
//		g.setColor(new Color(0xDCDCDC));
//		g.fillRect(0, 0, width, height);
//		for (int i = 0; i < 120; i++) {
//			int x = (int) (Math.random() * width);
//			int y = (int) (Math.random() * height);
//			int red = (int) (Math.random() * 255);
//			int green = (int) (Math.random() * 255);
//			int blue = (int) (Math.random() * 255);
//			g.setColor(new Color(red, green, blue));
//			g.drawOval(x, y, 1, 0);
//		}
//	}
//
//	private void drawRands(Graphics g, char[] rands, int width, int height) {
//		// g.setColor(Color.BLUE);
//		Random random = new Random();
//		int red = random.nextInt(110);
//		int green = random.nextInt(50);
//		int blue = random.nextInt(50);
//		g.setColor(new Color(red, green, blue));
//		int size = (int) (height * 0.7);
//		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, size));
//		g.drawString("" + rands[0], 0 * width / 4 + 1, size);
//		g.drawString("" + rands[1], 1 * width / 4 + 1, size - 1);
//		g.drawString("" + rands[2], 2 * width / 4 + 1, size);
//		g.drawString("" + rands[3], 3 * width / 4 + 1, size + 1);
//	}

}
