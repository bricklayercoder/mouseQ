package com.javaFXGUI;

import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class MiceVBox extends VBox {
    HBox searchMiceHBox=new SearchMiceHBox();
    TableView miceTableView=new MiceTableView();

    public MiceVBox() {
        this.getChildren().addAll(searchMiceHBox, miceTableView);
    }
}

