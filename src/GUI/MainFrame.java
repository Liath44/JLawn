package GUI;

import Property.Lawn;
import javax.swing.*;
import java.awt.*;

//TODO?: abstract panel for cycles, radius, import, export etc...
public class MainFrame extends JFrame
	{
	private static Lawn lawn = null;
	
	public MainFrame()
		{
		super("JLawn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 610);
		setResizable(false);
		setVisible(true);
		setLayout(new GridBagLayout());
		GridBagConstraints c;

		PrevStatusPanel psp = new PrevStatusPanel();
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		add(new CycleNumberPanel(psp), c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		add(new BouncePanel(psp), c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		add(new RadiusPanel(psp), c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		add(new AnimationTimePanel(psp), c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		add(new ImportPanel(psp), c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		add(new WaterButton(psp), c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		add(new AnimationButton(psp), c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		add(new FileButton(psp), c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		add(new BitmapButton(psp), c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.LINE_START;
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
