package org.ishome.jfp.framework.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/** 
 * 图片压缩工具类 提供的方法中可以设定生成的 缩略图片的大小尺寸、压缩尺寸的比例、图片的质量等 
 *  
 * @date 2012/11/14 
 * @versions 1.0 
 */  
public class ImageCompress {  
  
  
    /** 
     * * 将图片按照指定的尺寸比例、图片质量压缩 
     *  
     * @param srcImgPath 
     *            :源图片路径 
     * @param outImgPath 
     *            :输出的压缩图片的路径 
     * @param ratio 
     *            :压缩后的图片尺寸比例 
     * @param per 
     *            :百分比 
     */  
    public static InputStream Tosmallerpic(InputStream imgInputStream,  
            float ratio, float per) {  
        // 得到图片  
        BufferedImage newImg;
		try {
			BufferedImage src = ImageIO.read(imgInputStream);
			int old_w = src.getWidth();  
			// 得到源图宽  
			int old_h = src.getHeight();  
			// 得到源图长  
			int new_w = 0;  
			// 新图的宽  
			int new_h = 0;  
			// 新图的长  
			BufferedImage tempImg = new BufferedImage(old_w, old_h,  
			        BufferedImage.TYPE_INT_RGB);  
			Graphics2D g = tempImg.createGraphics();  
			g.setColor(Color.white);  
			// 从原图上取颜色绘制新图
			g.fillRect(0, 0, old_w, old_h);  
			g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);  
			g.dispose();  
			// 根据图片尺寸压缩比得到新图的尺寸
			new_w = (int) Math.round(old_w * ratio);  
			new_h = (int) Math.round(old_h * ratio);  
			newImg = new BufferedImage(new_w, new_h,  
			        BufferedImage.TYPE_INT_RGB);  
			newImg.getGraphics().drawImage(  
			        tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,  
			        0, null);
			 //调用方法输出图片文件
			 return OutImage(newImg, per);  
		} catch (IOException e) {
			e.printStackTrace();
		}
       return imgInputStream;
    }

    /** 
     * * 将图片文件输出到指定的路径，并可设定压缩质量 
     *  
     * @param outImgPath 
     * @param newImg 
     * @param per 
     */  
    private static InputStream OutImage(BufferedImage newImg,  
            float per) {	
        try {  
         //   FileOutputStream newimage = new FileOutputStream(outImgPath);  
             
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(newImg); 
            // 压缩质量  
            jep.setQuality(per, true);  
            encoder.encode(newImg, jep);
            ImageIO.write(newImg, "JPG", os); 
            //System.out.println("图片完成添加水印图片");
            return new ByteArrayInputStream(os.toByteArray()); 
        //    newimage.close();  
        } catch (Exception e) {  
            e.printStackTrace();
        }
		return null;
    }
}  