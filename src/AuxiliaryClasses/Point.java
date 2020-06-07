package AuxiliaryClasses;

/*
 * Used to mark Areas 
 * 
 * Area is a largest possible set of pixels where each pixel can be reached 
 * from every pixel without stepping on wall - the idea is analogical to 
 * connected graphs
 */
public class Point
	{
	//Point is defined with two numbers
	private final int x;
	private final int y;

	public boolean equals(int x, int y)
		{
		return (this.x == x && this.y == y);
		}
		
	public String toString()
		{
		return "Point: x = " + x + "     y = " + y;
		}
	
	public int getX()
		{
		return x;
		}
		
	public int getY()
		{
		return y;
		}
	
	public Point(int x, int y)
		{
		this.x = x;
		this.y = y;
		}
	}
