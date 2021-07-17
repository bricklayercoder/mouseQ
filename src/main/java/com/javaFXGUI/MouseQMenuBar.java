package com.javaFXGUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


public class MouseQMenuBar extends MenuBar {

    Menu mouseQMenu=new Menu("mouseQ");
    Menu fileMenu=new Menu("File");
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
    MenuItem tipsItem=new MenuItem("History");


    public MouseQMenuBar() {

        mouseQMenu.getItems().add(aboutMouseQMenuItem);
        mouseQMenu.getItems().add(exitMouseQMenuItem);

        fileMenu.getItems().add(openFileMenuItem);
        fileMenu.getItems().add(saveFileMenuItem);

        cageMenu.getItems().add(addCage);
        cageMenu.getItems().add(updateCage);

        recordMenu.getItems().add(syncItem);
        recordMenu.getItems().add(tipsItem);

        this.getMenus().addAll(mouseQMenu, fileMenu, recordMenu);

        tipsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AppLaunch.historyStage.show();

            }
        });


    }
}
