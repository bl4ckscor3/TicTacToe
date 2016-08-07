package bl4ckscor3.game.tictactoe.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import bl4ckscor3.game.tictactoe.TicTacToe;
import bl4ckscor3.game.tictactoe.listener.MouseActionsListener;
import bl4ckscor3.game.tictactoe.util.Shapes;
import bl4ckscor3.game.tictactoe.util.Shapes.ShapeItem;

/**
 * Contains the game's interface and logic
 * @author bl4ckscor3
 */
public class Screen extends JPanel
{
	private GameThread thread;
	private final Color bg = new Color(0x98AFC7); //the color of the background
	private final int width = TicTacToe.getWidth();
	private final int height = TicTacToe.getHeight();
	private final Font font = new Font("Calibri", Font.BOLD, 70);
	private final Font symbols = new Font("Calibri", Font.BOLD, 170);
	private final Shapes shapes = new Shapes(); //contains the clickable areas
	private boolean x = true;
	private final String[] used = new String[9];
	private boolean won = false;
	private boolean tie = false;
	private String winner = "";
	
	/**
	 * @param t The GameThread (loop) this game is running on
	 */
	public Screen(GameThread t)
	{
		thread = t;
		addMouseListener(new MouseActionsListener());
		shapes.add(new ShapeItem(new Rectangle(0, 0, 300, 75), "Reset"));
		shapes.add(new ShapeItem(new Rectangle(300, 0, 300, 75), "Exit"));
		shapes.add(new ShapeItem(new Rectangle(90, (height - 340) - 140, 140, 140), "0"));
		shapes.add(new ShapeItem(new Rectangle(230, (height - 340) - 140, 140, 140), "1"));
		shapes.add(new ShapeItem(new Rectangle(370, (height - 340) - 140, 140, 140), "2"));
		shapes.add(new ShapeItem(new Rectangle(90, (height - 340), 140, 140), "3"));
		shapes.add(new ShapeItem(new Rectangle(230, (height - 340), 140, 140), "4"));
		shapes.add(new ShapeItem(new Rectangle(370, (height - 340), 140, 140), "5"));
		shapes.add(new ShapeItem(new Rectangle(90, (height - 340) + 140, 140, 140), "6"));
		shapes.add(new ShapeItem(new Rectangle(230, (height - 340) + 140, 140, 140), "7"));
		shapes.add(new ShapeItem(new Rectangle(370, (height - 340) + 140, 140, 140), "8"));
		
		for(int i = 0; i < used.length; i++)
		{
			used[i] = " ";
		}
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		g.setColor(bg);
		g.setFont(font);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.drawLine(width, 75, 0, 75);
		g.drawLine(width / 2, 0, width / 2, 75);
		g.drawString("RESET", 60, 60);
		g.drawString("EXIT", 385, 60);

		if(won)
		{
			g.setColor(Color.GREEN);
			g.drawString(winner + " won!", 190, 150);
			g.setColor(Color.BLACK);
		}
		else if(tie)
		{
			g.setColor(Color.RED);
			g.drawString("It's a tie!", 180, 150);
			g.setColor(Color.BLACK);
		}
		else
			g.drawString("It's " + (x ? "X" : "O") + "'s turn.", 140, 150);

		g.drawLine(230, (height - 340) - 140, 230, (height - 230) + 170); //left vertical line
		g.drawLine(370, (height - 340) - 140, 370, (height - 230) + 170); //right vertical line
		g.drawLine(90, height - 340, 510, height - 340); //top horizontal line
		g.drawLine(90, height - 200, 510, height - 200); //bottom horizontal line
		g.setFont(symbols);
		g.drawString(used[0], 110, height - 360);
		g.drawString(used[1], 250, height - 360);
		g.drawString(used[2], 390, height - 360);
		g.drawString(used[3], 110, height - 220);
		g.drawString(used[4], 250, height - 220);
		g.drawString(used[5], 390, height - 220);
		g.drawString(used[6], 110, height - 80);
		g.drawString(used[7], 250, height - 80);
		g.drawString(used[8], 390, height - 80);
	}
	
	/**
	 * Uses up the specified field and places the current player's symbol
	 * @param i The field to place the symbol on from 0-8 i.e.:
	 * 			 _0_|_1_|_2_
	 * 			 _3_|_4_|_5_
	 * 			  6 | 7 | 8 
	 */
	public void useUp(int i)
	{
		if(!used[i].equals(" ") || won)
			return;
		
		used[i] = x ? "X" : "O";
		x = !x;
		
		if(used[0].equals(used[1]) && used[1].equals(used[2]) && !used[1].equals(" "))
		{
			won = true;
			winner = used[1];
		}
		else if(used[3].equals(used[4]) && used[4].equals(used[5]) && !used[4].equals(" "))
		{
			won = true;
			winner = used[4];
		}
		else if(used[6].equals(used[7]) && used[7].equals(used[8]) && !used[7].equals(" "))
		{
			won = true;
			winner = used[7];
		}
		else if(used[0].equals(used[4]) && used[4].equals(used[8]) && !used[4].equals(" "))
		{
			won = true;
			winner = used[4];
		}
		else if(used[2].equals(used[4]) && used[4].equals(used[6]) && !used[4].equals(" "))
		{
			won = true;
			winner = used[4];
		}
		else if(used[0].equals(used[3]) && used[3].equals(used[6]) && !used[3].equals(" "))
		{
			won = true;
			winner = used[3];
		}
		else if(used[1].equals(used[4]) && used[4].equals(used[7]) && !used[4].equals(" "))
		{
			won = true;
			winner = used[4];
		}
		else if(used[2].equals(used[5]) && used[5].equals(used[8]) && !used[5].equals(" "))
		{
			won = true;
			winner = used[5];
		}
		else
		{
			int count = 0;
			
			for(int j = 0; j < used.length; j++)
			{
				if(!used[j].equals(" "))
					count++;
			}
			
			if(count == 9)
				tie = true;
		}
	}
	
	/**
	 * Resets the game so it can be started again
	 */
	public void reset()
	{
		for(int i = 0; i < used.length; i++)
		{
			used[i] = " ";
		}
		
		x = winner.equals("X") ? false : true;
		won = false;
		tie = false;
		winner = "";
	}
	
	/**
	 * @return The game thread
	 */
	public GameThread getThread()
	{
		return thread;
	}

	/**
	 * @return The shapes on this screen
	 */
	public Shapes getShapes()
	{
		return shapes;
	}
}	
