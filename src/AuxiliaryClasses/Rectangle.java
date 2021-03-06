package AuxiliaryClasses;

/*
 * During the process of watering the Lawn each Area
 * is divided into rectangles.
 */
public class Rectangle
	{
	/*
	 * Rectangle is defined with two points
	 * p1 - left-up corner
	 * p2 - right-down corner
	 */
	private Point p1;
	private Point p2;

	/*
	 * Checks if point is inside the rectangle
	 * 
	 * int x, y - coordinates of said point
	 */
	public boolean isInRectangle(int x, int y)
		{
		return (x >= p1.getX() && y >= p1.getY() && x <= p2.getX() && y <= p2.getY());
		}
	
	//returns width of the rectangle
	public int getWidth()
		{
		return p2.getY() - p1.getY() + 1;
		}	
		
	//returns length of the rectangle
	public int getLength()
		{
		return p2.getX() - p1.getX() + 1;
		}
		
	public int getMiddleLength()
		{
		return (p1.getX() + p2.getX())/2;
		}
		
	public int getMiddleWidth()
		{
		return (p1.getY() + p2.getY())/2;
		}
		
	public String toString()
		{
		return "Rectangle:\n" + p1.toString() + "\n" + p2.toString();
		}
		
	public void setP1(Point p1)
		{
		this.p1 = p1;
		}
		
	public void setP2(Point p2)
		{
		this.p2 = p2;
		}
		
	public int getP1X()
		{
		return p1.getX();
		}
		
	public int getP1Y()
		{
		return p1.getY();
		}
		
	public int getP2X()
		{
		return p2.getX();
		}
		
	public int getP2Y()
		{
		return p2.getY();
		}
		
	public Point getP1()
		{
		return p1;
		}
		
	public Point getP2()
		{
		return p2;
		}
		
	public Rectangle()
		{
		}
	}
