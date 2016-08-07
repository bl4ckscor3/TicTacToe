package bl4ckscor3.game.tictactoe;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import bl4ckscor3.game.tictactoe.game.GameThread;
import bl4ckscor3.game.tictactoe.game.Screen;

/**
 * Main file used to set up and start the game
 * @author bl4ckscor3
 */
public class TicTacToe
{
	public static final String version = "0.1";
	private static Dimension size = new Dimension(600, 650);
	private static Screen screen;
	private static final JFrame frame = new JFrame();
	
	public static void main(String[] args) throws IOException, URISyntaxException
	{
		SwingUtilities.invokeLater(() -> {
			start();
		});
	}

	/**
	 * Sets up and starts the game
	 */
	private static void start()
	{
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(size);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		screen = new Screen(new GameThread());
		frame.add(screen);
		frame.setVisible(true);
		screen.getThread().start();
	}
	
	/**
	 * @return The Screen
	 */
	public static Screen getScreen()
	{
		return screen;
	}
	
	/**
	 * @return The width of the paintable screen
	 */
	public static int getWidth()
	{
		return size.width;
	}
	
	/**
	 * @return The height of the paintable screen
	 */
	public static int getHeight()
	{
		return size.height;
	}
	
	/**
	 * @return The window
	 */
	public static JFrame getFrame()
	{
		return frame;
	}
}
