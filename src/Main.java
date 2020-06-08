import GUI.MainFrame;
import java.awt.*;

/*
 * Responsible for calling MainFrame
 * Magic starts but does not end there
 */
public class Main
    {
    public static void main(String[] args)
        {
		EventQueue.invokeLater(new Runnable()
			{
			public void run()
				{
				new MainFrame();
				}
			});
        }
    }
