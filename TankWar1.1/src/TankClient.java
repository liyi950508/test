import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class TankClient extends Frame{
	
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	
	Tank mytank = new Tank(50, 50, true, Direction.STOP, this);
	
	Wall wall1 = new Wall(100, 200, 10, 150, this), wall2 = new Wall(300, 100, 300, 10, this);
	
	List<Tank> tanks = new ArrayList<Tank>();
	List<Missile> missiles = new ArrayList<Missile>();
	List<Explode> explodes = new ArrayList<Explode>();
	
	Blood blood = new Blood();
	Image offScreenImage = null;
	public void paint(Graphics g) {
		g.drawString("坦克数量       ：" + tanks.size(), 120, 40);
		g.drawString("坦克生命值  ：" + mytank.getLife(), 240, 40);
		g.drawString("炮弹数量       ：" + missiles.size(), 360, 40);
		g.drawString("爆炸数量       ：" + explodes.size(), 480, 40);
		
		if(tanks.size() <= 5) {
			for(int i = 0; i < Integer.parseInt(PropertyMgr.getProperty("reProduesTankCount")); i ++) {
				tanks.add(new Tank(50 + 40*(i + 1), 50, false, Direction.D, this));
			}
		}
		
		for(int i = 0; i < tanks.size(); i ++) {
			Tank tank = tanks.get(i);
			tank.collidesWithWall(wall1);
			tank.collidesWithWall(wall2);
			tank.collidesWithTanks(tanks);
			tank.draw(g);
		}
		
		for(int i = 0; i < missiles.size(); i ++) {
			Missile missile = missiles.get(i);
			missile.hitTanks(tanks);
			missile.hitTank(mytank);
			missile.hitWall(wall1);
			missile.hitWall(wall2);
			missile.draw(g);
		}
		
		for(int i = 0; i < explodes.size(); i ++) {
			Explode explode = explodes.get(i);
			explode.draw(g);
		}
		
		mytank.draw(g);
		mytank.collidesWithTanks(tanks);
		mytank.collidesWithWall(wall1);
		mytank.collidesWithWall(wall2);
		mytank.eat(blood);
		wall1.draw(g);
		wall2.draw(g);
		blood.draw(g);
	}
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color color = gOffScreen.getColor();
		gOffScreen.setColor(Color.DARK_GRAY);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(color);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	public void langFrame() {
		
		int initTankCount = Integer.parseInt(PropertyMgr.getProperty("initTankCount"));
		for(int i = 0; i < initTankCount ; i ++) {
			tanks.add(new Tank(50 + 40*(i + 1), 50, false, Direction.D, this));
		}
		
		this.setBounds(200, 120, GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("坦克大战");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setBackground(Color.DARK_GRAY);
		this.addKeyListener(new KeyMoniter());
		
		this.setVisible(true);
		
		new Thread(new Threadpaint()).start();
	}
	
	public class Threadpaint implements Runnable {
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class KeyMoniter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			mytank.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			mytank.keyReleased(e);
		}
	}
	
	public static void main(String[] args) {
		new TankClient().langFrame();
	}

}
