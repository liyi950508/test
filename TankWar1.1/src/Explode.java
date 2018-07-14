import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Explode {
	int x, y;
	int step = 0 ;
	private boolean live = true;
	private TankClient tankclient;
	
	private static boolean init = false;
	
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	private static Image[] images = { 
		toolkit.getImage(Explode.class.getClassLoader().getResource("images/0.gif")),
		toolkit.getImage(Explode.class.getClassLoader().getResource("images/1.gif")),
		toolkit.getImage(Explode.class.getClassLoader().getResource("images/2.gif")),
		toolkit.getImage(Explode.class.getClassLoader().getResource("images/3.gif")),
		toolkit.getImage(Explode.class.getClassLoader().getResource("images/4.gif")),
		toolkit.getImage(Explode.class.getClassLoader().getResource("images/5.gif")),
		toolkit.getImage(Explode.class.getClassLoader().getResource("images/6.gif")),
		toolkit.getImage(Explode.class.getClassLoader().getResource("images/7.gif")),
		toolkit.getImage(Explode.class.getClassLoader().getResource("images/8.gif")),
		toolkit.getImage(Explode.class.getClassLoader().getResource("images/9.gif")),
		toolkit.getImage(Explode.class.getClassLoader().getResource("images/10.gif"))
	};
	
	public Explode(int x, int y, TankClient tankclient) {
		this.x = x;
		this.y = y;
		this.tankclient = tankclient;
	}
	
	public void draw(Graphics g) {
		if(!init ) {
			for(int i = 0; i < images.length; i ++) {
				g.drawImage(images[i], -20, -20, null);
			}
			init = true;
		}
		
		if(!live) {
			tankclient.explodes.remove(this);
			return ;
		}
		
		if(step == images.length) {
			live = false;
			step = 0;
			return;
		}
		
		g.drawImage(images[step], x, y, null);
		step ++;
	}
}
