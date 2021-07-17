package com.javaFXGUI.SecondaryGUI;

import com.javaFXGUI.MiceVBox;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HistoryStage extends Stage {

    ScrollPane historScrollPane = new ScrollPane();
    Text historyText =new Text();
    Scene actionHistoryScene=new Scene(historScrollPane);
    public int sbCounter=0;
    public StringBuilder sb=new StringBuilder();

    public void setHistoryText(String s){
        historyText.setText(s);
    }


    public HistoryStage() {
        this.setTitle("Data operation history");
        this.setResizable(false);
        historScrollPane.setContent(historyText);
        historScrollPane.setPrefWidth(550);
        historScrollPane.setPrefHeight(650);
        historyText.setStyle("-fx-font-size: 13pt; -fx-fill: white;");
        historScrollPane.getStylesheets().add(HistoryStage.class.getResource("/historyStyleSheet.css").toExternalForm());
        this.setScene(actionHistoryScene);
        this.setAlwaysOnTop(true);
    }


}
