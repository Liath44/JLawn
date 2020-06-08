package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Clicking this button creates new window with animation
 * showing watering the lawn
 */
public class AnimationButton extends JButton implements ActionListener
	{
	private final PrevStatusPanel psp;
	
	public AnimationButton(PrevStatusPanel psp)
		{
		super("animation");
		setPreferredSize(new Dimension(110, 27));
		this.psp = psp;
		addActionListener(this);
		}
		
	public void actionPerformed(ActionEvent action)
		{
		//TODO: Implementation
		}
	}
