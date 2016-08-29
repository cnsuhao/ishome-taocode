package org.isotope.jfp.common.file;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import org.w3c.dom.Element;

import com.sun.imageio.plugins.jpeg.JPEGImageWriter;

/**
 * 图片压缩工具类 提供的方法中可以设定生成的 缩略图片的大小尺寸、压缩尺寸的比例、图片的质量等
 * 
 * @author Spook
 * @since 1.1.0
 * @version 1.1.0 2014/2/8
 */
public class ImageCompress {

	// /**
	// * * 将图片按照指定的尺寸比例、图片质量压缩
	// *
	// * @param srcImgPath
	// * :源图片路径
	// * @param outImgPath
	// * :输出的压缩图片的路径
	// * @param ratio
	// * :压缩后的图片尺寸比例
	// * @param per
	// * :百分比
	// */
	// public static InputStream Tosmallerpic(InputStream imgInputStream, float
	// ratio, float per) {
	// // 得到图片
	// BufferedImage newImg;
	// try {
	// BufferedImage src = ImageIO.read(imgInputStream);
	// int old_w = src.getWidth();
	// // 得到源图宽
	// int old_h = src.getHeight();
	// // 得到源图长
	// int new_w = 0;
	// // 新图的宽
	// int new_h = 0;
	// // 新图的长
	// BufferedImage tempImg = new BufferedImage(old_w, old_h,
	// BufferedImage.TYPE_INT_RGB);
	// Graphics2D g = tempImg.createGraphics();
	// g.setColor(Color.white);
	// // 从原图上取颜色绘制新图
	// g.fillRect(0, 0, old_w, old_h);
	// g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
	// g.dispose();
	// // 根据图片尺寸压缩比得到新图的尺寸
	// new_w = (int) Math.round(old_w * ratio);
	// new_h = (int) Math.round(old_h * ratio);
	// newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
	// newImg.getGraphics().drawImage(tempImg.getScaledInstance(new_w, new_h,
	// Image.SCALE_SMOOTH), 0, 0, null);
	// // 调用方法输出图片文件
	// return OutImage(newImg, per);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return imgInputStream;
	// }
	//
	// /**
	// * * 将图片文件输出到指定的路径，并可设定压缩质量
	// *
	// * @param outImgPath
	// * @param newImg
	// * @param per
	// */
	// private static InputStream OutImage(Integer dpi, float JPEGcompression) {
	// try {
	// JPEGImageWriter imageWriter = (JPEGImageWriter)
	// ImageIO.getImageWritersBySuffix("jpg").next();
	// ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
	// imageWriter.setOutput(ios);
	// // and metadata
	// IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new
	// ImageTypeSpecifier(image_to_save), null);
	//
	// // FileOutputStream newimage = new FileOutputStream(outImgPath);
	//
	// ByteArrayOutputStream os = new ByteArrayOutputStream();
	// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
	// JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(newImg);
	// // 压缩质量
	// jep.setQuality(per, true);
	// encoder.encode(newImg, jep);
	// ImageIO.write(newImg, "JPG", os);
	// // System.out.println("图片完成添加水印图片");
	// return new ByteArrayInputStream(os.toByteArray());
	// // newimage.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//

	/**
	 * 
	 * @param f
	 *            图片所在的文件夹路径
	 * @param file
	 *            图片路径
	 * @param ext
	 *            扩展名
	 * @param n
	 *            图片名
	 * @param w
	 *            目标宽
	 * @param h
	 *            目标高
	 * @param per
	 *            百分比
	 */
	public static void Tosmallerpic(String f, File file, String ext, String n, int w, int h, float per) {
		Image src;
		try {
			src = javax.imageio.ImageIO.read(file); // 构造Image对象

			String img_midname = f + n.substring(0, n.indexOf(".")) + ext + n.substring(n.indexOf("."));
			int old_w = src.getWidth(null); // 得到源图宽
			int old_h = src.getHeight(null);
			int new_w = 0;
			int new_h = 0; // 得到源图长

			double w2 = (old_w * 1.00) / (w * 1.00);
			double h2 = (old_h * 1.00) / (h * 1.00);

			// 图片跟据长宽留白，成一个正方形图。
			BufferedImage oldpic;
			if (old_w > old_h) {
				oldpic = new BufferedImage(old_w, old_w, BufferedImage.TYPE_INT_RGB);
			} else {
				if (old_w < old_h) {
					oldpic = new BufferedImage(old_h, old_h, BufferedImage.TYPE_INT_RGB);
				} else {
					oldpic = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
				}
			}
			Graphics2D g = oldpic.createGraphics();
			g.setColor(Color.white);
			if (old_w > old_h) {
				g.fillRect(0, 0, old_w, old_w);
				g.drawImage(src, 0, (old_w - old_h) / 2, old_w, old_h, Color.white, null);
			} else {
				if (old_w < old_h) {
					g.fillRect(0, 0, old_h, old_h);
					g.drawImage(src, (old_h - old_w) / 2, 0, old_w, old_h, Color.white, null);
				} else {
					// g.fillRect(0,0,old_h,old_h);
					g.drawImage(src.getScaledInstance(old_w, old_h, Image.SCALE_SMOOTH), 0, 0, null);
				}
			}
			g.dispose();
			src = oldpic;
			// 图片调整为方形结束
			if (old_w > w)
				new_w = (int) Math.round(old_w / w2);
			else
				new_w = old_w;
			if (old_h > h)
				new_h = (int) Math.round(old_h / h2);// 计算新图长宽
			else
				new_h = old_h;
			BufferedImage image_to_save = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			image_to_save.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
			FileOutputStream fos = new FileOutputStream(img_midname); // 输出到文件流

			// 新的方法
			saveAsJPEG(100, image_to_save, per, fos);

			fos.close();
		} catch (IOException ex) {
		}
	}

	/**
	 * 以JPEG编码保存图片
	 * 
	 * @param dpi
	 *            分辨率
	 * @param image_to_save
	 *            要处理的图像图片
	 * @param JPEGcompression
	 *            压缩比
	 * @param fos
	 *            文件输出流
	 * @throws IOException
	 */
	public static void saveAsJPEG(Integer dpi, BufferedImage image_to_save, float JPEGcompression, FileOutputStream fos) throws IOException {

		// useful documentation at
		// http://docs.oracle.com/javase/7/docs/api/javax/imageio/metadata/doc-files/jpeg_metadata.html
		// useful example program at
		// http://johnbokma.com/java/obtaining-image-metadata.html to output
		// JPEG data

		// old jpeg class
		// com.sun.image.codec.jpeg.JPEGImageEncoder jpegEncoder =
		// com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(fos);
		// com.sun.image.codec.jpeg.JPEGEncodeParam jpegEncodeParam =
		// jpegEncoder.getDefaultJPEGEncodeParam(image_to_save);

		// Image writer
		JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpg").next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
		imageWriter.setOutput(ios);
		// and metadata
		IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image_to_save), null);

		if (dpi != null && !dpi.equals("")) {

			// old metadata
			// jpegEncodeParam.setDensityUnit(com.sun.image.codec.jpeg.JPEGEncodeParam.DENSITY_UNIT_DOTS_INCH);
			// jpegEncodeParam.setXDensity(dpi);
			// jpegEncodeParam.setYDensity(dpi);

			// new metadata
			Element tree = (Element) imageMetaData.getAsTree("javax_imageio_jpeg_image_1.0");
			Element jfif = (Element) tree.getElementsByTagName("app0JFIF").item(0);
			jfif.setAttribute("Xdensity", Integer.toString(dpi));
			jfif.setAttribute("Ydensity", Integer.toString(dpi));

		}

		if (JPEGcompression >= 0 && JPEGcompression <= 1f) {

			// old compression
			// jpegEncodeParam.setQuality(JPEGcompression,false);

			// new Compression
			JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
			jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
			jpegParams.setCompressionQuality(JPEGcompression);

		}

		// old write and clean
		// jpegEncoder.encode(image_to_save, jpegEncodeParam);

		// new Write and clean up
		imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), null);
		ios.close();
		imageWriter.dispose();

	}

}