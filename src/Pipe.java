import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Pipe {
	
	public Pipe(int x) {
		this.x = x;
	}
	
	int x = Game.MAP_WIDTH;
	int y = 0;
	int upPipeLength = (int) (Math.random() * (300 - 100)) + 100; 
	public static final int IAMGE_WIDTH = 66;
	public static final int GAP = 120;
	public static final int IMAGE_HEIGHT = 400;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	private static Image[] img = {
			tk.getImage(Pipe.class.getClassLoader().getResource("pic/upPipe.png")),
			tk.getImage(Pipe.class.getClassLoader().getResource("pic/bottomPipe.png"))
	};
	
	
	public void draw(Graphics g) {
		/*
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(x, y, PIPE_WIDTH, upPipeLength);
		g.fillRect(x, y + upPipeLength + GAP, PIPE_WIDTH, Game.MAP_HEIGHT - upPipeLength - GAP);
		g.setColor(c);
		*/
		g.drawImage(img[0], x, upPipeLength - IMAGE_HEIGHT, null);
		g.drawImage(img[1], x, GAP + upPipeLength, null);
	}
	
	
	public void move() {
		x -= 7;
		if(x <= 0 - IAMGE_WIDTH) {
			x = Game.MAP_WIDTH;
			upPipeLength = (int) (Math.random() * (350 - 100)) + 100;
		}
		
		Rectangle upPipe = new Rectangle(x, y, IAMGE_WIDTH, upPipeLength);
		Rectangle bottomPipe = new Rectangle(x, y + upPipeLength + GAP, IAMGE_WIDTH, Game.MAP_HEIGHT - upPipeLength - GAP);
		

		if(Bird.live) {
			Game.score ++;
		}
		
		if(upPipe.getBounds().intersects(Bird.getRec()) || bottomPipe.getBounds().intersects(Bird.getRec()) || Bird.y == (Game.MAP_HEIGHT - Bird.BIRD_SIZE)) {
			Bird.live = false;
			Bird.x = x - Bird.BIRD_SIZE;
			Game.gameOver();
		}
	}
	
	public static class keyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == 'R' || key =='r') {
				Game.reStart();
			}
		}
	}
	
}
