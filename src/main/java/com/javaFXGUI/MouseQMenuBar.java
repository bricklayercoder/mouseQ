package com.javaFXGUI;

import com.javaFXGUI.SecondaryGUI.OpenMiceTableStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.io.File;


public class MouseQMenuBar extends MenuBar {

    Menu mouseQMenu=new Menu("mouseQ");
    Menu fileMenu=new Menu("File");
    Menu cageMenu=new Menu("Cage");
    Menu recordMenu=new Menu("Record");

    MenuItem aboutMouseQMenuItem=new MenuItem("About");
    MenuItem exitMouseQMenuItem=new MenuItem("Exit");

    MenuItem openFileMenuItem=new MenuItem("Open");
    File chosenTableFile;
    MenuItem saveFileMenuItem=new MenuItem("Save");

    MenuItem addCage=new MenuItem("Add");
    MenuItem updateCage=new MenuItem("Update");

    MenuItem syncItem=new MenuItem("Sync");
    MenuItem historyItem =new MenuItem("History");


    public MouseQMenuBar() {

        mouseQMenu.getItems().add(aboutMouseQMenuItem);
        mouseQMenu.getItems().add(exitMouseQMenuItem);

        fileMenu.getItems().add(openFileMenuItem);
        fileMenu.getItems().add(saveFileMenuItem);

        cageMenu.getItems().add(addCage);
        cageMenu.getItems().add(updateCage);

        recordMenu.getItems().add(syncItem);
        recordMenu.getItems().add(historyItem);

        this.getMenus().addAll(mouseQMenu, fileMenu, recordMenu);

        historyItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AppLaunch.historyStage.show();

            }
        });

        openFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final FileChooser fileChooser=new FileChooser();
                chosenTableFile=fileChooser.showOpenDialog(AppLaunch.openMiceTableStage);
                if(AppLaunch.openMiceTableStage !=null ){
                    AppLaunch.openMiceTableStage.close();
                }

                try {
                    AppLaunch.openMiceTableStage=new OpenMiceTableStage(chosenTableFile);
                    AppLaunch.openMiceTableStage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                    AppLaunch.mainFrameBorderPane.getProcessText().setText("Error Open File!");
                    AppLaunch.mainFrameBorderPane.processText.setStyle("-fx-fill: red; -fx-font-size: 13pt;");
                    return;
                }
                AppLaunch.mainFrameBorderPane.processText.setText(chosenTableFile.getName()+" opened!");
                AppLaunch.mainFrameBorderPane.processText.setStyle("-fx-fill: white; -fx-font-size: 13pt;");
                AppLaunch.openMiceTableStage.show();
            }
        });




    }
}
