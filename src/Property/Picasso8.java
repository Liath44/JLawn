package Property;

import AuxiliaryClasses.Colour;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Handles actual writing bitmap to file
 */
public class Picasso8
	{
	private FileOutputStream fos;
	private final static int BPM = 8;
	//size of single colour from palette in bytes
	private final static int COLSIZE = 4;
	//size of file header in bytes
	private final static int FHSIZE = 14;
	//size of bitmap info header in bytes
	private final static int BIHSIZE = 40;
	//bitmap signature
	private final static byte[] SIGNATURE = {'B', 'M'};
	//number of colours in palette
	private final int nocolours;

	/*
	 * Writes colour into file
	 * Called during palette creation
	 * 
	 * Colour colour - colour to be written
	 */
	public void writeColour(Colour colour) throws IOException
		{
		//No need for additional offset when JUMP == 100
		fos.write(colour.getBlue());
		fos.write(colour.getGreen());
		fos.write(colour.getRed());
		fos.write((byte)0);
		}
	
	/*
	 * Writes FH and BIH into file	
	 * 
	 * String path - path to output file
	 * 
	 * Lawn lawn - stores lawn
	 */
	public void initializeBitmap(String path, Lawn lawn) throws IOException
		{
		fos = new FileOutputStream(path);
		fos.write(SIGNATURE);
		fos.write(intToDWord(lawn.getXSize() * lawn.getYSize() + FHSIZE + BIHSIZE + nocolours * COLSIZE));
		fos.write(intToWord(0));
		fos.write(intToWord(0));
		fos.write(intToDWord(FHSIZE + BIHSIZE + nocolours * COLSIZE));
		fos.write(intToDWord(BIHSIZE));
		fos.write(intToDWord(lawn.getXSize()));
		fos.write(intToDWord(lawn.getYSize()));
		fos.write(intToWord(1));
		fos.write(intToWord(BPM));
		fos.write(intToDWord(0));
		fos.write(intToDWord(0));
		fos.write(intToDWord(0));
		fos.write(intToDWord(0));
		fos.write(intToDWord(nocolours));
		fos.write(intToDWord(0));
		}

	/*
	 * Writes colour into file
	 * Called during painting the actual bitmap
	 * 
	 * int colour - index of colour to be written
	 */
	public void paintBitmap(int colour) throws IOException
		{
		//IF JUMP = 100 PADDING NOT NEEDED
		fos.write((byte)colour);
		}
		
	/*
	 * Closes FileOutputStream
	 */
	public void tidyUp() throws IOException
		{
		if(fos != null)
			fos.close();
		}
	
	/*
	 * Represents subject value with 4 bytes
	 * 
	 * int value - value to be transformed
	 * 
	 * stolen from: https://www.javaworld.com/article/2077561/java-tip-60--saving-bitmap-files-in-java.html
	 */
	private byte[] intToDWord(int value)
		{
		byte[] outcome = new byte[4];
		outcome[0] = (byte)(value & 0x00FF);
		outcome[1] = (byte)((value >>  8) & 0x000000FF);
		outcome[2] = (byte)((value >>  16) & 0x000000FF);
		outcome[3] = (byte)((value >>  24) & 0x000000FF);
		return outcome;
		}
	/*
	 * Represents subject value with 2 bytes
	 *
	 * int value - value to be transformed
	 *
	 * stolen from: https://www.javaworld.com/article/2077561/java-tip-60--saving-bitmap-files-in-java.html
	 */
	private byte[] intToWord(int value)
		{
		byte[] outcome = new byte[2];
		outcome[0] = (byte)(value & 0x00FF);
		outcome[1] = (byte)((value >>  8) & 0x000000FF);
		return outcome;
		}
		
	public Picasso8(int nololours)
		{
		this.nocolours = nololours;	
		}
	}
