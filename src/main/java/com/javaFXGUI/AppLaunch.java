package com.javaFXGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppLaunch extends Application {
    static BorderPane mainFrameBorderPane=new MainFrameBorderPane();
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene=new Scene(mainFrameBorderPane, 1400, 1200);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(MainFrame.class.getResource("/stylesheet.css").toExternalForm());
        primaryStage.show();
    }
}
