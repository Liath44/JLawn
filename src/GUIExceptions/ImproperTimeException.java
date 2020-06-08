package GUIExceptions;

/*
 * Thrown when given number in AnimationTimePanel is improper
 */
public class ImproperTimeException extends Exception
	{
	//subject improper number
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
