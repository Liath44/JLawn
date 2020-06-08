package Property;

import AuxiliaryClasses.GardenersNote;
import AuxiliaryClasses.Rectangle;
import AuxiliaryClasses.Point;
import java.util.ArrayList;

/*
 * Responsible for communicating with Planner (in order to rectangulize)
 * and fill acquired rectangles accordingly
 */
//TODO: idea for a hypothetical redo. Setup another "rectangle" with method fill rectangle
//TODO: egz. rectangle that is filled with 2 * 180spr. xlen = radius+1 ylen = 2*radius+1
public class Gardener
	{
	//Sample sprinklers
	private final ArrayList<Sprinkler> catalogue;
		
	/*
	 * Places sprinklers on lawn
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * Planner planner - is able to find Areas and rectangulize
	 */
	public void placeSprinklers(Lawn lawn, Planner planner)
		{
		//if lawn was watered but needs to be watered because of change
		//in conditions the resetLawn()
		if(lawn.getWasWatered())
			lawn.resetLawn();
		//create new sprinklers list
		lawn.resetSprinklers();
		ArrayList<Point> areas = planner.findAreas(lawn);
		for(Point area: areas)
			{
			ArrayList<Rectangle> rectangles = planner.areaRectangulization(lawn, area);
			for(Rectangle rectangle: rectangles)
				{
				fillRecGreedily(lawn, rectangle);
				}
			}
		lawn.setWasWatered();
		}
	/*
	 * Fills rectangle with sprinkler. Sprinklers type/deg
	 * and method of placement are chosen based on said rectangle 
	 * parameters (written into GardenersNote note)
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * Rectangle rectangle - rectangle to be filled
	 */
	private void fillRecGreedily(Lawn lawn, Rectangle rectangle)
		{
		//decide on type/deg and method of placement
		GardenersNote note = makeDecisions(rectangle);
		if(note.getMode() == 'm')
			placeOneMiddle(lawn, rectangle);
		else
			placeSprGreedily(lawn, rectangle, note);
		}
		
	/*
	 * Places sprinklers in rectangle according to the note
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * Rectangle rectangle - rectangle to be filled
	 * 
	 * GardenersNote note - rectangle will be filled
	 * according to the information stored inside note
	 */
	private void placeSprGreedily(Lawn lawn, Rectangle rectangle, GardenersNote note)
		{
		int xlen = rectangle.getLength();
		int ylen = rectangle.getWidth();
		note.setAreaCovered(catalogue.get(Sprinkler.typeToCode(note.getType())).getRadius());
		//circles won't fit on XAxis
		if(xlen <= note.getCirXLen())
			placeOnYAxis(lawn, rectangle, note, catalogue.get(Sprinkler.typeToCode(note.getType())).getRadius());
		//circles won't fit on YAxis
		else if(ylen <= note.getCirYLen())
			placeOnXAxis(lawn, rectangle, note, catalogue.get(Sprinkler.typeToCode(note.getType())).getRadius());
		else
			placeInRectangle(lawn, rectangle, note, catalogue.get(Sprinkler.typeToCode(note.getType())).getRadius());
		}
		
	/*
	 * Place sprinklers in n rows. n is based on note
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * Rectangle rectangle - rectangle to be filled
	 * 
	 * GardenersNote note - rectangle will be filled
	 * according to the information stored inside note
	 * 
	 * int radius - radius of sprinkler rectangle will be
	 * filled with
	 */
	private void placeInRectangle(Lawn lawn, Rectangle rectangle, GardenersNote note, int radius)
		{
		int jump;
		int stopparam = rectangle.getP2Y();
		if(note.getType() == 90 || (note.getType() == 180 && note.getDeg() == 0))
			jump = (radius + 1)/2;
		else
			jump = radius + 1;
		//for specified amount of rows
		for(int i = rectangle.getP1Y(); i <= stopparam; i += jump)
			placeSprRow(i, lawn, rectangle, note, radius);
		}
		
	/*
	 * Analogical to PlaceOnYAxis but row is specified by
	 * int y parameter
	 */
	private void placeSprRow(int y, Lawn lawn, Rectangle rectangle, GardenersNote note, int radius)
		{
		int jump;
		int stopparam = rectangle.getP2X();
		int shift = 1;
		int i = rectangle.getP1X();
		if(note.getType() == 270 || note.getType() == 90 || (note.getType() == 180 && note.getDeg() == 90))
			jump = (radius + 1)/2;
		else
			jump = radius + 1;
		for(; i <= stopparam; i += jump)
			{
			switch(note.getType())
				{
				case 360:
					for(int j = 0; j < 3; j++)
						{
						Sprinkler360 sprinkler360 = new Sprinkler360(i, y, 0);
						sprinkler360.putSprinkler(lawn);
						lawn.addSprinkler(sprinkler360);
						}
				break;
				case 270:
					Sprinkler270 sprinkler270 = new Sprinkler270(i, y, note.getDeg());
					sprinkler270.putSprinkler(lawn);
					lawn.addSprinkler(sprinkler270);
					if(note.getDeg() == 0)
						note.setDeg(180);
					else
						{
						note.setDeg(0);
						i += radius;
						}
					y += shift;
					shift *= -1;
				break;
				case 180:
					Sprinkler180 sprinkler180 = new Sprinkler180(i, y, note.getDeg());
					sprinkler180.putSprinkler(lawn);
					lawn.addSprinkler(sprinkler180);
				break;
				default:
					Sprinkler90 sprinkler90 = new Sprinkler90(i, y, note.getDeg());
					sprinkler90.putSprinkler(lawn);
					lawn.addSprinkler(sprinkler90);
				}
			}
		if(note.getDeg() == 180)
			{
			i -= jump;
			for(int k = 0; k < 3; k++)
				{
				Sprinkler360 bonussprinkler = new Sprinkler360(i+radius/2+1, y+radius/2, 0);
				bonussprinkler.putSprinkler(lawn);
				lawn.addSprinkler(bonussprinkler);
				}
			note.setDeg(0);
			}
		else if(note.getDeg() == 90)
			{
			Sprinkler90 bonussprinkler = new Sprinkler90(rectangle.getP2X(), y, 90);
			bonussprinkler.putSprinkler(lawn);
			lawn.addSprinkler(bonussprinkler);
			}
		else if(note.getType() == 180 && note.getDeg() == 90)
			{
			Sprinkler180 bonussprinkler = new Sprinkler180(rectangle.getP2X(), y, 90);
			bonussprinkler.putSprinkler(lawn);
			lawn.addSprinkler(bonussprinkler);
			}
		}
	
	/*
	 * Analogical to PlaceOnYAxis but sprinklers are placed
	 * vertically in rectangle R
	 */
	private void placeOnXAxis(Lawn lawn, Rectangle rectangle, GardenersNote note, int radius)
		{
		int y;
		int jump;
		int shift = 1;
		int stopparam = rectangle.getP2X();
		int i = rectangle.getP1X();
		if(note.getType() == 90 || (note.getType() == 180 && note.getDeg() == 0))
			y = rectangle.getP2Y();
		else
			y = rectangle.getMiddleWidth();
		if(note.getType() == 270 || note.getType() == 90 || (note.getType() == 180 && note.getDeg() == 90))
			jump = (radius + 1)/2;
		else
			jump = radius + 1;
		for(; i <= stopparam; i += jump)
			{
			switch(note.getType())
				{
				case 360:
					for(int j = 0; j < 3; j++)
						{
						Sprinkler360 sprinkler360 = new Sprinkler360(i, y, 0);
						sprinkler360.putSprinkler(lawn);
						lawn.addSprinkler(sprinkler360);
						}
				break;
				case 270:
					Sprinkler270 sprinkler270 = new Sprinkler270(i, y, 0);
					sprinkler270.putSprinkler(lawn);
					lawn.addSprinkler(sprinkler270);
					if(note.getDeg() == 0)
						note.setDeg(180);
					else
						{
						note.setDeg(0);
						i += jump;
						}
					y += shift;
					shift *= -1;
				break;
				case 180:
					Sprinkler180 sprinkler180 = new Sprinkler180(i, y, note.getDeg());
					sprinkler180.putSprinkler(lawn);
					lawn.addSprinkler(sprinkler180);	
				break;
				default:
					Sprinkler90 sprinkler90 = new Sprinkler90(i, y, note.getDeg());
					sprinkler90.putSprinkler(lawn);
					lawn.addSprinkler(sprinkler90);
				}
			}
		if(note.getDeg() == 180)
			{
			i -= jump;
			for(int k = 0; k < 3; k++)
				{
				Sprinkler360 bonussprinkler = new Sprinkler360(i+radius/2+1, y+radius/2, 0);
				bonussprinkler.putSprinkler(lawn);
				lawn.addSprinkler(bonussprinkler);
				}
			}
		else if(note.getType() == 90)
			{
			Sprinkler90 bonussprinkler = new Sprinkler90(rectangle.getP2X(), y, 90);
			bonussprinkler.putSprinkler(lawn);
			lawn.addSprinkler(bonussprinkler);
			}
		else if(note.getType() == 180 && note.getDeg() == 90)
			{
			Sprinkler180 bonussprinkler = new Sprinkler180(rectangle.getP2X(), y, 90);
			bonussprinkler.putSprinkler(lawn);
			lawn.addSprinkler(bonussprinkler);
			}
		}
		
	/*
	 * Places sprinklers horizontally in Rectangle rectangle
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * Rectangle rectangle - rectangle to be filled
	 * 
	 * GardenersNote note - rectangle will be filled
	 * according to the information stored inside note
	 *
	 * int radius - radius of sprinkler rectangle will be
	 * filled with
	 * 
	 * Other parameters:
	 * 
	 * int x - column to place sprinklers in
	 * 
	 * int jump - number of pixels between y-coords of middles
	 * of next two sprinklers
	 *
	 * int i - row in which next sprinkler should be placed
	 *
	 * int stopparam - don't place sprinklers after this y-coord
	 *
	 * int deg - 270 sprinklers are placed alternately (0 - 180)
	 * deg stores current degree to be used
	 *
	 * int shift - when placing 270 sprinklers they should be
	 * placed in x and x+1 column alternately
	 */
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
		//place sprinklers in column
		for(; i <= stopparam; i += jump)
			{
			switch(note.getType())
				{
				case 360:
					//place 3 sprinklers for 360
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
					//turn next sprinkler to fit currently placed sprinkler
					if(note.getDeg() == 0)
						note.setDeg(180);
					//new formation - bigger i update
					else
						{
						note.setDeg(0);
						i += jump;
						}
					//change to x/x+1 column and prepare for next shift
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
		//additional sprinkler
		else if(note.getType() == 180 && note.getDeg() == 0)
			{
			Sprinkler180 bonussprinkler = new Sprinkler180(x, rectangle.getP2Y(), 0);
			bonussprinkler.putSprinkler(lawn);
			lawn.addSprinkler(bonussprinkler);
			}
		//additional sprinkler
		else if(note.getType() == 90)
			{
			Sprinkler90 bonussprinkler = new Sprinkler90(x, rectangle.getP2Y(), 0);
			bonussprinkler.putSprinkler(lawn);
			lawn.addSprinkler(bonussprinkler);
			}
		}	
		
	/*
	 * Places one sprinkler in the middle
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * Rectangle rectangle - rectangle to be filled
	 */
	private void placeOneMiddle(Lawn lawn, Rectangle rectangle)
		{
		//always filled with 360 sprinkler
		for(int i = 0; i < 3; i++)
			{
			Sprinkler360 sprinkler = new Sprinkler360(rectangle.getMiddleLength(), rectangle.getMiddleWidth(), 0);
			sprinkler.putSprinkler(lawn);
			lawn.addSprinkler(sprinkler);
			}
		}
		
	/*
	 * Decides on with which sprinkler and how to fill rectangle
	 * 
	 * Rectangle rectangle - rectangle to be filled
	 * 
	 * Other parameters:
	 * 
	 * int curbest - each sprinkler can be fit into a specific
	 * rectangle. Sprinkler to fill with is chosen based on
	 * how well this specific rectangle fits into Rectangle rectangle
	 */
	private GardenersNote makeDecisions(Rectangle rectangle)
		{
		GardenersNote note = new GardenersNote();
		int xlen = rectangle.getLength();
		int ylen = rectangle.getWidth();
		//if sprinkler does not fit on both axis go for mode m
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
		//180 placed vertically
		if(isABetterFit(curbest, xlen, ylen, catalogue.get(1).getDiameter(), catalogue.get(1).getRadius()+1))
			curbest = scribbleANote(note, 0, Sprinkler.codeToType(1), xlen, ylen, catalogue.get(1).getDiameter(), catalogue.get(1).getRadius()+1);
		//180 placed horizontally
		if(isABetterFit(curbest, xlen, ylen, catalogue.get(1).getRadius()+1, catalogue.get(1).getDiameter()))
			curbest = scribbleANote(note, 90, Sprinkler.codeToType(1), xlen, ylen, catalogue.get(1).getRadius()+1, catalogue.get(1).getDiameter());
		if(isABetterFit(curbest, xlen, ylen, catalogue.get(0).getDiameter(), catalogue.get(0).getDiameter()))
			scribbleANote(note, 0, Sprinkler.codeToType(0), xlen, ylen, catalogue.get(0).getDiameter(), catalogue.get(0).getDiameter());
		return note;
		}
		
	/*
	 * Checks if considered sprinkler fits better into rectangle
	 * 
	 * int curbest - currently best sprinkler's score
	 * 
	 * int recxlen, recylen - length and width of a rectangle
	 * 
	 * int cirxlen, cirylen - length and width of rectangle sprinkler
	 * can be fit into
	 */
	private boolean isABetterFit(int curbest, int recxlen, int recylen, int cirxlen, int cirylen)
		{
		return (recxlen >= cirxlen && recylen >= cirylen && recxlen%cirxlen + recylen%cirylen <= curbest);
		}
		
	/*
	 * Writes information int note
	 * 
	 * GardenersNote note - note
	 * 
	 * int deg, type - information to be written into note
	 * 
	 * int recxlen, recylen - length and width of a rectangle
	 * 
	 * int cirxlen, cirylen - length and width of rectangle sprinkler
	 * can be fit into
	 * 
	 * returns score based on how well sprinkler fits the rectangle
	 */
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
