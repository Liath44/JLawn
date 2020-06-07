package GUIExceptions;

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
