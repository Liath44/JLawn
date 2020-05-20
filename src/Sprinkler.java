public abstract class Sprinkler
	{
	//TODO: abstract variables - type/code???
	//workaround: another class for type and code. Just pass the reference
	protected final int x;
	protected final int y;
	//placement degree of sprinkler
	protected final int deg;
	protected static int radius;
	
	public static void setRadius(int radius)
		{
		Sprinkler.radius = radius;
		}
		
	public int getDiameter()
		{
		return getRadius() * 2 + 1;
		}
		
	public static int codeToType(int code)
		{
		return code * 90 + 90;
		}
		
	public static int typeToCode(int type)
		{
		return type/90 - 1;
		}

	public Sprinkler(int x, int y, int deg)
		{
		this.x = x;
		this.y = y;
		this.deg = deg;
		}
		
	protected void putPlusUp(Lawn lawn)
		{
		int i = y - 1;
		int yi = i;
		int direction = -1;
		if(y == 0 || lawn.getPixel(x, i) == 0)
			{
			yi = y;
			direction = 1;
			}
		while(i >= y - getRadius())
			{
			lawn.waterPixel(x, yi, 2*getEffParam());
			if(yi + direction == -1 || yi + direction == lawn.getYSize() || lawn.getPixel(x, yi+direction) == 0)
				direction *= -1;
			else
				yi += direction;
			--i;
			}
		}
		
	protected void putPlusDown(Lawn lawn)
		{
		int i = y + 1;
		int yi = i;
		int direction = 1;
		if(y == lawn.getYSize() - 1 || lawn.getPixel(x, y+1) == 0)
			{
			yi = y;
			direction = -1;
			}
		while(i <= y + getRadius())
			{
			lawn.waterPixel(x, yi, 2*getEffParam());
			if(yi + direction == -1 || yi + direction == lawn.getYSize() || lawn.getPixel(x, yi + direction) == 0)
				direction *= -1;
			else
				yi += direction;
			++i;
			}
		}
		
	protected void putPlusLeft(Lawn lawn)
		{
		int i = x - 1;
		int xi = i;
		int direction = -1;
		if(x == 0 || lawn.getPixel(x - 1, y) == 0)
			{
			xi = x;
			direction = 1;
			}
		while(i >= x - getRadius())
			{
			lawn.waterPixel(xi, y, 2*getEffParam());
			if(xi + direction == -1 || xi + direction == lawn.getXSize() || lawn.getPixel(xi + direction, y) == 0)
				direction *= -1;
			else
				xi += direction;
			--i;
			}
		}
		
	protected void putPlusRight(Lawn lawn)
		{
		int i = x + 1;
		int xi = i;
		int direction = 1;
		if(x + 1 == lawn.getXSize() || lawn.getPixel(x + 1, y) == 0)
			{
			xi = x;
			direction = -1;
			}
		while(i <= x + getRadius())
			{
			lawn.waterPixel(xi, y, 2*getEffParam());
			if(xi + direction == -1 || xi + direction == lawn.getXSize() || lawn.getPixel(xi + direction, y) == 0)
				direction *= -1;
			else
				xi += direction;
			++i;
			}
		}
		
	protected void quadrant1DownUp(Lawn lawn)
		{
		int i = x + 1;
		while(i <= x + getRadius() && i < lawn.getXSize() && lawn.getPixel(i, y) != 0)
			{
			int j = y - 1;
			int yi = j;
			int direction = -1;
			if(y == 0 || lawn.getPixel(i, y - 1) == 0)
				{
				yi = y;
				direction = 1;
				}
			while((x-i)*(x-i)+(y-j)*(y-j) <= getRadius() * getRadius())
				{
				lawn.waterPixel(i, yi, getEffParam());
				if(yi + direction == -1 || yi + direction == lawn.getYSize() || lawn.getPixel(i, yi + direction) == 0)
					direction *= -1;
				else
					yi += direction;
				--j;
				}
			++i;
			}
		}
		
	void quadrant1LeftRight(Lawn lawn)
		{
		
		}
		
	public abstract void putSprinkler(Lawn lawn);
	public abstract int getCode();
	public abstract int getType();
	public abstract int getEffParam();
	public abstract int getRadius();
	}
