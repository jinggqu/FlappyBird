import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class Bird {
	public static final int BIRD_SIZE = 20;

	public static int x = 200;
	public static int y = 300;
	public static int upSpeed = 0;
	public static boolean live = true;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	int gravity = 9;
	
	private static Image pic = tk.getImage(Bird.class.getClassLoader().getResource("pic/bird.png"));
	
	public void draw(Graphics g) {
		/*
		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, Bird.BIRD_SIZE, Bird.BIRD_SIZE);
		g.setColor(c);
		*/
		g.drawImage(pic, x, y, null);
		fall();
	}
	
	
	public void KeyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			upSpeed = (int) (Math.random() * (70 - 40)) + 40;
			y -= upSpeed;
			if(y < 20) {
				y = 20;
				fall();
			}
		}
	}
	
	
	public void fall() {
		if(y < Game.MAP_HEIGHT - BIRD_SIZE) {
			y += gravity;
		}
	}

	
	public static Rectangle getRec() {
		return new Rectangle(x, y, BIRD_SIZE, BIRD_SIZE);
	}

	public void reset() {
		x = 200;
		y = 300;
		upSpeed = 2;
		live = true;
	}
	
}
