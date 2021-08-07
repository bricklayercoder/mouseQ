package com.javaFXGUI.SecondaryGUI;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AboutStage extends Stage {
    String appIntro;
    ScrollPane pane=new ScrollPane();
    Scene scene=new Scene(pane);

    public AboutStage() {
        appIntro="mouseQ is a light-weight application that automates laboratory mice data recording.\n\n" +
                "How to use mouseQ:\n\n" +
                "To add new mouse record, in the lower middle panel, enter each record field,\n" +
                "Then press 'Add Mouse' button, the mouse's records are added to the mice table;\n\n" +
                "To update existing mouse records, select the mouse records from the table, and\n" +
                "Enter the information to be updated, then press 'Update Mouse' button, the entered\n" +
                "Update information will be reflected in the mice table.\n\n" +
                "To set up breeding cage, go to 'Record -> Breed'.\n\n" +
                "To check whether the cages statuses are compatible with statuses of the mice contained,\n" +
                "Go to 'Record -> Sanity'.\n\n" +
                "Information to be added or updated can be picked from existing choices located in " +
                "each \nPanel's left side.\n\n" +
                "For each updating or adding operation, processing information will be shown in the\n" +
                "Bottom. By pressing 'Record -> History', adding and updating history are shown in order.\n\n" +
                "To write the current table's records into file, press 'Record -> Sync'.\n\n" +
                "Each written file is located in Downloads/mouseQHome/mouseTables directory under user's\n" +
                "Home directory. The record file is named in format 'YYYY-MM-DD HH-MM.txt'. For example,\n" +
                "2021-06-06 06-06.txt indicates that the record file was created at 06:06 on 2021-06-06.\n\n" +
                "Historical records can be viewed by clicking 'File -> Open' and choosing the file to be opened.\n\n" +
                "To search a record, enter key phrases in the search field.\n" +
                "\n" +
                "To see visualization of mouse record, go to 'Record -> Visualization'\n" +
                "Mouse status can be searched through the upper-right search panel.\n\n" +
                "mouseQ was developed by Bo Zhou, under an open source licence. Commercial distribution of\n" +
                "The application is prohibited\n" +
                "All rights reserved to the Application's author.\n\n" +
                "For more information and questions regarding the application, please contact Bo Zhou at\n" +
                "41186531@qq.com.\n\n" +
                "Any suggestions for improvement are strongly appreciated!";
        Text introText=new Text(appIntro);
        this.setTitle("About mouseQ");
        this.setResizable(false);
        pane.setContent(introText);
        pane.setPrefWidth(710);
        pane.setPrefHeight(900);
        introText.setStyle("-fx-font-size: 13pt; -fx-fill: white;");
        pane.getStylesheets().add(HistoryStage.class.getResource("/historyStyleSheet.css").toExternalForm());
        this.setScene(scene);
        this.setAlwaysOnTop(true);


    }
}
