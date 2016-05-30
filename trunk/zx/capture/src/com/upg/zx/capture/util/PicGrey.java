package com.upg.zx.capture.util;
 

import static org.bytedeco.javacpp.lept.pixRead;
import static org.bytedeco.javacpp.lept.pixReadMem;

import java.io.*; 
import java.util.Arrays;
import java.util.Random;
import java.awt.image.*; 
import java.awt.geom.AffineTransform; 
import java.awt.color.ColorSpace; 
import java.awt.image.ConvolveOp; 
import java.awt.image.Kernel; 
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.bytedeco.javacpp.tesseract.*;

import com.upg.zx.capture.task.CompanyTask;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Image; 

public class PicGrey {
	BufferedImage image; 
	private int iw, ih; 
	private int[] pixels;
	private static int pixSeq = 0;
	
	public PicGrey(byte[] bitmap, BufferedImage bi) { 
		if ( bitmap != null) {
			FileImageOutputStream imageOutput;
			try {
				// 保留临时文件，便于事后分析哪些图片不能被识别
				if (pixSeq == 10) pixSeq = 0;
				String pixName = String.valueOf(pixSeq++);
				imageOutput = new FileImageOutputStream(new File("D:\\"+pixName+".jpg"));
				imageOutput.write(bitmap, 0, bitmap.length);
				imageOutput.close();
				
				ByteArrayInputStream fin = new ByteArrayInputStream(bitmap);
				image = ImageIO.read(fin);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			image = bi;
		}

		iw = image.getWidth();
		ih = image.getHeight();
		pixels = new int[iw * ih]; 
	}
	
	public BufferedImage changeGrey(int grey) { 
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		
		// 设定二值化的域值，默认值为100
		//int grey = 100; 
		//  对图像进行二值化处理，Alpha值保持不变 
		ColorModel cm = ColorModel.getRGBdefault(); 
		for (int i = 0; i < iw * ih; i++) { 
			int red, green, blue;
			int alpha = cm.getAlpha(pixels[i]); 
			if (alpha < 255) {
				System.out.println("alpha ");
			}
			
			if (cm.getRed(pixels[i]) > grey) {
				red = 255; 
			} else { 
				red = 0; 
			} 
			if (cm.getGreen(pixels[i]) > grey) { 
				green = 255; 
			} else {
				green = 0;
			} 
			if (cm.getBlue(pixels[i]) > grey) {
				blue = 255; 
			} else { 
				blue = 0;
			} 
			
			
			
			pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; 
		}
		//通过移位重新构成某一点像素的RGB值 } 
		// 将数组中的象素产生一个图像
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, pixels, 0, iw)); 
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
		return image; 
	}
	/*
	 * remove <ihigh, remove < iwidth
	 */
	public BufferedImage changeGZPix(int ihigh, int iwidth) { 
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 

		for(int i=iwidth; i < iw; i++)
		{
			for (int j=0; j < ih; j++){
				if (j < ihigh) pixels[iw*j+i] = -1;
			}
		}
		
		for(int i=0; i < iwidth; i++)
		{
			for (int j=0; j < ih; j++){
				pixels[iw*j+i] = -1;
			}
		}

		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, pixels, 0, iw)); 
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
		return image; 
	}
	
	public BufferedImage changeGreyBySpecialColor() { 
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		
		// 设定二值化的域值，默认值为100
		//int grey = 100; 
		//  对图像进行二值化处理，Alpha值保持不变 
		ColorModel cm = ColorModel.getRGBdefault(); 
		for (int i = 0; i < iw * ih; i++) { 
			int red, green, blue;
			int alpha = cm.getAlpha(pixels[i]); 
			if (alpha < 255) {
				System.out.println("alpha ");
			}
			
			// for the same values of red, green, blue
			if (cm.getRed(pixels[i]) == cm.getGreen(pixels[i])  && 
				cm.getGreen(pixels[i]) == cm.getBlue(pixels[i])  &&  
				cm.getBlue(pixels[i]) != 255) {
				red = 0; 
				green = 0;
				blue = 0;
			} else { 
				red = 255; 
				green = 255;
				blue = 255;
			} 	
			
			pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; 
		}
		//通过移位重新构成某一点像素的RGB值 } 
		// 将数组中的象素产生一个图像
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, pixels, 0, iw)); 
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
		return image; 

	}
	
	public void jsEcrateCircle(int ecrateValue) { 
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 

		//  remove the special points
		ColorModel cm = ColorModel.getRGBdefault(); 
		for (int i = iw; i < iw * ih - iw; i++) { 
			int red, green, blue;
			int alpha = cm.getAlpha(pixels[i]); 
			if (alpha < 255) {
				System.out.println("alpha ");
			}
			
			if ( ( ( cm.getRed(pixels[i-1]) + cm.getGreen(pixels[i-1]) + cm.getBlue(pixels[i-1]) ) > ecrateValue) &&
			     ( ( cm.getRed(pixels[i+1]) + cm.getGreen(pixels[i+1]) + cm.getBlue(pixels[i+1]) ) > ecrateValue) ||
			     ( ( cm.getRed(pixels[i-iw]) + cm.getGreen(pixels[i-iw]) + cm.getBlue(pixels[i-iw]) ) > ecrateValue) &&
				 ( ( cm.getRed(pixels[i+iw]) + cm.getGreen(pixels[i+iw]) + cm.getBlue(pixels[i+iw]) ) > ecrateValue) ) {

				red = 255; 
				green = 255; 
				blue = 255; 
				pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; 
			}
			
		}
		//通过移位重新构成某一点像素的RGB值 } 
		// 将数组中的象素产生一个图像
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, pixels, 0, iw)); 
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
	}
	
	public BufferedImage ahRemoveBase() { 
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		
		// 设定二值化的域值，默认值为100
		int ecrateValue = 300; 
		//  对图像进行二值化处理，Alpha值保持不变 
		ColorModel cm = ColorModel.getRGBdefault(); 
		for (int i = 1; i < iw * ih - 1; i++) { 
			int red, green, blue;
			int alpha = cm.getAlpha(pixels[i]); 
			if (alpha < 255) {
				System.out.println("alpha ");
			}
			
			if ( ( ( cm.getRed(pixels[i]) + cm.getGreen(pixels[i]) + cm.getBlue(pixels[i]) ) > ecrateValue) &&
				   (cm.getRed(pixels[i]) > 100 && cm.getGreen(pixels[i]) > 100 && cm.getBlue(pixels[i])> 100  ) ) {
				
				red = 255; 
				green = 255; 
				blue = 255; 
				pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; 
			}
			
		}
		//通过移位重新构成某一点像素的RGB值 } 
		// 将数组中的象素产生一个图像
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, pixels, 0, iw)); 
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
		
		return image; 

	}
	
	public BufferedImage removeEdegeBlack(int highParts) { 
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 

		//  对图像进行二值化处理，Alpha值保持不变 
		ColorModel cm = ColorModel.getRGBdefault(); 
		for (int i = 1; i < iw * ih; i++) { //四周的点去掉
			int red, green, blue;
			int alpha = cm.getAlpha(pixels[i]); 
			if (alpha < 255) {
				System.out.println("alpha ");
			}
		
			// 将上边和下边部分区域填白
			if ( i < iw*ih/highParts || i > iw*ih/highParts*(highParts-1) ){
				red = 255; 
				green = 255; 
				blue = 255; 
				pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; 
				continue;
			}
			
		}
		//通过移位重新构成某一点像素的RGB值 } 
		// 将数组中的象素产生一个图像
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, pixels, 0, iw)); 
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
		
		return image; 
	}
	
	public BufferedImage removeSingleBlack(int ecrateValue) { 
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		
		// 设定二值化的域值，默认值为100
		//int ecrateValue = 300; 
		//  对图像进行二值化处理，Alpha值保持不变 
		ColorModel cm = ColorModel.getRGBdefault(); 
		for (int i = iw; i < iw * ih - iw; i++) { //四周的点去掉
			int red, green, blue;
			int alpha = cm.getAlpha(pixels[i]); 
			if (alpha < 255) {
				System.out.println("alpha ");
			}
			// 
			try{
				if ( ( ( cm.getRed(pixels[i-1]) + cm.getGreen(pixels[i-1]) + cm.getBlue(pixels[i-1]) ) > ecrateValue) &&
				     ( ( cm.getRed(pixels[i+1]) + cm.getGreen(pixels[i+1]) + cm.getBlue(pixels[i+1]) ) > ecrateValue) &&
				     ( ( cm.getRed(pixels[i-iw]) + cm.getGreen(pixels[i-iw]) + cm.getBlue(pixels[i-iw]) ) > ecrateValue) &&
					 ( ( cm.getRed(pixels[i+iw]) + cm.getGreen(pixels[i+iw]) + cm.getBlue(pixels[i+iw]) ) > ecrateValue) ) {
						red = 255; 
						green = 255; 
						blue = 255; 
						pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; 
				}
			} catch(Exception e){
				System.out.println("Excepton: " + e);
			}
			
		}
		//通过移位重新构成某一点像素的RGB值 } 
		// 将数组中的象素产生一个图像
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, pixels, 0, iw)); 
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
		
		return image; 

	}
	
	public BufferedImage removeGreyPoint() { 
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		
		//  对图像进行二值化处理，Alpha值保持不变 
		ColorModel cm = ColorModel.getRGBdefault(); 
		for (int i = 1; i < iw * ih ; i++) { 
			int red, green, blue;
			int alpha = cm.getAlpha(pixels[i]); 
			if (alpha < 255) {
				System.out.println("alpha ");
			}
			
			// 
			try{
				if ( ( cm.getRed(pixels[i]) > 90 && cm.getRed(pixels[i]) < 130) &&
				     ( cm.getGreen(pixels[i]) > 90 && cm.getGreen(pixels[i]) < 130) &&
				     ( cm.getBlue(pixels[i]) > 90 && cm.getBlue(pixels[i]) < 130) ) {
						red = 255; 
						green = 255; 
						blue = 255; 
						pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; 
				}
			} catch(Exception e){
				System.out.println("Excepton: " + e);
			}
			
		}
		
		//通过移位重新构成某一点像素的RGB值 } 
		// 将数组中的象素产生一个图像
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, pixels, 0, iw)); 
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
		
		return image; 

	}
	
	public BufferedImage addBlack() { 
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		
		// 设定二值化的域值，默认值为100
		int ecrateValue = 300; 
		//  对图像进行二值化处理，Alpha值保持不变 
		ColorModel cm = ColorModel.getRGBdefault(); 
		for (int i = 0; i < iw * ih; i++) { //四周的点去掉
			int red, green, blue;
			int alpha = cm.getAlpha(pixels[i]); 

			// 将上边和下边部分区域填白
			if ( i < iw*ih/5 || i > iw*ih/5*4 ){
				red = 255; 
				green = 255; 
				blue = 255; 
				pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; 
			}
		}
		for (int i = iw; i < iw * ih - iw; i++) { //四周的点去掉
			int red, green, blue;
			int alpha = cm.getAlpha(pixels[i]); 
			if (alpha < 255) {
				System.out.println("alpha ");
			}
				
			// 将字符内部的白点填黑
			try{
				if ( ( ( cm.getRed(pixels[i-1]) + cm.getGreen(pixels[i-1]) + cm.getBlue(pixels[i-1]) ) < ecrateValue) &&
				     ( ( cm.getRed(pixels[i+1]) + cm.getGreen(pixels[i+1]) + cm.getBlue(pixels[i+1]) ) < ecrateValue) ) {
					
					red = 0; 
					green = 0; 
					blue = 0; 
					pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; 
				}
				
				if ( ( ( cm.getRed(pixels[i-iw]) + cm.getGreen(pixels[i-iw]) + cm.getBlue(pixels[i-iw]) ) < ecrateValue) &&
					     ( ( cm.getRed(pixels[i+iw]) + cm.getGreen(pixels[i+iw]) + cm.getBlue(pixels[i+iw]) ) < ecrateValue) ) {
						
						red = 0; 
						green = 0; 
						blue = 0; 
						pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; 
				}
			} catch(Exception e){
				System.out.println("Excepton: " + e);
			}
			
		}
		//通过移位重新构成某一点像素的RGB值 } 
		// 将数组中的象素产生一个图像
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, pixels, 0, iw)); 
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
		
		return image; 

	}
	
	public String calculateJXPoint(BufferedImage bImage) { 
		int[] imagePixels = new int[iw * ih]; ;
		PixelGrabber pg = new PixelGrabber(bImage.getSource(), 0, 0, iw, ih, imagePixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		
		ColorModel cm = ColorModel.getRGBdefault(); 
		int red, green, blue;
		int[] sumByWidth = new int[iw];
		int[] sumByHigh = new int[ih];
		int[] wordLeftWidth = new int[ih], wordRightWidth = new int[ih];
		boolean wordBegin = false, wordEnd = false;
		int startWidth = 0, endWidth = 0;
		int startHigh = 0, endHigh = 0;
		int wordSec = 0;
		String code = "";
		
		// start to scan the word 
		// width
		for (int i = 1; i < iw -1 ; i++) { 
			// high
			for (int j = 1; j < ih ; j++) { 
				red = cm.getRed(imagePixels[j*iw+i]); 
				green = cm.getGreen(imagePixels[j*iw+i]);
				blue = cm.getBlue(imagePixels[j*iw+i]);
				if (red + green + blue == 0){
					// sum the number of black points by column
					sumByWidth[i] += 1;
				}
			}
			
			// get the start point
			if (sumByWidth[i] != 0 && startWidth == 0) {
				wordBegin = true;
				startWidth = i;
			}
			
			// get the end point
			if (sumByWidth[i] == 0 && wordBegin == true && i < iw -1) {
				int nextWidthCount = 0;
				for (int j = 1; j < ih ; j++) { 
					red = cm.getRed(imagePixels[j*iw+i+1]); 
					green = cm.getGreen(imagePixels[j*iw+i+1]);
					blue = cm.getBlue(imagePixels[j*iw+i+1]);
					if (red + green + blue == 0){
						// sum the number of black points by column
						nextWidthCount += 1;
					}
				}
				if (wordSec == 1 && nextWidthCount > 8) 
					// to avoid the case: the second word JIA is partly scanned 
					;
				else {
					wordEnd = true;
					endWidth = i;
				}
			}
			
			// get the whole word now
			if (wordBegin == true && wordEnd == true) {
				// word
				wordSec += 1;
				int[][] word = new int[ih][endWidth];
				
				// high
				for (int m = 1; m < ih ; m++) { 
					// width					
					for (int n = startWidth; n < endWidth ; n++) { 
						red = cm.getRed(imagePixels[m*iw+n]); 
						green = cm.getGreen(imagePixels[m*iw+n]);
						blue = cm.getBlue(imagePixels[m*iw+n]);
						if (red + green + blue == 0){
							sumByHigh[m] += 1;
							word[m][n] = 1;
							if (startHigh == 0){
								startHigh = m;
							}
							if (wordLeftWidth[m] == 0) wordLeftWidth[m] = n; 
							if (wordRightWidth[m] < n) wordRightWidth[m] = n; 
						}
						if((n == (endWidth -1)) && startHigh>1 && sumByHigh[m] == 0 &&  (( endHigh-startHigh) < 4 ) ){
							endHigh = m-1;
							// to avoid the noise black point and other useless scan
							if ((endHigh-startHigh) > 4 ) break;
						}
					}
				}
				
				// the number of black/white times for each line of the word
				int blackTimesByRow = 0, whiteTimesByRow = 0; 
				// the number of circle times of the word
				int circleNumber = 0, circleHighUp = 0;
				// definition for the circle property, to check  0, 6, 8, 9,    and 4
				boolean isCircleStart = false, isCircleEnd = false, isCircleStartUp = false, isCircleEndDown = false, isCircleFound = false;
				boolean isFour = false, isLeftLine = false, isRightLine = false;
				// the circle up and down width
				int circleUpHigh = 0, circleDownHigh = 0;
				// the word's high is separated for five parts
				int highCheck = (endHigh - startHigh)/5;
				// the word's width is separated for 2 parts
				int widthCheck = (endWidth - startWidth)/3;
				// definition for whether the black happened in the four places, to check 2, 3, 5
				int upLeftCount = 0, upRightCount = 0, downLeftCount = 0, downRightCount = 0;
				boolean isLeftUp = false, isLeftDown = false, isRightUp = false, isRightDown = false;
                // the width for the up of 1/5 width, to decide whether the word is 1 or 7
				int upPartialWidthStart = 0, upPartialWidthEnd = 0, downPartialWidthStart = 0, downPartialWidthEnd = 0;
				// definition whether the word is JIA, to check JIA, CHENG
				boolean isJia = true;
				
				// the up and down start width, end width
				int upStartWidth = 0, upEndWidth = 0, downStartWidth = 0, downEndWidth = 0;
				for (int m = startHigh; m <= endHigh ; m++) { 
					// width
					for (int n = startWidth; n < endWidth ; n++) { 
						if (word[m][n] == 1){
							// up
							if (m < (startHigh + endHigh)/2 ){
								if (upStartWidth > n || upStartWidth == 0) upStartWidth = n;
								if (upEndWidth < n) upEndWidth = n;
							}
							else{    // down
								if (downStartWidth > n || downStartWidth == 0) downStartWidth = n;
								if (downEndWidth < n) downEndWidth = n;
							}
						}
					}
				}
				
				for (int m = startHigh; m <= endHigh ; m++) { 
					whiteTimesByRow = 0;
					blackTimesByRow = 0;
					// width
					for (int n = startWidth; n < endWidth ; n++) { 
						if (word[m][n] == 1){
							// count the black point to check whether the circle is to be started, ignore the cycle > 180
							blackTimesByRow = whiteTimesByRow + 1;
							
							// check the value of isLeftUp, isLeftDown, isRightUp, isRightDown
							if ( (m > startHigh + highCheck) &&  (m < startHigh + highCheck*2) &&  (n <= startWidth + widthCheck) ){
								isLeftUp = true;
							}
							else if ( (m > startHigh + highCheck) &&  (m < startHigh + highCheck*2) &&  (n >= startWidth + widthCheck*2) ){
								isRightUp = true;
							}
							else if ( (m > startHigh + highCheck*3) &&  (m < startHigh + highCheck*4) &&  (n >= startWidth + widthCheck*2) ){
								isRightDown = true;
							}
							
							// to check whether is's 1 or 7
							if (m <= startHigh + highCheck) {
								if (upPartialWidthStart > n || upPartialWidthStart == 0) upPartialWidthStart = n;
								if (upPartialWidthEnd < n ) upPartialWidthEnd = n;
							}
							// to check whether is's 2 or 7
							if (m >= startHigh + highCheck * 4) {
								if (downPartialWidthStart > n || downPartialWidthStart == 0) downPartialWidthStart = n;
								if (downPartialWidthEnd < n ) downPartialWidthEnd = n;
							}
							
							// count up / down 's  left / right black points 
							if (m < (startHigh + endHigh)/2 ){
								if (n < ( upStartWidth + upEndWidth )/2 ) 
									upLeftCount++;
								else if (n >( upStartWidth + upEndWidth )/2 ) 
									upRightCount++;
							}
							else if (m > (startHigh + endHigh)/2 ) {    // down
								if (n < ( downStartWidth + downEndWidth )/2 ) 
									downLeftCount++;
								else if (n > ( downStartWidth + downEndWidth )/2 ) 
									downRightCount++;
							}
							
							// set the circle found
							if (isCircleEnd) {
								isCircleFound = true;
								circleDownHigh = m;
							}
						}
						else {
							if (isCircleFound ){
								// such case, do nothing
							}
							else if (isCircleStart) {
								// initial the default value for each partial time  
								if (blackTimesByRow > whiteTimesByRow ) {
									isCircleEnd = true;
								}
								// to check whether the circle is end
								if (word[m+1][n] == 1){
									isCircleEnd = isCircleEnd && true;
								}
								else{
									isCircleEnd = false;
								}
								//double check whether the circle is end through checking whether up is OK
								boolean isCircleHighUpGot = false;
								for (int kk = m-1; kk >= circleHighUp; kk--) {
									if (word[kk][n] == 1) {
										isCircleHighUpGot = true;
										break;
									}
								}
								isCircleEnd = isCircleEnd && isCircleHighUpGot;
							}
							whiteTimesByRow = blackTimesByRow ;
						}
					}
					if (isCircleStart ){
						// check whether the second line is correct
						if ( blackTimesByRow < 2 ){
							isCircleStart = false;
							isCircleEnd = false;
							if (circleNumber == 0){
								isCircleStartUp = false;
								isCircleEndDown = false;
							}
						}
					}
					else if ( blackTimesByRow >= 2){
						boolean isCircleBeginCheck = false;
						boolean isChecked = false;
						int tmpBlackTimesByRow = 1;
						for (int n = startWidth; n < endWidth ; n++) { 
							if (word[m][n] == 1){
								if (isChecked && isCircleStart)  {
									circleHighUp = m-1;
									break; // circle start found
								}
								
								if (isChecked) tmpBlackTimesByRow += 1;
								// to start check the blank points again
								if (tmpBlackTimesByRow < blackTimesByRow) 
									isCircleBeginCheck = true;    
								else
									isCircleBeginCheck = false;
								
								isChecked = false;
							}
							else {
								if (isCircleBeginCheck){
									if (! isChecked) {
										isCircleStart = true;
										isChecked = true;
									}
									// check whether the above is black
									if (word[m-1][n] == 1){
										isCircleStart = isCircleStart && true;
									}
									else{
										isCircleStart = false;
									}
								}
							}
							if (isCircleStart && circleDownHigh == 0) circleUpHigh = m;
						}
					}
					
					// check whether it's JIA
					if (( m > startHigh+(endHigh - startHigh)/2 -2) && (  m < startHigh+(endHigh - startHigh)/2 +2 ) ){
						if (blackTimesByRow == 4) 
							isJia = isJia && true;
						else 
							isJia = false;
					}

					// check whether there is a circle
					if (isCircleStart == true && isCircleEnd == true ){					
						isCircleStart = false;
						isCircleEnd = false;
						isCircleFound = false;
						// check whether the first circle is start by up
						if (m >= endHigh - highCheck){
							isCircleEndDown = true;
						}
						circleNumber += 1;
					}
					
					if(isCircleStart) {
						// check whether the first circle is end by down
						if (( m < startHigh + 3) ){
							isCircleStartUp = true;
						}
					}
				}
				
				// count black number to distinct JIA / CHEN
				int sumBlack = 0;
				for (int k = 0; k < ih; k++){
					sumBlack = sumBlack + sumByHigh[k];
				}
				
				// calculate the circle whether is for 4
				if (circleNumber == 1) {
					int lineStart = 0, lineLCount = 0, lineRCount = 0;
					int upCompareValue = wordRightWidth[(startHigh+endHigh)/2] - wordRightWidth[startHigh];
					int downCompareValue = wordRightWidth[endHigh -2] - wordRightWidth[(startHigh+endHigh)/2];
					if ( ( upCompareValue == 0 && downCompareValue == 0 ) ) {
						for (int m = startHigh; m <= endHigh; m++){
							if ( wordRightWidth[m+1] - wordRightWidth[m] == 0 ) {
								lineRCount ++ ;
							}
						}
						if ( lineRCount >= (endHigh - startHigh)/5*4 )  isFour = true;
					}
					else if (  upCompareValue >= 0 && downCompareValue >= 0 ) {
						for (int m = startHigh; m <= endHigh; m++){
							if ( wordRightWidth[m+1] - wordRightWidth[m] >= 0 ) {
								lineRCount ++ ;
							}
						}
						if ( lineRCount >= (endHigh - startHigh)/5*4 )  isFour = true;
					}
					else if (  upCompareValue <= 0 && downCompareValue <= 0 ) {
						for (int m = startHigh; m <= endHigh; m++){
							if ( wordRightWidth[m+1] - wordRightWidth[m] <= 0 ) {
								lineRCount ++ ;
							}
						}
						if ( lineRCount >= (endHigh - startHigh)/5*4 )  isFour = true;
					}
					
					if (isFour) {
						// to avoid the special 0
						if ( (upCompareValue == 0 || downCompareValue == 0) && isCircleEndDown )
							isFour = false;
						// to avoid the special 9
						if ( !isCircleEndDown && ( (upPartialWidthEnd - upPartialWidthStart)+2 > (downPartialWidthEnd - downPartialWidthStart) ) )
							isFour = false;
					}
				}
				
				// calculate the circle whether is for left and right line
				if (circleNumber == 0) {
					int compareValue = 0;
					if ( wordLeftWidth[startHigh+highCheck+1] > wordLeftWidth[endHigh-highCheck-1] ) 
						compareValue = -1;
					else
						compareValue = 1;
					for (int m = startHigh+highCheck+1; m < endHigh-highCheck-1; m++){
						if ((wordLeftWidth[m+1] - wordLeftWidth[m] == compareValue) || (wordLeftWidth[m+1] - wordLeftWidth[m] ==0)) {
							isLeftLine = true ;
						}
						else {
							isLeftLine = false ;
							break;
						}
					}	
					
					if ( wordRightWidth[startHigh+highCheck+1] > wordRightWidth[endHigh-highCheck-1] ) 
						compareValue = -1;
					else
						compareValue = 1;
					for (int m = startHigh+1; m < endHigh-highCheck-1; m++){
						if ((wordRightWidth[m+1] - wordRightWidth[m] == compareValue) || (wordRightWidth[m+1] - wordRightWidth[m] ==0)) {
							isRightLine = true ;
						}
						else {
							isRightLine = false ;
							break;
						}
					}
				}
	
				// make decision for the word
				if (wordSec == 1 || wordSec == 3){
	 				
	 				// to check whether there's a line 
					// calculate the circle whether is for left and right line
					int compareValue = 0;
					if ( wordLeftWidth[startHigh+highCheck+1] > wordLeftWidth[endHigh-highCheck-1] ) 
						compareValue = -1;
					else
						compareValue = 1;
					for (int m = startHigh+highCheck+1; m < endHigh-highCheck-1; m++){
						if ((wordLeftWidth[m+1] - wordLeftWidth[m] == compareValue) || (wordLeftWidth[m+1] - wordLeftWidth[m] ==0)) {
							isLeftLine = true ;
						}
						else {
							isLeftLine = false ;
							break;
						}
					}	
					
					boolean isRightLineFor7 = false, isRightLineFor4 =false;
					if ( wordRightWidth[startHigh+highCheck+1] > wordRightWidth[endHigh-highCheck-1] ) 
						compareValue = -1;
					else
						compareValue = 1;
					for (int m = startHigh+1; m < startHigh+(endHigh-startHigh)/2; m++){
						if ((wordRightWidth[m+1] - wordRightWidth[m] == compareValue) || (wordRightWidth[m+1] - wordRightWidth[m] ==0)) {
							isRightLineFor4 = true ;
						}
						else {
							isRightLineFor4 = false ;
							break;
						}
					}
					for (int m = startHigh+(endHigh-startHigh)/2; m < endHigh; m++){
						if ((wordRightWidth[m+1] - wordRightWidth[m] == compareValue) || (wordRightWidth[m+1] - wordRightWidth[m] ==0)) {
							isRightLineFor7 = true ;
						}
						else {
							isRightLineFor7 = false ;
							break;
						}
					}
	 				
					boolean isGot = false;
					if (circleNumber >= 2){
						code = code + "8";
					} 					
					else if(circleNumber == 1){
						if (isRightLineFor4 && ( endWidth - startWidth) >=2*( upPartialWidthEnd - upPartialWidthStart) ){
							code = code +  "4";
							isGot = true;
						}
						if (! isGot) {
							for (int m = (endHigh - startHigh)/2+startHigh; m <= endHigh; m++){
								if ( (wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordRightWidth[m+1] - wordRightWidth[m] >= 1) ) {
									code = code +  "4";
									isGot = true;
									break;
								}
							}
						}
						if (! isGot) {							
							for (int m = startHigh; m <= (endHigh - startHigh)/2+startHigh; m++){
								if ((wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordRightWidth[m+1] - wordRightWidth[m] <= -2) ) {
									code = code +  "6";
									isGot = true;
									break;
								}
							}	
						}
						if (! isGot) {							
							for (int m = (endHigh - startHigh)/2+startHigh; m < endHigh; m++){
								if ((wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordLeftWidth[m+1] - wordLeftWidth[m] >= 2) ) {
									code = code +  "9";
									isGot = true;
									break;
								}
							}
						}
						if (isGot == false) code = code +  "0";
					}
					else  {
						if (isRightLineFor4){
							for (int m = (endHigh - startHigh)/2+startHigh; m <= endHigh; m++){
								if ((wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordRightWidth[m+1] - wordRightWidth[m] >= 1) ) {
									code = code +  "1";
									isGot = true;
									break;
								}
							}	
						}
						if (! isGot && isRightLineFor7 && ( upPartialWidthEnd - upPartialWidthStart) >=2*( downPartialWidthEnd - downPartialWidthStart) ) {
							code = code + "7";
							isGot = true;
						}
						if (! isGot) {
							for (int m = (endHigh - startHigh)/2+startHigh; m <= endHigh; m++){
								if ((wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordRightWidth[m+1] - wordRightWidth[m] >= 2) ) {
									code = code + "2";
									isGot = true;
									break;
								}
							}	
						}
						if (! isGot) {								
							for (int m = startHigh; m < (endHigh - startHigh)/2+startHigh; m++){
								if ((wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordRightWidth[m+1] - wordRightWidth[m] <= -2) ) {
									code = code + "5";
									isGot = true;
									break;
								}
							}
						}
						if (isGot == false) code = code + "3";
					}

					
					if (wordSec == 3){
						// just check the top three words
						break;
					}
				}
				else if (wordSec == 2 ) {
//					if (sumBlack < 130)
//						code = code + "+";
//					else {
//						if (isJia && sumBlack < 162)
//							code = code + "+";
//						else {
							// to check the right part points again
							int lCount = 0, zCount = 0, bCount = 0, lastTypeValue = -2, currentTypeValue = -2;
							for (int m = startHigh; m < endHigh; m++){
								if ( wordRightWidth[m+1] - wordRightWidth[m] == 0) {
									zCount ++ ;
									// added for the last type
									if (lastTypeValue == -1)
										lCount ++ ;
									else if (lastTypeValue == 1)
										bCount ++ ;
									
									if (currentTypeValue == -2) {
										// initial
										lastTypeValue = 0;
										currentTypeValue = 0;
									}
									else if ( currentTypeValue != 0){
										// there is a change
										lastTypeValue = currentTypeValue;
									    currentTypeValue = 0;
									}
								}
								else if ( wordRightWidth[m+1] - wordRightWidth[m] == 1) {
									bCount ++ ;
									// added for the last zero type
									if (currentTypeValue == 0) {
										bCount = bCount + zCount;
										zCount = 0;
									}
									
									if (currentTypeValue == -2) {
										lastTypeValue = 1;
										currentTypeValue = 1;
									}
									else if ( currentTypeValue != 1){
										lastTypeValue = currentTypeValue;
									    currentTypeValue = 1;
									}
									// change the last type
									if (bCount == 2) lastTypeValue = 1;
											
									// for the change, reset the value
									if (lCount < 6) lCount = 0;
								}
								else if ( wordRightWidth[m+1] - wordRightWidth[m] == -1) {
									lCount ++ ;
									// added for the last zero type
									if (currentTypeValue == 0) {
										lCount = lCount + zCount;
										zCount = 0;
									}
									
									if (currentTypeValue == -2) {
										lastTypeValue = -1;
										currentTypeValue = -1;
									}
									else  if ( currentTypeValue != -1) {
										lastTypeValue = currentTypeValue;
										currentTypeValue = -1;
									}
									// change the last type
									if (lCount == 2) lastTypeValue = -1;
									
									// for the change, reset the value
									if (bCount < 6) bCount = 0;
								}
								else {
									lastTypeValue = -2; currentTypeValue = -2; zCount = 0; 
									if (lCount < 6) lCount = 0; 
									if (bCount < 6) bCount = 0; 
								}
							}	
							if (lCount >= (endHigh- startHigh)/2 ||
								bCount >= (endHigh- startHigh)/2	)
								code = code + "+";
							else
								code = code + "*";
//						}
//					}
				}
								
				// reset
				// print the value
				for (int m = 1; m < ih ; m++) { 
					sumByHigh[m] = 0;				
				}
				for (int n = startWidth; n < endWidth ; n++) { 
					sumByWidth[n] = 0;
				}

				wordBegin = false;
				wordEnd = false;
				startWidth = 0;
				endWidth = 0;
				startHigh = 0;
				endHigh = 0;
			}
		}	
		
		return code; 
	}
	
	
	public String guessNumberWord(BufferedImage bImage, boolean isNumber) { 
		int[] imagePixels = new int[iw * ih]; ;
		PixelGrabber pg = new PixelGrabber(bImage.getSource(), 0, 0, iw, ih, imagePixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		
		ColorModel cm = ColorModel.getRGBdefault(); 
		int red, green, blue;
		int[][] wordPoints = new int[40][ih];
		int[] sumByWidth = new int[iw];
		int[] sumByHigh = new int[ih];
		int[] wordLeftWidth = new int[ih], wordRightWidth = new int[ih];
		boolean wordBegin = false, wordEnd = false;
		int startWidth = 0, endWidth = 0;
		int startHigh = 0, endHigh = 0;
		String code = "";
		
		// start to scan the word 
		// width
		for (int i = 1, wordPointsWidth = 0; i < iw -1 ; i++) { 
			// high
			for (int j = 1; j < ih ; j++) { 
				red = cm.getRed(imagePixels[j*iw+i]); 
				green = cm.getGreen(imagePixels[j*iw+i]);
				blue = cm.getBlue(imagePixels[j*iw+i]);
				if (red + green + blue == 0){
					wordPoints[wordPointsWidth][j] = 1;
					if (startHigh == 0 || startHigh > j) startHigh = j;
					if (endHigh < j) endHigh = j;
					// sum the number of black points by column
					sumByWidth[i] += 1;
				}
			}
			
			// get the start point
			if (sumByWidth[i] != 0 && startWidth == 0) {
				wordBegin = true;
				startWidth = i;
			}
			if (wordBegin) wordPointsWidth++;
			
			// get the end point
			if (sumByWidth[i] == 0 && wordBegin == true && i < iw -1) {
				if (i - startWidth < 10) 
					// to avoid some special case
					;
				else {
					wordEnd = true;
					endWidth = i;
				}
			}
			
			int colorationNumber = 2;
			// get the whole word now
			if (wordBegin == true && wordEnd == true) {
				// word analyze now: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
				// circle: there is a part of points, which can be got the black point on 8 directions (米) 
				// 0: 1 circle, 1: nothing, 2: left semi-circle and right semi-circlecle, 3: 2 left semi-circle, 4: 1 cicle
				// 5: right semi-circle and left semi-circlecle, 6: right semi-cicle and circle, 7: left semi-circle
				// 8: 2 circle, 9: circle and left semi-circle
				
				// scan from up to down, and scan from left to right
				// condition: to black parts found, if the line before this is black, it maybe a initial of circle or semi-circle;
				//            otherwise, this is start of semi-circle;
				boolean isBlack = false, maybeFour = false;
				int blackTimes = 0, whiteTimesSeq = 0;
				int[][] whiteTimesPointsStart = new int[5][2];  // the white point's width/high
				int[][] whiteTimesPointsEnd = new int[5][2];
				int[][] circlesType = new int[5][1];  // type: circle/up semi-circle/ down semi-circle
				int circleCount = 0, upSemiCircleCount = 0, downSemiCircleCount = 0;
				int[][] circlesRowPoint = new int[5][2];  // white start/end
				boolean isPartOfCircle = false;
				int leftCountBlack = 0, rightCountBlack = 0;
 				for (int n = startHigh; n <= endHigh; n++ ) {
					// get the blackTimes for a row
					for (int m = 0; m <= (endWidth - startWidth); m++ ) {
						if (wordPoints[m][n] == 1 && ! isBlack){
							// count black times
							blackTimes ++;
							// record the white times end point
							// ignore the first time
							if (blackTimes > 1) {
								whiteTimesPointsEnd[whiteTimesSeq][0] = m-1;
								whiteTimesPointsEnd[whiteTimesSeq][1] = n;
								whiteTimesSeq ++;
							}
							isBlack = true;
						}
						else if (wordPoints[m][n] == 0 && isBlack){
							if (whiteTimesSeq < 5) {
								whiteTimesPointsStart[whiteTimesSeq][0] = m;
								whiteTimesPointsStart[whiteTimesSeq][1] = n;
							}
							isBlack = false;
						}
					} 
					if (blackTimes >= 2 ){
						// to check 
						for (int k = 0 ; k < blackTimes; k++) {
							// to check whether it belongs to one of the white points
							isPartOfCircle = false;
							for (int j = 0; j < 5; j++){
								if (circlesRowPoint[j][0] > 0 ){
									leftCountBlack = 0;
									rightCountBlack = 0;
									// -- wps -- cs -- wpe
									if (circlesRowPoint[j][0] >= whiteTimesPointsStart[k][0] && circlesRowPoint[j][0] <= whiteTimesPointsStart[k][1]) {
										// circle belongs found
										isPartOfCircle = true;

										for(int x = whiteTimesPointsStart[k][0]; x < circlesRowPoint[j][0]; x++){
											leftCountBlack = leftCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
										}
										// -- wps -- cs -- wpe -- ce
										if (circlesRowPoint[j][1] > whiteTimesPointsEnd[k][01]) {
											for(int x = whiteTimesPointsEnd[k][0]; x < circlesRowPoint[j][1]; x++){
												rightCountBlack = rightCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
											}
										}// -- wps -- cs -- ce --  wpe
										else if (circlesRowPoint[j][1] < whiteTimesPointsEnd[k][0]  ){
											for(int x = circlesRowPoint[j][1]; x < whiteTimesPointsEnd[k][0]; x++){
												rightCountBlack = rightCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
											}
										}
									}  // -- cs -- wps -- ce
									else if (circlesRowPoint[j][0] <= whiteTimesPointsStart[k][0] && circlesRowPoint[j][1] >= whiteTimesPointsStart[k][0] ){
										// circle belongs found
										isPartOfCircle = true;
										
										for(int x = circlesRowPoint[k][0]; x < whiteTimesPointsStart[k][0]; x++){
											leftCountBlack = leftCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
										}
										// -- cs -- wps-- wpe  -- ce
										if (circlesRowPoint[j][1] > whiteTimesPointsEnd[k][0]) {
											for(int x = whiteTimesPointsEnd[k][0]; x < circlesRowPoint[j][1]; x++){
												rightCountBlack = rightCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
											}
										}// -- cs -- wps-- ce-- wpe  
										else if (circlesRowPoint[j][1] < whiteTimesPointsEnd[k][0]  ){
											for(int x = circlesRowPoint[j][1]; x < whiteTimesPointsEnd[k][0]; x++){
												rightCountBlack = rightCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
											}
										}
									}
									else{
										;
									}
									// make decision for the circles
									if (leftCountBlack >= Math.abs(circlesRowPoint[j][0] - whiteTimesPointsStart[k][0]) &&
											rightCountBlack >= Math.abs(circlesRowPoint[j][1] - whiteTimesPointsStart[k][1])	){
										// to check whether the circle is end
										boolean isCircleEnd = true;
										for (int x = whiteTimesPointsStart[k][0]; x <= whiteTimesPointsEnd[k][0]; x++ ) {
											if (wordPoints[x][n] == 0 ){
												if (wordPoints[x][n+1] == 0) {
													isCircleEnd = false;  
													break;
												}
											}
										}
										if (isCircleEnd){
											if (circlesType[j][0] == 2){
												circleCount = circleCount + 1;
											}
											else if (circlesType[j][0] == 1){
												upSemiCircleCount = upSemiCircleCount + 1;
											}
											// to set the circlesRowPoint
											circlesType[j][0] = -1;
											circlesRowPoint[j][0] = 0;
											circlesRowPoint[j][1] = 0;
											
											// to check it's maybe 4
											if (n <= endHigh -2) {
												maybeFour = true;
											}
										}
										else {
											// to set the circlesRowPoint
											circlesRowPoint[j][0] = whiteTimesPointsStart[k][0];
											circlesRowPoint[j][1] = whiteTimesPointsStart[k][1];
										}
									}
									else {
										// to set the circlesRowPoint
										circlesRowPoint[j][0] = 0;
										circlesRowPoint[j][1] = 0;
									}
								}
							}
							// maybe a new circle
							if (!isPartOfCircle) {
								int typeValue = 2; // initial to  down semi-circle
								// to check the up line is black
								for (int j = whiteTimesPointsStart[k][0]; j <= whiteTimesPointsEnd[k][0]; j++ ) {
									if (wordPoints[j][n] == 0 ){
										if (wordPoints[j][n-1] == 0) {
											typeValue = 1;  // set to up semi-circle 
											break;
										}
									}
								}
								for (int j = 0; j <circlesRowPoint.length; j++ ){
									if (circlesRowPoint[j][0] == 0){
										circlesType[j][0] = typeValue;
										circlesRowPoint[j][0] = whiteTimesPointsStart[k][0];
										circlesRowPoint[j][1] = whiteTimesPointsEnd[k][0];
									}
								}
							}
						}
					}
				}
 				
 				// to make count for downSemiCircleCount
 				for (int j = 0; j < circlesType.length; j++){
 					if (circlesType[j][0] == 2){
 						downSemiCircleCount = downSemiCircleCount + 1;
 					}
 				}
 				
				// to make decision for the word by: circle, left line scan and right line scan
 				if (circleCount == 2 ){
 					code = "8";
 				}
 				else if (circleCount == 1 && upSemiCircleCount == 0 && downSemiCircleCount == 0  ){
 					code = "0";
 				} // or the 4's zero is higher than 0
 				else if (circleCount == 1 && ( upSemiCircleCount == 1 || downSemiCircleCount == 1 || maybeFour )  ){
 					code = "4";
 				}
// 				else if (circleCount == 1 && upSemiCircleCount == 0 && downSemiCircleCount == 1  ){
// 					code = "6";
// 				}
// 				else if (circleCount == 1 && upSemiCircleCount == 1 && downSemiCircleCount == 0  ){
//					code = "9";
//				}
 				else if (circleCount == 1 && upSemiCircleCount == 0 && downSemiCircleCount == 0  ){
 					code = "5";
 				}
 				else if (circleCount == 1 && upSemiCircleCount == 0 && downSemiCircleCount == 0  ){
 					code = "2";
 				}
 				else if (circleCount == 1 && upSemiCircleCount == 0 && downSemiCircleCount == 0  ){
 					code = "3";
 				}
 				else if (circleCount == 1 && upSemiCircleCount == 0 && downSemiCircleCount == 0  ){
 					code = "1";
 				}
 				else if (circleCount == 1 && upSemiCircleCount == 0 && downSemiCircleCount == 0  ){
 					code = "7";
 				}
 				else
 					code = "2"; // default value
				return code;
			}
		}
		
		return code;
	}
	
	public String guessWord(BufferedImage bImage, boolean isNumber) { 
		int[] imagePixels = new int[iw * ih]; ;
		PixelGrabber pg = new PixelGrabber(bImage.getSource(), 0, 0, iw, ih, imagePixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		
		ColorModel cm = ColorModel.getRGBdefault(); 
		int red, green, blue;
		int[] sumByWidth = new int[iw];
		int[] sumByHigh = new int[ih];
		int[] wordLeftWidth = new int[ih], wordRightWidth = new int[ih];
		boolean wordBegin = false, wordEnd = false;
		int startWidth = 0, endWidth = 0;
		int startHigh = 0, endHigh = 0;
		int wordSec = 0;
		String code = "";
		int[][] wordPoints = new int[iw][ih];
		
		try {
			FileOutputStream fout = new FileOutputStream("D:\\picGrey2.jpg");
			try {
				ImageIO.write(bImage, "jpg", fout);
				fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// start to scan the word 
		// width
		for (int i = 1; i < iw -1 ; i++) { 
			// high
			for (int j = 1; j < ih ; j++) { 
				red = cm.getRed(imagePixels[j*iw+i]); 
				green = cm.getGreen(imagePixels[j*iw+i]);
				blue = cm.getBlue(imagePixels[j*iw+i]);
				if (red + green + blue == 0){
					wordPoints[i][j] = 1;
					// sum the number of black points by column
					sumByWidth[i] += 1;
				}
			}
			
			// get the start point
			if (sumByWidth[i] != 0 && startWidth == 0) {
				wordBegin = true;
				startWidth = i;
			}
			
			// get the end point
			if (sumByWidth[i] == 0 && wordBegin == true && i < iw -1) {
				int nextWidthCount = 0;
				for (int j = 1; j < ih ; j++) { 
					red = cm.getRed(imagePixels[j*iw+i+1]); 
					green = cm.getGreen(imagePixels[j*iw+i+1]);
					blue = cm.getBlue(imagePixels[j*iw+i+1]);
					if (red + green + blue == 0){
						
						// sum the number of black points by column
						nextWidthCount += 1;
					}
				}
				if (i - startWidth < 15) 
					// to avoid the case: the second word JIA is partly scanned 
					;
				else {
					wordEnd = true;
					endWidth = i;
				}
			}
			
			// get the whole word now
			if (wordBegin == true && wordEnd == true) {
				// word
				wordSec += 1;
				int[][] word = new int[ih][endWidth];
				
				// high
				for (int m = 1; m < ih ; m++) { 
					// width					
					for (int n = startWidth; n < endWidth ; n++) { 
						red = cm.getRed(imagePixels[m*iw+n]); 
						green = cm.getGreen(imagePixels[m*iw+n]);
						blue = cm.getBlue(imagePixels[m*iw+n]);
						if (red + green + blue == 0){
							sumByHigh[m] += 1;
							word[m][n] = 1;
							if (startHigh == 0){
								startHigh = m;
							}
							if (wordLeftWidth[m] == 0) wordLeftWidth[m] = n; 
							if (wordRightWidth[m] < n) wordRightWidth[m] = n; 
						}
						if((n == (endWidth -1)) && startHigh>1 && sumByHigh[m] == 0 &&  (( endHigh-startHigh) < 4 ) ){
							endHigh = m-1;
							// to avoid the noise black point and other useless scan
							if ((endHigh-startHigh) > 4 ) break;
						}
					}
				}
				
				// the number of black/white times for each line of the word
				int blackTimesByRow = 0, whiteTimesByRow = 0; 
				// the number of circle times of the word
				int circleNumber = 0, circleHighUp = 0;
				// definition for the circle property, to check  0, 6, 8, 9,    and 4
				boolean isCircleStart = false, isCircleEnd = false, isCircleStartUp = false, isCircleEndDown = false, isCircleFound = false;
				boolean isFour = false, isLeftLine = false, isRightLine = false;
				// the circle up and down width
				int circleUpHigh = 0, circleDownHigh = 0;
				// the word's high is separated for five parts
				int highCheck = (endHigh - startHigh)/5;
				// the word's width is separated for 2 parts
				int widthCheck = (endWidth - startWidth)/3;
				// definition for whether the black happened in the four places, to check 2, 3, 5
				int upLeftCount = 0, upRightCount = 0, downLeftCount = 0, downRightCount = 0;
				boolean isLeftUp = false, isLeftDown = false, isRightUp = false, isRightDown = false;
                // the width for the up of 1/5 width, to decide whether the word is 1 or 7
				int upPartialWidthStart = 0, upPartialWidthEnd = 0, downPartialWidthStart = 0, downPartialWidthEnd = 0;
				// definition whether the word is JIA, to check JIA, CHENG
				boolean isJia = true;
				
				// the up and down start width, end width
				int upStartWidth = 0, upEndWidth = 0, downStartWidth = 0, downEndWidth = 0;
				for (int m = startHigh; m <= endHigh ; m++) { 
					// width
					for (int n = startWidth; n < endWidth ; n++) { 
						if (word[m][n] == 1){
							// up
							if (m < (startHigh + endHigh)/2 ){
								if (upStartWidth > n || upStartWidth == 0) upStartWidth = n;
								if (upEndWidth < n) upEndWidth = n;
							}
							else{    // down
								if (downStartWidth > n || downStartWidth == 0) downStartWidth = n;
								if (downEndWidth < n) downEndWidth = n;
							}
						}
					}
				}
				
				for (int m = startHigh; m <= endHigh ; m++) { 
					whiteTimesByRow = 0;
					blackTimesByRow = 0;
					// width
					for (int n = startWidth; n < endWidth ; n++) { 
						if (word[m][n] == 1){
							// count the black point to check whether the circle is to be started, ignore the cycle > 180
							blackTimesByRow = whiteTimesByRow + 1;
							
							// check the value of isLeftUp, isLeftDown, isRightUp, isRightDown
							if ( (m > startHigh + highCheck) &&  (m < startHigh + highCheck*2) &&  (n <= startWidth + widthCheck) ){
								isLeftUp = true;
							}
							else if ( (m > startHigh + highCheck) &&  (m < startHigh + highCheck*2) &&  (n >= startWidth + widthCheck*2) ){
								isRightUp = true;
							}
							else if ( (m > startHigh + highCheck*3) &&  (m < startHigh + highCheck*4) &&  (n >= startWidth + widthCheck*2) ){
								isRightDown = true;
							}
							
							// to check whether is's 1 or 7
							if (m <= startHigh + highCheck) {
								if (upPartialWidthStart > n || upPartialWidthStart == 0) upPartialWidthStart = n;
								if (upPartialWidthEnd < n ) upPartialWidthEnd = n;
							}
							// to check whether is's 2 or 7
							if (m >= startHigh + highCheck * 4) {
								if (downPartialWidthStart > n || downPartialWidthStart == 0) downPartialWidthStart = n;
								if (downPartialWidthEnd < n ) downPartialWidthEnd = n;
							}
							
							// count up / down 's  left / right black points 
							if (m < (startHigh + endHigh)/2 ){
								if (n < ( upStartWidth + upEndWidth )/2 ) 
									upLeftCount++;
								else if (n >( upStartWidth + upEndWidth )/2 ) 
									upRightCount++;
							}
							else if (m > (startHigh + endHigh)/2 ) {    // down
								if (n < ( downStartWidth + downEndWidth )/2 ) 
									downLeftCount++;
								else if (n > ( downStartWidth + downEndWidth )/2 ) 
									downRightCount++;
							}
							
							// set the circle found
							if (isCircleEnd) {
								isCircleFound = true;
								circleDownHigh = m;
							}
						}
						else {
							if (isCircleFound ){
								// such case, do nothing
							}
							else if (isCircleStart) {
								// initial the default value for each partial time  
								if (blackTimesByRow > whiteTimesByRow ) {
									isCircleEnd = true;
								}
								// to check whether the circle is end
								if (word[m+1][n] == 1){
									isCircleEnd = isCircleEnd && true;
								}
								else{
									isCircleEnd = false;
								}
								//double check whether the circle is end through checking whether up is OK
								boolean isCircleHighUpGot = false;
								for (int kk = m-1; kk >= circleHighUp; kk--) {
									if (word[kk][n] == 1) {
										isCircleHighUpGot = true;
										break;
									}
								}
								isCircleEnd = isCircleEnd && isCircleHighUpGot;
							}
							whiteTimesByRow = blackTimesByRow ;
						}
					}
					if (isCircleStart ){
						// check whether the second line is correct
						if ( blackTimesByRow < 2 ){
							isCircleStart = false;
							isCircleEnd = false;
							if (circleNumber == 0){
								isCircleStartUp = false;
								isCircleEndDown = false;
							}
						}
					}
					else if ( blackTimesByRow >= 2){
						boolean isCircleBeginCheck = false;
						boolean isChecked = false;
						int tmpBlackTimesByRow = 1;
						for (int n = startWidth; n < endWidth ; n++) { 
							if (word[m][n] == 1){
								if (isChecked && isCircleStart)  {
									circleHighUp = m-1;
									break; // circle start found
								}
								
								if (isChecked) tmpBlackTimesByRow += 1;
								// to start check the blank points again
								if (tmpBlackTimesByRow < blackTimesByRow) 
									isCircleBeginCheck = true;    
								else
									isCircleBeginCheck = false;
								
								isChecked = false;
							}
							else {
								if (isCircleBeginCheck){
									if (! isChecked) {
										isCircleStart = true;
										isChecked = true;
									}
									// check whether the above is black
									if (word[m-1][n] == 1){
										isCircleStart = isCircleStart && true;
									}
									else{
										isCircleStart = false;
									}
								}
							}
							if (isCircleStart && circleDownHigh == 0) circleUpHigh = m;
						}
					}
					
					// check whether it's JIA
					if (( m > startHigh+(endHigh - startHigh)/2 -2) && (  m < startHigh+(endHigh - startHigh)/2 +2 ) ){
						if (blackTimesByRow == 4) 
							isJia = isJia && true;
						else 
							isJia = false;
					}

					// check whether there is a circle
					if (isCircleStart == true && isCircleEnd == true ){					
						isCircleStart = false;
						isCircleEnd = false;
						isCircleFound = false;
						// check whether the first circle is start by up
						if (m >= endHigh - highCheck){
							isCircleEndDown = true;
						}
						circleNumber += 1;
					}
					
					if(isCircleStart) {
						// check whether the first circle is end by down
						if (( m < startHigh + 3) ){
							isCircleStartUp = true;
						}
					}
				}
				
				// count black number to distinct JIA / CHEN
				int sumBlack = 0;
				for (int k = 0; k < ih; k++){
					sumBlack = sumBlack + sumByHigh[k];
				}
				
				// make decision for the word
				code = null;
				if (isNumber){
					boolean isBlack = false, maybeFour = false;
					int blackTimes = 0, whiteTimesSeq = 0;
					int[][] whiteTimesPointsStart = new int[5][2];  // the white point's width/high
					int[][] whiteTimesPointsEnd = new int[5][2];
					int[][] circlesType = new int[5][1];  // type: circle/up semi-circle/ down semi-circle
					int circleCount = 0, upSemiCircleCount = 0, downSemiCircleCount = 0;
					int[][] circlesRowPoint = new int[5][2];  // white start/end
					boolean isPartOfCircle = false;
					int leftCountBlack = 0, rightCountBlack = 0;
	 				for (int n = startHigh; n <= endHigh; n++ ) {
						// get the blackTimes for a row
						for (int m = 0; m <= (endWidth - startWidth); m++ ) {
							if (wordPoints[m][n] == 1 && ! isBlack){
								// count black times
								blackTimes ++;
								// record the white times end point
								// ignore the first time
								if (blackTimes > 1) {
									whiteTimesPointsEnd[whiteTimesSeq][0] = m-1;
									whiteTimesPointsEnd[whiteTimesSeq][1] = n;
									whiteTimesSeq ++;
								}
								isBlack = true;
							}
							else if (wordPoints[m][n] == 0 && isBlack){
								if (whiteTimesSeq < 5) {
									whiteTimesPointsStart[whiteTimesSeq][0] = m;
									whiteTimesPointsStart[whiteTimesSeq][1] = n;
								}
								isBlack = false;
							}
						} 
						
						if (blackTimes >= 2 ){
							// to check 
							for (int k = 0 ; k < blackTimes; k++) {
								// to check whether it belongs to one of the white points
								isPartOfCircle = false;
								for (int j = 0; j < 5; j++){
									if (circlesRowPoint[j][0] > 0 ){
										leftCountBlack = 0;
										rightCountBlack = 0;
										// -- wps -- cs -- wpe
										if (circlesRowPoint[j][0] >= whiteTimesPointsStart[k][0] && circlesRowPoint[j][0] <= whiteTimesPointsStart[k][1]) {
											// circle belongs found
											isPartOfCircle = true;

											for(int x = whiteTimesPointsStart[k][0]; x < circlesRowPoint[j][0]; x++){
												leftCountBlack = leftCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
											}
											// -- wps -- cs -- wpe -- ce
											if (circlesRowPoint[j][1] > whiteTimesPointsEnd[k][01]) {
												for(int x = whiteTimesPointsEnd[k][0]; x < circlesRowPoint[j][1]; x++){
													rightCountBlack = rightCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
												}
											}// -- wps -- cs -- ce --  wpe
											else if (circlesRowPoint[j][1] < whiteTimesPointsEnd[k][0]  ){
												for(int x = circlesRowPoint[j][1]; x < whiteTimesPointsEnd[k][0]; x++){
													rightCountBlack = rightCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
												}
											}
										}  // -- cs -- wps -- ce
										else if (circlesRowPoint[j][0] <= whiteTimesPointsStart[k][0] && circlesRowPoint[j][1] >= whiteTimesPointsStart[k][0] ){
											// circle belongs found
											isPartOfCircle = true;
											
											for(int x = circlesRowPoint[k][0]; x < whiteTimesPointsStart[k][0]; x++){
												leftCountBlack = leftCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
											}
											// -- cs -- wps-- wpe  -- ce
											if (circlesRowPoint[j][1] > whiteTimesPointsEnd[k][0]) {
												for(int x = whiteTimesPointsEnd[k][0]; x < circlesRowPoint[j][1]; x++){
													rightCountBlack = rightCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
												}
											}// -- cs -- wps-- ce-- wpe  
											else if (circlesRowPoint[j][1] < whiteTimesPointsEnd[k][0]  ){
												for(int x = circlesRowPoint[j][1]; x < whiteTimesPointsEnd[k][0]; x++){
													rightCountBlack = rightCountBlack + wordPoints[x][n] + wordPoints[x][n-1];  
												}
											}
										}
										else{
											;
										}
										// make decision for the circles
										if (leftCountBlack >= Math.abs(circlesRowPoint[j][0] - whiteTimesPointsStart[k][0]) &&
												rightCountBlack >= Math.abs(circlesRowPoint[j][1] - whiteTimesPointsStart[k][1])	){
											// to check whether the circle is end
											boolean isCEnd = true;
											for (int x = whiteTimesPointsStart[k][0]; x <= whiteTimesPointsEnd[k][0]; x++ ) {
												if (wordPoints[x][n] == 0 ){
													if (wordPoints[x][n+1] == 0) {
														isCEnd = false;  
														break;
													}
												}
											}
											if (isCEnd){
												if (circlesType[j][0] == 2){
													circleCount = circleCount + 1;
												}
												else if (circlesType[j][0] == 1){
													upSemiCircleCount = upSemiCircleCount + 1;
												}
												// to set the circlesRowPoint
												circlesType[j][0] = -1;
												circlesRowPoint[j][0] = 0;
												circlesRowPoint[j][1] = 0;
												
												// to check it's maybe 4
												if (n <= endHigh -2) {
													maybeFour = true;
												}
											}
											else {
												// to set the circlesRowPoint
												circlesRowPoint[j][0] = whiteTimesPointsStart[k][0];
												circlesRowPoint[j][1] = whiteTimesPointsStart[k][1];
											}
										}
										else {
											// to set the circlesRowPoint
											circlesRowPoint[j][0] = 0;
											circlesRowPoint[j][1] = 0;
										}
									}
								}
								// maybe a new circle
								if (!isPartOfCircle) {
									int typeValue = 2; // initial to  down semi-circle
									// to check the up line is black
									for (int j = whiteTimesPointsStart[k][0]; j <= whiteTimesPointsEnd[k][0]; j++ ) {
										if (wordPoints[j][n] == 0 ){
											if (wordPoints[j][n-1] == 0) {
												typeValue = 1;  // set to up semi-circle 
												break;
											}
										}
									}
									for (int j = 0; j <circlesRowPoint.length; j++ ){
										if (circlesRowPoint[j][0] == 0){
											circlesType[j][0] = typeValue;
											circlesRowPoint[j][0] = whiteTimesPointsStart[k][0];
											circlesRowPoint[j][1] = whiteTimesPointsEnd[k][0];
										}
									}
								}
							}
						}
						
						// reset for the next row
						whiteTimesSeq = 0;  
						blackTimes = 0;
						for (int j = 0; j < whiteTimesPointsStart.length; j++){
							whiteTimesPointsStart[j][0] = 0;
							whiteTimesPointsStart[j][1] = 0;
							whiteTimesPointsEnd[j][0] = 0;
							whiteTimesPointsEnd[j][1] = 0;
						}
					}
	 				
	 				// to check whether there's a line 
	 			// calculate the circle whether is for left and right line
					int compareValue = 0;
					boolean isRightLineFor7 = false, isRightLineFor4 =false;
					try {
						if ( wordRightWidth[startHigh+highCheck+1] > wordRightWidth[endHigh-highCheck-1] ) 
							compareValue = -1;
						else
							compareValue = 1;
					}
					catch(Exception e){
						System.out.println("array out of bound");
						compareValue = 1;
					}
					for (int m = startHigh+1; m < startHigh+(endHigh-startHigh)/2; m++){
						if ((wordRightWidth[m+1] - wordRightWidth[m] == compareValue) || (wordRightWidth[m+1] - wordRightWidth[m] ==0)) {
							isRightLineFor4 = true ;
						}
						else {
							isRightLineFor4 = false ;
							break;
						}
					}
					for (int m = startHigh+(endHigh-startHigh)/2; m < endHigh; m++){
						if ((wordRightWidth[m+1] - wordRightWidth[m] == compareValue) || (wordRightWidth[m+1] - wordRightWidth[m] ==0)) {
							isRightLineFor7 = true ;
						}
						else {
							isRightLineFor7 = false ;
							break;
						}
					}
	 				
					if (circleNumber >= 2){
						code = "8";
					} 					
					else if(circleNumber == 1){
						for (int m = (endHigh - startHigh)/2+startHigh; m <= endHigh; m++){
							if (isRightLineFor4 || (wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordRightWidth[m+1] - wordRightWidth[m] >= 1) ) {
								code = "4";
								return code;
							}
						}	
						for (int m = startHigh; m <= (endHigh - startHigh)/2+startHigh; m++){
							if ((wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordRightWidth[m+1] - wordRightWidth[m] <= -2) ) {
								code = "6";
								return code;
							}
						}	
						for (int m = (endHigh - startHigh)/2+startHigh; m < endHigh; m++){
							if ((wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordLeftWidth[m+1] - wordLeftWidth[m] >= 2) ) {
								code = "9";
								return code;
							}
						}
						if (code == null) code = "0";
					}
					else  {
						if (isRightLineFor4 && ( endWidth - startWidth) >=2*( upPartialWidthEnd - upPartialWidthStart) ){
							code = "4";
							return code;
						}
						for (int m = (endHigh - startHigh)/2+startHigh; m <= endHigh; m++){
							if ( (wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordRightWidth[m+1] - wordRightWidth[m] >= 1) ) {
								code = "4";
								return code;
							}
						}
						if (isRightLineFor7 && ( upPartialWidthEnd - upPartialWidthStart) >2*( downPartialWidthEnd - downPartialWidthStart) ) {
							code = "7";
							return code;
						}
						
						for (int m = (endHigh - startHigh)/2+startHigh; m <= endHigh; m++){
							if ((wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordRightWidth[m+1] - wordRightWidth[m] >= 2) ) {
								code = "2";
								return code;
							}
						}	
						for (int m = startHigh; m < (endHigh - startHigh)/2+startHigh; m++){
							if ((wordRightWidth[m] != 0 || wordRightWidth[m+1] != 0 ) && (wordRightWidth[m+1] - wordRightWidth[m] <= -2) ) {
								code = "5";
								return code;
							}
						}
						if (code == null) code = "3";
					}
					
					return code;

				}
				else {
					// the word up width value
					int[] wordUpHigh = new int[iw];
					for (int m = startWidth ; m < endWidth  ; m++) { 
						// width
						for (int n = startHigh; n <= endHigh ; n++) { 
							if (word[n][m] == 1){
								// up
								wordUpHigh[m] = n;
								break;
							}
						}
					}
					// 乘: up part almost a line
					// 加: right part almost a line
					// 除: left part almost a line
					// 减: 
					
					// first to check the right part points 
					// second to check the up part points 
					// third to check the left part points 
					for (int calculateType = 0; calculateType < 3; calculateType++) {
						int lValue = 0, bValue = 0, lCount = 0, zCount = 0, bCount = 0, lastTypeValue = -2, currentTypeValue = -2;
						int startPoint = 0, endPoint = 0;
						int[] checkArray = null;
						
						// initial the type variables
						if (calculateType == 0) {
							startPoint = startHigh;
							endPoint = endHigh;
							checkArray = wordRightWidth;
						}
						else if (calculateType == 1){
							startPoint = startWidth;
							endPoint = endWidth-1;
							checkArray = wordUpHigh;
						}
						else if (calculateType == 2){
							startPoint = startHigh;
							endPoint = endHigh;
							checkArray = wordLeftWidth;
						}
						
						
						// start to check the line
						for (int m = startPoint; m < endPoint; m++){
							if ( checkArray[m+1] - checkArray[m] == 0) {
								// count the zero times in the small line
								zCount ++ ;
//								// added for the last type
//								if (lastTypeValue == -1)
//									lCount ++ ;
//								else if (lastTypeValue == 1)
//									bCount ++ ;
								
								if (currentTypeValue == -2) {
									// initial
									lastTypeValue = 0;
									currentTypeValue = 0;
								}
								else if ( currentTypeValue != 0){
									// there is a change
									lastTypeValue = currentTypeValue;
									currentTypeValue = 0;
								}
							}
							else if ( checkArray[m+1] - checkArray[m] == 1) {
								// count the 1 times 
								bCount ++ ;
								// added by the last zero type
								if (currentTypeValue == 0) {
									bCount = bCount + zCount;
									zCount = 0;
								}
								else if (currentTypeValue == -2) {
									lastTypeValue = 1;
									currentTypeValue = 1;
								}
								else if ( currentTypeValue != 1){
									lastTypeValue = currentTypeValue;
									currentTypeValue = 1;
								}
								// change the last type
								if (bCount == 2) lastTypeValue = 1;
								
								// for the change, get the max count value reset the value
								if (lValue < lCount) lValue = lCount;
								lCount = 0;
							}
							else if ( checkArray[m+1] - checkArray[m] == -1) {
								// count the -1 times 
								lCount ++ ;
								// added by the last zero type
								if (currentTypeValue == 0) {
									lCount = lCount + zCount;
									zCount = 0;
								}
								else if (currentTypeValue == -2) {
									lastTypeValue = -1;
									currentTypeValue = -1;
								}
								else  if ( currentTypeValue != -1) {
									lastTypeValue = currentTypeValue;
									currentTypeValue = -1;
								}
								// change the last type
								if (lCount == 2) lastTypeValue = -1;
								
								// for the change, get the max count value reset the value
								if (bValue < bCount) bValue = bCount;
								bCount = 0;
							}
							else {
								// reset
								lastTypeValue = -2; currentTypeValue = -2; zCount = 0; 
								if (lCount < 6) lCount = 0; 
								if (bCount < 6) bCount = 0; 
							}
						}	
						
						// check whether the part line is bigger than 8
						if (bValue < bCount) bValue = bCount;
						if (lValue < lCount) lValue = lCount;
						if (lValue >= (endPoint - startPoint)/2 || bValue >= (endPoint - startPoint)/2	) {
							if (calculateType == 0 )
								code = "加";
							else if (calculateType == 1 )
								code = "乘";
							else if (calculateType == 2 )
								code = "除";
							return code;
						}
						else if (calculateType == 2 )
							return "减";
					}
				}
			}
		}	
		
		return code; 
	}
	
	public void removeLowBase(int sumValue) { 
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels,0, iw);
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		//  remove the background point, which total value less than sumValue
		ColorModel cm = ColorModel.getRGBdefault(); 
		for (int i = 0; i < iw * ih; i++) { //四周的点去掉
			int red, green, blue;
			int alpha = cm.getAlpha(pixels[i]); 
			alpha = 255;

			if ( ( cm.getRed(pixels[i]) + cm.getGreen(pixels[i]) + cm.getBlue(pixels[i]) ) > sumValue ) {
					
					red = 255; 
					green = 255; 
					blue = 255; 
					pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; 
				}
				
		}
		//通过移位重新构成某一点像素的RGB值 } 
		// 将数组中的象素产生一个图像
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, pixels, 0, iw)); 
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
	}
		
	public BufferedImage getMedian() { 
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels, 0, iw); 
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) { e.printStackTrace(); } 
		
		// 对图像进行中值滤波，Alpha值保持不变
		ColorModel cm = ColorModel.getRGBdefault(); 
		for (int i = 1; i < ih - 1; i++) {
			for (int j = 1; j < iw - 1; j++) { 
				int red, green, blue; 
				int alpha = cm.getAlpha(pixels[i * iw + j]); 
				// int red2 = cm.getRed(pixels[(i - 1) * iw + j]);
				int red4 = cm.getRed(pixels[i * iw + j - 1]); 
				int red5 = cm.getRed(pixels[i * iw + j]);
				int red6 = cm.getRed(pixels[i * iw + j + 1]);
				
				// int red8 = cm.getRed(pixels[(i + 1) * iw + j]);
				// 水平方向进行中值滤波 
				if (red4 >= red5) {
					if (red5 >= red6) {
						red = red5; 
					} else {
						if (red4 >= red6) {
							red = red6;
						} else { 
							red = red4; 
						} 
					} 
				} else {
					if (red4 > red6) {
						red = red4; 
					} else { 
						if (red5 > red6) {
							red = red6;
						} else {
							red = red5; 
						}
					} 
				} 
				
				int green4 = cm.getGreen(pixels[i * iw + j - 1]); 
				int green5 = cm.getGreen(pixels[i * iw + j]); 
				int green6 = cm.getGreen(pixels[i * iw + j + 1]);
				
				// 水平方向进行中值滤波
				if (green4 >= green5) { 
					if (green5 >= green6) {
						green = green5;
					} else {
						if (green4 >= green6) {
							green = green6; 
						} else { 
							green = green4;
						} 
					} 
				} else { 
					if (green4 > green6) { 
						green = green4; 
					} else { 
						if (green5 > green6) {
							green = green6; 
						} else { 
							green = green5;
						}
					} 
				} 
				
				// int blue2 = cm.getBlue(pixels[(i - 1) * iw + j]); 
				int blue4 = cm.getBlue(pixels[i * iw + j - 1]); 
				int blue5 = cm.getBlue(pixels[i * iw + j]); 
				int blue6 = cm.getBlue(pixels[i * iw + j + 1]); 
				
				// int blue8 = cm.getBlue(pixels[(i + 1) * iw + j]); 
				// 水平方向进行中值滤波 
				if (blue4 >= blue5) {
					if (blue5 >= blue6) { 
						blue = blue5; 
					} else { 
						if (blue4 >= blue6) {
							blue = blue6;
						} else { 
							blue = blue4;
						} 
					} 
				} else { 
					if (blue4 > blue6) {
						blue = blue4; 
					} else { 
						if (blue5 > blue6) { 
							blue = blue6;
						} else {
							blue = blue5;
						} 
					} 
				} 
				pixels[i * iw + j] = alpha << 24 | red << 16 | green << 8 | blue; 
			}
		} 
		
		// 将数组中的象素产生一个图像 
		
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, pixels, 0, iw));
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
		return image; 
	} 
			
	public String analyseTiltWord(){
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels, 0, iw); 
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) { e.printStackTrace(); }
		
		ColorModel cm = ColorModel.getRGBdefault(); 
		int red, green, blue;
		int[] sumByWidth = new int[iw];
		int[] sumByHigh = new int[ih];
		boolean wordBegin = false, wordEnd = false;
		int startWidth = 0, endWidth = 0;
		int startHigh = 0, endHigh = 0;
		int wordSec = 0;
		String codeValue = "";
		
		// start to scan the word 
		// width
		for (int i = 1; i < iw -1 ; i++) { 
			// high
			for (int j = 1; j < ih ; j++) { 
				red = cm.getRed(pixels[j*iw+i]); 
				green = cm.getGreen(pixels[j*iw+i]);
				blue = cm.getBlue(pixels[j*iw+i]);
				if (red + green + blue == 0){
					// sum the number of black points by column
					sumByWidth[i] += 1;
				}
			}
			
			// get the start point
			if (sumByWidth[i] != 0 && startWidth == 0) {
				wordBegin = true;
				startWidth = i;
			}
			
			// get the end point
			if (sumByWidth[i] == 0 && wordBegin == true) {
				if (wordSec == 1 && (i - startWidth ) < 15) 
					// to avoid the case: the second word JIA is partly scanned 
					;
				else {
					wordEnd = true;
					endWidth = i;
				}
			}
			
			// get the whole word now
			if (wordBegin == true && wordEnd == true) {
				int[] tmpPixels=new int[iw*ih];
				int[] analyzePixels = new int[iw*ih];
				// initial
				for (int kk = 0; kk < iw ; kk++) { 
					// high
					for (int jj = 0; jj < ih ; jj++) { 
						tmpPixels[jj*iw+kk] = -1;
						analyzePixels[jj*iw+kk] = -1;
					}
				}
				
				// word
				wordSec += 1;

				int[] wordHighUpPosition = new int[iw]; 
				int[] wordHighDownPosition = new int[iw];
				
				// high
				for (int m = 1; m < ih ; m++) { 
					// width					
					for (int n = startWidth; n < endWidth ; n++) { 
						red = cm.getRed(pixels[m*iw+n]); 
						green = cm.getGreen(pixels[m*iw+n]);
						blue = cm.getBlue(pixels[m*iw+n]);
						if (red + green + blue == 0){
							sumByHigh[m] += 1;
							tmpPixels[m*iw+n] = 0;
							if (startHigh == 0){
								startHigh = m;
							}
							if (wordHighUpPosition[n] == 0 ) 
								wordHighUpPosition[n] = m;
							else
								wordHighDownPosition[n] = m;
						}
						if((n == (endWidth -1)) && startHigh>1 && sumByHigh[m] == 0 &&  (( endHigh-startHigh) < 4 ) ){
							endHigh = m-1;
							// to avoid the noise black point and other useless scan
							if ((endHigh-startHigh) > 4 ) break;
						}
					}
				}

				// analyze word 
				// calculate points' D-value times
				int[] compareValue = new int[endWidth - startWidth];
				int[] countLeftDTimes = new int[7]; // left direction: count the 0 times for D-Value 0
				int[] countRightDTimes = new int[7]; // right direction: count the 0 times for D-Value 0
				boolean isLeft = true;
				int zeroCount = 0;
				for (int k = 0; k < compareValue.length; k++) { 
					if (wordHighUpPosition[startWidth+k] == 1) continue; // ignore such case
					compareValue[k] = wordHighUpPosition[startWidth+k+1] - wordHighUpPosition[startWidth+k];
					if (compareValue[k] == 0) {
						zeroCount ++;
					}
					else if (compareValue[k] >= 1) {
						if (zeroCount >= 7)
							countLeftDTimes[6] ++;
						else if (zeroCount != 0)
							countLeftDTimes[zeroCount] ++;
						zeroCount = 0;
						isLeft = true;
					}
					else if (compareValue[k] <= -1) {
						if (zeroCount >= 7)
							countRightDTimes[6] ++;
						else if (zeroCount != 0)
							countRightDTimes[zeroCount] ++;
						zeroCount = 0;
						isLeft = false;
					}
				}
				// to cover the last time
				if (zeroCount >= 7) {
					if (isLeft) 
						countLeftDTimes[6] ++;
					else
						countRightDTimes[6] ++;
				}
				else{
					if (isLeft) 
						countLeftDTimes[zeroCount] ++;
					else
						countRightDTimes[zeroCount] ++;
				}
				
				// calculate rotate angle
				int wordMaxHighUpPosition = 80, wordMaxHighDownPosition = 0;
				for (int k = startWidth; k <= endWidth ; k++) { 
					if ( wordMaxHighUpPosition > wordHighUpPosition[k] ) wordMaxHighUpPosition = wordHighUpPosition[k];
					if ( wordMaxHighDownPosition < wordHighDownPosition[k] ) wordMaxHighDownPosition = wordHighDownPosition[k];
				}

				int gapValue = wordHighUpPosition[startWidth] - wordHighUpPosition[endWidth];
				boolean isRotate = true;
				// count the D-value
				int rotateDegree = 0 , rotateLeftDegree = 0, countLeftMax = 0, maxLeftPos = 0, rotateRightDegree = 0, countRightMax = 0, maxRightPos = 0;
				for (int k = 0; k < 7; k++) {
					if (countLeftDTimes[k] > countLeftMax ) {
						countLeftMax = countLeftDTimes[k];
						maxLeftPos = k;
					}
					if (countRightDTimes[k] > countRightMax ) {
						countRightMax = countRightDTimes[k];
						maxRightPos = k;
					}
				}
				// for left
				if (countLeftDTimes[6] > 0 )
					rotateLeftDegree = 0;
				else if (countLeftDTimes[6] > 0 )
					rotateLeftDegree = 4;
				else if (countLeftDTimes[4] > 0)
					rotateLeftDegree = 8;
				else {
					if (maxLeftPos == 3)
						rotateLeftDegree = 12;
					else if (maxLeftPos == 2)
						rotateLeftDegree = 16;
					else if (maxLeftPos == 1)
						rotateLeftDegree = 20;
					else if (maxLeftPos == 0)
						rotateLeftDegree = 24;
				}
				// for right
				if (countRightDTimes[6] > 0 )
					rotateRightDegree = 0;
				else if (countRightDTimes[6] > 0 )
					rotateRightDegree = -4;
				else if (countRightDTimes[4] > 0)
					rotateRightDegree = -8;
				else {
					if (maxRightPos == 3)
						rotateRightDegree = -12;
					else if (maxRightPos == 2)
						rotateRightDegree = -16;
					else if (maxRightPos == 1)
						rotateRightDegree = -20;
					else if (maxRightPos == 0)
						rotateRightDegree = -24;
				}
				
				if ( gapValue > 2 ) {
					// left rotate
					if ( wordHighUpPosition[startWidth] < wordHighUpPosition[endWidth] +3 )
						rotateDegree = 0-rotateLeftDegree;     // some word special, both points in up part
					else
						rotateDegree = rotateLeftDegree;
				}
				else if ( gapValue < -2 ){
					// right rotate
					if ( wordHighUpPosition[startWidth] < wordHighUpPosition[endWidth] + 3 )
						rotateDegree = 0-rotateRightDegree;     // some word special, both points in up part
					else
						rotateDegree = rotateRightDegree;
				}
				else {
					isRotate = false;
				}
				if (rotateDegree == 0) isRotate = false;

				int[] result = new int[2];
				// rotate the word points
				for (int k = startWidth; k <= endWidth ; k++) { 
					// high
					for (int j = 0; j < ih ; j++) { 
						if (tmpPixels[j*iw+k] == 0 ){
							if ( isRotate ) {
								result = rotatePoint(rotateDegree, startWidth, 0, k, j);
								try {
									analyzePixels[result[1]*iw+result[0]] = 0;
								} catch (Exception e) {
									
								}
							}
							else 
								analyzePixels[j*iw+k] = 0;
						}
					}
				}


				Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, analyzePixels, 0, iw));
				BufferedImage bImage = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_RGB ); 
				bImage.createGraphics().drawImage(tempImg, 0, 0, null); 
				
				codeValue = codeValue + getCode("chi_sim", bImage);
				
				// reset
				// print the value
				for (int m = 1; m < ih ; m++) { 
					sumByHigh[m] = 0;				
				}
				for (int n = startWidth; n < endWidth ; n++) { 
					sumByWidth[n] = 0;
				}

				wordBegin = false;
				wordEnd = false;
				startWidth = 0;
				endWidth = 0;
				startHigh = 0;
				endHigh = 0;
			}
		}

		return codeValue;
	}
	
	public String analyseTiltNumber(){
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels, 0, iw); 
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) { e.printStackTrace(); }
		
		ColorModel cm = ColorModel.getRGBdefault(); 
		int red, green, blue;
		int[] sumByWidth = new int[iw];
		int[] sumByHigh = new int[ih];
		boolean wordBegin = false, wordEnd = false;
		int startWidth = 0, endWidth = 0;
		int startHigh = 0, endHigh = 0;
		int wordSec = 0;
		String codeValue = "";
		
		// start to scan the word 
		// width
		for (int i = 1; i < iw -1 ; i++) { 
			// high
			for (int j = 1; j < ih ; j++) { 
				red = cm.getRed(pixels[j*iw+i]); 
				green = cm.getGreen(pixels[j*iw+i]);
				blue = cm.getBlue(pixels[j*iw+i]);
				if (red + green + blue == 0){
					// sum the number of black points by column
					sumByWidth[i] += 1;
				}
			}
			
			// get the start point
			if (sumByWidth[i] != 0 && startWidth == 0) {
				wordBegin = true;
				startWidth = i;
			}
			
			// get the end point
			if (sumByWidth[i] == 0 && wordBegin == true) {
				if (wordSec == 1 && (i - startWidth ) < 15) 
					// to avoid the case: the second word JIA is partly scanned 
					;
				else {
					wordEnd = true;
					endWidth = i;
				}
			}
			
			// get the whole word now
			if (wordBegin == true && wordEnd == true) {
				int[] tmpPixels=new int[iw*ih];
				int[] analyzePixels = new int[iw*ih];
				// initial
				for (int kk = 0; kk < iw ; kk++) { 
					// high
					for (int jj = 0; jj < ih ; jj++) { 
						tmpPixels[jj*iw+kk] = -1;
						analyzePixels[jj*iw+kk] = -1;
					}
				}
				
				// word
				wordSec += 1;

				int[] wordHighUpPosition = new int[iw]; 
				int[] wordHighDownPosition = new int[iw];
				
				// high
				for (int m = 1; m < ih ; m++) { 
					// width					
					for (int n = startWidth; n < endWidth ; n++) { 
						red = cm.getRed(pixels[m*iw+n]); 
						green = cm.getGreen(pixels[m*iw+n]);
						blue = cm.getBlue(pixels[m*iw+n]);
						if (red + green + blue == 0){
							sumByHigh[m] += 1;
							tmpPixels[m*iw+n] = 0;
							if (startHigh == 0){
								startHigh = m;
							}
							if (wordHighUpPosition[n] == 0 ) 
								wordHighUpPosition[n] = m;
							else
								wordHighDownPosition[n] = m;
						}
						if((n == (endWidth -1)) && startHigh>1 && sumByHigh[m] == 0 &&  (( endHigh-startHigh) < 4 ) ){
							endHigh = m-1;
							// to avoid the noise black point and other useless scan
							if ((endHigh-startHigh) > 4 ) break;
						}
					}
				}

				// analyze word 
				// calculate points' D-value times
				int[] compareValue = new int[endWidth - startWidth];
				int[] countLeftDTimes = new int[7]; // left direction: count the 0 times for D-Value 0
				int[] countRightDTimes = new int[7]; // right direction: count the 0 times for D-Value 0
				boolean isLeft = true;
				int zeroCount = 0;
				for (int k = 0; k < compareValue.length; k++) { 
					if (wordHighUpPosition[startWidth+k] == 1) continue; // ignore such case
					compareValue[k] = wordHighUpPosition[startWidth+k+1] - wordHighUpPosition[startWidth+k];
					if (compareValue[k] == 0) {
						zeroCount ++;
					}
					else if (compareValue[k] >= 1) {
						if (zeroCount >= 7)
							countLeftDTimes[6] ++;
						else if (zeroCount != 0)
							countLeftDTimes[zeroCount] ++;
						zeroCount = 0;
						isLeft = true;
					}
					else if (compareValue[k] <= -1) {
						if (zeroCount >= 7)
							countRightDTimes[6] ++;
						else if (zeroCount != 0)
							countRightDTimes[zeroCount] ++;
						zeroCount = 0;
						isLeft = false;
					}
				}
				// to cover the last time
				if (zeroCount >= 7) {
					if (isLeft) 
						countLeftDTimes[6] ++;
					else
						countRightDTimes[6] ++;
				}
				else{
					if (isLeft) 
						countLeftDTimes[zeroCount] ++;
					else
						countRightDTimes[zeroCount] ++;
				}
				
				// calculate rotate angle
				int wordMaxHighUpPosition = 80, wordMaxHighDownPosition = 0;
				for (int k = startWidth; k <= endWidth ; k++) { 
					if ( wordMaxHighUpPosition > wordHighUpPosition[k] ) wordMaxHighUpPosition = wordHighUpPosition[k];
					if ( wordMaxHighDownPosition < wordHighDownPosition[k] ) wordMaxHighDownPosition = wordHighDownPosition[k];
				}

				int gapValue = wordHighUpPosition[startWidth] - wordHighUpPosition[endWidth];
				boolean isRotate = true;
				// count the D-value
				int rotateDegree = 0 , rotateLeftDegree = 0, countLeftMax = 0, maxLeftPos = 0, rotateRightDegree = 0, countRightMax = 0, maxRightPos = 0;
				for (int k = 0; k < 7; k++) {
					if (countLeftDTimes[k] > countLeftMax ) {
						countLeftMax = countLeftDTimes[k];
						maxLeftPos = k;
					}
					if (countRightDTimes[k] > countRightMax ) {
						countRightMax = countRightDTimes[k];
						maxRightPos = k;
					}
				}
				// for left
				if (countLeftDTimes[6] > 0 )
					rotateLeftDegree = 0;
				else if (countLeftDTimes[6] > 0 )
					rotateLeftDegree = 4;
				else if (countLeftDTimes[4] > 0)
					rotateLeftDegree = 8;
				else {
					if (maxLeftPos == 3)
						rotateLeftDegree = 12;
					else if (maxLeftPos == 2)
						rotateLeftDegree = 16;
					else if (maxLeftPos == 1)
						rotateLeftDegree = 20;
					else if (maxLeftPos == 0)
						rotateLeftDegree = 24;
				}
				// for right
				if (countRightDTimes[6] > 0 )
					rotateRightDegree = 0;
				else if (countRightDTimes[6] > 0 )
					rotateRightDegree = -4;
				else if (countRightDTimes[4] > 0)
					rotateRightDegree = -8;
				else {
					if (maxRightPos == 3)
						rotateRightDegree = -12;
					else if (maxRightPos == 2)
						rotateRightDegree = -16;
					else if (maxRightPos == 1)
						rotateRightDegree = -20;
					else if (maxRightPos == 0)
						rotateRightDegree = -24;
				}
				
				if ( gapValue > 2 ) {
					// left rotate
					if ( wordHighUpPosition[startWidth] < wordHighUpPosition[endWidth] +3 )
						rotateDegree = 0-rotateLeftDegree;     // some word special, both points in up part
					else
						rotateDegree = rotateLeftDegree;
				}
				else if ( gapValue < -2 ){
					// right rotate
					if ( wordHighUpPosition[startWidth] < wordHighUpPosition[endWidth] + 3 )
						rotateDegree = 0-rotateRightDegree;     // some word special, both points in up part
					else
						rotateDegree = rotateRightDegree;
				}
				else {
					isRotate = false;
				}
				if (rotateDegree == 0) isRotate = false;

				int[] result = new int[2];
				// rotate the word points
				for (int k = startWidth; k <= endWidth ; k++) { 
					// high
					for (int j = 0; j < ih ; j++) { 
						if (tmpPixels[j*iw+k] == 0 ){
							if ( isRotate ) {
								result = rotatePoint(rotateDegree, startWidth, 0, k, j);
								try {
									analyzePixels[result[1]*iw+result[0]] = 0;
								} catch (Exception e) {
									
								}
							}
							else 
								analyzePixels[j*iw+k] = 0;
						}
					}
				}


				Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, analyzePixels, 0, iw));
				BufferedImage bImage = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_RGB ); 
				bImage.createGraphics().drawImage(tempImg, 0, 0, null); 
				
				codeValue = codeValue + getCode("chi_sim", bImage);
				
				// reset
				// print the value
				for (int m = 1; m < ih ; m++) { 
					sumByHigh[m] = 0;				
				}
				for (int n = startWidth; n < endWidth ; n++) { 
					sumByWidth[n] = 0;
				}

				wordBegin = false;
				wordEnd = false;
				startWidth = 0;
				endWidth = 0;
				startHigh = 0;
				endHigh = 0;
			}
		}

		return codeValue;
	}
	
	public String filterByColorCount(){
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels, 0, iw); 
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) { e.printStackTrace(); }
			
		//统计出有几种颜色的字符，分类抽取
		String codeValue = "";
		int colorNum = 240, wordSeq = 1;
		float[] color=new float[colorNum];
		int[] colorcount=new int[colorNum];
		float lastColor = 0f;
		int lastWidth = 0;
		ColorModel cm = ColorModel.getRGBdefault(); 
		
		// scan the high 
		for (int i = 0; i < iw-1  ; i++) { 
			// high
			for (int j = 0; j < ih-1 ; j++) { 
				int red = cm.getRed(pixels[iw*j+i]);
				int green  = cm.getGreen(pixels[iw*j+i]);
				int blue = cm.getBlue(pixels[iw*j+i]);
				
				if (red + green + blue > 700 ) continue;
				
				float[] hsbValue = rgb2hls(red, green, blue );
				if (lastColor > 0.0f) {
					// ignore the last color value
					// need to consider the color circle, like the relation 230 and 5
					int tmp = (int)Math.abs( Math.round( lastColor - hsbValue[0]) );
					if ( tmp < 15) continue;
					else if ( tmp > 225) continue;
				}
				
				for (int k = 0; k < colorNum - 1; k++) {
					if (color[k] == 0.0) {
						color[k] = hsbValue[0];
						colorcount[k]++;
						break;
					}
					else if ((int)Math.abs( Math.round( color[k] - hsbValue[0]) ) < 2 ) {
						colorcount[k]++;
						break;
					}
				}
			} 
			// to check whether the word is started
			for (int k = 0; k < colorNum-1; k++){
				if (colorcount[k] + colorcount[k+1] > 10 ){
					float currentWordColor = color[k+1];
					if (colorcount[k+1] == 0) currentWordColor = color[k]; // special case, just the only one color value got
					
					// get the word image
					BufferedImage bImage = filterImageByHSBColor(lastWidth, currentWordColor);
					
					if (bImage == null) {
						System.out.println("bImage Error: NULL");
						return "";
					}
					if (wordSeq == 1 ){
						// Analyze the word
						codeValue = codeValue + guessWord(bImage, true);
						wordSeq ++;
					}
					else if (wordSeq == 2) {
						// to check whether it's 加,减,乘,除
						if (true) {
							codeValue = codeValue + guessWord(bImage, false);
							wordSeq ++;
						}
//						else{
//							// do not need add the wordSeq at this time
//							codeValue = codeValue + calculateJXPoint(bImage);
//						}
					}
					else if (wordSeq == 3) {
						// do not need to analyze the word
						wordSeq++;
					}
					else if (wordSeq == 4){
						// Analyze the word
						codeValue = codeValue + guessWord(bImage, true);
						return codeValue;
					}

					//reset
					lastColor = currentWordColor;
					lastWidth = i;
					for (int m = 0; m < colorNum - 1; m++){
						color[m] = 0.0f;
						colorcount[m] = 0;
					}
					break; // terminate to get next word
				}
			}
		}
		
		return codeValue;
	}
	
	public BufferedImage filterImageByHSBColor(int startWidth, float currentWordColor){
		ColorModel cm = ColorModel.getRGBdefault(); 
		// set the picture value
		int[] sumByWidth = new int[iw];
		int[] wordHighUpPosition = new int[iw]; 
		int[] wordHighDownPosition = new int[iw];
		int[] outPixels=new int[iw*ih];
		float[] pixelsColor=new float[iw*ih];
		int wordLeftWidth = 0, wordRightWidth = 0;
		
		for(int m = startWidth; m < iw  ; m++){
			for (int n = 0; n < ih ; n++) { 
				int red = cm.getRed(pixels[iw*n+m]);
				int green  = cm.getGreen(pixels[iw*n+m]);
				int blue = cm.getBlue(pixels[iw*n+m]);

				outPixels[n*iw+m] = -1;

				float[] hsbValue = rgb2hls(red, green, blue );
				// calculate the color value minus
				int tmp = (int)Math.abs( Math.round( currentWordColor - hsbValue[0]) );
					
				// ignore the other color value
				// count the point by width 
//				if (tmp < 10 || tmp > 230){
				if (tmp < 25 || tmp > 210){	
					// ignore the white color
					if( red + green + blue == 765) 
						;
					else {						
						if (wordLeftWidth == 0) wordLeftWidth = m;
						outPixels[n*iw+m] = pixels[iw*n+m];
						pixelsColor[n*iw+m] = hsbValue[0];
						
						sumByWidth[m] += 1;
						if (wordHighUpPosition[m] == 0 ) 
							wordHighUpPosition[m] = n;
						else
							wordHighDownPosition[m] = n;
					}
				}
			
				// find the word position and analyze word 
				if (wordLeftWidth != 0 && n == ih-1 && sumByWidth[m] == 0 && m - wordLeftWidth > 15 ) {
					wordRightWidth = m-1;
					
					float[] tmpPixels=new float[iw*ih];
//					int[] analyzePixels = new int[iw*ih];
//					for (int x = 0; x < iw ; x++) { 
//						// high
//						for (int j = 0; j < ih ; j++) { 
//							analyzePixels[j*iw+x] = -1;
//						}
//					}
						
					// calculate points' D-value times
					int[] compareValue = new int[wordRightWidth - wordLeftWidth];
					int[] countLeftDTimes = new int[7]; // left direction: count the 0 times for D-Value 0
					int[] countRightDTimes = new int[7]; // right direction: count the 0 times for D-Value 0
					boolean isLeft = true;
					int zeroCount = 0;
					for (int x = 0; x < wordRightWidth - wordLeftWidth; x++) { 
						if (wordHighUpPosition[wordLeftWidth+x] == 1) continue; // ignore such case
						compareValue[x] = wordHighUpPosition[wordLeftWidth+x+1] - wordHighUpPosition[wordLeftWidth+x];
						if (compareValue[x] == 0) {
							zeroCount ++;
						}
						else if (compareValue[x] >= 1) {
							if (zeroCount >= 7)
								countLeftDTimes[6] ++;
							else if (zeroCount != 0)
								countLeftDTimes[zeroCount] ++;
							zeroCount = 0;
							isLeft = true;
						}
						else if (compareValue[x] <= -1) {
							if (zeroCount >= 7)
								countRightDTimes[6] ++;
							else if (zeroCount != 0)
								countRightDTimes[zeroCount] ++;
							zeroCount = 0;
							isLeft = false;
						}
					}
					// to cover the last time
					if (zeroCount >= 7) {
						if (isLeft) 
							countLeftDTimes[6] ++;
						else
							countRightDTimes[6] ++;
					}
					else{
						if (isLeft) 
							countLeftDTimes[zeroCount] ++;
						else
							countRightDTimes[zeroCount] ++;
					}
					
					// calculate rotate angle
					int wordMaxHighUpPosition = 80, wordMaxHighDownPosition = 0;
					for (int x = wordLeftWidth; x <= wordRightWidth ; x++) { 
						if ( wordMaxHighUpPosition > wordHighUpPosition[x] ) wordMaxHighUpPosition = wordHighUpPosition[x];
						if ( wordMaxHighDownPosition < wordHighDownPosition[x] ) wordMaxHighDownPosition = wordHighDownPosition[x];
					}

					int gapValue = wordHighUpPosition[wordLeftWidth] - wordHighUpPosition[wordRightWidth];
					boolean isRotate = true;
					// count the D-value
					int rotateDegree = 0 , rotateLeftDegree = 0, countLeftMax = 0, maxLeftPos = 0, rotateRightDegree = 0, countRightMax = 0, maxRightPos = 0;
					for (int x = 0; x < 7; x++) {
						if (countLeftDTimes[x] > countLeftMax ) {
							countLeftMax = countLeftDTimes[x];
							maxLeftPos = x;
						}
						if (countRightDTimes[x] > countRightMax ) {
							countRightMax = countRightDTimes[x];
							maxRightPos = x;
						}
					}
					// for left
					if (countLeftDTimes[6] > 0 )
						rotateLeftDegree = 0;
					else if (countLeftDTimes[6] > 0 )
						rotateLeftDegree = 4;
					else if (countLeftDTimes[4] > 0)
						rotateLeftDegree = 8;
					else {
						if (maxLeftPos == 3)
							rotateLeftDegree = 12;
						else if (maxLeftPos == 2)
							rotateLeftDegree = 16;
						else if (maxLeftPos == 1)
							rotateLeftDegree = 20;
						else if (maxLeftPos == 0)
							rotateLeftDegree = 24;
					}
					// for right
					if (countRightDTimes[6] > 0 )
						rotateRightDegree = 0;
					else if (countRightDTimes[6] > 0 )
						rotateRightDegree = -4;
					else if (countRightDTimes[4] > 0)
						rotateRightDegree = -8;
					else {
						if (maxRightPos == 3)
							rotateRightDegree = -12;
						else if (maxRightPos == 2)
							rotateRightDegree = -16;
						else if (maxRightPos == 1)
							rotateRightDegree = -20;
						else if (maxRightPos == 0)
							rotateRightDegree = -24;
					}
					
					if ( gapValue > 2 ) {
						// left rotate
						if ( wordHighUpPosition[wordLeftWidth] < wordHighUpPosition[wordRightWidth] +3 )
							rotateDegree = 0-rotateLeftDegree;     // some word special, both points in up part
						else
							rotateDegree = rotateLeftDegree;
					}
					else if ( gapValue < -2 ){
						// right rotate
						if ( wordHighUpPosition[wordRightWidth] < wordHighUpPosition[wordLeftWidth] + 3 )
							rotateDegree = 0-rotateRightDegree;     // some word special, both points in up part
						else
							rotateDegree = rotateRightDegree;
					}
					else {
						isRotate = false;
					}
					if (rotateDegree == 0) isRotate = false;


					int[] result = new int[2];
					int[] analyzePixels = new int[iw*ih];
					for (int x = 0; x < iw ; x++) { 
						// high
						for (int j = 0; j < ih ; j++) { 
							analyzePixels[j*iw+x] = -1;
						}
					}
					int hsGapValue = 0;

					// rotate the word points
					for (int x = wordLeftWidth; x <= wordRightWidth ; x++) { 
						// high
						for (int y = 0; y < ih ; y++) { 
							hsGapValue = (int)Math.abs( Math.round( currentWordColor - pixelsColor[y*iw+x]) );
							
							// ignore the other color value
							// count the point by width 
//							if (tmp < 10 || tmp > 230){
							if (pixelsColor[y*iw+x] != 0 && ( hsGapValue < 25 || hsGapValue > 210)){	
//							if ( (int)Math.abs( Math.round( currentWordColor - pixelsColor[y*iw+x]) ) <= 25  ){
//									if ( isRotate ) {
//										result = rotatePoint(rotateDegree, wordLeftWidth, wordMaxHighUpPosition, x, y);
//										try {
//											analyzePixels[result[1]*iw+result[0]] = 0;
//										} catch (Exception e) {
//											
//										}
//									}
//									else 
								// do not rotate currently, just analyze the word 1~9, and 加,减,乘,除
									analyzePixels[y*iw+x] = 0;
							}
						}
					}
					// add black points 
					for (int x = wordLeftWidth+1; x <= wordRightWidth-1 ; x++) { 
						// high
						for (int y = 1; y < ih-1 ; y++) { 
							// at least, there's there black points around, than set it to black
							if ( analyzePixels[y*iw+x-1] + analyzePixels[y*iw+x+1] + analyzePixels[(y-1)*iw+x] + analyzePixels[(y+1)*iw+x] >= -1){	
									analyzePixels[y*iw+x] = 0;
							}
						}
					}
														
					Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, analyzePixels, 0, iw));
					BufferedImage bImage = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_RGB ); 
					bImage.createGraphics().drawImage(tempImg, 0, 0, null); 

					return bImage;
				}
			}
		}
		return null;
	}
	
	public int[] rotatePoint(float degree, int x0, int y0, int x, int y){
		int[] result = new int[2];
		degree = new Float(Math.toRadians(degree));
		double tmp = (x-x0)*Math.cos(degree) ;
		tmp = tmp + (y-y0)*Math.sin(degree);
		tmp = tmp + x0;
		result[0] = (int)Math.abs( Math.round( (x-x0)*Math.cos(degree) +(y-y0)*Math.sin(degree)+x0 ) );
		result[1] = (int)Math.abs( Math.round(-(x-x0)*Math.sin(degree) + (y-y0)*Math.cos(degree)+y0) );
		return result;
	}
	public void rotateImage(int x, int degree) {
        int type = image.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(iw, ih, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(24), x, 0);
        graphics2d.drawImage(image, 0, 0, null);
        graphics2d.rotate(Math.toRadians(12), x, 0);
        graphics2d.dispose();
        image = img;
	}
	
	/**
	 *  distinguish by the H, ignore the light > 200 (this bring problem sometimes), 
	 *              and such case need to be resolved: 
	 *              1, same H, but different L
	 *              2, when strong color disturb, near points color need to be corrected
	 * @return
	 */
	public void filterByHSBColor(int iLight){
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels, 0, iw); 
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) { e.printStackTrace(); }
			
		//统计出有几种颜色的字符，分类抽取
		String codeValue = "";
		int num = 360/5; // to separate the Hue by 10
		float[] color=new float[num];
		for (int i = 0; i < num; i++){
			color[i] = i*5;
		}
		int[] colorCount = new int[num];
		float[] colorAvarageL = new float[num];
		float[][] hsbPixels = new float[pixels.length][3];
		
		ColorModel cm = ColorModel.getRGBdefault(); 
		
		int[] analyzePixels = new int[iw*ih];
		for (int i = 0; i < analyzePixels.length ; i++) { 
			analyzePixels[i] = -1;
		}
		
		boolean isWordStart = false;
		int gapValueH = 30, gapValueL = 30, wordColorHighCount = 0, wordStartWidth = 0, wordCoreWidth = 0;
		float wordColorCoreValue = 0, wordColorCoreL = 0;
		
		// scan the high 
		for (int i = 0; i < iw  ; i++) { 
			// high
			for (int j = 0; j < ih ; j++) { 
				int red = cm.getRed(pixels[iw*j+i]);
				int green  = cm.getGreen(pixels[iw*j+i]);
				int blue = cm.getBlue(pixels[iw*j+i]);
				
				if (red + green + blue > 700) continue;
				
				float[] hsbValue = rgb2hls(red, green, blue );
				// ignore the light > iLight
				if (hsbValue[1] > iLight ) continue;
				
				hsbPixels[iw*j+i] = hsbValue;
						
				for (int k=1; k<num; k++){
					if ( ( color[k] >= hsbValue[0])  ) {
						colorAvarageL[k-1] = (colorAvarageL[k-1]*colorCount[k-1] + hsbValue[1]) / (colorCount[k-1] + 1);
						colorCount[k-1] ++;
						break;
					}
				}
			}
			
			// check whether there is a word color is started
			for( int m = 1; m < num-1 ; m++) {
				if (colorCount[m]+colorCount[m+1] >= 30){
					isWordStart = true;
					int corePos = 0;
					if (colorCount[m] > colorCount[m+1])
						corePos = m;
					else
						corePos = m +1;
					wordColorCoreValue = color[corePos];
					wordColorCoreL = colorAvarageL[corePos];
					wordCoreWidth = i;
					// System.out.println("wordColorCoreValue: " + wordColorCoreValue +"  wordCoreWidth: "+ wordCoreWidth +"  wordColorCoreL: "+ wordColorCoreL);
					break;
				}
			}
			
			if (isWordStart){
				for (int m = wordStartWidth; m < iw  ; m++) { 
					// high
					for (int n = 0; n < ih ; n++) { 
						if (m < i) {
							// use above value
							if ( ( hsbPixels[iw*n+m][0] + 240 <= wordColorCoreValue + gapValueH ||
								   (hsbPixels[iw*n+m][0] >= wordColorCoreValue - gapValueH && hsbPixels[iw*n+m][0] <= wordColorCoreValue + gapValueH)  ) &&
							   (hsbPixels[iw*n+m][1] > wordColorCoreL - gapValueL && hsbPixels[iw*n+m][1] < wordColorCoreL + gapValueH) ) {
									analyzePixels[iw*n+m] = pixels[iw*n+m];
//									analyzePixels[iw*n+m] = 0;
									wordColorHighCount ++;
							}
						}
						else {
							int red = cm.getRed(pixels[iw*n+m]);
							int green  = cm.getGreen(pixels[iw*n+m]);
							int blue = cm.getBlue(pixels[iw*n+m]);
							
							if (red + green + blue > 700) continue;
							
							float[] hsbValue = rgb2hls(red, green, blue );
							// ignore the light > 200
							if (hsbValue[1] > 220 ) continue;
							
							hsbPixels[iw*n+m] = hsbValue;
							if ( ( hsbPixels[iw*n+m][0] + 240 <= wordColorCoreValue + gapValueH ||
									   (hsbPixels[iw*n+m][0] >= wordColorCoreValue - gapValueH && hsbPixels[iw*n+m][0] <= wordColorCoreValue + gapValueH)  ) &&
								   (hsbPixels[iw*n+m][1] > wordColorCoreL - gapValueL && hsbPixels[iw*n+m][1] < wordColorCoreL + gapValueH) ) {
								analyzePixels[iw*n+m] = pixels[iw*n+m];
//								analyzePixels[iw*n+m] = 0;
								wordColorHighCount ++;
							}
						}
					}
					
					if (wordColorHighCount == 0 && m > wordCoreWidth) {
						// set the i value
						i = m;
						wordStartWidth = m;
						// System.out.println("word end: " + m);
						break;
					}
					else
						wordColorHighCount = 0 ;
				}

				// reset for the next word
				colorCount=new int[num];
				colorAvarageL = new float[num];
				isWordStart = false;
				wordColorCoreValue = 0;
			}
		}
		
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, analyzePixels, 0, iw));
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_RGB ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
	}
	
	public void filterLessLight(int iLight){
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels, 0, iw); 
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) { e.printStackTrace(); }
		
		ColorModel cm = ColorModel.getRGBdefault(); 
		
		// 50 width is used to seperated the words by 10 width
		int[] analyzePixels = new int[iw*ih];
		for (int i = 0; i < analyzePixels.length ; i++) { 
			analyzePixels[i] = -1;
		}

		float[] avarageHsbH = new float[iw];
		float[][] hsbH = new float[iw][ih];
		// scan the high 
		for (int i = 0; i < iw  ; i++) { 
			// high, ignore the ignoreHigh
			for (int j = 0; j < ih ; j++) { 
				int red = cm.getRed(pixels[iw*j+i]);
				int green  = cm.getGreen(pixels[iw*j+i]);
				int blue = cm.getBlue(pixels[iw*j+i]);
				
				if (red + green + blue > 700) continue;
				
				float[] hsbValue = rgb2hls(red, green, blue );
				// ignore the light > iLight
				if (hsbValue[1] < iLight ) continue;
				
				analyzePixels[iw*j+i] = pixels[iw*j+i];
			} 
		}
		
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, analyzePixels, 0, iw));
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_RGB ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
	}
	
	public void filterByHSB(int iType, boolean isBigger, float iValue){
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels, 0, iw); 
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) { e.printStackTrace(); }
		
		ColorModel cm = ColorModel.getRGBdefault(); 
		
		// 50 width is used to seperated the words by 10 width
		int[] analyzePixels = new int[iw*ih];
		for (int i = 0; i < analyzePixels.length ; i++) { 
			analyzePixels[i] = -1;
		}

		float[] avarageHsbH = new float[iw];
		float[][] hsbH = new float[iw][ih];
		// scan the high 
		for (int i = 0; i < iw  ; i++) { 
			// high, ignore the ignoreHigh
			for (int j = 0; j < ih ; j++) { 
				int red = cm.getRed(pixels[iw*j+i]);
				int green  = cm.getGreen(pixels[iw*j+i]);
				int blue = cm.getBlue(pixels[iw*j+i]);
				
				if (red + green + blue > 700) continue;
				
				float[] hsbValue = rgb2hls(red, green, blue );
				// filter such points
				if (isBigger) {
					if (hsbValue[iType] > iValue ) continue;
				}
				else{
					if (hsbValue[iType] < iValue && !(iType==2 && pixels[iw*j+i]==0) ) continue;
				}
				
				analyzePixels[iw*j+i] = pixels[iw*j+i];
			} 
		}
		
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, analyzePixels, 0, iw));
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
	}
	
	
	public void filterByHSBLight(int iLight, int ignoreHigh){
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels, 0, iw); 
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) { e.printStackTrace(); }
			
		//颜色gap
		float gapValue = 30.0f;
		
		ColorModel cm = ColorModel.getRGBdefault(); 
		
		// 50 width is used to seperated the words by 10 width
		int[] analyzePixels = new int[iw*ih];
		for (int i = 0; i < analyzePixels.length ; i++) { 
			analyzePixels[i] = -1;
		}
		int[] resultPixels = new int[(iw+50)*ih];
		for (int i = 0; i < resultPixels.length ; i++) { 
			resultPixels[i] = -1;
		}

		float[] avarageHsbH = new float[iw];
		float[][] hsbH = new float[iw][ih];
		// scan the high 
		for (int i = 0; i < iw  ; i++) { 
			// high, ignore the ignoreHigh
			for (int j = ignoreHigh; j < ih-ignoreHigh ; j++) { 
				int red = cm.getRed(pixels[iw*j+i]);
				int green  = cm.getGreen(pixels[iw*j+i]);
				int blue = cm.getBlue(pixels[iw*j+i]);
				
				if (red + green + blue > 700) continue;
				
				float[] hsbValue = rgb2hls(red, green, blue );
				// ignore the light > iLight
				if (hsbValue[1] > iLight ) continue;
				
				analyzePixels[iw*j+i] = pixels[iw*j+i];
				hsbH[i][j] = hsbValue[0];
				if ( avarageHsbH[i] == 0 ) {
					avarageHsbH[i] = hsbValue[0];
				}
				else {
					avarageHsbH[i] = (avarageHsbH[i] + hsbValue[0]) / 2;
				}
			} 
		}
		
		// Separate the word
		int startWidth = 0, endWidth = 0, wordWidth = 20, wordGapWidth = 0, step = 0;
		float wordHsbH = 0;
		int[] wordHsbHCount = new int[wordWidth];
		float[] wordAvarageHsbH = new float[wordWidth];
//
//		for (int i = 0; i < iw-2  ; i++) {
//			if (avarageHsbH[i] != 0) {
//				for (int j = 1; j < wordWidth; j++){
//					if ( wordAvarageHsbH[j] == 0 ) 
//						wordAvarageHsbH[j] = avarageHsbH[i];
//					else if ( Math.abs( avarageHsbH[i] - wordAvarageHsbH[j]) < gapValue ) {
//						wordHsbHCount[j] ++;
//					}
//				}
//				step ++;
//				if (startWidth == 0) startWidth = i;
//			}
//
//			// word found
//			if (step == wordWidth) {
//				int index = 0, countValue = 0;
//				for (int m = 0; m < wordWidth; m++){
//					if (countValue < wordHsbHCount[m] ) {
//						countValue = wordHsbHCount[m];
//						index = m;
//					}
//				}
//
//				wordHsbH = wordAvarageHsbH[index];
//				System.out.println("wordHsbH -----: " + wordHsbH);
//
//				// one word found
//				int pointCount = 0;
//				for (int m = startWidth; m < iw  ; m++) { 
//					for (int n = ignoreHigh; n < ih-ignoreHigh ; n++) { 
//						if (hsbH[m][n] == 0) continue;  // ignore white point
//						if ( Math.abs( wordHsbH - hsbH[m][n]) < gapValue ){
//							resultPixels[(iw+50)*n+m+wordGapWidth] = analyzePixels[iw*n+m];
//							pointCount ++ ;
//						}
//					}
//					if (pointCount == 0) {
//						i = m;
//						System.out.println("iiiiiiiiiiiiiiiiii: " + i);
//						break;
//					}
//					else pointCount = 0;
//				}
//
//				// reset the value
//				startWidth = 0;
//				endWidth = 0;
//				step = 0;
//				wordGapWidth = wordGapWidth + 10;
//				for (int j = 1; j < wordWidth; j++){
//					wordAvarageHsbH[j] = 0;
//					wordHsbHCount[j] = 0;
//				}
//			}
//
//		}
		
		// 20, 36, 54
		for (int m = 1; m < iw  ; m++) { 
			for (int n = ignoreHigh; n < ih-ignoreHigh ; n++) { 
				if (hsbH[m][n] == 0) continue;  // ignore white point
				resultPixels[(iw+50)*n+m+wordGapWidth] = analyzePixels[iw*n+m];
			}
			if (m == 20 || m == 36 || m == 54) {
				wordGapWidth = wordGapWidth + 10;
			}
		}
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw+50,ih, resultPixels, 0, iw+50));
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_RGB ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
	}
	
	public void filterTheHSB(int iHsbH, int iLight, float gapValue){
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels, 0, iw); 
		try { 
			pg.grabPixels(); 
		} catch (InterruptedException e) { e.printStackTrace(); }
		
		ColorModel cm = ColorModel.getRGBdefault(); 
		
		// 
		int[] analyzePixels = new int[iw*ih];
		for (int i = 0; i < analyzePixels.length ; i++) { 
			analyzePixels[i] = -1;
		}
		
		// scan the high 
		for (int i = 0; i < iw ; i++) { 
			// high, ignore the ignoreHigh
			for (int j = 0; j < ih ; j++) { 
				int red = cm.getRed(pixels[iw*j+i]);
				int green  = cm.getGreen(pixels[iw*j+i]);
				int blue = cm.getBlue(pixels[iw*j+i]);
				
				if (red + green + blue > 700) continue;

				float[] hsbValue = rgb2hls(red, green, blue );

				// ignore such H && light
				if (hsbValue[0] > iHsbH - gapValue && hsbValue[0] < iHsbH + gapValue && 
					hsbValue[1] > iLight - gapValue && hsbValue[1] < iLight + gapValue	)
					continue;
				
				analyzePixels[iw*j+i] = pixels[iw*j+i];
			} 
		}
		
		Image tempImg=Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw,ih, analyzePixels, 0, iw));
		image = new BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null), BufferedImage.TYPE_INT_RGB ); 
		image.createGraphics().drawImage(tempImg, 0, 0, null); 
	}
	
	public void greyJSPix(String input, String output) throws IOException {
		FileInputStream fin=new FileInputStream("D:\\001296\\tmp\\shandong\\"+input+".jpg"); 
		image = ImageIO.read(fin);

		changeGrey(140);
		getGrey(); 
		getBrighten();
		BufferedImage bi=getProcessedImg(); 

		String pname= "D:\\001296\\tmp\\shandong\\tmp\\"+output+"";
		File file = new File(pname+".jpg");
		ImageIO.write(bi, "jpg", file); 
	}
	
	public void greyJXPix(String input, String output) throws IOException {
		FileInputStream fin=new FileInputStream("D:\\001296\\tmp\\jiangxi\\"+input+".jpg"); 
		image = ImageIO.read(fin);

		removeLowBase(300);
		changeGrey(210);
		getGrey(); 
		getBrighten();
		BufferedImage bi=getProcessedImg(); 

		String pname= "D:\\001296\\tmp\\jiangxi\\tmp\\"+output+"";
		File file = new File(pname+".jpg");
		ImageIO.write(bi, "jpg", file); 
	}
	
	public BufferedImage getGrey() { 
		ColorConvertOp ccp=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null); 
		return image=ccp.filter(image,null); 
	} 
	
	//Brighten using a linear formula that increases all color values 
	public BufferedImage getBrighten() { 
		RescaleOp rop=new RescaleOp(1.25f, 0, null);
		return image=rop.filter(image,null);
	} 
	
	//Blur by "convolving" the image with a matrix 
	public BufferedImage getBlur() { 
		float[] data = { .1111f, .1111f, .1111f, .1111f, .1111f, .1111f, .1111f, .1111f, .1111f, }; 
		ConvolveOp cop = new ConvolveOp(new Kernel(3, 3, data));
		return image=cop.filter(image,null); 
	} 
	
	// Sharpen by using a different matrix 
	public BufferedImage getSharpen() { 
		float[] data = { 0.0f, -0.75f, 0.0f, -0.75f, 4.0f, -0.75f, 0.0f, -0.75f, 0.0f}; 
		ConvolveOp cop = new ConvolveOp(new Kernel(3, 3, data));
		return image=cop.filter(image,null); 
	} 
	
	// 11) Rotate the image 180 degrees about its center point
	public BufferedImage getRotate() { 
		AffineTransformOp atop=new AffineTransformOp(AffineTransform.getRotateInstance(Math.PI,image.getWidth()/2,image.getHeight()/2), AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return image=atop.filter(image,null); 
	} 
	
	public BufferedImage getProcessedImg() { 
		return image; 
	} 
	
	public String getCode(String lang, BufferedImage bImage) {	
		if (bImage == null) return "";
		
		String codeResult = "";
	    TessBaseAPI api = new TessBaseAPI();
	    // Initialize tesseract-ocr with English, without specifying tessdata path
	    if (api.Init(".", lang) != 0) {
	        System.err.println("Could not initialize tesseract.");
	        System.exit(1);
	    }
//	    api.SetPageSegMode(tesseract.PSM_AUTO);

	    File file = new File("D:\\tmp.jpg");
		try {
			ImageIO.write(bImage, "jpg", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	    // Open input image with leptonica library
	    // pixReadMem
	    PIX image = pixRead("D:\\tmp.jpg");
	    file.delete();
	    api.SetImage(image);
	    
//		try {
//			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			ImageIO.write(bImage, "jpg", out);
//			byte[] b = out.toByteArray();
//		    PIX imageTmp =  pixReadMem(b, (long)(pixels.length));
//		    api.SetImage(imageTmp);
//		    
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 

	    // Get OCR result
	    BytePointer outText = api.GetUTF8Text();
	    api.End();
	    codeResult = outText.getString();
	    
	    // remove \n\n
	    if ( codeResult.length()>2 ){
	    	codeResult = codeResult.substring(0, codeResult.length()-2);
	    }
	    
	    codeResult = codeResult.replaceAll(" ", "");
	    codeResult = codeResult.replaceAll("'", "");
	    codeResult = codeResult.replaceAll(",", "");
	    
	    return codeResult;
	}
	
	public static float[] rgb2hls(int rgbR, int rgbG, int rgbB) {  
	    assert 0 <= rgbR && rgbR <= 255;  
	    assert 0 <= rgbG && rgbG <= 255;  
	    assert 0 <= rgbB && rgbB <= 255;  
	    int[] rgb = new int[] { rgbR, rgbG, rgbB };  
	    Arrays.sort(rgb);  
	    int max = rgb[2];  
	    int min = rgb[0];
	    int diff = max - min;
	    int sum = max + min;
	  
	    float hlsL = sum / 2f / 255f * 240 ;  
	    
	    float hlsS = 0.0f;
	    if ( max == min )
	    	hlsS = 0.0f;
	    else {
	    	if ((max + min )/510f <= 0.5f) 
	    		hlsS = diff * 240f / sum ;
	    	else 
	    		hlsS = diff * 240f / (510 - sum ) ;
	    }
	  
	    float hlsH = 0;  

	    if ( diff == 0) 
	    	hlsH = 160f;
	    else {
		    if (max == rgbR && rgbG >= rgbB) {  
		        hlsH = (rgbG - rgbB) * 60f / diff + 0;  
		    } else if (max == rgbR && rgbG < rgbB) {  
		        hlsH = (rgbG - rgbB) * 60f / diff + 360;  
		    } else if (max == rgbG) {  
		        hlsH = (rgbB - rgbR) * 60f / diff + 120;  
		    } else if (max == rgbB) {  
		        hlsH = (rgbR - rgbG) * 60f / diff + 240;  
		    }  
		    // special resolve, does not know the reason now
		    hlsH = hlsH * 2f / 3f;
	    }
	  
	    return new float[] { hlsH,  hlsL, hlsS};  
	}  
	 
	/**
	 * this function has not be verified, it may have problem
	 * @param h
	 * @param s
	 * @param v
	 * @return
	 */
	public static int[] hsb2rgb(float h, float s, float v) {  
	    assert Float.compare(h, 0.0f) >= 0 && Float.compare(h, 360.0f) <= 0;  
	    assert Float.compare(s, 0.0f) >= 0 && Float.compare(s, 1.0f) <= 0;  
	    assert Float.compare(v, 0.0f) >= 0 && Float.compare(v, 1.0f) <= 0;  
	  
	    float r = 0, g = 0, b = 0;  
	    int i = (int) ((h / 60) % 6);  
	    float f = (h / 60) - i;  
	    float p = v * (1 - s);  
	    float q = v * (1 - f * s);  
	    float t = v * (1 - (1 - f) * s);  
	    switch (i) {  
	    case 0:  
	        r = v;  
	        g = t;  
	        b = p;  
	        break;  
	    case 1:  
	        r = q;  
	        g = v;  
	        b = p;  
	        break;  
	    case 2:  
	        r = p;  
	        g = v;  
	        b = t;  
	        break;  
	    case 3:  
	        r = p;  
	        g = q;  
	        b = v;  
	        break;  
	    case 4:  
	        r = t;  
	        g = p;  
	        b = v;  
	        break;  
	    case 5:  
	        r = v;  
	        g = p;  
	        b = q;  
	        break;  
	    default:  
	        break;  
	    }  
	    return new int[] { (int) (r * 255.0), (int) (g * 255.0),  
	            (int) (b * 255.0) };  
	}

	    
	private boolean isMatches(String codeValue, String regex){
//	      *各种字符的unicode编码的范围：
//	      * 汉字：[0x4e00,0x9fa5]（或十进制[19968,40869]）
//	      * 数字：[0x30,0x39]（或十进制[48, 57]）
//	      *小写字母：[0x61,0x7a]（或十进制[97, 122]）
//	      * 大写字母：[0x41,0x5a]（或十进制[65, 90]）
//		String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";   ^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$

		//remove the /0
		if (codeValue.length() < 1 ) return false;
		
		if (codeValue.matches(regex)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String getSDCode() {	
//		//山东
		changeGrey(140);
		getGrey(); 
		getBrighten();

	    return getCode("shandong", image);
	}
	
	public String getJSCode() {
		//江苏
		String codeValue = "";
		BufferedImage originalImage = image; 
		boolean isMathed =false;
		String[] codeValues = new String[20];
		
		for(int greyValue=190, i=0; greyValue>=160; greyValue = greyValue -2, i++){
			jsEcrateCircle(550);  // 江苏图片需要预处理：去掉圆圈
			changeGrey(greyValue);
			getGrey(); 
			addBlack();  // 江苏灰度后的图片需要补充黑点
			getBrighten();
			
			codeValue = getCode("ENG", image);
			codeValues[i] = codeValue.substring( 0, codeValue.length() );

			if (isMatches(codeValue.substring(0, codeValue.length() ), "^[0-9A-Za-z]{6}$") ){
				isMathed = true;
				break;
			}
			else
			{
				image = originalImage;
				System.out.println("wrong code value: " + codeValue + "  ---try again by greyValue " + greyValue);
			}
		}
		
		// try to guess the codeValue by analyst above codeValues
		if (! isMathed){
			System.out.println("code value to be decided by above values");
			byte[] codeTmp = new byte[6];
			byte[][] charValue = new byte[6][codeValues.length];
			int[][] charCount = new int[6][codeValues.length];
			byte[] byteTmp = null;
			boolean isFound = false;
			for (int i=0; i< codeValues.length; i++){
				if (codeValues[i]==null || codeValues[i].length() != 6) {
					continue;
				}
				// char count
				byteTmp = codeValues[i].getBytes();
				for (int j = 0; j<6; j++){
					byte[] charTmp = {byteTmp[j]};
					if ( isMatches(new String(charTmp), "^[0-9A-Za-z]{1}$") ) {
						isFound = false;
						for (int k=0; k < codeValues.length; k++){ 
							if (byteTmp[j] == charValue[j][k] ) {
								charValue[j][k] = byteTmp[j]; 
								charCount[j][k]++;
								isFound = true;
							}
						}
						if (! isFound){
							charValue[j][i] = byteTmp[j]; 
							charCount[j][i] = 1;
						}
					}
				}
			}
			// set codeValue
			int maxPos = 0;
			for (int i=0; i< 6; i++){
				int maxCount = 0;
				for (int j=0; j< codeValues.length; j++){
					if ( charCount[i][j] > maxCount ) {
						maxCount = charCount[i][j];
						maxPos = j;
					}
				}
				if (maxCount == 0){
					// if no reasonable char has been found, replaced by A,B,C, etc ...
					codeTmp[i] = (byte)(100+i*2);
				}
				else{
					codeTmp[i] = charValue[i][maxPos];
					byte[] charTmp = {codeTmp[i]};
					if ( isMatches(new String(charTmp), "^[0-9A-Za-z]{1}$") ){
						
					}
				}
			}
			codeValue = new String(codeTmp);
		}
		
		return codeValue;
	}
	
	public String getAHCode() {
		//安徽：three type of picture
		String codeValue = "";
		BufferedImage originalImage = image; 
		
		// type 1, blue base, white word: four Chinese words
		changeGrey(210);
		getGrey(); 
		getBrighten();
		codeValue =  getCode("chi_sim", image);
		if (codeValue.length() < 2) return "";
		codeValue = codeValue.replaceAll("\n", "");
		codeValue = codeValue.replaceAll("、", "");
		codeValue = codeValue.replaceAll("。", "");
		codeValue = codeValue.replaceAll("夭", "天");
		codeValue = codeValue.replaceAll("逃之天天", "逃之夭夭");

		return codeValue;
		
		// type 2, blue base, white word: for pinying
//		image = originalImage;
//		changeGrey(210);
		
		// // type 3, grey base,
//		image = originalImage;
//		ahRemoveBase();
//		changeGrey(210);
//		getGrey(); 
//		removeSingleBlack();
//		getBrighten();
//		codeValue =  getCode("chi_sim");

//	    return getCode("chi_sim");
	}
	
	public String getSHCode() {
		// 上海
		String codeValue = "";
		BufferedImage originalImage = image; 
		
		// find the line, and update it
		
		// remove single point and the points, which are isolated and less than seven
		
		// 

//		changeGrey(210);
//		getGrey(); 
//		getBrighten();
//		
		HoughLineFilter hf = new HoughLineFilter();
		hf.filter(image, image);
		
		File file = new File("D:\\tmp.jpg");
		try {
			ImageIO.write(image, "jpg", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

//		codeValue =  getCode("chi_sim");
		
	    return codeValue;
	}
	
	public String getJXCode() {
		// 江西
		String codeValue = "", code = "";
		BufferedImage originalImage = image; 

		// remove background
		removeLowBase(300);
		changeGrey(210);
		getGrey(); 
		getBrighten();
		removeEdegeBlack(6);
		removeSingleBlack(300);
		
		CompanyTask ct = new CompanyTask();
		
		code = calculateJXPoint(image);
		codeValue = ct.technologySdCode(code);

		System.out.println("code "+code+"    code value: " + codeValue );
		
	    return codeValue;
	}
	public String getJXTestCode() {
		// 江西
		String codeValue = "", code = "";
		BufferedImage originalImage = image; 

		// remove background
		removeLowBase(300);
		changeGrey(210);
		getGrey(); 
		getBrighten();
		removeEdegeBlack(6);
		removeSingleBlack(300);
		
		CompanyTask ct = new CompanyTask();
		
		
//		codeValue = ct.technologySdCode(code);
        
//		codeValue =  getCode("chi_sim");
		codeValue = analyseTiltWord() ;
		System.out.println("code "+code+"    code value: " + codeValue );
		
	    return codeValue;
	}
	
	public String getFJCode() {
		// 福建
		// use the default random value to cheat the code system
		String[] codeValue = {"文过饰非","谈笑风生","好好先生","勇往直前","金玉满堂","人生如梦","肆无忌惮","声色犬马","铁石心汤","病人膏肓",
				              "0","1","2","3","4","5","6","7","8","9",
				              "星火燎原","草场莺飞","为虎作伥","壮志凌云","悬梁刺股","叹为观止","万家灯火","行尸走肉","精忠报国","细水长流",
				              "一路顺风","国色天香","尾大不掉","舌战群儒","缘木求鱼","万里长城","穿针引线","东张西望","瓜田李下","龙虎风云",
				              "-1","-2","-3","-4","-5","-6","时来运转","-7","暗度陈仓","随波逐流",
				              "时不我待","欢聚一堂","神机妙算","画龙点睛","韬光养晦","淋漓尽致","相濡以沫","锦上添花","知法犯法","和光同尘"};

		// remove background
		Random random = new Random();
		int value = random.nextInt(60);
		if (value == 60) value = 0;
		
	    return codeValue[value];
	}
	
	public String getGDCode() {
		// 广东
		String codeValue = "", code = "";
		BufferedImage originalImage = image; 

		codeValue = filterByColorCount() ;
		
//		codeValue =  getCode("chi_sim");
		
	    return codeValue;
	}
	
	public String getHBCode() {
		// 湖北
		String codeValue = "";
		BufferedImage originalImage = image; 
		
		removeGreyPoint();
		removeLowBase(600);
		removeSingleBlack(600);
		changeGrey(250);
		removeSingleBlack(600);
//		getGrey(); 
//		getBrighten();
		
		codeValue =  getCode("chi_sim", image);
		if (codeValue.length() < 2) return "";
		codeValue = codeValue.replaceAll("\n", "");
		codeValue = codeValue.replaceAll("、", "");
		codeValue = codeValue.replaceAll("。", "");
		codeValue = codeValue.replaceAll("夭", "天");
		codeValue = codeValue.replaceAll("逃之天天", "逃之夭夭");

		return codeValue;
	}
	
	public String getCQCode() {
		// 湖北
		String codeValue = "", code = "";
		BufferedImage originalImage = image; 
		
		changeGreyBySpecialColor();
		
		code =  getCode("chi_sim", image);

		CompanyTask ct = new CompanyTask();
		codeValue = ct.technologySdCode(code);

		System.out.println("code "+code+"    code value: " + codeValue );
		
	    return codeValue;
	}
	
	public String getSCCode() {
		// 江西
		String codeValue = "", code = "";
		BufferedImage originalImage = image; 

		// remove background
		removeLowBase(300);
		changeGrey(210);
		getGrey(); 
		getBrighten();
		
		CompanyTask ct = new CompanyTask();
		
		code = calculateJXPoint(image);
		codeValue = ct.technologySdCode(code);
		
		System.out.println("code "+code+"    code value: " + codeValue );

	    return codeValue;
	}
	
	public String getGZCode() {
		// 广东
		String codeValue = "", code = "";
		BufferedImage originalImage = image; 
		
		changeGZPix(10, 30);
		filterByHSBColor(220) ;
		
		changeGrey(240);
		getGrey(); 
		
		codeValue =  getCode("chi_sim", image);
		
		CompanyTask ct = new CompanyTask();
		codeValue = ct.technologySdCode( filterByColorCount() );
		
	    return codeValue;
	}
	
	public String getGSCode() {
		// 广东
		String codeValue = "", code = "";
		BufferedImage originalImage = image; 
		
		filterByHSBLight(173, 6) ;
		
		codeValue =  getCode("chi_sim", image);
		System.out.println("code text: " + codeValue );
		
		CompanyTask ct = new CompanyTask();
		codeValue = ct.technologySdCode( codeValue );
		
	    return codeValue;
	}
	 
	public String getLNCode() {
		// 广东
		String codeValue = "", code = "";
		BufferedImage originalImage = image; 

		codeValue =  getCode("chi_sim", image);
		
		codeValue = codeValue.replace("碱", "减");
		System.out.println("code text: " + codeValue );
		if ( codeValue.indexOf("加")>0 || codeValue.indexOf("减")>0 || 
			 codeValue.indexOf("乘")>0 || codeValue.indexOf("除")>0 ) {
			CompanyTask ct = new CompanyTask();
			codeValue = ct.technologySdCode( codeValue );
		}

	    return codeValue;
	}
	
	public String getTJCode() {
		// 广东
		String codeValue = "", code = "";
		BufferedImage originalImage = image; 
		
		filterTheHSB(80, 125, 40);

		codeValue =  getCode("chi_sim", image);
		
		codeValue = codeValue.replace("碱", "减");
		System.out.println("code text: " + codeValue );
		if ( codeValue.indexOf("加")>0 || codeValue.indexOf("减")>0 || 
			 codeValue.indexOf("乘")>0 || codeValue.indexOf("除")>0 ) {
			CompanyTask ct = new CompanyTask();
			codeValue = ct.technologySdCode( codeValue );
		}

	    return codeValue;
	}
	
	public String getBJCode() {
		// 广东
		String codeValue = "", code = "";
		BufferedImage originalImage = image; 
		
		filterLessLight(30);

		codeValue =  getCode("eng", image);

		if ( isMatches(new String(codeValue), "^[0-9A-Za-z]{4}$") ){
			return codeValue;
		}
		else {
			image = originalImage;
			return getSX2Code();
		}
	    
	}
	
	public String getSX2Code() {
		// 山西
		String codeValue = "", code = "";
		
		// remove background
		filterByHSB(2,false,30);
		filterByHSB(1,true,150);
		removeSingleBlack(600);
		
		code = filterByColorCount();
			
		System.out.println("code value: " + code );
		
		CompanyTask ct = new CompanyTask();
		codeValue = ct.technologySdCode( code );
		
	    return codeValue;
	}
	
	public String getNXCode() {
		// 江西
		String codeValue = "", code = "";
		BufferedImage originalImage = image; 

		// remove background
		removeLowBase(300);
		changeGrey(210);
		getGrey(); 
		getBrighten();
		removeEdegeBlack(6);
		removeSingleBlack(300);
		
		CompanyTask ct = new CompanyTask();
		
		code = getCode("eng", image);
		code = code.substring(0, code.indexOf("="));
		codeValue = ct.technologySdCode(code);

		System.out.println("code "+code+"    code value: " + codeValue );
		
	    return codeValue;
	}
	
	public static void main(String[] args){
		FileInputStream fin;
		BufferedImage bi = null;
		
		// convert the original picture file to picGrey.jpg, which to be worked
		try {
//			fin = new FileInputStream("D:\\001296\\桌面\\corp\\1.jpg");
			fin = new FileInputStream("D:\\001296\\tmp\\xinjiang\\0.jpg");
//			fin = new FileInputStream("D:\\11.jpg");
			try {
				bi=ImageIO.read(fin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		// initial PicGrey, default pic file is D:\\picGrey.jpg
		byte[] bitmap = null;
		PicGrey picgrey = new PicGrey(bitmap, bi);
		
		String code = picgrey.getSCCode();
		
		System.out.println("code value: " + code );
		
//		// HSB color test
//		float[][] tmp = new float[3][3];
//		tmp[0] = picgrey.rgb2hls(53, 255, 179);
//		tmp[1] = picgrey.rgb2hls(81, 234, 178);
//		tmp[2] = picgrey.rgb2hls(100, 223, 177);
//		System.out.println("code value: h: " + tmp[0][0] + " L: " + + tmp[0][1] +" S: " +tmp[0][2] +
//				"h: " + tmp[1][0] + " L: " + + tmp[1][1] +" S: " +tmp[1][2] +
//				"h: " + tmp[2][0] + " L: " + + tmp[2][1] +" S: " +tmp[2][2] );

//		// 山东 图片 测试
//		CompanyTask ct = new CompanyTask();
//		System.out.println("code value: " + ct.technologySdCode(code) );
		
		// 灰度化山东的训练图片
//		for (int i=26; i<=40; i++ ){
//			try{
//				String tmp = String.valueOf(i);
//				picgrey.greyJSPix(tmp, tmp);
//			} catch (Exception e) {
//			
//			}
//		}
		
//		// 灰度化江西的训练图片
//		for (int i=22; i<=22; i++ ){
////			try{
////				String tmp = String.valueOf(i);
////				picgrey.greyJXPix(tmp, tmp);
//				
//				try {
////					fin = new FileInputStream("D:\\001296\\tmp\\jiangxi\\"+i+".jpg");
//					fin = new FileInputStream("D:\\3.jpg");
//					try {
//						bo=ImageIO.read(fin);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} 
//					fout = new FileOutputStream("D:\\picGrey.jpg");
//					try {
//						ImageIO.write(bo, "jpg", fout);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} 
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
//				
//				
//				// initial PicGrey, default pic file is D:\\picGrey.jpg
//				byte[] bitmap = null;
//				PicGrey picgrey = new PicGrey(bitmap);
//				
//				String code = picgrey.getJXCode();
//				
//				System.out.println("code value: " + code );
////			} catch (Exception e) {
////			
////			}
//		}
		
// TBD 1、求图形横向统计节点的波峰与波谷，可以得出字符个数； 
//     2、求阿拉伯数字的相对算法特征； ??先进行腐蚀，留下数字的骨架，然后进行扭向相对位置计算(节点)？
//     3、求汉子的旋转角度算法 (可以根据上、下、左、右的直线最长的做标准，进行旋转斜率计算)
//     4、待定：腐蚀算法(凸包算法), 旋转卡壳算法
	}
}