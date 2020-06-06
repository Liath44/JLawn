package Property;
/*
import java.util.ArrayList;
import AuxiliaryClasses.*;
 */

public class Main
    {
    public static void main(String[] args)
        {
	    LawnReader lawnreader = new LawnReader();
		Exporter exporter = new Exporter();
		try
			{
			Lawn lawn = lawnreader.createLawn(args[0], 1);
			Gardener Josh = new Gardener();
			Josh.placeSprinklers(lawn, new Planner());
			exporter.createBitmap(lawn, "BITMAP");
			exporter.createSprinklerList(lawn, "SPRINKLERS");
			}
		catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		finally 
			{
			try
				{
				exporter.tidyUp();
				lawnreader.tidyUp();
				}
			catch(Exception e)
				{
				System.out.println(e.getMessage());
				}
			}
        }
    }
