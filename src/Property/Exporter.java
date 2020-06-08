package Property;

import AuxiliaryClasses.Colour;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Responsible for exporting sprinklers file and bitmap
 */
public class Exporter 
	{
	//number of green/red shades present in palette
	private final static int NOSHADES = 106;
	//lightest green shade is (0, 45, 0)
	private final static int ENDSHADE = 45;
	private final Picasso8 picasso;
	//used to write sprinklers output file
	private FileWriter writer;
		
	/*
	 * Creates sprinklers file
	 * 
	 * Lawn lawn - stores lawn which stores list of used sprinklers
	 * 
	 * String path - path to output file
	 */
	public void createSprinklerList(Lawn lawn, String path) throws IOException
		{
		writer = new FileWriter(path);
		writer.write("Number of sprinklers: ");
		writer.write(Integer.toString(lawn.getSprinklerNumber()));
		writer.write("\n\n");
		for(Sprinkler sprinkler: lawn.getSprinklers())
			{
			writer.write(sprinkler.toString());
			}
		}
	
	/*
	 * Writes necessary colours to palette by calling 
	 * adequate functions from picasso8 (FileOutputStream
	 * is in there)
	 */
	private void initializePalette() throws IOException
		{
		//fill greens - from lightest to dimmest
		for(int i = 255; i >= ENDSHADE; i -= 2)
			picasso.writeColour(new Colour(0, i, 0));
		//fill reds - from lightest to dimmest
		for(int j = 255; j >= ENDSHADE; j -= 2)
			picasso.writeColour(new Colour(0, 0, j));
		//white
		picasso.writeColour(new Colour(255, 255, 255));
		//black
		picasso.writeColour(new Colour(0, 0,0 ));
		}
		
	/*
	 * Writes the picture itself into the bitmap file
	 * 
	 * Lawn lawn - stores lawn
	 */
	private void paintBitmap(Lawn lawn) throws IOException
		{
		int pixelmean = lawn.meanPixel();
		//If number of required greens exceeds number of greens in palette
		//then each pixel is compressed (same for reds)
		//example - greencompression == 2 then pixel 45 and 46 might have the same shade
		int greencompression = (lawn.maxPixel() - pixelmean)/NOSHADES + 1;
		int redcompression =  (pixelmean - 1)/NOSHADES + 1;
		for(int j = lawn.getYSize() - 1; j >= 0; --j)
			{
			for(int i = 0; i < lawn.getXSize(); i++)
				{
				int pixel = lawn.getPixel(i, j);
				//wall - black pixel
				if(pixel == 0)
					picasso.paintBitmap(2 * NOSHADES + 1);
				//empty - white pixel
				else if(pixel == 1)
					picasso.paintBitmap(2 * NOSHADES);
				//underwatered - red pixel
				else if(pixel < pixelmean)
					picasso.paintBitmap((pixel - 1)/redcompression + NOSHADES);
				//watered well - green pixel
				else//pixel >= pixelmean
					picasso.paintBitmap((pixel - pixelmean)/greencompression);
				}
			}
		}
	
	/*
	 * Responsible for closing picasso8.FileOutputStream
	 * and writer
	 */
	public void tidyUp() throws IOException
		{
		if(writer != null)
			writer.close();
		picasso.tidyUp();
		}
		
	/*
	 * Creates bitmap
	 * 
	 * Lawn lawn - stores lawn
	 * 
	 * String path - path to output file
	 */
	public void createBitmap(Lawn lawn, String path) throws IOException
		{
		picasso.initializeBitmap(path, lawn);
		initializePalette();
		paintBitmap(lawn);
		}
		
	public Exporter()
		{
		picasso = new Picasso8(2 * NOSHADES + 2);
		}
	}
