package GUI;

import Property.Lawn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUIExceptions.ImproperCycleNumberException;

/*
 * Panel used to input number of 360sprinkler's cycles
 */
public class CycleNumberPanel extends JPanel implements ActionListener
	{
	private final JTextField field;
	private final JButton setnumber;
	private final PrevStatusPanel psp;
	
	public CycleNumberPanel(PrevStatusPanel psp)
		{
		super();
		this.psp = psp;
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
		JLabel label = new JLabel("set NO cycles:");
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
		setnumber = new JButton("set");
		setnumber.addActionListener(this);
		setnumber.setPreferredSize(new Dimension(75, 28));
		add(setnumber, c);
		}
		
	public void actionPerformed(ActionEvent action)
		{
		Object source = action.getSource();
		if(source == setnumber)
			{ 
			psp.showWait();
			String number = field.getText();
			try
				{
				//check if input is proper
				int n = Integer.parseInt(number);
				if(n <= 0)
					throw new ImproperCycleNumberException(n);
				Lawn.setTime(n);
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
