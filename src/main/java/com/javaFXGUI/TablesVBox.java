package com.javaFXGUI;

import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TablesVBox extends VBox {
    VBox miceVBox=new MiceVBox();
    VBox cagezVBox=new CagezVBox();
    Node spaceMiddle=new VBox();

    public TablesVBox() {
        VBox.setVgrow(spaceMiddle, Priority.SOMETIMES);
        this.getChildren().addAll(miceVBox, spaceMiddle, cagezVBox);
    }
}
