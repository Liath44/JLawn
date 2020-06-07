package FileExceptions;

/*
 * Thrown when a character read from lawnfile does not 
 * represent lawn nor wall
 */
public class ImproperCharException extends Exception
	{
	//eponymous improper char
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
