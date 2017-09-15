import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game extends Frame{
	
	public static final int MAP_WIDTH = 500;
	public static final int MAP_HEIGHT = 500;
	public static boolean live = true;
	public static int score = 0;
	static String deathMessage = "";
	static String scoreMessage = "";
	static String tips = "";
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	static Bird bird = new Bird();
	static Pipe pipe_1 = new Pipe(450); 
	static Pipe pipe_2 = new Pipe(450 + Pipe.IAMGE_WIDTH * 4);
	Image offScreenImage = null;
	
	private static Image img = tk.getImage(Game.class.getClassLoader().getResource("pic/background.png"));
	
	public static void main(String[] args) {
		Game game = new Game();
		game.launchFrame();
	}
	
	
	public void launchFrame() {
		this.setSize(MAP_WIDTH, MAP_HEIGHT);
		this.setLocation(600, 200);
		this.setTitle("Flappy Bird");
		this.setResizable(false);
		//this.setBackground(Color.GRAY);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);			
			}
		});
		
		this.setVisible(true);
		new Thread(new PaintThread()).start();
		this.addKeyListener(new KeyMonitor());
		this.addKeyListener(new Pipe.keyMonitor());
	}
	
	
	public void paint(Graphics g) {	
		if(live) {
			g.drawImage(img, 0, 0, null);
			bird.draw(g);
			pipe_1.draw(g);
			pipe_2.draw(g);
			pipe_1.move();
			pipe_2.move();
			
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.setFont(new Font("微软雅黑", Font.BOLD|Font.ITALIC, 30));
			g.drawString("" +score, 30, 70);
			g.drawString(deathMessage, 150, 250);
			g.drawString(scoreMessage, 180, 300);
			g.setColor(Color.BLACK);
			g.setFont(new Font("华文楷体", Font.CENTER_BASELINE, 25));
			g.drawString(tips, 170, 400);
			g.setColor(c);
		}
	}
	
	
	private class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			bird.KeyPressed(e);
		}
	}
	
	
	private class PaintThread implements Runnable {
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(MAP_WIDTH, MAP_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		/*
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GRAY);
		gOffScreen.fillRect(0, 0, MAP_WIDTH, MAP_HEIGHT);
		gOffScreen.setColor(c);
		*/
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	
	public static void gameOver() {
		deathMessage = "Game Over!";
		scoreMessage = "Score:"+score;
		tips = "按R键重新开始";
	}
	
	public static void reStart() {
		bird.reset();
		deathMessage = "";
		scoreMessage = "";
		tips = "";
		score = 0;
		pipe_1 = new Pipe(450);
		pipe_2 = new Pipe(450 + Pipe.IAMGE_WIDTH * 4);
	}
	
}
		
	

	

