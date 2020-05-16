public class Main
    {
    public static void main(String[] args)
        {
	    LawnReader lawnreader = new LawnReader();
	    try
			{
			Lawn lawn = lawnreader.createLawn("lawnfile", 10, true);
			lawn.printLawn();
			}
	    catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
        }
    }
