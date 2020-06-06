package GUI;

import Property.Lawn;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
	{
	private static Lawn lawn = null;
	
	public MainFrame()
		{
		super("JLawn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 610);
		//TODO: Remake to GridBagLayout
		setLayout(new FlowLayout());

		PrevStatusPanel psp = new PrevStatusPanel();
		
		add(new CycleNumberPanel(psp));
		add(psp);
		
		setResizable(false);
		setVisible(true);
		}
	}
