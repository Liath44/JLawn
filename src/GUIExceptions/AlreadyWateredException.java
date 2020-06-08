package GUIExceptions;

/*
 * Thrown when Lawn had been already watered and conditions didn't change
 */
public class AlreadyWateredException extends Exception
	{
	public AlreadyWateredException()
		{
		}
		
	public String getMessage()
		{
		return "Lawn has been already watered.";
		}
	}
