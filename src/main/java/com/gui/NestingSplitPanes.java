package com.gui;

import javax.swing.*;
import java.awt.*;

public class NestingSplitPanes extends JSplitPane {

    public NestingSplitPanes() {
        super();
        makeNestingSplitPanes();
    }

    private void makeNestingSplitPanes(){

        //Mice and Cagez table Panels splitPane           left
        //action and loginfosplitpane   right
        MiceAndCagezTableSplitPane miceAndCagezTableSplitPane=new MiceAndCagezTableSplitPane();
        ActionCardsAndLogsPane actionCardsAndLogsPane=new ActionCardsAndLogsPane();

        this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        this.setLeftComponent(miceAndCagezTableSplitPane);
        this.setRightComponent(actionCardsAndLogsPane);
        this.setPreferredSize(new Dimension(300, 450));
        this.setOneTouchExpandable(true);
        this.setDividerLocation(0.6);
        this.setResizeWeight(0.5);
        Dimension minimumDimension=new Dimension(50, 0);
      //left setMinimum size
      //right set minimum size



    }
    /*
    This is the two component table parts of the left panel in the MainFrame
     */

    class MiceAndCagezTableSplitPane extends JSplitPane{
       MiceTablePanels miceTablePanels=new MiceTablePanels();
       CagezTablePanels cagezTablePanels=new CagezTablePanels();

        public MiceAndCagezTableSplitPane() {
            super();
            this.setOrientation(JSplitPane.VERTICAL_SPLIT);
            this.setTopComponent(miceTablePanels);
            this.setBottomComponent(cagezTablePanels);
            this.setPreferredSize(new Dimension(600, 500));
            this.setResizeWeight(0.5);
            Dimension minimumDimension=new Dimension(0, 0);
            miceTablePanels.setMinimumSize(minimumDimension);
            cagezTablePanels.setMinimumSize(minimumDimension);
        }
    }



}
