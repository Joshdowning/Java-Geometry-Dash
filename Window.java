import java.awt.Dimension;
import javax.swing.JFrame;
public class Window {

	Handler handler;

	public Window(int width, int height, String title, Game game) {
		game.setPreferredSize(new Dimension(width, height));
		game.setMaximumSize(new Dimension(width, height));
		game.setMinimumSize(new Dimension(width, height));

		JFrame f = new JFrame(title);
		f.add(game);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);

		game.start();
	}

}