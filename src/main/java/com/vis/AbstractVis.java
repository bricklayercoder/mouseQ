package com.vis;

import prefuse.Display;
import prefuse.Visualization;
import prefuse.data.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class AbstractVis extends JPanel {

    protected Graph miceGraph;
    protected Visualization vis;
    protected Display dis;

    protected JPanel  containerComponent;

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

}
