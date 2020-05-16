package FileExceptions;

public class TooManyRowsException extends Exception
	{
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
