import AuxiliaryClasses.Colour;

import java.io.FileWriter;
import java.io.IOException;

public class Exporter 
	{
	private final static int NOSHADES = 106;
	private final static int ENDSHADE = 45;
	private final Picasso8 picasso;
	private FileWriter writer;
		
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
	
	private void initializePalette() throws IOException
		{
		//fill greens - from lightest to dimmest
		for(int i = 255; i >= ENDSHADE; i -= 2)
			picasso.writeColour(new Colour(0, i, 0));
		//fill reds - from dimmest to lightest
		for(int j = 255; j >= ENDSHADE; j -= 2)
			picasso.writeColour(new Colour(0, 0, j));
		//white
		picasso.writeColour(new Colour(255, 255, 255));
		//black
		picasso.writeColour(new Colour(0, 0,0 ));
		}
		
	private void paintBitmap(Lawn lawn) throws IOException
		{
		int pixelmean = lawn.meanPixel();
		int greencompression = (lawn.maxPixel() - pixelmean)/NOSHADES + 1;
		//TODO: small bug - might ignore it lul
		int redcompression =  (pixelmean - 1)/NOSHADES + 1;
		for(int j = lawn.getYSize() - 1; j >= 0; --j)
			{
			for(int i = 0; i < lawn.getXSize(); i++)
				{
				int pixel = lawn.getPixel(i, j);
				if(pixel == 0)
					picasso.paintBitmap(2 * NOSHADES + 1);
				else if(pixel == 1)
					picasso.paintBitmap(2 * NOSHADES);
				else if(pixel < pixelmean)
					picasso.paintBitmap((pixel - 1)/redcompression + NOSHADES);
				else//pixel >= pixelmean
					picasso.paintBitmap((pixel - pixelmean)/greencompression);
				}
			}
		}
		
	public void tidyUp() throws IOException
		{
		if(writer != null)
			writer.close();
		picasso.tidyUp();
		}
		
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
