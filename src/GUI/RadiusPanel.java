package GUI;

import GUIExceptions.ImproperCycleNumberException;
import Property.Lawn;

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
		psp.showWait();
		Object source = action.getSource();
		if(source == setradius)
			{
			String number = field.getText();
			try
				{
				int n = Integer.parseInt(number);
				if(n <= 0)
					throw new ImproperCycleNumberException(n);
				Lawn.setTime(n);
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
