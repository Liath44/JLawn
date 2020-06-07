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

public class WaterButton extends JButton implements ActionListener
	{
	private final PrevStatusPanel psp;
	private static boolean waswatered = false;
	
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
		if(waswatered)
			throw new AlreadyWateredException();
		else if(lawn != null)
			{
			Gardener Josh = new Gardener();
			Josh.placeSprinklers(lawn, new Planner());
			waswatered = true;
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
		
	public static void dewater()
		{
		WaterButton.waswatered = false;
		}
	}
