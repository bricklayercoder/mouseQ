package com.vis;

import prefuse.action.layout.Layout;
import prefuse.util.ColorLib;
import prefuse.visual.DecoratorItem;
import prefuse.visual.VisualItem;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

public class FinalDecoratorLayout extends Layout {

    public FinalDecoratorLayout(String group) {
        super(group);
    }

    Color fillColor=Color.BLACK;


    @Override
    public void run(double frac) {
        Iterator iter=m_vis.items(m_group);
        while(iter.hasNext()){
            DecoratorItem decorator= (DecoratorItem) iter.next();
            decorator.setFont(new Font("Calibri", Font.PLAIN, 14));

            decorator.setTextColor(ColorLib.rgb(
                    fillColor.getRed(),
                    fillColor.getGreen(),
                    fillColor.getBlue()));

            VisualItem decoratedItem=decorator.getDecoratedItem();
            Rectangle2D bounds=decoratedItem.getBounds();

            double x=bounds.getCenterX();
            double y=bounds.getCenterY();

            setX(decorator, null, x);
            setY(decorator, null, y);
        }
    }
}
