package FileExceptions;

public class ImproperCharException extends Exception
	{
	private final char c;
	
	public ImproperCharException(char c)
		{
		this.c = c;
		}
		
	public String getMessage()
		{
		return "Improper char: " + c;
		}
	}
