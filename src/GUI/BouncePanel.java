package GUI;

import Property.Sprinkler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		GridBagConstraints c;

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		JLabel label = new JLabel("bounce:");
		add(label, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		button = new JButton("true");
		button.setForeground(TRUECOLOUR);
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(75, 27));
		add(button, c);
		}
		
	private void switchLabel()
		{
		if(Sprinkler.getBounce())
			{
			button.setForeground(TRUECOLOUR);
			button.setText("true");
			}
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
			Sprinkler.setBounce();
			switchLabel();
			WaterButton.dewater();
			psp.showOK();
			}
		}
	}
