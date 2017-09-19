
package org.isotope.jfp.framework.utils;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 二维码生成器
 * 
 * @author Spook
 * @version 4.1.3 2017/07/25
 * @since 4.1.3 2017/07/25
 */
public class QRCodeHelper {
	public static void main(String[] args) throws Exception {
		loadQRCode("这里是测试文本", 120, 200, null);

	}

	/**
	 * 生成图像
	 * 
	 * @param content
	 *            内容
	 * @param width
	 *            图像宽度
	 * @param height
	 *            图像高度
	 * @param stream
	 *            输出流
	 * @throws Exception
	 */

	public static void loadQRCode(String content, int width, int height, OutputStream stream) throws Exception {

		String format = "jpg";// 图像类型
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵

		MatrixToImageWriter.writeToStream(bitMatrix, format, stream);// 输出图像

		System.out.println("输出成功.");
	}

	// /**
	// * 解析图像
	// */
	//
	// public static void testDecode() {
	// String filePath = "D://zxing.png";
	// BufferedImage image;
	// try {
	// image = ImageIO.read(new File(filePath));
	// LuminanceSource source = new BufferedImageLuminanceSource(image);
	// Binarizer binarizer = new HybridBinarizer(source);
	// BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
	// Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType,
	// Object>();
	// hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
	// Result result = new MultiFormatReader().decode(binaryBitmap, hints);//
	// 对图像进行解码
	// JSONObject content = JSONObject.parseObject(result.getText());
	// System.out.println("图片中内容： ");
	// System.out.println("author： " + content.getString("author"));
	// System.out.println("zxing： " + content.getString("zxing"));
	// System.out.println("图片中格式： ");
	// System.out.println("encode： " + result.getBarcodeFormat());
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (NotFoundException e) {
	// e.printStackTrace();
	// }
	// }
}
