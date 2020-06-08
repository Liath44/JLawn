package GUIExceptions;

/*
 * Thrown when given number in RadiusPanel is improper
 */
public class ImproperRadiusException extends Exception
	{
	//subject improper number
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
