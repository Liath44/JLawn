package GUI;

import Property.Lawn;
import javax.swing.*;
import java.awt.*;
/*
 * Responsible for showing majority of GUI
 */
public class MainFrame extends JFrame
	{
	private static Lawn lawn = null;
	
	public MainFrame()
		{
		super("JLawn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(470, 240);
		setResizable(false);
		setVisible(true);
		setLayout(new GridBagLayout());
		GridBagConstraints c;

		PrevStatusPanel psp = new PrevStatusPanel();
		
		//set NO cycles: 
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(new CycleNumberPanel(psp), c);
		
		//bounce: 
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(new BouncePanel(psp), c);
		
		//set radius: 
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(new RadiusPanel(psp), c);
		
		//cycle time: 
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(new AnimationTimePanel(psp), c);
		
		//lawn file: 
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.LINE_START;
		add(new ImportPanel(psp), c);
		
		//buttons: water, animation, to file, bitmap
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		c.insets = new Insets(7, 0, 7, 0);
		c.anchor = GridBagConstraints.CENTER;
		add(new ButtonPanel(psp), c);
		
		//previous status: 
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		add(psp, c);
		}
		
	public static void setLawn(Lawn lawn)
		{
		MainFrame.lawn = lawn;
		}
		
	public static Lawn getLawn()
		{
		return lawn;
		}
	}
