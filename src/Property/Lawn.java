package Property;

import java.util.ArrayList;

/*
 * Used to store and manage lawn
 */
public class Lawn
	{
	//matrix that stores lawn's pixels
	private final int[][] lawn;
	private ArrayList<Sprinkler> sprinklers;
	//number of sprinkler360 cycles
	private static int time = 1;
	//number of pixels that can be watered
	private int waterablepixels;
	/*
	 * sometimes lawn can be watered but will
	 * need to be re-watered due to the change
	 * in conditions
	 */
	private boolean waswatered = false;
	
	public int getPixel(int x, int y)
		{
		return lawn[y][x];
		}
		
	public int getPixelJump(int x, int y)
		{
		return lawn[y*LawnReader.getJump()][x*LawnReader.getJump()];
		}
		
	public void waterPixel(int x, int y, int nospins)
		{
		lawn[y][x] += time*nospins;
		}
		
	public void markPixel(int x, int y)
		{
		lawn[y][x] = -lawn[y][x];
		}
		
	public int getYSize()
		{
		return lawn.length;
		}
		
	public int getXSize()
		{
		return lawn[0].length;
		}
		
	public void addSprinkler(Sprinkler sprinkler)
		{
		sprinklers.add(sprinkler);
		}
		
	public ArrayList<Sprinkler> getSprinklers()
		{
		return sprinklers;
		}
		
	public int getSprinklerNumber()
		{
		return sprinklers.size();
		}
		
	public static void setTime(int time)
		{
		Lawn.time = time;
		}
		
	public void resetSprinklers()
		{
		sprinklers = new ArrayList<>();
		}	
		
	public boolean getWasWatered()
		{
		return waswatered;
		}
		
	public void setWasWatered()
		{
		waswatered = true;
		}
		
	public void resetLawn()
		{
		for(int j = 0; j < getYSize(); j++)
			{
			for(int i = 0; i < getXSize(); i++)
				{
				if(lawn[j][i] != 0)
					lawn[j][i] = 1;
				}
			}
		}
		
	public int maxPixel()
		{
		int outcome = lawn[0][0];
		for(int j = 0; j < getYSize(); j++)
			{
			for(int i = 0; i < getXSize(); i++)
				{
				if(lawn[j][i] > outcome)
					outcome = lawn[j][i];
				}
			}
		return outcome;
		}
		
	public void setWaterablePixels()
		{
		int outcome = 0;
		for(int j = 0; j < getYSize(); j++)
			{
			for(int i = 0; i < getXSize(); i++)
				{
				if(lawn[j][i] != 0)
					outcome++;
				}
			}
		waterablepixels = outcome;
		}
		
	public int meanPixel()
		{
		int sum = 0;
		for(int j = 0; j < getYSize(); j++)
			{
			for(int i = 0; i < getXSize(); i++)
				{
				sum += lawn[j][i];
				}
			}
		return sum/waterablepixels;
		}
		
	public void printLawn()
		{
		for(int j = 0; j < getYSize(); j++)
			{
			for(int i = 0; i < getXSize(); i++)
				System.out.print(getPixel(i, j));
			System.out.print("\n");
			}
		}
		
	public Lawn(int[][] lawn)
		{
		this.lawn = lawn;
		sprinklers = new ArrayList<>();
		}
	}
