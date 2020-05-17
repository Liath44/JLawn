import AuxiliaryClasses.*;
import java.util.ArrayList;

public class Planner
	{
	private void signAreaJump(Lawn lawn, int xsize, int ysize, int x, int y)
		{
		lawn.markPixel(x * LawnReader.getJump(), y * LawnReader.getJump());
		if(x + 1 < xsize && lawn.getPixelJump(x + 1, y) == 1)
			signAreaJump(lawn, xsize, ysize, x + 1, y);
		if(x - 1 >= 0 && lawn.getPixelJump(x - 1, y) == 1)
			signAreaJump(lawn, xsize, ysize, x - 1, y);
		if(y + 1 < ysize && lawn.getPixelJump(x, y + 1) == 1)
			signAreaJump(lawn, xsize, ysize, x, y + 1);
		if(y - 1 >= 0 && lawn.getPixelJump(x, y - 1) == 1)
			signAreaJump(lawn, xsize, ysize, x, y - 1);
		}	
		
	private void resetSignsJump(Lawn lawn, int xsize, int ysize)
		{
		for(int j = 0; j < ysize; j++)
			{
			for(int i = 0; i < xsize; i++)
				lawn.markPixel(i * LawnReader.getJump(), j * LawnReader.getJump());
			}
		}
		
	private ArrayList<Point> findAreas(Lawn lawn)
		{
		int xsize = lawn.getXSize()/LawnReader.getJump();
		int ysize = lawn.getYSize()/LawnReader.getJump();
		ArrayList<Point> areas = new ArrayList<>();
		for(int j = 0; j < ysize; j++)
			{
			for(int i = 0; i < xsize; i++)
				{
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
	
	private boolean isNotOnList(int x, int y, ArrayList<Rectangle> rectangles)
		{
		for(Rectangle rectangle: rectangles)
			{
			if(rectangle.isInRectangle(x, y))
				return false;
			}
		return true;
		}
		
	private int calcLenRight(Lawn lawn, int x, int y, int xsize)
		{
		int i = x + 1;
		while(i < xsize && lawn.getPixelJump(i, y) != 0)
			i++;
		return i - x;
		}	
		
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
		
	private ArrayList<Rectangle> areaRectangulization(Lawn lawn, Point area)
		{
		ArrayList<Rectangle> rectangles = new ArrayList<>();
		upDownRectangle(lawn, area.getX(), area.getY(), lawn.getXSize()/LawnReader.getJump(), 
		lawn.getYSize()/LawnReader.getJump(), rectangles);
		return rectangles;
		}
	}
