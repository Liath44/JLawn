import java.util.ArrayList;

public class Lawn
	{
	private int[][] lawn;
	private ArrayList<Sprinkler> sprinklers;
	private int time;
	private boolean bounce;
	
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
		
	public void printLawn()
		{
		for(int j = 0; j < getYSize(); j++)
			{
			for(int i = 0; i < getXSize(); i++)
				System.out.print(getPixel(i, j));
			System.out.print("\n");
			}
		}
		
	public Lawn(int[][] lawn, int time, boolean bounce)
		{
		this.lawn = lawn;
		sprinklers = new ArrayList<>();
		this.time = time;
		this.bounce = bounce;
		}
	}
