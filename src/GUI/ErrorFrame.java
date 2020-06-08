package GUI;

import javax.swing.*;
import java.awt.*;

/*
 * Frame that pops up whenever exceptions are thrown
 * Shows message from said exception
 * 
 * TODO: freeze rest of the GUI when present
 */
public class ErrorFrame extends JFrame
	{
	//message to be shown
	private final String message;

	public ErrorFrame(String message)
		{
		super("ERROR");
		this.message = message;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 100);
		setLayout(new GridBagLayout());
		JLabel text = new JLabel(message);
		text.setVerticalAlignment(JLabel.CENTER);
		text.setHorizontalAlignment(JLabel.CENTER);
		add(text);
		setVisible(true);
		setResizable(false);
		}
	}
