package GUIExceptions;

public class ImproperCycleNumberException extends Exception
	{
	private final int number;
	
	public ImproperCycleNumberException(int number)
		{
		this.number = number;
		}
		
	public String getMessage()
		{
		return "Number of cycles should be positive. Number provided: " + number;
		}
	}
