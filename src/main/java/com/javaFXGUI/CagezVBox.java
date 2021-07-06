package com.javaFXGUI;

import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CagezVBox extends VBox {

    HBox searchCagezVBox=new SearchCagezHBox();
    TableView cagezTableView=new CagezTableView();

    public CagezVBox() {
        this.getChildren().addAll(searchCagezVBox, cagezTableView);
    }
}
