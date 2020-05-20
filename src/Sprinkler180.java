public class Sprinkler180 extends Sprinkler
    {
    private final static int TYPE = 180;
    private final static int CODE = 1;
    private final static int EFFPARAM = 3;

    //TODO
    public void putSprinkler(Lawn lawn)
        { 
        System.out.println("Sprinkler 180: x = " + x + " y = " + y + " deg = " + deg);
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
