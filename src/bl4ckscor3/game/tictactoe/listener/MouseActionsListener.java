package bl4ckscor3.game.tictactoe.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import bl4ckscor3.game.tictactoe.TicTacToe;
import bl4ckscor3.game.tictactoe.game.Screen;

/**
 * Models what happens with specific mouse actions
 * @author bl4ckscor3
 */
public class MouseActionsListener implements MouseListener
{
	@Override
	public void mouseReleased(MouseEvent e)
	{
		String i;
		Screen s = TicTacToe.getScreen();
		
		if((i = s.getShapes().getContains(e.getPoint())) != null)
		{
			switch(i)
			{
				case "Reset":
					s.reset();
					break;
				case "Exit":
					System.exit(0);
					break;
				default:
					s.useUp(Integer.parseInt(i));
					break;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}
	
	@Override
	public void mouseClicked(MouseEvent e){}

	@Override
	public void mousePressed(MouseEvent e){}
}
