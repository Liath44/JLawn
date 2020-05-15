import java.util.ArrayList;

public class Lawn
	{
	private int[][] lawn;
	private ArrayList<Sprinkler> sprinklers;
	private int time;
	
	public int getPixel(int x, int y)
		{
		return lawn[y][x];
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
	}
