package com.gui;

import javax.swing.*;
import java.awt.*;

public class SpringLayoutUtils {

    public static void makeGrid(
                                 Container parent,
                                 int rows, int cols,
                                 int initialX, int initialY,
                                 int xPad, int yPad){
        SpringLayout layout = null;
        try {
            layout= (SpringLayout) parent.getLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Spring xPadSpring =Spring.constant(xPad);
        Spring yPadSpring=Spring.constant(yPad);
        Spring initialXSpring=Spring.constant(initialX);
        Spring initialYSpring=Spring.constant(initialY);

        int max=rows * cols;
        Spring maxWidthSpring=layout.getConstraints((parent.getComponent(0))).getWidth();
        Spring maxHeightSpring=layout.getConstraints(parent.getComponent(0)).getHeight();

        for (int i=0; i< max; i++){
            SpringLayout.Constraints cons=layout.getConstraints(parent.getComponent(i));
            maxWidthSpring =Spring.max(maxWidthSpring, cons.getWidth());
            maxHeightSpring=Spring.max(maxHeightSpring, cons.getHeight());
        }

        for(int i=0; i<max; i++){
            SpringLayout.Constraints cons=layout.getConstraints(parent.getComponent(i));
            cons.setWidth(maxWidthSpring);
            cons.setHeight(maxHeightSpring);
        }

        SpringLayout.Constraints lastCons=null;
        SpringLayout.Constraints lastRowCons=null;
        for (int i=0; i<max; i++){
            SpringLayout.Constraints cons=layout.getConstraints(parent.getComponent(i));
            if(i % cols ==0){
                lastRowCons=lastCons;
                cons.setX(initialXSpring);
            }else {
                cons.setX(Spring.sum(lastCons.getConstraint(SpringLayout.EAST), xPadSpring));
            }

            if(i / cols ==0){
                cons.setY(initialYSpring);
            }else {
                cons.setY(Spring.sum(lastRowCons.getConstraint((SpringLayout.SOUTH)),yPadSpring));
            }
            lastCons=cons;
        }

        SpringLayout.Constraints pCons=layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH,
                Spring.sum(Spring.constant(yPad), lastCons.getConstraint(SpringLayout.SOUTH)));
        pCons.setConstraint((SpringLayout.EAST),
                Spring.sum(Spring.constant((xPad)), lastCons.getConstraint(SpringLayout.EAST)));










    }




}
