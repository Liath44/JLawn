/*
import java.util.ArrayList;
import AuxiliaryClasses.*;
 */

public class Main
    {
    public static void main(String[] args)
        {
	    LawnReader lawnreader = new LawnReader();
	    Picasso8 picasso = new Picasso8();
		try
			{
			Lawn lawn = lawnreader.createLawn(args[0], 1);
			picasso.initializeBitmap("BITMAP", lawn);
			picasso.paintBitmap(lawn);
			}
		catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
        }
    }
