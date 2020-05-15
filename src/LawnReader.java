public class LawnReader
	{
	private final static char GRASS = '*';
	private final static char WALL = '#';
	private final static int JUMP = 100;
	private final static int MAXWIDTH = 8000;
	private final static int MAXLEN = 4000;
	
	private int[][] readFile(String path)
		{
		
		}
		
	public Lawn createLawn(String path, int time, boolean bounce)
		{
		return new Lawn(readFile(path), time, bounce);
		}
	
	public LawnReader()
		{
		}
	}
