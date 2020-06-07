package GUI;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel
    {   
    public ButtonPanel(PrevStatusPanel psp)
        {   
        super();
        GridLayout gl = new GridLayout(1, 4);
        gl.setHgap(3);
        setLayout(gl);
        setPreferredSize(new Dimension(443, 30));
        add(new WaterButton(psp));
        add(new AnimationButton(psp));
        add(new FileButton(psp));
        add(new BitmapButton(psp));
        }
    }
