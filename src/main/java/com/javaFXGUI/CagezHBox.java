package com.javaFXGUI;

import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class CagezHBox extends HBox {
    TableView cagezTableView=new CagezTableView();
    GridPane cagezGridPane=new SelectedCageGridPane();

    public CagezHBox() {
        this.getChildren().addAll(cagezTableView, cagezGridPane);
    }
}
