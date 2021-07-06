package com.javaFXGUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SelectedMouseGridPane extends GridPane {

    public SelectedMouseGridPane() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(30); this.setVgap(10);
        this.setPadding(new Insets(5, 5, 15, 5));

        Text currentText=new Text("Selected");
        currentText.setId("mouseSelected");
        this.add(currentText, 0, 0, 2, 1);

        Label tagNumberLabel =new Label("Tag-Number:");
        Text  tagNumberText=new Text();
        this.add(tagNumberLabel, 0, 1);
        this.add(tagNumberText, 1, 1);

        Label motherTagLabel=new Label("Mother:");
        Text  motherTagText =new Text();
        this.add(motherTagLabel, 0, 2);
        this.add(motherTagText, 1, 2);

        Label fatherTagLabel=new Label("Father:");
        Text fatherTagText=new Text();
        this.add(fatherTagLabel, 0, 3);
        this.add(fatherTagText, 1, 3);

        Label genotypeLabel=new Label("Genotype:");
        Text genotypeText=new Text();
        this.add(genotypeLabel, 0, 4);
        this.add(genotypeText, 1, 4);

        Label birthDateLabel=new Label("Birth Date:");
        Text birthDateText=new Text();
        this.add(birthDateLabel, 0, 5);
        this.add(birthDateText, 1, 5);

        Label genderLabel=new Label("Gender:");
        Text genderText=new Text();
        this.add(genderLabel, 0, 6);
        this.add(genderText, 1, 6);

        Label strainLabel=new Label("Strain:");
        Text strainText=new Text();
        this.add(strainLabel, 0, 7);
        this.add(strainText, 1, 7);

        Label coatColorLabel=new Label("Coat Color:");
        Text coatColorText=new Text();
        /*
        test code
         */
        coatColorText.setId("coatColorText");
        coatColorText.setText("This is a test code");

        this.add(coatColorLabel, 0, 8);
        this.add(coatColorText, 1, 8);

        Label weanDateLabel=new Label("Wean Date:");
        Text weanDateText=new Text();
        this.add(weanDateLabel, 0, 9);
        this.add(weanDateText, 1, 9);

        Label cageNumberLabel=new Label("Cage Number:");
        Text cageNumberText=new Text();
        this.add(cageNumberLabel, 0, 10);

        Label statusLabel=new Label("Status:");
        Text statusText=new Text();
        this.add(statusLabel, 0, 11);
        this.add(statusText, 1, 11);

        Label notesLabel=new Label("Notes");
        Text notesText=new Text();
        this.add(notesLabel, 0, 12);
        this.add(notesText, 1, 12);

        Button updateButton =new Button("_Update");
        this.add(updateButton, 1, 14);
    }
}
