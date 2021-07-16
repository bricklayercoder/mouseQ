package com.javaFXGUI;

import com.javaFXGUI.SecondaryGUI.HistoryStage;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AppLaunch extends Application {

    public static MainFrameBorderPane mainFrameBorderPane =new MainFrameBorderPane();

    static HistoryStage historyStage=new HistoryStage();


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene=new Scene(mainFrameBorderPane, 1500, 1200);
        primaryStage.setTitle("Welcome to mouseQ");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(AppLaunch.class.getResource("/stylesheet.css").toExternalForm());

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
               historyStage.close();
            }
        });

        primaryStage.show();
    }
}
