package AuxiliaryClasses;

/*
 * Stores information on best sprinkler to fill
 * rectangle with.
 */
public class GardenersNote 
    {
    /*
     * mode:
     * m - middle - place sprinkler in the middle
     * h - horizontal - fill multiple rows/columns
     */
    private char mode;
    //sprinkler type - 90/180/270/360
    private int type;
    //how sprinkler is placed - 0/90/180/270
    private int deg;
    
    /*
     * Each sprinkler can be fit into a certain rectangle
     * cirxlen and cirylen describe width and height of said rectangle
     */
    private int cirylen;
    private int cirxlen;
    
    /*
     * Adequately sets cirxlen and cirylen
     * 
     * int radius - radius of considered sprinkler
     */
    public void setAreaCovered(int radius)
        {
        //rectangle
        if(type == 180)
            { 
            //horizontal rectangle
            if(deg == 0)
                {
                cirxlen = 2 * radius + 1;
                cirylen = radius + 1;
                }
            //vertical rectangle
            else
                {
                cirxlen = radius + 1;
                cirylen = 2 * radius + 1;
                }
            }
        //square for other sprinkler types
        else
            {
            cirylen = 2 * radius + 1;
            cirxlen = 2 * radius + 1;
            }
        }
    
    public void setDeg(int deg)
        {
        this.deg = deg;
        }
        
    public void setMode(char mode)
        {
        this.mode = mode;
        }
    
    public void setType(int type)
        {
        this.type = type;
        }
    
    public int getDeg()
        {
        return deg;
        }
        
    public char getMode()
        {
        return mode;
        }
        
    public int getType()
        {
        return type;
        }
        
    public int getCirXLen()
        {
        return cirxlen;
        }
        
    public int getCirYLen()
        {
        return cirylen;
        }
    
    public GardenersNote()
        {   
        mode = 'h';
        type = 360;
        deg = 0;
        }
    }
