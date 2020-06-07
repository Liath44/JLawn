package GUI;

import GUIExceptions.ImproperRadiusException;
import Property.Sprinkler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		GridBagConstraints c;

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		JLabel label = new JLabel("set radius:");
		add(label, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		field = new JTextField(3);
		add(field, c);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
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
				int r = Integer.parseInt(number);
				if(r <= 0)
					throw new ImproperRadiusException(r);
				Sprinkler.setRadius(r);
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
