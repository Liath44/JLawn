package Property;

/*
 * Sprinkler which type is 90
 */
public class Sprinkler90 extends Sprinkler
	{
	private final static int TYPE = 90;
	private final static int CODE = 0;
	//number of cycles
	private final static int EFFPARAM = 4;
	
	public void putSprinkler(Lawn lawn)
		{
		putFirstPixel(lawn);
		switch(deg)
			{
			case 0:
				putPlusUp(lawn);
				putPlusRight(lawn);
				quadrant1(lawn);
			break;
			case 90:
				putPlusUp(lawn);
				putPlusLeft(lawn);
				quadrant2(lawn);
			break;
			case 180:
				putPlusDown(lawn);
				putPlusLeft(lawn);
				quadrant3(lawn);
			break;
			case 270:
				putPlusDown(lawn);
				putPlusRight(lawn);
				quadrant4(lawn);
			break;
			default:
				System.out.println("DEBUG - ERROR");
			}
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
