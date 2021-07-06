package com.javaFXGUI;

import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class MainFrameBorderPane extends BorderPane {

    MenuBar menuBar=new MouseQMenuBar();
    VBox tablesVBox=new TablesVBox();
    VBox stageVBox=new StageVBox();
    Text processText=new Text();




    public MainFrameBorderPane() {
        processText.setText("process will be shown here");
        processText.setId("processText");
        this.setPadding(new Insets(2, 5, 5, 5));
        this.setTop(menuBar);
        this.setCenter(tablesVBox);
        this.setRight(stageVBox);
        this.setBottom(processText);

    }
}
