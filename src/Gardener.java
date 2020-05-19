import AuxiliaryClasses.GardenersNote;
import AuxiliaryClasses.Rectangle;
import AuxiliaryClasses.Point;
import java.util.ArrayList;

public class Gardener
	{
	public void placeSprinklers(Lawn lawn, Planner planner)
		{
		ArrayList<Point> areas = planner.findAreas(lawn);
		for(Point area: areas)
			{
			ArrayList<Rectangle> rectangles = planner.areaRectangulization(lawn, area);
			for(Rectangle rectangle: rectangles)
				{
				fillRecGreedily(lawn, rectangle);
				}
			}
		}
		
	private void fillRecGreedily(Lawn lawn, Rectangle rectangle)
		{
		GardenersNote note = makeDecisions(rectangle);
		}
	}
