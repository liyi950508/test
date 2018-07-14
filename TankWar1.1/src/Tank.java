import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Tank {
	public static final int XSPEED = 6;
	public static final int YSPEED = 6;
	public static final int TANK_WIDTH = 35;
	public static final int TANK_HEIGHT = 35;
	
	private int x, y, oldX, oldY;
	TankClient tankclient;
	private int life = 5;
	private int step = random.nextInt(12) + 3;
	private boolean good;
	private boolean live = true;
	private BloodBar bloodbar = new BloodBar();
	private static Random random = new Random();

	private boolean bL = false, bU = false, bR = false, bD = false;
	private Direction direction = Direction.STOP;
	private Direction ptdirection = Direction.D;
	
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static Image[] tankimages = null;
	private static Map<String, Image> images = new HashMap<String, Image>();
	static {
		tankimages = new Image[]{ 
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/TankL.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/TankLU.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/TankU.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/TankRU.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/TankR.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/TankRD.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/TankD.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/TankLD.gif")),
		};
		images.put("L", tankimages[0]);
		images.put("LU", tankimages[1]);
		images.put("U", tankimages[2]);
		images.put("RU", tankimages[3]);
		images.put("R", tankimages[4]);
		images.put("RD", tankimages[5]);
		images.put("D", tankimages[6]);
		images.put("LD", tankimages[7]);
	}
	
	public Tank(int x, int y, boolean good ) {
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good = good;
	}
	
	public Tank(int x, int y, boolean good, Direction direction, TankClient tankclient) {
		this(x, y, good);
		this.direction = direction;
		this.tankclient = tankclient;
	}
	
	public void draw(Graphics g) {
		if(!live) {
			if(!good) {
				tankclient.tanks.remove(this);
			}
			return;
		}
	
		if(good) bloodbar.draw(g);
		
		switch(ptdirection) {
		case L:
			g.drawImage(images.get("L"), x, y, null);
			break;
		case LU:
			g.drawImage(images.get("LU"), x, y, null);
			break;
		case U:
			g.drawImage(images.get("U"), x, y, null);
			break;
		case RU:
			g.drawImage(images.get("RU"), x, y, null);
			break;
		case R:
			g.drawImage(images.get("R"), x, y, null);
			break;
		case RD:
			g.drawImage(images.get("RD"), x, y, null);
			break;
		case D:
			g.drawImage(images.get("D"), x, y, null);
			break;
		case LD:
			g.drawImage(images.get("LD"), x, y, null);
			break;
		default:
			break;
		}
		move();
	}

	public void move() {
		this.oldX = x;
		this.oldY = y;
		
		switch(direction) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		}
		
		if(this.direction != Direction.STOP) {
			this.ptdirection = this.direction;
		}
		
		if(x < 0) x = 0;
		if(y < 28) y = 28;
		if(x + Tank.TANK_WIDTH >TankClient.GAME_WIDTH) x = TankClient.GAME_WIDTH - Tank.TANK_WIDTH;
		if(y + Tank.TANK_HEIGHT > TankClient.GAME_HEIGHT) y = TankClient.GAME_HEIGHT - Tank.TANK_HEIGHT;
		
		if(!good) {
			Direction[] directions = Direction.values();
			if(step == 0) {
				step = random.nextInt(12) + 3;
				int missile = random.nextInt(directions.length);
				direction = directions[missile];
			}
			step --;
			
			if(random.nextInt(40) > 38) 
				this.fire();
		}
	}
	
	private void stay() {
		x = oldX;
		y = oldY;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_F2:
			if(!this.live) {
				this.live = true;
				this.life = 5;
			}
			break;
		case KeyEvent.VK_LEFT:
			bL = true;
			break;
		case KeyEvent.VK_UP:
			bU = true;
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			break;
		}
		locateDirection();
	}
	
	public void locateDirection() {
		if(bL && !bU && !bR && !bD)        direction = Direction.L;
		else if(bL && bU && !bR && !bD)    direction = Direction.LU;
		else if(!bL && bU && !bR && !bD)   direction = Direction.U;
		else if(!bL && bU && bR && !bD)    direction = Direction.RU;
		else if(!bL && !bU && bR && !bD)    direction = Direction.R;
		else if(!bL && !bU && bR && bD)    direction = Direction.RD;
		else if(!bL && !bU && !bR && bD)   direction = Direction.D;
		else if(bL && !bU && !bR && bD)    direction = Direction.LD;
		else if(!bL && !bU && !bR && !bD)  direction = Direction.STOP;
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_CONTROL:
			fire();
			break;
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		case KeyEvent.VK_UP:
			bU = false;
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		case KeyEvent.VK_DOWN:
			bD = false;
			break;
		case KeyEvent.VK_A:
		superFire();
		break;
		}
		locateDirection();
	}
	
	public Missile fire() {
		if(!live) return null;
		int x = this.x + Tank.TANK_WIDTH/2 - Missile.MISSILE_WIDTH/2;
		int y = this.y + Tank.TANK_HEIGHT/2 - Missile.MISSILE_HEIGHT/2;
		Missile missile = new Missile(x, y, good, ptdirection, this.tankclient);
		tankclient.missiles.add(missile);
		return missile;
	}
	
	public Missile fire(Direction direction) {
		if(!live) return null;
		int x = this.x + Tank.TANK_WIDTH/2 - Missile.MISSILE_WIDTH/2;
		int y = this.y + Tank.TANK_HEIGHT/2 - Missile.MISSILE_HEIGHT/2;
		Missile missile = new Missile(x, y, good, direction, this.tankclient);
		tankclient.missiles.add(missile);
		return missile;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, TANK_WIDTH, TANK_HEIGHT);
	}
	
	public boolean isLive() {
		return live;
	}
	
	public void setLive(boolean live) {
		this.live = live;
	}
	
	public boolean isGood() {
		return good;
	}
	
	public boolean collidesWithWall(Wall wall) {
		if(this.live && this.getRect().intersects(wall.getRect())) {
			this.stay();
			return true;
		}
		return false;
	}
	
	public boolean collidesWithTanks(java.util.List<Tank>tanks) {
		for(int i = 0; i < tanks.size(); i ++) {
			Tank tank = tanks.get(i);
			if(this != tank) {
				if(this.live && tank.isLive() && this.getRect().intersects(tank.getRect())) {
					this.stay();
					tank.stay();
					return true;
				}
			}
		}
		return false;
	}
	
	private void superFire() {
		Direction[] directions = Direction.values();
		for(int i = 0; i < 8; i++) {
			fire(directions[i]);
		}
	}
	
	
	public int getLife() {
		return life;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
	public class BloodBar {
		public void draw(Graphics g) {
			Color color = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(x, y, TANK_WIDTH, 10);
			int w = TANK_WIDTH*life/5;
			g.fillOval(x, y-10, w, 10);
			g.setColor(color);
		}
	}
	
	public boolean eat(Blood blood) {
		if(this.live && blood.isLive() && this.getRect().intersects(blood.getRect())) {
			this.life = 5;
			blood.setLive(false);
			return true;
		}
		return false;
	}
}
