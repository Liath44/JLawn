package GUI;

import Property.Lawn;
import Property.LawnReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportPanel extends JPanel implements ActionListener
	{
	private final JTextField field;
	private final JButton compute;
	private final PrevStatusPanel psp;
	
	public ImportPanel(PrevStatusPanel psp)
		{
		super();
		this.psp = psp;
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(443, 40));
		GridBagConstraints c;
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 2;
		c.insets = new Insets(0, 5, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		JLabel label = new JLabel("lawn file:");
		label.setPreferredSize(new Dimension(71, 30));
		add(label, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		field = new JTextField(23);
		add(field, c);
		
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 3;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(0, 3, 0, 0);
		compute = new JButton("import");
		compute.addActionListener(this);
		compute.setPreferredSize(new Dimension(100, 30));
		add(compute, c);
		}
		
	public void actionPerformed(ActionEvent action)
		{
		Object source = action.getSource();
		if(source == compute)
			{
			psp.showWait();
			LawnReader lawnreader = new LawnReader();
			try
				{
				String path = field.getText();
				Lawn lawn = lawnreader.createLawn(path);
				MainFrame.setLawn(lawn);
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
			finally
				{
				try
					{
					lawnreader.tidyUp();
					}
				catch(Exception e)
					{
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
	}
