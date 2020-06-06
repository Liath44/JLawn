package GUIExceptions;

public class ImproperTimeException extends Exception
	{
	private final int t;
	
	public ImproperTimeException(int t)
		{
		this.t = t;
		}
		
	public String getMessage()
		{
		return "Time should be a positive number. Number given: " + t;
		}
	}
