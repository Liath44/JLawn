public class Sprinkler90 extends Sprinkler
	{
	private final static int TYPE = 90;
	private final static int CODE = 0;
	private final static int EFFPARAM = 4;

	//TODO
	public void putSprinkler(Lawn lawn)
		{
		System.out.println("Sprinkler 90: x = " + x + " y = " + y + " deg = " + deg);
		}
	
	public int getCode()
		{
		return CODE;
		}
		
	public int getType()
		{
		return TYPE;
		}
		
	public int getEffParam()
		{
		return EFFPARAM;
		}

	public int getRadius()
		{
		return EFFPARAM * radius;
		}
		
	public Sprinkler90(int x, int y, int deg)
		{
		super(x, y, deg);
		}
	}
