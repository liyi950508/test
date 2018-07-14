import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Missile {
	public static final int YSPEED = 9;
	public static final int XSPEED = 9;
	public static final int MISSILE_WIDTH = 10;
	public static final int MISSILE_HEIGHT = 10;
	
	int x, y;
	TankClient tankclient;
	Direction direction;
	private boolean good;
	private boolean live = true;
	
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static Image[] missileimages = null;
	private static Map<String, Image> images = new HashMap<String, Image>();
	static {
		missileimages = new Image[]{ 
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/MissileL.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/MissileLU.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/MissileU.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/MissileRU.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/MissileR.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/MissileRD.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/MissileD.gif")),
			toolkit.getImage(Explode.class.getClassLoader().getResource("images/MissileLD.gif")),
		};
		images.put("L", missileimages[0]);
		images.put("LU", missileimages[1]);
		images.put("U", missileimages[2]);
		images.put("RU", missileimages[3]);
		images.put("R", missileimages[4]);
		images.put("RD", missileimages[5]);
		images.put("D", missileimages[6]);
		images.put("LD", missileimages[7]);
	}
	
	public Missile(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public Missile(int x, int y, boolean good, Direction direction, TankClient tankclient) {
		this(x, y, direction);
		this.good = good;
		this.tankclient = tankclient;
	}
	
	public void draw(Graphics g) {
		if(!live) {
			tankclient.missiles.remove(this);
			return;
		}
		
		switch(direction) {
		case L:
			g.drawImage(images.get("L"), x, y, null);
			break;
		case LU:
			g.drawImage(images.get("LU"), x, y, null);
			break;
		case U:
			g.drawImage(images.get("D"), x, y, null);
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
			g.drawImage(images.get("U"), x, y, null);
			break;
		case LD:
			g.drawImage(images.get("LD"), x, y, null);
			break;
		default:
			break;
		}
		move();
	}

	private void move() {
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
		default:
			break;
		}
		
		if(x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT) {
			live = false;
		}
	}
	
	public boolean isLive(){
		return live;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, MISSILE_WIDTH, MISSILE_HEIGHT);
	}
	
	public boolean hitTank(Tank tank) {
		if(this.live && this.getRect().intersects(tank.getRect()) && tank.isLive() && this.good != tank.isGood()) {
			if(tank.isGood()) {
				tank.setLife(tank.getLife() - 1);
				if(tank.getLife() <= 0) tank.setLive(false);
			}
			else {
				tank.setLive(false);
			}
			
			this.live = false;
			Explode explode = new Explode(x, y, tankclient);
			tankclient.explodes.add(explode);
			return true;
		}
		return false;
	}
	
	public boolean hitTanks(List<Tank>tanks) {
		for(int i = 0; i < tanks.size(); i ++) {
			if(hitTank(tanks.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hitWall(Wall wall) {
		if(this.live && this.getRect().intersects(wall.getRect())) {
			this.live = false;
			return true;
		}
		return false;
	}
}
