package com.vis;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class LegendPanel extends Component {
    protected Ellipse2D m_box=new Ellipse2D.Double();

    Double width= 30d;
    Double height= 25d;

    Color femaleColor =Color.CYAN;
    Color maleColor =new Color(0xA2A4F5);


    public LegendPanel() {
        setMinimumSize(new Dimension(120, 75));
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2= (Graphics2D) g;
        m_box.setFrame(30d, 30d,
                width,
                height);

        g2.setPaint(femaleColor);
        g2.draw(m_box);
        g2.fill(m_box);
        g2.setPaint(Color.BLACK);
        g2.setFont(new Font("Calibri", Font.PLAIN, 18));
        g2.drawString("Female", 30, 65);

        m_box.setFrame(75d, 30d, width, height);
        g2.setPaint(maleColor);
        g2.draw(m_box);
        g2.fill(m_box);
        g2.setPaint(Color.BLACK);
        g2.drawString("Male", 75, 65);



    }


}
