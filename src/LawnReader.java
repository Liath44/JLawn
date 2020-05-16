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
	
	private void copyRow(int row, int to, int[][] source, int[][] destination)
		{
		for(int i = 0; i < to; i++)
			destination[row][i] = source[row][i];
		}
		
	private void copyRec(int endcol, int endrow, int[][] source, int[][] destination)
		{
		for(int i = 0; i < endrow; i++)
			copyRow(i, endcol, source, destination);
		}
		
	private void fillRow(int with, int row, int from, int to, int[][] destination)
		{
		for(int i = from; i < to; i++)
			destination[row][i] = with;
		}
		
	private void fillRec(int with, int begrow, int endrow, int begcol, int endcol, int[][] destination)
		{
		for(int i = begrow; i < endrow; i++)
			fillRow(with, i, begcol, endcol, destination);
		}
	
	private int[][] makeLonger(int[][] prevoutcome, int prevlen, int newlen)
		{
		int[][] outcome = new int[1][newlen];
		copyRow(0, prevlen, prevoutcome, outcome);
		return outcome;
		}
	
	private int[][] makeDeeper(int[][] prevoutcome, int prevwidth, int newwidth)
		{
		int[][] outcome = new int[newwidth][prevoutcome[0].length];
		copyRec(prevoutcome[0].length, prevwidth, prevoutcome, outcome);
		return outcome;
		}
		
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
				outcome = makeLonger(outcome, curlen, maxlen);
				}
			if(c == GRASS)
				{
				fillRow(1, 0, curlen, curlen+JUMP, outcome);
				curlen += JUMP;
				}
			else if(c == WALL)
				{
				fillRow(0, 0, curlen, curlen+JUMP, outcome);
				curlen += JUMP;
				}
			else
				throw new ImproperCharException((char)c);
			c = filereader.read();
			}
		return outcome;
		}
	
	private int[][] readRest(FileReader filereader, int[][] outcome)
		{
		int maxwidth = JUMP;
		int curwidth = JUMP;
		outcome = makeDeeper(outcome, 1, maxwidth);
		for(int i = 1; i < curwidth; i++)
			
		}
		
	private int[][] readFile(String path) throws IOException, EmptyFileException, ImproperCharException, 
	TooManyColumnsException
		{
		FileReader filereader = new FileReader(path);
		int[][] outcome = new int[1][JUMP];
		int c = filereader.read();
		if(c == -1)
			throw new EmptyFileException();
		outcome = readFirstRow(c, filereader, outcome);
		outcome = readRest(filereader, outcome);
		filereader.close();
		}
		
	public Lawn createLawn(String path, int time, boolean bounce) throws IOException, EmptyFileException, 
	ImproperCharException, TooManyColumnsException
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
