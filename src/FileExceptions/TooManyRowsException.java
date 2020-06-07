package FileExceptions;

/*
 * Thrown when too many characters are present in a row
 */
public class TooManyRowsException extends Exception
	{
	//maximum allowed number of rows
	private final int maxwid;
	
	public TooManyRowsException(int maxwid)
		{
		this.maxwid = maxwid;
		}
		
	public String getMessage()
		{
		return "Too many rows defined in file. Max number of rows: " + maxwid;
		}
	}
