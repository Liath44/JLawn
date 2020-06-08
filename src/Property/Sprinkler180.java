package Property;

/*
 * Sprinkler which type is 180
 */
public class Sprinkler180 extends Sprinkler
    {
    private final static int TYPE = 180;
    private final static int CODE = 1;
    //number of cycles
    private final static int EFFPARAM = 3;
    
    public void putSprinkler(Lawn lawn)
        {
        putFirstPixel(lawn);
        switch(deg)
            {   
            case(0):
                putPlusUp(lawn);
                putPlusRight(lawn);
                putPlusLeft(lawn);
                quadrant1(lawn);
                quadrant2(lawn);
            break;
            case(90):
                putPlusUp(lawn);
                putPlusLeft(lawn);
                putPlusDown(lawn);
                quadrant2(lawn);
                quadrant3(lawn);
            break;
            case(180):
                putPlusLeft(lawn);
                putPlusDown(lawn);
                putPlusRight(lawn);
                quadrant3(lawn);
                quadrant4(lawn);
            break;
            case(270):
                putPlusUp(lawn);
                putPlusRight(lawn);
                putPlusDown(lawn);
                quadrant1(lawn);
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

    public Sprinkler180(int x, int y, int deg)
        {
        super(x, y, deg);
        }
    }
