package com.javaFXGUI;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class StageVBox extends VBox {

    GridPane selectedMouseGridPane=new SelectedMouseGridPane();
    GridPane selectedCagezGridPane=new SelectedCageGridPane();
    Node spaceMiddle=new VBox();
    Node spaceBottom=new VBox();

    public StageVBox() {
        this.setPrefHeight(1250);
        this.setPrefWidth(300);
        VBox.setVgrow(spaceMiddle, Priority.SOMETIMES);
        VBox.setVgrow(spaceBottom, Priority.SOMETIMES);
        this.getChildren().add(selectedMouseGridPane);
        this.getChildren().add(spaceMiddle);
        this.getChildren().add(selectedCagezGridPane);
        this.getChildren().add(spaceBottom);
    }
}
