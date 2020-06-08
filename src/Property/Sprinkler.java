package Property;

/*
 * Describes joint arguments and methods
 * for every sprinkler
 */
//TODO: abstract variables - type/code???
//TODO(?): another class for type and code. Just pass the reference
public abstract class Sprinkler
	{
	//coordinates of sprinkler
	private final int x;
	private final int y;
	//placement degree of sprinkler
	protected final int deg;
	protected static int radius = 50;
	//should the water bounce against walls
	private static boolean bounce = true;
	
	public static void setRadius(int radius)
		{
		Sprinkler.radius = radius;
		}
		
	public static void setBounce()
		{
		bounce = !bounce;
		}
		
	public static void setBounce(boolean bounce)
		{
		Sprinkler.bounce = bounce;
		}
		
	public int getDiameter()
		{
		return getRadius() * 2 + 1;
		}
		
	public int getx()
		{
		return x;
		}
		
	public int gety()
		{
		return y;
		}
		
	public int getDeg()
		{
		return deg;
		}
		
	public String toString()
		{
		return "type: " + getType() + " x = " + x + " y = " + y + " degree: " + getDeg() + "\n";
		}
	
	//Transforms given code to type
	//0/1/2/3 -> 90/180/270/360
	public static int codeToType(int code)
		{
		return code * 90 + 90;
		}

	//Transforms given type to code
	//0/1/2/3 <- 90/180/270/360
	public static int typeToCode(int type)
		{
		return type/90 - 1;
		}
		
	public static boolean getBounce()
		{
		return bounce;
		}

	public Sprinkler(int x, int y, int deg)
		{
		this.x = x;
		this.y = y;
		this.deg = deg;
		}
		
	/*
	 * Waters Lawn north to the put sprinkler's center
	 * 
	 * Lawn lawn - lawn to be watered
	 */
	protected void putPlusUp(Lawn lawn)
		{
		int i = y - 1;
		int yi = i;
		int direction = -1;
		if(y == 0 || lawn.getPixel(x, i) == 0)
			{
			if(!bounce)
				return;
			yi = y;
			direction = 1;
			}
		while(i >= y - getRadius())
			{
			lawn.waterPixel(x, yi, 2*getEffParam());
			if(yi + direction == -1 || yi + direction == lawn.getYSize() || lawn.getPixel(x, yi+direction) == 0)
				{
				if(!bounce)
					return;
				direction *= -1;
				}
			else
				yi += direction;
			--i;
			}
		}

	/*
	 * Waters Lawn south to the put sprinkler's center
	 *
	 * Lawn lawn - lawn to be watered
	 */
	protected void putPlusDown(Lawn lawn)
		{
		int i = y + 1;
		int yi = i;
		int direction = 1;
		if(y == lawn.getYSize() - 1 || lawn.getPixel(x, y+1) == 0)
			{
			if(!bounce)
				return;
			yi = y;
			direction = -1;
			}
		while(i <= y + getRadius())
			{
			lawn.waterPixel(x, yi, 2*getEffParam());
			if(yi + direction == -1 || yi + direction == lawn.getYSize() || lawn.getPixel(x, yi + direction) == 0)
				{
				if(!bounce)
					return;
				direction *= -1;
				}
			else
				yi += direction;
			++i;
			}
		}

	/*
	 * Waters Lawn west to the put sprinkler's center
	 *
	 * Lawn lawn - lawn to be watered
	 */
	protected void putPlusLeft(Lawn lawn)
		{
		int i = x - 1;
		int xi = i;
		int direction = -1;
		if(x == 0 || lawn.getPixel(x - 1, y) == 0)
			{
			if(!bounce)
				return;
			xi = x;
			direction = 1;
			}
		while(i >= x - getRadius())
			{
			lawn.waterPixel(xi, y, 2*getEffParam());
			if(xi + direction == -1 || xi + direction == lawn.getXSize() || lawn.getPixel(xi + direction, y) == 0)
				{
				if(!bounce)
					return;
				direction *= -1;
				}
			else
				xi += direction;
			--i;
			}
		}

	/*
	 * Waters Lawn east to the put sprinkler's center
	 *
	 * Lawn lawn - lawn to be watered
	 */
	protected void putPlusRight(Lawn lawn)
		{
		int i = x + 1;
		int xi = i;
		int direction = 1;
		if(x + 1 == lawn.getXSize() || lawn.getPixel(x + 1, y) == 0)
			{
			if(!bounce)
				return;
			xi = x;
			direction = -1;
			}
		while(i <= x + getRadius())
			{
			lawn.waterPixel(xi, y, 2*getEffParam());
			if(xi + direction == -1 || xi + direction == lawn.getXSize() || lawn.getPixel(xi + direction, y) == 0)
				{
				if(!bounce)
					return;
				direction *= -1;
				}
			else
				xi += direction;
			++i;
			}
		}
	
	/*
	 * Updates Lawn's first quadrant from south to north
	 * 
	 * Lawn lawn - lawn to be watered
	 */
	private void quadrant1DownUp(Lawn lawn)
		{
		int i = x + 1;
		while(i <= x + getRadius() && i < lawn.getXSize() && lawn.getPixel(i, y) != 0)
			{
			int j = y - 1;
			int yi = j;
			int direction = -1;
			if(y == 0 || lawn.getPixel(i, y - 1) == 0)
				{
				//If we shouldn't bounce then end this iteration
				if(!bounce)
					j = y - getRadius() - 1;
				yi = y;
				direction = 1;
				}
			while((x-i)*(x-i)+(y-j)*(y-j) <= getRadius() * getRadius())
				{
				lawn.waterPixel(i, yi, getEffParam());
				if(yi + direction == -1 || yi + direction == lawn.getYSize() || lawn.getPixel(i, yi + direction) == 0)
					{
					if(!bounce)
						j = y - getRadius();
					direction *= -1;
					}
				else
					yi += direction;
				--j;
				}
			++i;
			}
		}

	/*
	 * Analogical to quadrant1DownUp(...) but updates
	 * first quadrant from west to east
	 */
	private void quadrant1LeftRight(Lawn lawn)
		{
		int j = y - 1;
		while(j >= y - getRadius() && j >= 0 && lawn.getPixel(x, j) != 0)
			{
			int i = x + 1;
			int xi = i;
			int direction = 1;
			if(x == lawn.getXSize() - 1 || lawn.getPixel(x + 1, j) == 0)
				{
				if(!bounce)
					i = x - getRadius() - 1;
				xi = x;
				direction = -1;
				}
			while((x-i)*(x-i)+(y-j)*(y-j) <= getRadius() * getRadius())
				{
				lawn.waterPixel(xi, j, getEffParam());
				if(xi + direction == -1 || xi + direction == lawn.getXSize() || lawn.getPixel(xi + direction, j) == 0)
					{
					if(!bounce)
						i = x - getRadius() - 2;
					direction *= -1;
					}
				else
					xi += direction;
				++i;
				}
			--j;
			}
		}

	/*
	 * Analogical to quadrant1DownUp(...) but updates
	 * second quadrant from south to north
	 */
	private void quadrant2DownUp(Lawn lawn)
		{
		int i = x - 1;
		while(i >= x - getRadius() && i >= 0 && lawn.getPixel(i, y) != 0)
			{
			int j = y - 1;
			int yi = j;
			int direction = -1;
			if(y == 0 || lawn.getPixel(i, y - 1) == 0)
				{
				if(!bounce)
					j = y - getRadius() - 1;
				yi = y;
				direction = 1;
				}
			while((x-i)*(x-i)+(y-j)*(y-j) <= getRadius()*getRadius())
				{
				lawn.waterPixel(i, yi, getEffParam());
				if(yi+direction == -1 || yi+direction == lawn.getYSize() || lawn.getPixel(i, yi + direction) == 0)
					{
					if(!bounce)
						j = y - getRadius();
					direction *= -1;
					}
				else
					yi += direction;
				--j;
				}
			--i;
			}
		}

	/*
	 * Analogical to quadrant1DownUp(...) but updates
	 * second quadrant from east to west
	 */
	private void quadrant2RightLeft(Lawn lawn)
		{
		int j = y - 1;
		while(j >= y - getRadius() && j >= 0 && lawn.getPixel(x, j) != 0)
			{
			int i = x - 1;
			int xi = i;
			int direction = -1;
			if(x == 0 || lawn.getPixel(x - 1, j) == 0)
				{
				if(!bounce)
					i = x - getRadius() - 1;
				xi = x;
				direction = 1;
				}
			while((x-i)*(x-i)+(y-j)*(y-j) <= getRadius() * getRadius())
				{
				lawn.waterPixel(xi, j, getEffParam());
				if(xi + direction == -1 || xi + direction == lawn.getXSize() || lawn.getPixel(xi + direction, j) == 0)
					{
					if(!bounce)
						i = x - getRadius();
					direction *= -1;
					}
				else
					xi += direction;
				--i;
				}
			--j;
			}
		}

	/*
	 * Analogical to quadrant1DownUp(...) but updates
	 * third quadrant from north to south
	 */
	private void quadrant3UpDown(Lawn lawn)
		{
		int i = x - 1;
		while( i >= x - getRadius() && i >= 0 && lawn.getPixel(i, y) != 0)
			{
			int j = y + 1;
			int yi = j;
			int direction = 1;
			if(y == lawn.getYSize() - 1 || lawn.getPixel(i, y + 1) == 0)
				{
				if(!bounce)
					j = y - getRadius() - 1;
				yi = y;
				direction = -1;
				}
			while((x-i)*(x-i)+(y-j)*(y-j) <= getRadius()*getRadius())
				{
				lawn.waterPixel(i, yi, getEffParam());
				if(yi + direction == -1 || yi + direction == lawn.getYSize() || lawn.getPixel(i, yi + direction) == 0)
					{
					if(!bounce)
						j = y - getRadius() - 2;
					direction *= -1;
					}
				else
					yi += direction;
				++j;
				}
			--i;
			}
		}

	/*
	 * Analogical to quadrant1DownUp(...) but updates
	 * third quadrant from east to west
	 */
	private void quadrant3RightLeft(Lawn lawn)
		{
		int j = y + 1;
		while(j <= y + getRadius() && j < lawn.getYSize() && lawn.getPixel(x, j) != 0)
			{
			int i = x - 1;
			int xi = i;
			int direction = -1;
			if(x == 0 || lawn.getPixel(x - 1, j) == 0)
				{
				if(!bounce)
					i = x - getRadius() - 1;
				xi = x;
				direction = 1;
				}
			while((x-i)*(x-i)+(y-j)*(y-j) <= getRadius() * getRadius())
				{
				lawn.waterPixel(xi, j, getEffParam());
				if(xi + direction == -1 || xi + direction == lawn.getXSize() || lawn.getPixel(xi + direction, j) == 0)
					{
					i = x - getRadius();
					direction *= -1;
					}
				else
					xi += direction;
				--i;
				}
			++j;
			}
		}

	/*
	 * Analogical to quadrant1DownUp(...) but updates
	 * fourth quadrant from north to south
	 */
	private void quadrant4UpDown(Lawn lawn)
		{
		int i = x + 1;
		while(i <= x + getRadius() && i < lawn.getXSize() && lawn.getPixel(i, y) != 0)
			{
			int j = y + 1;
			int yi = j;
			int direction = 1;
			if(y == lawn.getYSize() - 1 || lawn.getPixel(i, y + 1) == 0)
				{
				if(!bounce)
					j = y - getRadius() - 1;
				yi = y;
				direction = -1;
				}
			while((x-i)*(x-i)+(y-j)*(y-j) <= getRadius() * getRadius())
				{
				lawn.waterPixel(i, yi, getEffParam());
				if(yi + direction == -1 || yi + direction == lawn.getYSize() || lawn.getPixel(i, yi + direction) == 0)
					{
					if(!bounce)
						j = y - getRadius() - 2;
					direction *= -1;
					}
				else
					yi += direction;
				++j;
				}
			++i;
			}
		}

	/*
	 * Analogical to quadrant1DownUp(...) but updates
	 * fourth quadrant from west to east
	 */
	private void quadrant4LeftRight(Lawn lawn)
		{
		int j = y + 1;
		while(j <= y + getRadius() && j < lawn.getYSize() && lawn.getPixel(x, j) != 0)
			{
			int i = x + 1;
			int xi = i;
			int direction = 1;
			if(x == lawn.getXSize() - 1 || lawn.getPixel(x + 1, j) == 0)
				{
				if(!bounce)
					i = x - getRadius() - 1;
				xi = x;
				direction = -1;
				}
			while((x-i)*(x-i)+(y-j)*(y-j) <= getRadius() * getRadius())
				{
				lawn.waterPixel(xi, j, getEffParam());
				if(xi + direction == -1 || xi + direction == lawn.getXSize() || lawn.getPixel(xi + direction, j) == 0)
					{
					if(!bounce)
						i = x - getRadius() - 2;
					direction *= -1;
					}
				else
					xi += direction;
				++i;
				}
			++j;
			}
		}	
		
	/*
	 * Fills first quadrant of a circle
	 * 
	 * Lawn lawn - lawn to be watered
	 */
	protected void quadrant1(Lawn lawn)	
		{
		quadrant1DownUp(lawn);
		quadrant1LeftRight(lawn);
		}

	/*
	 * Fills second quadrant of a circle
	 *
	 * Lawn lawn - lawn to be watered
	 */
	protected void quadrant2(Lawn lawn)
		{
		quadrant2RightLeft(lawn);
		quadrant2DownUp(lawn);
		}

	/*
	 * Fills third quadrant of a circle
	 *
	 * Lawn lawn - lawn to be watered
	 */
	protected void quadrant3(Lawn lawn)
		{
		quadrant3RightLeft(lawn);
		quadrant3UpDown(lawn);
		}

	/*
	 * Fills fourth quadrant of a circle
	 *
	 * Lawn lawn - lawn to be watered
	 */
	protected void quadrant4(Lawn lawn)
		{
		quadrant4LeftRight(lawn);
		quadrant4UpDown(lawn);
		}
		
	/*
	 * Fills the middle of a circle
	 * 
	 * Lawn lawn - lawn to be watered
	 */
	protected void putFirstPixel(Lawn lawn)
		{
		lawn.waterPixel(x, y, 2*getEffParam());
		}
		
	/* 
	 * Sprinkler waters the lawn in accordance to its parameters
	 * 
	 * Lawn lawn - lawn to be watered
	 */
	public abstract void putSprinkler(Lawn lawn);
	public abstract int getCode();
	public abstract int getType();
	//returns number of cycles
	public abstract int getEffParam();
	public abstract int getRadius();
	}
