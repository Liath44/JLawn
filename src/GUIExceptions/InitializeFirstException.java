package GUIExceptions;

/*
 * Thrown when operation on Lawn is required but it hasn't been initialized.
 * In other words lawn file hasn't been given yet and lawn == null
 */
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
