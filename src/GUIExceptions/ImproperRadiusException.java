package GUIExceptions;

public class ImproperRadiusException extends Exception
	{
	private final int r;
	
	public ImproperRadiusException(int r)
		{
		this.r = r;
		}
		
	public String getMessage()
		{
		return "Radius should be a positive number. Radius given: " + r;
		}
	}
