package GUI;

import GUIExceptions.InitializeFirstException;
import Property.Exporter;
import Property.Lawn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileButton extends JButton implements ActionListener
	{
	private final PrevStatusPanel psp;
	private final static String PATH = "SPRINKLERS";
	
	public FileButton(PrevStatusPanel psp)
		{
		super("to file");
		setPreferredSize(new Dimension(100, 27));
		this.psp = psp;
		addActionListener(this);
		}
	
	private void exportFile() throws Exception
		{
		Lawn lawn = MainFrame.getLawn();
		if(lawn != null)
			{
			Exporter exporter = new Exporter();
			exporter.createSprinklerList(lawn, PATH);
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
			exportFile();
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
