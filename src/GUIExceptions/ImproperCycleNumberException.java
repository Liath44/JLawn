package GUIExceptions;

/*
 * Thrown when given number in CycleNumberPanel is improper
 */
public class ImproperCycleNumberException extends Exception
	{
	//subject improper number
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
