import java.io.FileReader;
import java.io.IOException;
import FileExceptions.*;

public class LawnReader
	{
	//TODO: what if exception is thrown - filereader.close()
		
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
	
	private int[][] adjustLength(int[][] prevoutcome, int prevlen, int newlen)
		{
		int[][] outcome = new int[1][newlen];
		copyRow(0, prevlen, prevoutcome, outcome);
		return outcome;
		}
	
	private int[][] adjustDepth(int[][] prevoutcome, int prevwidth, int newwidth)
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
				throw new TooManyColumnsException(MAXLEN/JUMP);
			if(curlen == maxlen)
				{
				maxlen *= 2;
				outcome = adjustLength(outcome, curlen, maxlen);
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
		outcome = adjustLength(outcome, curlen, curlen);
		return outcome;
		}
	
	private int[][] readRest(FileReader filereader, int[][] outcome) throws IOException, 
	InconsistentCharAmountException, ImproperCharException, TooManyRowsException
		{
		int maxwidth = JUMP;
		int curwidth = JUMP;
		int maxlen = outcome[0].length;
		int curlen = 0;
		outcome = adjustDepth(outcome, 1, maxwidth);
		for(int i = 0; i < outcome[0].length; i += JUMP)
			fillRec(outcome[0][i], 1, curwidth, i, i+JUMP, outcome);
		int c = filereader.read();
		while(c != -1)
			{
			if(curwidth == MAXWIDTH)
				throw new TooManyRowsException(MAXWIDTH/JUMP);
			if(curwidth == maxwidth)
				{
				maxwidth *= 2;
				outcome = adjustDepth(outcome, curwidth, maxwidth);
				}
			if(c == '\n')
				{
				if(curlen != maxlen)
					throw new InconsistentCharAmountException();
				else
					{
					curlen = 0;
					curwidth += JUMP;
					}
				}
			//too much char
			else if(curlen == maxlen)
				throw new InconsistentCharAmountException();
			else if(c == GRASS)
				{
				fillRec(1, curwidth, curwidth+JUMP, curlen, curlen+JUMP, outcome);
				curlen += JUMP;
				}
			else if(c == WALL)
				{
				fillRec(0, curwidth, curwidth+JUMP, curlen, curlen+JUMP, outcome);
				curlen += JUMP;
				}
			else
				throw new ImproperCharException((char)c);
			c = filereader.read();
			}
		if(curlen != 0 && curlen != maxlen)
			throw new InconsistentCharAmountException();
		if(curlen == maxlen)
			curwidth += JUMP;
		outcome = adjustDepth(outcome, curwidth, curwidth);
		return outcome;
		}
		
	private int[][] readFile(String path) throws IOException, EmptyFileException, ImproperCharException, 
	TooManyColumnsException, InconsistentCharAmountException, TooManyRowsException
		{
		FileReader filereader = new FileReader(path);
		int[][] outcome = new int[1][JUMP];
		int c = filereader.read();
		if(c == -1)
			throw new EmptyFileException();
		if(c != GRASS && c != WALL)
			throw new ImproperCharException((char)c);
		outcome = readFirstRow(c, filereader, outcome);
		outcome = readRest(filereader, outcome);
		//TODO: what if exception is thrown
		filereader.close();
		return outcome;
		}
		
	public Lawn createLawn(String path, int time, boolean bounce) throws IOException, EmptyFileException, 
	ImproperCharException, TooManyColumnsException, InconsistentCharAmountException, TooManyRowsException
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
		
	public static int getJump()
		{
		return JUMP;
		}
	}
