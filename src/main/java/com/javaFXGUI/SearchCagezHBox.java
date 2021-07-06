package com.javaFXGUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SearchCagezHBox extends HBox {
    Label searchLabel=new Label("Search");
    TextField searchField=new TextField();

    public SearchCagezHBox() {
        this.setSpacing(15);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(searchLabel, searchField);
    }

}
