package com.javaFXGUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SelectedCageGridPane extends GridPane {

    public SelectedCageGridPane() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(30); this.setVgap(10);
        this.setPadding(new Insets(5, 5, 15, 5));

        Text selectedText=new Text("Selected");
        selectedText.setId("cageSelected");
        this.add(selectedText, 0, 0, 2, 1);

        Label cageNUmberLabelCageTab=new Label("Cage Number:");
        Text cageNumberTextCageTab=new Text();
        this.add(cageNUmberLabelCageTab, 0, 1);
        this.add(cageNumberTextCageTab, 1, 1);

        Label strainLabelCageTab=new Label("Strain:");
        Text strainTextCageTab=new Text();
        this.add(strainLabelCageTab, 0, 2);
        this.add(strainTextCageTab, 1, 2);

        Label statusLabelCageTab=new Label("Status:");
        Text statusTextCageTab=new Text();
        this.add(statusLabelCageTab, 0, 3);
        this.add(statusTextCageTab, 1, 3);

        Label sizeLabelCageTab=new Label("Size:");
        Text sizeTextCageTab=new Text();
        this.add(sizeLabelCageTab, 0, 4);
        this.add(sizeTextCageTab, 1, 4);

        Label notesLabelCageTab=new Label("Notes:");
        Text notesTextCageTab=new Text();
        this.add(notesLabelCageTab, 0, 5);
        this.add(notesTextCageTab, 1, 5);

        Button updateCageButton=new Button("Update");
        this.add(updateCageButton, 1, 7);

    }
}
