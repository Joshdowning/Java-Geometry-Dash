import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	private Thread thread;
	private Thread musicThread;
	private boolean running = false;
	Handler handler;
	Camera camera;
	public static int WIDTH, HEIGHT;
	private BufferedImage background;
	private BufferedImage level = null;
	private BufferedImageLoader loader;
	private BufferedImage menuimg;
	private Player player;
	private boolean musicInitialized = false;
	private boolean playingMusic = false;
	private MusicHandler music;
	static Texture tex;
	public enum STATE {
		MENU, GAME
	};
	public static STATE State = STATE.MENU;
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
		musicThread = new Thread(music);
		musicThread.start();
		musicInitialized = true;
		playingMusic = true;
	}

	public void pauseMusic() {
		musicThread.interrupt();
		playingMusic = false;
	}

	public void run() {
		handler = new Handler();

		WIDTH = getWidth();
		HEIGHT = getHeight();

		tex = new Texture();

		level = loader.loadImage("/res/leveldesign.png");
		camera = new Camera(0, 0);
		LoadImageLevel(level);

		this.addKeyListener(new KeyInput(handler));

		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick() {
		if (State == STATE.MENU) {
			menu.tick();
			if (playingMusic)
				pauseMusic();
		}
		if (State == STATE.GAME) {
			handler.tick();
			camera.tick();
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
		if (State == STATE.MENU) {
			g.drawImage(menuimg, 0, 0, null);
			g.dispose();
			bufstrat.show();
			camera.setX(0);
			camera.setY(0);
			camera.setAccel(4);
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

	private void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if (red == 255 && green == 255 && blue == 255) { // detect white pixel																// pixel
					handler.addObj(new Block(i * 32, j * 32, ObjId.Block));
				}
				if (red == 255 && green == 0 && blue == 0) { // detect red pixel
					player = new Player(i * 32, j * 32, handler, ObjId.Player);
					handler.addObj(player);
				}
				if (red == 0 && green == 0 && blue == 255) { // detect blue																// pixel
					int xx[] = { (i * 32), (i * 32) + 16, (i * 32) + 32 };
					int yy[] = { (j * 32) + 32, (j * 32), (j * 32) + 32 };
					handler.addObj(new Spike(xx, yy, i * 32, j * 32, ObjId.Spike));
				}
				if (red == 255 && green == 127 && blue == 182) { // detect pink																	// pixel
					int xx[] = { (i * 32), (i * 32) + 16, (i * 32) + 32 };
					int yy[] = { (j * 32), (j * 32) + 32, (j * 32) };
					handler.addObj(new FlipSpike(xx, yy, i * 32, j * 32, ObjId.FlipSpike));
				}
				if (red == 0 && green == 255 && blue == 0) { // detect green																// pixel
					handler.addObj(new JumpPad(i * 32, j * 32, ObjId.JumpPad));
				}
				if (red == 255 && green == 0 && blue == 255) { // detect purple																// pixel
					handler.addObj(new FallPad(i * 32, j * 32, ObjId.FallPad));
				}
				if (red == 255 && green == 255 && blue == 0) { // detect yellow																// pixel
					handler.addObj(new GravityPortalUp(i * 32, j * 32, ObjId.GravityPortalUp));
				}
				if (red == 255 && green == 106 && blue == 0) { // detect orange																// pixel
					handler.addObj(new GravityPortalDown(i * 32, j * 32, ObjId.GravityPortalDown));
				}
			}
		}
	}

	public static Texture getTextureInstance() {
		return tex;
	}
	
	public static void main(String args[]) {
		new Window(980, 788, "Cube Runner", new Game());
	}

}