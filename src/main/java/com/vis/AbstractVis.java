package com.vis;

import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.data.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class AbstractVis extends JComponent {

    protected Graph miceGraph;
    protected Visualization vis;
    protected Display dis;

 //   protected JComponent visualComponent;

    protected JComponent  containerComponent;

    protected Ellipse2D m_box=new Ellipse2D.Double();

    Double width= 30d;
    Double height= 25d;

    Color femaleColor =Color.CYAN;
    Color maleColor =new Color(0xA2A4F5);

    public abstract void setupData();
    public abstract void setUpVisualization();
    public abstract void setUpRenderers();
    public abstract void setUpActions();
    public abstract void setUpDisplay();

    public abstract void runVis();

    public AbstractVis() {
        containerComponent=this;
    }
/**
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
*/


}
