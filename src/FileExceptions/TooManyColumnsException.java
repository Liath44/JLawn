package FileExceptions;

/*
 * Thrown when too many characters are present in a column
 */
public class TooManyColumnsException extends Exception
	{
	//maximum allowed number of columns 
	private final int maxlen;
	
	public TooManyColumnsException(int maxlen)
		{
		this.maxlen = maxlen;
		}
		
	public String getMessage()
		{
		return "Too many columns defined in file. Max number of columns: " + maxlen;
		}
	}
