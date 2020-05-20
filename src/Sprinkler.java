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
		
	public abstract void putSprinkler(Lawn lawn);
	public abstract int getCode();
	public abstract int getType();
	public abstract int getEffParam();
	public abstract int getRadius();
	}
