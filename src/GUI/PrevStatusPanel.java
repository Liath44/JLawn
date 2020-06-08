package GUI;

import javax.swing.*;
import java.awt.*;

public class PrevStatusPanel extends JPanel
	{
	private final static JLabel INITMESSAGE = new JLabel("previous status: ");
	private final JLabel status;
	private final static Color OKCOLOUR = Color.GREEN.darker();
	private final static Color ERRORCOLOUR = Color.RED;
	private final static Color WAITCOLOUR = Color.YELLOW.darker();

	public PrevStatusPanel()
		{
		super();
		status = new JLabel("");
		add(INITMESSAGE);
		add(status);
		}

	public void showError()
		{
		status.setForeground(ERRORCOLOUR);
		status.setText("ERROR");
		}

	public void showOK()
		{
		status.setForeground(OKCOLOUR);
		status.setText("OK");
		}

	public void showWait()
		{
		status.setForeground(WAITCOLOUR);
		status.setText("computing...");
		}
	}
