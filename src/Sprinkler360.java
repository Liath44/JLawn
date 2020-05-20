public class Sprinkler360 extends Sprinkler
    {
    private final static int TYPE = 360;
    private final static int CODE = 3;
    private final static int EFFPARAM = 1;

    //TODO
    public void putSprinkler(Lawn lawn)
        {
        System.out.println("Sprinkler 360: x = " + x + " y = " + y + " deg = " + deg);
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
