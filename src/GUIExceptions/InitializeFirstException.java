package GUIExceptions;

public class InitializeFirstException extends Exception
	{
	public InitializeFirstException()
		{
		}
		
	public String getMessage()
		{
		return "Lawn should be first initialized by importing proper file";
		}
	}
