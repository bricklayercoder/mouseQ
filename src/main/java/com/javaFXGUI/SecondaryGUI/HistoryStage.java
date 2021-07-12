package com.javaFXGUI.SecondaryGUI;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HistoryStage extends Stage {

    FlowPane historyFlowPane= new FlowPane();
    Text historyText=new Text();
    Scene actionHistoryScene=new Scene(historyFlowPane);


    public HistoryStage() {
        historyText.setText("User operation history will be shown here:\n"+"Operation 1:\n"+"Operation 1");
        historyFlowPane.getChildren().add(historyText);
        this.setScene(actionHistoryScene);
        this.setAlwaysOnTop(true);
    }


}
