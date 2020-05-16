package FileExceptions;

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
