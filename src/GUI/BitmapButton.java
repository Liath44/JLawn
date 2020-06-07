package GUI;

import GUIExceptions.InitializeFirstException;
import Property.Exporter;
import Property.Lawn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BitmapButton extends JButton implements ActionListener
	{
	private final PrevStatusPanel psp;
	private final static String PATH = "BITMAP";
	
	public BitmapButton(PrevStatusPanel psp)
		{
		super("bitmap");
		setPreferredSize(new Dimension(100, 27));
		this.psp = psp;
		addActionListener(this);
		}

	private void exportBitmap() throws Exception
		{
		Lawn lawn = MainFrame.getLawn();
		if(lawn != null)
			{
			Exporter exporter = new Exporter();
			exporter.createBitmap(lawn, PATH);
			exporter.tidyUp();
			}
		else
			throw new InitializeFirstException();
		}
		
	public void actionPerformed(ActionEvent action)
		{
		psp.showWait();
		try
			{
			exportBitmap();
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
	}
