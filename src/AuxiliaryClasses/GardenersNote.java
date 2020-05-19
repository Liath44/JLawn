package AuxiliaryClasses;

public class GardenersNote 
    {
    private char mode;
    private int type;
    
    public void setMode(char mode)
        {
        this.mode = mode;
        }
    
    public void setType(int type)
        {
        this.type = type;
        }
    
    public char getMode()
        {
        return mode;
        }
        
    public int getType()
        {
        return type;
        }
    
    public GardenersNote()
        {   
        mode = 'h';
        type = 360;
        }
    }
