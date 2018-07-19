package com.help;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class RandomNum extends ActionSupport {

	private ByteArrayInputStream inputStream;
	private String str;
	public RandomNum() {
		init();
	}

	public static RandomNum Instance() {
		return new RandomNum();
	}

	public ByteArrayInputStream getinputStream() {
		return this.inputStream;
	}

	public String getString() {
		return this.str;
	}

	public void init() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 图片宽高
		int width = 80, height = 40;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 生成随机数
		Random random = new Random();
		// 画图片
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 画干扰线
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 生成验证码
		String sRand = "";
		for (int i = 0; i < 6; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;

			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

;
		}
		this.str = sRand;

		g.dispose();
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
			ImageIO.write(image, "JPEG", imageOut);
			imageOut.close();
			input = new ByteArrayInputStream(output.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.inputStream = input;

		request.setAttribute("checkCode", sRand);
	}

	// 颜色填充
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	// 验证码验证
	public boolean vcodeImg(){
		String randomNum = ServletActionContext.getRequest().getParameter("randomNum");
		if(randomNum.equals(str)){
			return true;
		}else
			return false;
	}
	
	public String execute(){ 
		return "success";
	}
}
