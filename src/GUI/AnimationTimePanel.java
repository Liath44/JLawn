package GUI;

import GUIExceptions.ImproperTimeException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Panel for animation time input
 * TODO: actual animation lol
 */
public class AnimationTimePanel extends JPanel implements ActionListener
	{
	private final JTextField field;
	private final JButton settime;
	private final PrevStatusPanel psp;

	public AnimationTimePanel(PrevStatusPanel psp)
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
		JLabel label = new JLabel("cycle time:");
		label.setPreferredSize(new Dimension(71, 30));
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
		settime = new JButton("set");
		settime.addActionListener(this);
		settime.setPreferredSize(new Dimension(75, 27));
		add(settime, c);
		}

	public void actionPerformed(ActionEvent action)
		{
		Object source = action.getSource();
		if(source == settime)
			{
			psp.showWait();
			String number = field.getText();
			try
				{
				//check if input is proper
				int t = Integer.parseInt(number);
				if(t <= 0)
					throw new ImproperTimeException(t);
				//TODO: function here
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
