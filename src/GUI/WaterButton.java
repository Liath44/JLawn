package GUI;

import GUIExceptions.AlreadyWateredException;
import GUIExceptions.InitializeFirstException;
import Property.Gardener;
import Property.Lawn;
import Property.Planner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Button used to commence watering the lawn
 */
public class WaterButton extends JButton implements ActionListener
	{
	private final PrevStatusPanel psp;
	/*
	 * if Lawn was once watered it shouldn't be watered again
	 * unless new Lawn is imported or watering conditions change
	 * (radius is changed, bounce parameter gets changed etc...)
	 */
	private static boolean operationdone = false;
	
	public WaterButton(PrevStatusPanel psp)
		{
		super("water");
		setPreferredSize(new Dimension(110, 27));
		this.psp = psp;
		addActionListener(this);
		}
		
	private void waterLawn() throws AlreadyWateredException, InitializeFirstException
		{
		Lawn lawn = MainFrame.getLawn();
		if(operationdone)
			throw new AlreadyWateredException();
		else if(lawn != null)
			{
			Gardener Josh = new Gardener();
			Josh.placeSprinklers(lawn, new Planner());
			operationdone = true;
			}
		else
			throw new InitializeFirstException();
		}	
		
	public void actionPerformed(ActionEvent action)
		{
		psp.showWait();
		try
			{
			waterLawn();
			psp.showOK();
			}
		catch(Exception e)
			{
			psp.showError();
			EventQueue.invokeLater(new Runnable()
				{
				public void run()
					{
					new ErrorFrame(e.getMessage());
					}
				});	
			}
		}
		
	//called when conditions change
	public static void dewater()
		{
		WaterButton.operationdone = false;
		}
	}
