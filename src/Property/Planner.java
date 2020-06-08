package Property;

import AuxiliaryClasses.Rectangle;
import AuxiliaryClasses.Point;
import java.util.ArrayList;

/*
 * Responsible for finding areas and rectangulization
 */
public class Planner
	{
	/*
	 * Rectangulizes area
	 * 
	 * Lan lawn - stores lawn
	 * 
	 * Point area - marks area
	 * 
	 * returns list of Rectangles which area is consisted of
	 */
	public ArrayList<Rectangle> areaRectangulization(Lawn lawn, Point area)
		{
		ArrayList<Rectangle> rectangles = new ArrayList<>();
		upDownRectangle(lawn, area.getX(), area.getY(), lawn.getXSize()/LawnReader.getJump(),
				lawn.getYSize()/LawnReader.getJump(), rectangles);
		return rectangles;
		}

	/*
	 * Finds areas in Lawn
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * returns list of Points - each Point marks one area
	 */
	public ArrayList<Point> findAreas(Lawn lawn)
		{
		int xsize = lawn.getXSize()/LawnReader.getJump();
		int ysize = lawn.getYSize()/LawnReader.getJump();
		ArrayList<Point> areas = new ArrayList<>();
		for(int j = 0; j < ysize; j++)
			{
			for(int i = 0; i < xsize; i++)
				{
				//pixel not marked - new area
				if(lawn.getPixelJump(i, j) == 1)
					{
					areas.add(new Point(i, j));
					signAreaJump(lawn, xsize, ysize, i, j);
					}
				}
			}
		resetSignsJump(lawn, xsize, ysize);
		return areas;
		}
		
	/*
	 * Marks entire area (recursively)
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * int xsize, ysize - lawn's size
	 * 
	 * int x, y - current pixel (to be marked)
	 */
	private void signAreaJump(Lawn lawn, int xsize, int ysize, int x, int y)
		{
		lawn.markPixel(x * LawnReader.getJump(), y * LawnReader.getJump());
		//check if neighbours should be marked
		if(x + 1 < xsize && lawn.getPixelJump(x + 1, y) == 1)
			signAreaJump(lawn, xsize, ysize, x + 1, y);
		if(x - 1 >= 0 && lawn.getPixelJump(x - 1, y) == 1)
			signAreaJump(lawn, xsize, ysize, x - 1, y);
		if(y + 1 < ysize && lawn.getPixelJump(x, y + 1) == 1)
			signAreaJump(lawn, xsize, ysize, x, y + 1);
		if(y - 1 >= 0 && lawn.getPixelJump(x, y - 1) == 1)
			signAreaJump(lawn, xsize, ysize, x, y - 1);
		}	
		
	/*
	 * De-marks entire lawn
	 */
	private void resetSignsJump(Lawn lawn, int xsize, int ysize)
		{
		for(int j = 0; j < ysize; j++)
			{
			for(int i = 0; i < xsize; i++)
				lawn.markPixel(i * LawnReader.getJump(), j * LawnReader.getJump());
			}
		}
	
	/*
	 * Checks if given point is inside one of rectangles
	 * 
	 * int x, y - coordinates of said point
	 * 
	 * List<Rectangle> rectangles - list of rectangles
	 * 
	 * returns false if point is inside at least one of rectangles
	 * returns true otherwise
	 */
	private boolean isNotOnList(int x, int y, ArrayList<Rectangle> rectangles)
		{
		for(Rectangle rectangle: rectangles)
			{
			if(rectangle.isInRectangle(x, y))
				return false;
			}
		return true;
		}
	
	/*
	 * Calculates considered rectangle length from
	 * left to right.
	 */
	private int calcLenRight(Lawn lawn, int x, int y, int xsize)
		{
		int i = x + 1;
		while(i < xsize && lawn.getPixelJump(i, y) != 0)
			i++;
		return i - x;
		}	
		
	/*
	 * Calculates considered rectangle length from
	 * right to left.
	 */
	private int calcLenLeft(Lawn lawn, int x, int y)
		{
		int i = x - 1;
		while(i >= 0 && lawn.getPixelJump(i, y) != 0)
			--i;
		return x - i;
		}

	/*
	 * Checks whether considered row should still be
	 * considered a part of subject rectangle or not
	 *
	 * Returns false if row is improper
	 * Returns true otherwise
	 */
	private boolean rowIsProper(Lawn lawn, int x, int y, int len, int xsize, int ysize)
		{
		if(y == ysize || y == -1)
			return false;
		if(x - 1 >= 0 && lawn.getPixelJump(x - 1, y) != 0)
			return false;
		int i = 0;
		while(i < len)
			{
			if(lawn.getPixelJump(x + i, y) == 0)
				return false;
			i++;
			}
		return (x + i >= xsize || lawn.getPixelJump(x + i, y) == 0);
		}	
		
	/*
	 * After examining subject rectangle check for
	 * other rectangles that could be examined
	 * from top to bottom (upDownRectangle(...))
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * int x - x coordinate of a right-down corner of
	 * subject rectangle
	 *
	 * int j - y coordinate of a row in which to search
	 * for new rectangles
	 *
	 * int xsize, ysize - Lawn's size
	 *
	 * int len - length of subject rectangle
	 * 
	 * List<Rectangle> rectangles - stores already found rectangles
	 */
	private void checkForUpDown(Lawn lawn, int x, int j, int xsize, int ysize, int len, ArrayList<Rectangle> rectangles)
		{
		int i = x + len - 1;
		while(i >= x)
			{
			if(lawn.getPixelJump(i, j) != 0 && (i == 0 || lawn.getPixelJump(i - 1, j) == 0))
				{
				if(isNotOnList(i * LawnReader.getJump(), j * LawnReader.getJump(), rectangles))
					upDownRectangle(lawn, i, j, xsize, ysize, rectangles);
				}
			--i;
			}
		if(i >= 0 && lawn.getPixelJump(i, j) != 0 && lawn.getPixelJump(i + 1, j) != 0)
			{
			while(i >= 0 && lawn.getPixelJump(i, j) != 0)
				--i;
			++i;
			if(isNotOnList(i * LawnReader.getJump(), j * LawnReader.getJump(), rectangles))
				upDownRectangle(lawn, i, j, xsize, ysize, rectangles);
			}
		}	
		
	/*
	 * Analogical to checkForDownUp but called
	 * by downUpRectangle
	 */
	private void checkForUpDown2(Lawn lawn, int x1, int x2, int y, int xsize, int ysize, ArrayList<Rectangle> rectangles)
		{
		int i = x1 - 1;
		if(lawn.getPixelJump(x1, y) != 0)
			{
			while(i >= 0 && lawn.getPixelJump(i, y) != 0)
				{
				if(lawn.getPixelJump(i, y+1) != 0)
					{
					int newrec = i - 1;
					while(newrec >= 0 && lawn.getPixelJump(newrec, y+1) != 0)
						--newrec;
					++newrec;
					if(isNotOnList(newrec*LawnReader.getJump(), LawnReader.getJump()*(y+1), rectangles))
						upDownRectangle(lawn, newrec, y+1, xsize, ysize, rectangles);
					while(i >= newrec)
						{
						if(lawn.getPixelJump(i, y) == 0)
							i = -4;
						--i;
						}
					++i;
					}
				--i;
				}
			}
		//TODO: check BUGREPPORT
		int j = x2 + 1;
		if(lawn.getPixelJump(x2, y) != 0)
			{
			while(j < xsize && lawn.getPixelJump(j, y) != 0)
				{
				if(lawn.getPixelJump(j, y+1) != 0)
					{
					if(isNotOnList(j*LawnReader.getJump(), (y+1)*LawnReader.getJump(), rectangles))
						upDownRectangle(lawn, j, y+1, xsize, ysize, rectangles);
					++j;
					while(j < xsize && lawn.getPixelJump(j, y+1) != 0)
						{
						if(lawn.getPixelJump(j, y) == 0)
							j = xsize + 4;
						++j;
						}
					--j;
					}
				++j;
				}
			}
		}
	
	/*
	 * After examining subject rectangle check for
	 * other rectangles that could be examined
	 * from bottom to top (upDownRectangle(...))
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * int x1 - x coordinate of a left-down corner of
	 * subject rectangle
	 *
	 * int x2 - x coordinate of a right-down corner of
	 * subject rectangle
	 *
	 * int y - y coordinate a row above which new
	 * rectangles should be searched for
	 *
	 * int xsize, ysize - Lawn's size
	 * 
	 * List<Rectangle> rectangles - stores already found rectangles
	 */
	private void checkForDownUp(Lawn lawn, int x1, int x2, int y, int xsize, int ysize, ArrayList<Rectangle> rectangles)
		{
		int i = x1 - 1;
		if(lawn.getPixelJump(x1, y) != 0)
			{
			while(i >= 0 && lawn.getPixelJump(i, y) != 0)
				{
				if(lawn.getPixelJump(i, y - 1) != 0)
					{
					if(isNotOnList(LawnReader.getJump()*(i+1)-1, LawnReader.getJump()*y-1, rectangles))
						downUpRectangle(lawn, i, y-1, xsize, ysize, rectangles);
					--i;
					while(i >= 0 && lawn.getPixelJump(i, y-1) != 0)
						{
						if(lawn.getPixelJump(i, y) == 0)
							i = -4;
						--i;
						}
					++i;
					}
				--i;
				}
			}
		int j = x2 + 1;
		if(lawn.getPixelJump(x2, y) != 0)
			{
			while(j < xsize && lawn.getPixelJump(j, y) != 0)
				{
				if(lawn.getPixelJump(j, y-1) != 0)
					{
					int newrec = j + 1;
					while(newrec < xsize && lawn.getPixelJump(newrec, y-1) != 0)
						++newrec;
					--newrec;
					if(isNotOnList(LawnReader.getJump()*(newrec+1)-1,y*LawnReader.getJump()-1, rectangles))
						downUpRectangle(lawn, newrec, y-1, xsize, ysize, rectangles);
					while(j <= newrec)
						{
						if(lawn.getPixelJump(j, y) == 0)
							j = xsize + 4;
						++j;
						}
					--j;
					}
				++j;
				}
			}
		}
	
	/*
	 * Analogical to checkForUpDown but called
	 * by cownUpRectangle
	 */
	private void checkForDownUp2(Lawn lawn, int x, int j, int xsize, int ysize, int len, ArrayList<Rectangle> rectangles)
		{
		int i1 = x - len + 1;
		while(i1 <= x)
			{
			if(lawn.getPixelJump(i1, j) != 0 && (i1 == xsize - 1 || lawn.getPixelJump(i1+1, j) == 0))
				{
				if(isNotOnList(LawnReader.getJump()*(i1+1)-1, LawnReader.getJump()*(j+1)-1, rectangles))
					downUpRectangle(lawn, i1, j, xsize, ysize, rectangles);
				}
			++i1;
			}
		if(i1 < xsize && lawn.getPixelJump(i1, j) != 0 && lawn.getPixelJump(i1-1, j) != 0)
			{
			while(i1 < xsize && lawn.getPixelJump(i1, j) != 0)
				++i1;
			--i1;
			if(isNotOnList(LawnReader.getJump()*(i1+1)-1, LawnReader.getJump()*(j+1)-1, rectangles))
				downUpRectangle(lawn, i1, j, xsize, ysize, rectangles);
			}
		}
		
	/*
	 * Function finds a rectangle which:
	 *  - is defined by two points one of which
	 * is (x, y)
	 *  - has the longest length possible
	 *  - is enclosed by walls from left & right
	 * The search is done from top-left corner to
	 * down-right corner.
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * int x, y - coordinates of top-left corner
	 *
	 * int xsize, ysize - Lawn's size
	 * 
	 * List<Rectangle> rectangles - stores already found rectangles
	 */
	private void upDownRectangle(Lawn lawn, int x, int y, int xsize, int ysize, ArrayList<Rectangle> rectangles)
		{
		Rectangle rectangle = new Rectangle();
		rectangles.add(rectangle);
		rectangle.setP1(new Point(x*LawnReader.getJump(), y*LawnReader.getJump()));
		int len = calcLenRight(lawn, x, y, xsize);
		int i = y + 1;
		while(rowIsProper(lawn, x, i, len, xsize, ysize))
			i++;
		rectangle.setP2(new Point(LawnReader.getJump()*(x+len)-1, LawnReader.getJump()*i-1));
		if(i < ysize)
			{
			checkForUpDown(lawn, x, i, xsize, ysize, len, rectangles);
			checkForDownUp(lawn, x, x + len - 1, i, xsize, ysize, rectangles);
			}
		}
	
	/*
	 * Analogical to UpDownRectangle but the
	 * search is done from down-right corner
	 * to up-left corner
	 */
	private void downUpRectangle(Lawn lawn, int x, int y, int xsize, int ysize, ArrayList<Rectangle> rectangles)
		{
		Rectangle rectangle = new Rectangle();
		rectangles.add(rectangle);
		rectangle.setP2(new Point(LawnReader.getJump()*(x+1)-1, LawnReader.getJump()*(y+1)-1));
		int len = calcLenLeft(lawn, x, y);
		int i = y - 1;
		while(rowIsProper(lawn, x-len+1, i, len, xsize, ysize))
			--i;
		rectangle.setP1(new Point((x-len+1)*LawnReader.getJump(), (i+1)*LawnReader.getJump()));
		if(i >= 0)
			{
			checkForUpDown2(lawn, x-len+1, x, i, xsize, ysize, rectangles);
			checkForDownUp2(lawn, x, i, xsize, ysize, len, rectangles);
			}
		}
		
	public Planner()
		{
		}
	}
