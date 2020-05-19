public class Sprinkler270 extends Sprinkler
    {
    private final static int TYPE = 270;
    private final static int CODE = 2;
    private final static int EFFPARAM = 2;

    //TODO
    public void putSprinkler(Lawn lawn)
        {
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
