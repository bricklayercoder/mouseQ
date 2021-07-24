package com.javaFXGUI.SecondaryGUI;

import com.javaFXGUI.AppLaunch;
import com.vis.MiceVis;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;

public class VisualStage extends Stage {
    public final SwingNode swingNode=new SwingNode();
    MiceVis miceVis =new MiceVis(AppLaunch.mainFrameBorderPane.miceVBox.getObservableListOfMouse());
    StackPane pane=new StackPane();

    public VisualStage() {
        createSwingContent(swingNode);
        swingNode.isResizable();
        pane.getChildren().add(swingNode);
        this.setTitle("Mice | Visualization");
        pane.setPrefHeight(500);
        pane.setPrefWidth(700);

        Scene scene=new Scene(pane) ;
        this.setScene(scene);
        this.setResizable(true);
        this.setAlwaysOnTop(true);
    }

    private void createSwingContent(final SwingNode swingNode){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(miceVis);
            }
        });
    }


}



