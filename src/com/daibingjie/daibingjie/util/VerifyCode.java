package com.daibingjie.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyCode {
	
		private int w = 70;
		private int h = 40;//[图片长宽]
		private Random r = new Random();
		private String[]fontNames={"宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"};
		private String codes="0123456789";
//		private String codes="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		private Color bgColor=new Color(240,240,240);
		private String text;

		private Color randomColor(){
			int red = r.nextInt(256);
			int green = r.nextInt(256);
			int blue = r.nextInt(256);
			return new Color(red, green, blue);
		}
		//[随机生成字体方法]
		private Font randomFont(){
			int index = r.nextInt(fontNames.length);
			String fontName = fontNames[index];
			int style = r.nextInt(4);
			int size = r.nextInt(5) + 24;//[字号在24~28之间]
			return new Font(fontName, style, size);
		}
		//[画干扰线方法]
		private void drawLine(BufferedImage image){
			 //[一共画5线干扰线]
			int num= 3;
			Graphics2D g2 = (Graphics2D)image.getGraphics();
			for(int i = 0; i < num; i++) {
				int x1 = r.nextInt(w);
				int y1 = r.nextInt(h);
				int x2 = r.nextInt(w);
				int y2 = r.nextInt(h);//[x坐标在图片的长度之内；y坐标在图片的宽度之内]
				g2.setStroke(new BasicStroke(1.5F));//[设置画笔粗细，默认为1]
				g2.setColor(Color.BLUE);//[设置干扰线颜色]
				g2.drawLine(x1, y1, x2, y2);
			}
		}
		//[随机生成验证码字符]
		private char randomChar(){
			int index = r.nextInt(codes.length());
			return codes.charAt(index);
		}
		//[创建内存图片]
		private BufferedImage createImage(){
			BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);//[创建内存图片，指定其长宽]
			Graphics2D g2 = (Graphics2D)image.getGraphics();//[获取图片绘制环境对象]
			g2.setColor(this.bgColor);
			g2.fillRect(0, 0, w, h);
			//[设置图片背景色]
			return image;
		}
		//[外界使用本方法获取验证码图片。]
		public BufferedImage getImage(){
			BufferedImage image = createImage();//[创建只有背景色的空图片]
			Graphics2D g2 = (Graphics2D)image.getGraphics();
			StringBuilder sb = new StringBuilder();
			// 向图片中画4个字符
			for(int i = 0; i < 4; i++){ //[向图片中写入4个字符] 
				String s = randomChar() + "";//[随机生成字符]
				sb.append(s);//[把字符添加到缓冲区中]
				float x = i * 1.0F * w / 4;//[计算第i个字符的位置]
				g2.setFont(randomFont());//[设置随机字体]
				g2.setColor(randomColor());//[设置随机颜色]
				g2.drawString(s, x, h);//[画字符]
			}
			this.text = sb.toString();//[保存验证码字符串]
			drawLine(image);//[添加干扰线]
			return image;		
		}
		//[外界可以通过方法返回验证码]
		public String getText(){
			return text;
		}
		//[外界可以通过该方法向指定的输出流中写入指定的图片]
		public static void output(

		BufferedImage image, OutputStream out)throws IOException{
			ImageIO.write(image, "JPEG", out);
		}
	

}
