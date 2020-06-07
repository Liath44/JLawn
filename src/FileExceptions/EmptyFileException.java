package FileExceptions;

/*
 * Thrown when given lawnfile is empty (has no characters inside)
 */
public class EmptyFileException extends Exception
	{
	public EmptyFileException()
		{
		super();
		}
		
	public String getMessage()
		{
		return "Provided file is empty";
		}
	}
