package AuxiliaryClasses;

/*
 * Stores information and methods necessary for 
 * writing colour into palette.
 */
public class Colour 
	{
	private final int blue;
	private final int green;
	private final int red;
	
	public byte getBlue()
		{
		return (byte)blue;
		}
		
	public byte getGreen()
		{
		return (byte)green;
		}
		
	public byte getRed()
		{
		return (byte)red;
		}
	
	public Colour(int blue, int green, int red)
		{
		this.blue = blue;
		this.green = green;
		this.red = red;
		}
	}
