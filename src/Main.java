import java.util.ArrayList;
import AuxiliaryClasses.*;

public class Main
    {
    public static void main(String[] args)
        {
	    LawnReader lawnreader = new LawnReader();
	    try
			{
			Lawn lawn = lawnreader.createLawn("lawnfile", 10, true);
			Planner planner = new Planner();
			ArrayList<Point> areas = planner.findAreas(lawn);
			for(Point area: areas)
				{ 
				System.out.println(area.toString());
				ArrayList<Rectangle> rectangles = planner.areaRectangulization(lawn, area);
				for(Rectangle rectangle: rectangles)
					{
					System.out.println(rectangle.toString());
					}
				System.out.println("");
				}
			lawn.printLawn();
			}
	    catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
        }
    }
