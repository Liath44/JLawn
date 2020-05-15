package FileExceptions;

public class TooManyColumnsException extends Exception
	{
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
