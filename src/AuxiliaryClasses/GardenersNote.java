package AuxiliaryClasses;

public class GardenersNote 
    {
    private char mode;
    private int type;
    private int deg;
    
    private int cirylen;
    private int cirxlen;
    
    public void setAreaCovered(int radius)
        {   
        if(type == 180)
            {   
            if(deg == 0)
                {
                cirxlen = 2 * radius + 1;
                cirylen = radius + 1;
                }
            else
                {
                cirxlen = radius + 1;
                cirylen = 2 * radius + 1;
                }
            }
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
