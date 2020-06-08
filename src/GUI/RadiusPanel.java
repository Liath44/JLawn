package GUI;

import GUIExceptions.ImproperRadiusException;
import Property.Sprinkler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Panel used to input 360sprinkler's radius
 */
public class RadiusPanel extends JPanel implements ActionListener
	{
	private final JTextField field;
	private final JButton setradius;
	private final PrevStatusPanel psp;
	
	public RadiusPanel(PrevStatusPanel psp)
		{
		super();
		this.psp= psp;
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(230, 40));
		GridBagConstraints c;

		//label
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 2;
		c.ipadx = 5;
		c.anchor = GridBagConstraints.LINE_END;
		JLabel label = new JLabel("set radius:");
		label.setPreferredSize(new Dimension(100, 30));
		add(label, c);

		//text field
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		field = new JTextField(3);
		add(field, c);

		//button
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 3;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(0, 0, 0, 3);
		setradius = new JButton("set");
		setradius.addActionListener(this);
		setradius.setPreferredSize(new Dimension(75, 27));
		add(setradius, c);
		}

	public void actionPerformed(ActionEvent action)
		{
		Object source = action.getSource();
		if(source == setradius)
			{
			psp.showWait();
			String number = field.getText();
			try
				{
				//check if argument is proper
				int r = Integer.parseInt(number);
				if(r <= 0)
					throw new ImproperRadiusException(r);
				Sprinkler.setRadius(r);
				//Lawn can be watered again since conditions changed
				WaterButton.dewater();
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
	}
