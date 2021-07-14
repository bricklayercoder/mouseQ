package com.javaFXGUI;

import com.cagezz.Cage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SelectedCageGridPane extends GridPane {

    Text cageNumberText;
    Text strainText;
    Text statusText;
    Text sizeText;
    Text notesText;

    public SelectedCageGridPane() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(30); this.setVgap(10);
        this.setPadding(new Insets(5, 5, 15, 5));

        Text selectedText=new Text("Selected");
        selectedText.setId("cageSelected");
        this.add(selectedText, 0, 0, 2, 1);

        Label cageNUmberLabel=new Label("Cage Number:");
        cageNumberText=new Text();
        cageNumberText.setId("TextIDCageNumber");
        this.add(cageNUmberLabel, 0, 1);
        this.add(cageNumberText, 1, 1);

        Label strainLabel=new Label("Strain:");
        strainText=new Text();
        strainText.setId("textIDStrain");
        this.add(strainLabel, 0, 2);
        this.add(strainText, 1, 2);

        Label statusLabel=new Label("Status:");
        statusText=new Text();
        statusText.setId("textIDStatus");
        this.add(statusLabel, 0, 3);
        this.add(statusText, 1, 3);

        Label sizeLabel=new Label("Size:");
        sizeText=new Text();
        sizeText.setId("textIDSize");
        this.add(sizeLabel, 0, 4);
        this.add(sizeText, 1, 4);

        Label notesLabel=new Label("Notes:");
        notesText=new Text();
        notesText.setId("textIDNotes");
        this.add(notesLabel, 0, 5);
        this.add(notesText, 1, 5);

        Button updateCageButton=new Button("Update");
        this.add(updateCageButton, 1, 7);

    }

    public void setSelectedCageAttribute(Cage cage){
         cageNumberText.setText(cage.getCageNumber());
         strainText.setText(cage.getStrain());
         statusText.setText(cage.getStatus());
         sizeText.setText(cage.getSize());
         notesText.setText(cage.getNotes());
    }
}
