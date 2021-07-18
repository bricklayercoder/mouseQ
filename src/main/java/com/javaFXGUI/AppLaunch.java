package com.javaFXGUI;

import com.cagezz.Utilities;
import com.javaFXGUI.SecondaryGUI.HistoryStage;
import com.javaFXGUI.SecondaryGUI.OpenMiceTableStage;
import com.mouse.Mouse;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;


public class AppLaunch extends Application {

    public static Stage primaryStage;

    public static MainFrameBorderPane mainFrameBorderPane =new MainFrameBorderPane();

    static HistoryStage historyStage=new HistoryStage();
    static OpenMiceTableStage openMiceTableStage;


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Utilities.createMouseQHomeFolder();
        Utilities.createMouseQTablesFolder();
        Scene scene=new Scene(mainFrameBorderPane, 1500, 1200);
        primaryStage.setTitle("Welcome to mouseQ");
        AppLaunch.primaryStage=primaryStage;
        primaryStage.setScene(scene);
        scene.getStylesheets().add(AppLaunch.class.getResource("/stylesheet.css").toExternalForm());

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if(historyStage !=null){
                    historyStage.close();
                }
                if (openMiceTableStage !=null){
                    openMiceTableStage.close();
                }

                ArrayList<Mouse> miceArrayList= new ArrayList<>();
                for (Mouse mouse :mainFrameBorderPane.miceVBox.miceRecordObservableList)
                        miceArrayList.add(mouse);
                Utilities.createMiceRecordTableFile(miceArrayList);
            }

        });

        primaryStage.show();
    }
}
