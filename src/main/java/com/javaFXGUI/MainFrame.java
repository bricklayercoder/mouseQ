package com.javaFXGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainFrame extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Welcome to mouseQ");
        BorderPane root=new BorderPane();
        root.setPadding(new Insets(2, 5, 5, 5));

        HBox topToolBox= new HBox();

        MenuBar menuBar=new MenuBar();

        Menu mouseQMenu=new Menu("mouseQ");
        Menu fileMenu=new Menu("File");
        Menu mouseMenu=new Menu("Mouse");
        Menu cageMenu=new Menu("Cage");
        Menu recordMenu=new Menu("Record");

        MenuItem aboutMouseQMenuItem=new MenuItem("About");
        MenuItem exitMouseQMenuItem=new MenuItem("Exit");

        MenuItem openFileMenuItem=new MenuItem("Open");
        MenuItem saveFileMenuItem=new MenuItem("Save");

        MenuItem addMouse=new MenuItem("Add");
        MenuItem updateMouse =new MenuItem("Update");

        MenuItem addCage=new MenuItem("Add");
        MenuItem updateCage=new MenuItem("Update");

        MenuItem syncItem=new MenuItem("Sync");
        MenuItem tipsItem=new MenuItem("Tips");

        mouseQMenu.getItems().add(aboutMouseQMenuItem);
        mouseQMenu.getItems().add(exitMouseQMenuItem);

        fileMenu.getItems().add(openFileMenuItem);
        fileMenu.getItems().add(saveFileMenuItem);

        mouseMenu.getItems().add(addMouse);
        mouseMenu.getItems().add(updateMouse);

        cageMenu.getItems().add(addCage);
        cageMenu.getItems().add(updateCage);

        recordMenu.getItems().add(syncItem);
        recordMenu.getItems().add(tipsItem);

        menuBar.getMenus().addAll(mouseQMenu, fileMenu, mouseMenu, cageMenu, recordMenu);
        topToolBox.getChildren().add(menuBar);








        root.setTop(topToolBox);
        Scene scene=new Scene(root, 1750, 1200);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(MainFrame.class.getResource("/stylesheet.css").toExternalForm());
        primaryStage.show();

    }

    private void makeSearchMiceHBox(){
        HBox hBox =new HBox();
        Label searchLabel=new Label("search");
        TextField searchField=new TextField();
        Text currentText=new Text("Current");

    }





}
