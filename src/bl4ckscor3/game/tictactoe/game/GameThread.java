package bl4ckscor3.game.tictactoe.game;

import bl4ckscor3.game.tictactoe.TicTacToe;

/**
 * The game thread used to update and render the screen
 * @author bl4ckscor3
 */
public class GameThread extends Thread implements Runnable
{
	@Override
	public void run()
	{
		int targetTps = 60;
		double fpsTimer = System.currentTimeMillis();
		double secondsPerTick = 1.0D / targetTps; //how long to wait between each update
		double nanosecondsPerTick = secondsPerTick * 1_000_000_000.0D;
		double then = System.nanoTime();
		double now = System.nanoTime();
		double unprocessed = 0;
		
		while(true)
		{
			now = System.nanoTime();
			unprocessed += (now - then) / nanosecondsPerTick;
			then = now;
			
			while(unprocessed >= 1)
			{
				unprocessed--;
			}
			
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			TicTacToe.getScreen().repaint();
			
			if(System.currentTimeMillis() - fpsTimer >= 1000)
			{
				fpsTimer += 1000;
			}
		}
	}
}
