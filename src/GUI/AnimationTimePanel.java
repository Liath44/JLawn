package GUI;

import GUIExceptions.ImproperTimeException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		GridBagConstraints c;

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		JLabel label = new JLabel("cycle time:");
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
