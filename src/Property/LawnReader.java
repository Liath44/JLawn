package Property;

import java.io.FileReader;
import java.io.IOException;
import FileExceptions.*;

/*
 * Used to create Lawn from provided file
 */
public class LawnReader
	{
	//char representing waterable pixel - 1
	private final static char GRASS = '*';
	//char representing wall - 0
	private final static char WALL = '#';
	//one sign equals to JUMPxJUMP lawn of either GRASS or WALL
	private final static int JUMP = 100;
	private final static int MAXWIDTH = 8000;
	private final static int MAXLEN = 4000;
	private FileReader filereader;
	
	/*
	 * Copies fragment of one row (values 0 - to)
	 * into another row with the same index from
	 * one matrix to other
	 * 
	 * int row - index of row that will be copied
	 * 
	 * int to - index of column on which to stop copying
	 * 
	 * int[][] source - matrix copied from
	 * 
	 * int[][] destination - matrix copied to
	 */
	private void copyRow(int row, int to, int[][] source, int[][] destination)
		{
		for(int i = 0; i < to; i++)
			destination[row][i] = source[row][i];
		}
		
	/*
	 * Copies a certain rectangle (from point (0,0) to
	 * (endcol, endrow)) into corresponding rectangle from 
	 * one matrix to other
	 * 
	 * int endcol, endrow 
	 *
	 * int[][] source - matrix copied from
	 *
	 * int[][] destination - matrix copied to
	 */
	private void copyRec(int endcol, int endrow, int[][] source, int[][] destination)
		{
		for(int i = 0; i < endrow; i++)
			copyRow(i, endcol, source, destination);
		}
		
	/*
	 * Fills specified row with specified value (from - to)
	 * 
	 * int with - value
	 * 
	 * int row - index of row to be filled
	 * 
	 * int from, to - start and end
	 * 
	 * int[][] destination - matrix to be filled
	 */
	private void fillRow(int with, int row, int from, int to, int[][] destination)
		{
		for(int i = from; i < to; i++)
			destination[row][i] = with;
		}
	
	/*
	 * Fills specified rectangle with specified value
	 * ((begcol, begrow), (endcol, endrow))
	 * 
	 * int with - value
	 *
	 * int[][] destination - matrix to be filled
	 */
	private void fillRec(int with, int begrow, int endrow, int begcol, int endcol, int[][] destination)
		{
		for(int i = begrow; i < endrow; i++)
			fillRow(with, i, begcol, endcol, destination);
		}
	
	/*
	 * Creates array longer than the specified one
	 * 
	 * int[][] prevoutcome - specified array
	 * 
	 * int prevlen - length of prevoutcome
	 * 
	 * int newlen - length of new array
	 * 
	 * returns created array
	 */
	private int[][] adjustLength(int[][] prevoutcome, int prevlen, int newlen)
		{
		int[][] outcome = new int[1][newlen];
		copyRow(0, prevlen, prevoutcome, outcome);
		return outcome;
		}
	
	/*
	 * Creates new matrix with width larger than specified matrix
	 * 
	 * int[][] prevoutcome - specified matrix
	 * 
	 * int prevwidth - width of prevoutcome
	 * 
	 * int newwidth - width of new array
	 * 
	 * return created array
	 */
	private int[][] adjustDepth(int[][] prevoutcome, int prevwidth, int newwidth)
		{
		int[][] outcome = new int[newwidth][prevoutcome[0].length];
		copyRec(prevoutcome[0].length, prevwidth, prevoutcome, outcome);
		return outcome;
		}
	
	/*
	 * Creates one by n matrix by reading first line of file
	 * 
	 * int c - first read character
	 * 
	 * int[][] outcome - matrix which will be filled/lengthened
	 * 
	 * returns desired matrix
	 */
	private int[][] readFirstRow(int c, int[][] outcome) throws IOException, 
	ImproperCharException, TooManyColumnsException
		{
		//outcome[][]'s length
		int maxlen = JUMP;
		//number of currently filled columns
		int curlen = 0;
		while(c != '\n' && c != -1)
			{
			if(curlen == MAXLEN)
				throw new TooManyColumnsException(MAXLEN/JUMP);
			//outcome[][] needs to be lengthened
			if(curlen == maxlen)
				{
				maxlen *= 2;
				outcome = adjustLength(outcome, curlen, maxlen);
				}
			//fill with ones
			if(c == GRASS)
				{
				fillRow(1, 0, curlen, curlen+JUMP, outcome);
				curlen += JUMP;
				}
			//fill with zeros
			else if(c == WALL)
				{
				fillRow(0, 0, curlen, curlen+JUMP, outcome);
				curlen += JUMP;
				}
			else
				throw new ImproperCharException((char)c);
			c = filereader.read();
			}
		//trim outcome[][] to proper size (curlen)
		outcome = adjustLength(outcome, curlen, curlen);
		return outcome;
		}
	/*
	 * After completing lawn's first row this function is called
	 * to deepen lawn to proper size and read rest of the file
	 * 
	 * int[][] outcome - current matrix
	 * 
	 * returns desired matrix
	 */
	private int[][] readRest(int[][] outcome) throws IOException, 
	InconsistentCharAmountException, ImproperCharException, TooManyRowsException
		{
		//outcome[][]'s width
		int maxwidth = JUMP;
		//number of currently filled rows
		int curwidth = JUMP;
		//outcome[][]'s length
		int maxlen = outcome[0].length;
		//number of currently filled columns
		int curlen = 0;
		//set proper width
		outcome = adjustDepth(outcome, 1, maxwidth);
		//fill with necessary values
		for(int i = 0; i < outcome[0].length; i += JUMP)
			fillRec(outcome[0][i], 1, curwidth, i, i+JUMP, outcome);
		int c = filereader.read();
		while(c != -1)
			{
			if(curwidth == MAXWIDTH)
				throw new TooManyRowsException(MAXWIDTH/JUMP);
			//adjust depth
			if(curwidth == maxwidth)
				{
				maxwidth *= 2;
				outcome = adjustDepth(outcome, curwidth, maxwidth);
				}
			//end of line - check if row's size was proper
			if(c == '\n')
				{
				if(curlen != maxlen)
					throw new InconsistentCharAmountException();
				//new row
				else
					{
					curlen = 0;
					curwidth += JUMP;
					}
				}
			//too much char
			else if(curlen == maxlen)
				throw new InconsistentCharAmountException();
			//fill with ones
			else if(c == GRASS)
				{
				fillRec(1, curwidth, curwidth+JUMP, curlen, curlen+JUMP, outcome);
				curlen += JUMP;
				}
			//fill with zeros
			else if(c == WALL)
				{
				fillRec(0, curwidth, curwidth+JUMP, curlen, curlen+JUMP, outcome);
				curlen += JUMP;
				}
			else
				throw new ImproperCharException((char)c);
			c = filereader.read();
			}
		//check if last row has proper size
		if(curlen != 0 && curlen != maxlen)
			throw new InconsistentCharAmountException();
		//last row requires adjusting curwidth
		if(curlen == maxlen)
			curwidth += JUMP;
		//trim to proper depth
		outcome = adjustDepth(outcome, curwidth, curwidth);
		return outcome;
		}
		
	/*
	 * Reads file in order to create lawn matrix
	 * 
	 * String path - path to input file
	 * 
	 * returns lawn matrix
	 */
	private int[][] readFile(String path) throws IOException, EmptyFileException, ImproperCharException, 
	TooManyColumnsException, InconsistentCharAmountException, TooManyRowsException
		{
		filereader = new FileReader(path);
		int[][] outcome = new int[1][JUMP];
		int c = filereader.read();
		if(c == -1)
			throw new EmptyFileException();
		if(c != GRASS && c != WALL)
			throw new ImproperCharException((char)c);
		outcome = readFirstRow(c, outcome);
		outcome = readRest(outcome);
		return outcome;
		}
		
	/*
	 * Creates Lawn from file
	 * 
	 * String path - path to input file
	 * 
	 * return said Lawn
	 */
	public Lawn createLawn(String path) throws IOException, EmptyFileException, 
	ImproperCharException, TooManyColumnsException, InconsistentCharAmountException, TooManyRowsException
		{
		Lawn lawn = new Lawn(readFile(path));
		lawn.setWaterablePixels();
		return lawn;
		}
	
	/*
	 * Responsible for closing FileReader
	 */
	public void tidyUp() throws IOException
		{
		if(filereader != null)	
			filereader.close();
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
