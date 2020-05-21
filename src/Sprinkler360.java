public class Sprinkler360 extends Sprinkler
    {
    private final static int TYPE = 360;
    private final static int CODE = 3;
    private final static int EFFPARAM = 1;
    
    public void putSprinkler(Lawn lawn)
        {
        putPlusUp(lawn);
        putPlusDown(lawn);
        putPlusRight(lawn);
        putPlusLeft(lawn);
        quadrant1(lawn);
        quadrant2(lawn);
        quadrant3(lawn);
        quadrant4(lawn);
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

    public Sprinkler360(int x, int y, int deg)
        {
        super(x, y, deg);
        }
    }
