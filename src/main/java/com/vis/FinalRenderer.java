package com.vis;

import prefuse.render.AbstractShapeRenderer;
import prefuse.visual.VisualItem;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class FinalRenderer extends AbstractShapeRenderer {
    protected Ellipse2D m_box=new Ellipse2D.Double();
    @Override
    protected Shape getRawShape(VisualItem item) {
        m_box.setFrame(item.getX(), item.getY(), 20, 20);
        return m_box;
    }
}

