package GUI;

import Property.Sprinkler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Panel used to switch sprinklers' bouncing flag
 */
public class BouncePanel extends JPanel implements ActionListener
	{
	private final JButton button;
	private final PrevStatusPanel psp;
	private final static Color TRUECOLOUR = Color.GREEN.darker();
	private final static Color FALSECOLOUR = Color.RED;
	
	public BouncePanel(PrevStatusPanel psp)
		{
		super();
		this.psp = psp;
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(213, 40));
		GridBagConstraints c;

		//label
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 2;
		c.ipadx = 5;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0, 15, 0, 0);
		JLabel label = new JLabel("bounce:");
		label.setPreferredSize(new Dimension(71, 30));
		add(label, c);

		//button
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.anchor = GridBagConstraints.LINE_END;
		button = new JButton("true");
		button.setForeground(TRUECOLOUR);
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(75, 28));
		add(button, c);
		}
		
	private void switchLabel()
		{
		//if true switch to true label
		if(Sprinkler.getBounce())
			{
			button.setForeground(TRUECOLOUR);
			button.setText("true");
			}
		//switch to false label
		else
			{
			button.setForeground(FALSECOLOUR);
			button.setText("false");
			}
		}	
		
	public void actionPerformed(ActionEvent action)
		{
		Object source = action.getSource();
		if(source == button)
			{
			psp.showWait();
			//if true switch to false and vice versa
			Sprinkler.setBounce();
			switchLabel();
			//Lawn can be watered again because conditions changed
			WaterButton.dewater();
			psp.showOK();
			}
		}
	}
