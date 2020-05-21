public class Sprinkler270 extends Sprinkler
    {
    private final static int TYPE = 270;
    private final static int CODE = 2;
    private final static int EFFPARAM = 2;
    
    public void putSprinkler(Lawn lawn)
        {
        putPlusUp(lawn);
        putPlusRight(lawn);
        putPlusLeft(lawn);
        putPlusDown(lawn);
        switch(deg)
            {
            case(0):
                quadrant1(lawn);
                quadrant2(lawn);
                quadrant3(lawn);
            break;
            case(90):
                quadrant2(lawn);
                quadrant3(lawn);
                quadrant4(lawn);
            break;
            case(180):
                quadrant3(lawn);
                quadrant4(lawn);
                quadrant1(lawn);
            break;
            case(270):
                quadrant4(lawn);
                quadrant1(lawn);
                quadrant2(lawn);
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

    public Sprinkler270(int x, int y, int deg)
        {
        super(x, y, deg);
        }
    }
