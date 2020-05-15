import java.io.FileReader;
import java.io.IOException;
import FileExceptions.*;

public class LawnReader
	{
	private final static char GRASS = '*';
	private final static char WALL = '#';
	private final static int JUMP = 100;
	private final static int MAXWIDTH = 8000;
	private final static int MAXLEN = 4000;
	
	private int[][] readFirstRow(int c, FileReader filereader, int[][] outcome) throws IOException, 
	ImproperCharException, TooManyColumnsException
		{
		int maxlen = JUMP;
		int curlen = 0;
		while(c != '\n' && c != -1)
			{
			if(curlen == MAXLEN)
				throw new TooManyColumnsException(MAXLEN);
			if(curlen == maxlen)
				{
				maxlen *= 2;
				
				}
			if(c == GRASS)
				{
				
				curlen += 100;
				}
			else if(c == WALL)
				{

				curlen += 100;
				}
			else
				throw new ImproperCharException((char)c);
			c = filereader.read();
			}
		}
	
	private int[][] readFile(String path) throws IOException, EmptyFileException
		{
		FileReader filereader = new FileReader(path);
		int[][] outcome = new int[1][JUMP];
		int c = filereader.read();
		if(c == -1)
			throw new EmptyFileException();
		outcome = readFirstRow(c, filereader, outcome);
		filereader.close();
		}
		
	public Lawn createLawn(String path, int time, boolean bounce) throws IOException, EmptyFileException
		{
		return new Lawn(readFile(path), time, bounce);
		}
	
	public LawnReader()
		{
		}
		
	public int getMAXLEN()
		{
		return MAXLEN;
		}
		
	public int getMAXWIDTH()
		{
		return MAXWIDTH;
		}
	}
