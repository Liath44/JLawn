import java.io.FileOutputStream;
import java.io.IOException;

public class Picasso8
	{
	private FileOutputStream fos;
	private final static int BPM = 8;
	private final static int COLSIZE = 4;
	private final static int FHSIZE = 14;
	private final static int BIHSIZE = 40;
	private final static byte[] SIGNATURE = {'B', 'M'};

	public void initializeBitmap(String path, Lawn lawn) throws IOException
		{
		int tmpnocolours = 3;
		fos = new FileOutputStream(path);
		fos.write(SIGNATURE);
		fos.write(intToDWord(lawn.getXSize() * lawn.getYSize() + FHSIZE + BIHSIZE + tmpnocolours * COLSIZE));
		fos.write(intToWord(0));
		fos.write(intToWord(0));
		fos.write(intToDWord(FHSIZE + BIHSIZE + tmpnocolours * COLSIZE));
		fos.write(intToDWord(BIHSIZE));
		fos.write(intToDWord(lawn.getXSize()));
		fos.write(intToDWord(lawn.getYSize()));
		fos.write(intToWord(1));
		fos.write(intToWord(BPM));
		fos.write(intToDWord(0));
		fos.write(intToDWord(0));
		fos.write(intToDWord(0));
		fos.write(intToDWord(0));
		fos.write(intToDWord(tmpnocolours));
		fos.write(intToDWord(0));
		//TEST PALETTE
		byte[] tmptab = new byte[4];
		tmptab[0] = (byte)0;//blue
		tmptab[1] = (byte)0;//green
		tmptab[2] = (byte)255;//red
		tmptab[3] = (byte)0;//reserved
		fos.write(tmptab);
		fos.write(tmptab);
		fos.write(tmptab);
		}

	//TODO: prototype
	public void paintBitmap(Lawn lawn) throws IOException
		{
		for(int j = 0; j < lawn.getYSize(); j++)
			{
			//IMPORTANT
			//The bits representing the bitmap pixels are packed in rows. The size of each row is rounded up to a multiple of 4 bytes (a 32-bit DWORD) by padding.
			fos.write((byte)0);
			fos.write((byte)0);
			for(int i = 0; i < lawn.getXSize(); i++)
				fos.write((byte)2);
			}
		}
		
	public void tidyUp() throws IOException
		{
		if(fos != null)
			fos.close();
		}
		
	//https://www.javaworld.com/article/2077561/java-tip-60--saving-bitmap-files-in-java.html
	private byte[] intToDWord(int value)
		{
		byte[] outcome = new byte[4];
		outcome[0] = (byte)(value & 0x00FF);
		outcome[1] = (byte)((value >>  8) & 0x000000FF);
		outcome[2] = (byte)((value >>  16) & 0x000000FF);
		outcome[3] = (byte)((value >>  24) & 0x000000FF);
		return outcome;
		}
		
	private byte[] intToWord(int value)
		{
		byte[] outcome = new byte[2];
		outcome[0] = (byte)(value & 0x00FF);
		outcome[1] = (byte)((value >>  8) & 0x000000FF);
		return outcome;
		}
		
	public Picasso8()
		{
		}
	}
