/**
 * Class handles the creation of the game and game loop
 * @author Josh Downing
 * @version 01/08/2020
 */

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static STATE State = STATE.MENU;
	public static int WIDTH, HEIGHT;
	public static Texture tex;
	
	public enum STATE {
		MENU, GAME
	};
	
	private Thread thread;
	private Thread gameMusic;
	private boolean running = false;
	private Handler handler;
	private Camera camera;	
	private BufferedImage background;
	private BufferedImage level = null;
	private BufferedImageLoader loader;
	private BufferedImage menuimg;
	private Player player;
	private boolean playingMusic = false;
	private MusicHandler music;
	private Menu menu;

	public Game() {
		menu = new Menu();
		loader = new BufferedImageLoader();
		background = loader.loadImage("/res/background1.png");
		loader = new BufferedImageLoader();
		menuimg = loader.loadImage("/res/Menu.png");
		

	}
	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public void startMusic() {
		music = new MusicHandler("BackOnTrack");
		gameMusic = new Thread(music);
		gameMusic.start();
		playingMusic = true;
	}
	public void pauseMusic() {
		gameMusic.interrupt();
		playingMusic = false;
	}
	
	public void run() {
		handler = new Handler();
		WIDTH = getWidth();
		HEIGHT = getHeight();
		tex = new Texture();
		level = loader.loadImage("/res/leveldesign.png");
		camera = new Camera(0, 0);
		loadImageLevel(level);
		this.addKeyListener(new KeyInput(handler));
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
	}
	private void tick() {
		if (State == STATE.MENU) {
			
			menu.tick();
			if (playingMusic)
				pauseMusic();
			
			
			/*
			if(!playingMenuMusic)
				startMenuMusic();*/
		}
		if (State == STATE.GAME) {
			handler.tick();
			for(GameObject entity:handler.obj){
				if(entity.getId()==ObjId.PLAYER){
					camera.tick(entity);
				}
			}
			//camera.tick();
			/*if(playingMenuMusic)
				pauseMenuMusic();*/
			if (!playingMusic)
				startMusic();
		}
	}
	private void render() {
		BufferStrategy bufstrat = this.getBufferStrategy();
		if (bufstrat == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bufstrat.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.scale(1.2,1.2);
		
		g2d.translate(0,-100);
		if (State == STATE.MENU) {
			g.drawImage(menuimg, 0, 0, null);
			g.dispose();
			bufstrat.show();
			camera.setX(0);
			camera.setY(0);
		}
		if (State == STATE.GAME) {
			g.drawImage(background, 0, 0, null);
			g2d.translate(camera.getX(), camera.getY());
			handler.render(g);
			g2d.translate(-camera.getX(), -camera.getY());
			g.dispose();
			bufstrat.show();
		}
	}

	private void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if (red == 255 && green == 255 && blue == 255) { // detect white pixel
					handler.addObj(new Block(i * 32, j * 32, ObjId.BLOCK));
				}
				if (red == 255 && green == 0 && blue == 0) { // detect red pixel
					player = new Player(i * 32, j * 32, ObjId.PLAYER);
					handler.addObj(player);
				}
				if (red == 0 && green == 0 && blue == 255) { // detect blue
					int xx[] = { (i * 32), (i * 32) + 16, (i * 32) + 32 };
					int yy[] = { (j * 32) + 32, (j * 32), (j * 32) + 32 };
					handler.addObj(new Spike(xx, yy, i * 32, j * 32, ObjId.SPIKE));
				}
				if (red == 255 && green == 127 && blue == 182) { // detect pink	
					int xx[] = { (i * 32), (i * 32) + 16, (i * 32) + 32 };
					int yy[] = { (j * 32), (j * 32) + 32, (j * 32) };
					handler.addObj(new FlipSpike(xx, yy, i * 32, j * 32, ObjId.FLIPSPIKE));
				}
				if (red == 0 && green == 255 && blue == 0) { // detect green 
					handler.addObj(new JumpPad(i * 32, j * 32, ObjId.JUMPPAD));
				}
				if (red == 255 && green == 0 && blue == 255) { // detect purple
					handler.addObj(new FallPad(i * 32, j * 32, ObjId.FALLPAD));
				}
				if (red == 255 && green == 255 && blue == 0) { // detect yellow
					handler.addObj(new GravityPortalUp(i * 32, j * 32, ObjId.GRAVITYPORTALUP));
				}
				if (red == 255 && green == 106 && blue == 0) { // detect orange
					handler.addObj(new GravityPortalDown(i * 32, j * 32, ObjId.GRAVITYPORTALDOWN));
				}
				if (red == 100 && green == 100 && blue == 100) { // detect grey
					handler.addObj(new SpaceShipPortal(i * 32, j * 32, ObjId.SPACESHIPPORTAL));
				}
				if (red == 180 && green == 255 && blue == 0) { // detect lime
					handler.addObj(new RevertPortal(i * 32, j * 32, ObjId.REVERTPORTAL));
				}
			}
		}
	}

	public static Texture getTextureInstance() {
		return tex;
	}
	
	public static void main(String args[]) {
		new Window(980, 788, "Geometry Dash", new Game());
	}

}