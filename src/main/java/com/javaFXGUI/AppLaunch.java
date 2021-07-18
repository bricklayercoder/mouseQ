package com.javaFXGUI;

import com.cagezz.Utilities;
import com.javaFXGUI.SecondaryGUI.AboutStage;
import com.javaFXGUI.SecondaryGUI.HistoryStage;
import com.javaFXGUI.SecondaryGUI.OpenMiceTableStage;
import com.javaFXGUI.SecondaryGUI.SetUpBreedersStage;
import com.mouse.Mouse;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;


public class AppLaunch extends Application {

    public static Stage primaryStage;

    public static MainFrameBorderPane mainFrameBorderPane =new MainFrameBorderPane();

    public static HistoryStage historyStage=new HistoryStage();
    static OpenMiceTableStage openMiceTableStage;
    static AboutStage aboutStage=new AboutStage();
    static SetUpBreedersStage setUpBreedersStage=new SetUpBreedersStage();

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

                if (aboutStage !=null ){
                    aboutStage.close();
                }

                if(setUpBreedersStage !=null ){
                    setUpBreedersStage.close();
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
