import AuxiliaryClasses.GardenersNote;
import AuxiliaryClasses.Rectangle;
import AuxiliaryClasses.Point;
import java.util.ArrayList;

public class Gardener
	{
	private final ArrayList<Sprinkler> catalogue;
		
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
		if(note.getMode() == 'm')
			placeOneMiddle(lawn, rectangle);
		else
			placeSprGreedily(lawn, rectangle, note);
		}
		
	private void placeSprGreedily(Lawn lawn, Rectangle rectangle, GardenersNote note)
		{
		int xlen = rectangle.getLength();
		int ylen = rectangle.getWidth();
		note.setAreaCovered(catalogue.get(Sprinkler.typeToCode(note.getType())).getRadius());
		if(xlen <= note.getCirXLen())
			placeOnYAxis(lawn, rectangle, note, catalogue.get(Sprinkler.typeToCode(note.getType())).getRadius());
		else if(ylen <= note.getCirYLen())
			
		else
			
		}
		
	private void placeOnYAxis(Lawn lawn, Rectangle rectangle, GardenersNote note, int radius)
		{
		int x;
		int jump;
		int i = rectangle.getP1Y();
		int shift = 1;
		int stopparam = rectangle.getP2Y();
		if(note.getType() == 90 || (note.getType() == 180 && note.getDeg() == 90))
			x = rectangle.getP1X();
		else
			x = rectangle.getMiddleLength();
		if(note.getType() == 360 || (note.getType() == 180 && note.getDeg() == 90))
			jump = radius + 1;
		else
			jump = (1 + radius)/2;
		for(; i <= stopparam; i += jump)
			{
			switch(note.getType())
				{
				case 360:
					for(int j = 0; j < 3; j++)
						{
						Sprinkler360 sprinkler360 = new Sprinkler360(x, i, 0);
						sprinkler360.putSprinkler(lawn);
						lawn.addSprinkler(sprinkler360);
						}
				break;
				case 270:
					Sprinkler270 sprinkler270 = new Sprinkler270(x, i, note.getDeg());
					sprinkler270.putSprinkler(lawn);
					lawn.addSprinkler(sprinkler270);
					if(note.getDeg() == 0)
						note.setDeg(180);
					else
						{
						note.setDeg(0);
						i += radius;
						}
					x += shift;
					shift *= -1;
				break;
				case 180:
					Sprinkler180 sprinkler180 = new Sprinkler180(x, i, note.getDeg());
					sprinkler180.putSprinkler(lawn);
					lawn.addSprinkler(sprinkler180);
				break;
				default:
					Sprinkler90 sprinkler90 = new Sprinkler90(x, i, note.getDeg());
					sprinkler90.putSprinkler(lawn);
					lawn.addSprinkler(sprinkler90);
				}
			}
		if(note.getDeg() == 180)
			{
			i -= jump;
			if(lawn.getPixel(x+radius/2, i+radius/2) != 0)
				{
				for(int k = 0; k < 3; k++)
					{
					Sprinkler360 bonussprinkler = new Sprinkler360(x+radius/2, i+radius/2, 0);
					bonussprinkler.putSprinkler(lawn);
					lawn.addSprinkler(bonussprinkler);
					}
				}
			}
		else if(note.getType() == 180 && note.getDeg() == 0)
			{
			Sprinkler180 bonussprinkler = new Sprinkler180(x, rectangle.getP2Y(), 0);
			bonussprinkler.putSprinkler(lawn);
			lawn.addSprinkler(bonussprinkler);
			}
		else if(note.getType() == 90)
			{
			Sprinkler90 bonussprinkler = new Sprinkler90(x, rectangle.getP2Y(), 0);
			bonussprinkler.putSprinkler(lawn);
			lawn.addSprinkler(bonussprinkler);
			}
		}	
		
	private void placeOneMiddle(Lawn lawn, Rectangle rectangle)
		{
		for(int i = 0; i < 3; i++)
			{
			Sprinkler360 sprinkler = new Sprinkler360(rectangle.getMiddleLength(), rectangle.getMiddleWidth(), 0);
			sprinkler.putSprinkler(lawn);
			lawn.addSprinkler(sprinkler);
			}
		}
		
	private GardenersNote makeDecisions(Rectangle rectangle)
		{
		GardenersNote note = new GardenersNote();
		int xlen = rectangle.getLength();
		int ylen = rectangle.getWidth();
		if(xlen <= catalogue.get(3).getDiameter() && ylen <= catalogue.get(3).getDiameter())
			{
			note.setMode('m');
			return note;
			}
		int curbest = xlen + ylen;
		if(isABetterFit(curbest, xlen, ylen, catalogue.get(3).getDiameter(), catalogue.get(3).getDiameter()))
			curbest = scribbleANote(note, 0, Sprinkler.codeToType(3), xlen, ylen, catalogue.get(3).getDiameter(), catalogue.get(3).getDiameter());
		if(isABetterFit(curbest, xlen, ylen, catalogue.get(2).getDiameter(), catalogue.get(2).getDiameter()))
			curbest = scribbleANote(note, 0, Sprinkler.codeToType(2), xlen, ylen, catalogue.get(2).getDiameter(), catalogue.get(2).getDiameter());
		if(isABetterFit(curbest, xlen, ylen, catalogue.get(1).getDiameter(), catalogue.get(1).getRadius()+1))
			curbest = scribbleANote(note, 0, Sprinkler.codeToType(1), xlen, ylen, catalogue.get(1).getDiameter(), catalogue.get(1).getRadius()+1);
		if(isABetterFit(curbest, xlen, ylen, catalogue.get(1).getRadius()+1, catalogue.get(1).getDiameter()))
			curbest = scribbleANote(note, 90, Sprinkler.codeToType(1), xlen, ylen, catalogue.get(1).getRadius()+1, catalogue.get(1).getDiameter());
		if(isABetterFit(curbest, xlen, ylen, catalogue.get(0).getDiameter(), catalogue.get(0).getDiameter()))
			scribbleANote(note, 0, Sprinkler.codeToType(0), xlen, ylen, catalogue.get(0).getDiameter(), catalogue.get(0).getDiameter());
		return note;
		}
		
	private boolean isABetterFit(int curbest, int recxlen, int recylen, int cirxlen, int cirylen)
		{
		return (recxlen >= cirxlen && recylen >= cirylen && recxlen%cirxlen + recylen%cirylen <= curbest);
		}
		
	private int scribbleANote(GardenersNote note, int deg, int type, int recxlen, int recylen, int cirxlen, int cirylen)
		{
		note.setType(type);
		note.setDeg(deg);
		return recxlen%cirxlen + recylen%cirylen;
		}	
		
	public Gardener()
		{
		catalogue = new ArrayList<>();
		catalogue.add(new Sprinkler90(0, 0, 0));
		catalogue.add(new Sprinkler180(0, 0, 0));
		catalogue.add(new Sprinkler270(0, 0, 0));
		catalogue.add(new Sprinkler360(0, 0, 0));
		}
	}
