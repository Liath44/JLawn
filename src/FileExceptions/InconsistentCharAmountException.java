package FileExceptions;

/*
 * Thrown when the amount of characters in one row
 * is inconsistent with the amount of characters in other rows 
 */
public class InconsistentCharAmountException extends Exception
	{
	public String getMessage()
		{
		return "Inconsistent amount of characters in a row";
		}	
		
	public InconsistentCharAmountException()
		{
		super();
		}
	}
