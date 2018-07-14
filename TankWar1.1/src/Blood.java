import java.awt.*;

public class Blood {
	int x, y, w, h;
	int step = 0;
	TankClient tankclient;
	private boolean live = true;
	
	private int[][] pos = {{350, 300}};
	
	public Blood() { 
		x = pos[0][0];
		y = pos[0][1];
		w = h = 15;
	}
	
	public void draw(Graphics g) {
		if(!live) return;
		
		Color color = g.getColor();
		g.setColor(Color.MAGENTA);
		g.fillOval(x, y, w, w);
		g.setColor(color);
		
		move();
	}

	private void move() {
		step ++;
		if(step == pos.length) {
			step =  0;
		}
		x = pos[step][0];
		y = pos[step][1];
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}
	
	public boolean isLive() {
		return live;
	}
	
	public void setLive(boolean live) {
		this.live = live;
	}
} 
