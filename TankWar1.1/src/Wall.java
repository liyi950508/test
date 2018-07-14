import java.awt.*;
import java.awt.Color;

public class Wall {
	int x, y, w, h;
	TankClient tankclient;
	
	public Wall(int x, int y, int w, int h, TankClient tankclient) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tankclient = tankclient;
	}
	
	public void draw(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.PINK);
		g.fillRect(x, y, w, h);
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}
}
