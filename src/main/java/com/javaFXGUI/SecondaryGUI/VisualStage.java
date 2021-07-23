package com.javaFXGUI.SecondaryGUI;

import com.vis.MiceVis;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;

public class VisualStage extends Stage {
    final SwingNode swingNode=new SwingNode();

    public VisualStage() {
        createSwingContent(swingNode);
        StackPane pane=new StackPane();
        pane.getChildren().add(swingNode);
        this.setTitle("Mice | Visualization");
        pane.setPrefHeight(500);
        pane.setPrefWidth(700);
        Scene scene=new Scene(pane) ;
        this.setScene(scene);
        this.setResizable(false);
    }

    private void createSwingContent(final SwingNode swingNode){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(new MiceVis());
            }
        });
    }


}



