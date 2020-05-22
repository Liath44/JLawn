/*
import java.util.ArrayList;
import AuxiliaryClasses.*;
 */

public class Main
    {
    public static void main(String[] args)
        {
	    LawnReader lawnreader = new LawnReader();
	    try
			{
			Sprinkler.setRadius(2);
			Gardener gardener = new Gardener();
			Lawn lawn = lawnreader.createLawn("lawnfile", 10);
			Planner planner = new Planner();
			gardener.placeSprinklers(lawn, planner);
			lawn.printLawn();
			}
	    catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
        }
    }
