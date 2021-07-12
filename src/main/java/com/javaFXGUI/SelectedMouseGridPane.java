package com.javaFXGUI;

import com.mouse.Mouse;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SelectedMouseGridPane extends GridPane {

    Text  tagNumberText;
    Text  motherTagText;
    Text fatherTagText;
    Text genotypeText;
    Text birthDateText;
    Text genderText;
    Text strainText;
    Text coatColorText;
    Text weanDateText;
    Text cageNumberText;
    Text statusText;
    Text notesText;

    public SelectedMouseGridPane() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(30); this.setVgap(10);
        this.setPadding(new Insets(5, 5, 15, 5));

        Text currentText=new Text("Selected");
        currentText.setId("mouseSelected");
        this.add(currentText, 0, 0, 2, 1);

        Label tagNumberLabel =new Label("Tag-Number:");
        tagNumberText=new Text();
        tagNumberText.setId("textIDSelectedMouseTagNumber");
        this.add(tagNumberLabel, 0, 1);
        this.add(tagNumberText, 1, 1);

        Label motherTagLabel=new Label("Mother:");
        motherTagText =new Text();
        motherTagText.setId("textIDSelectedMouseMotherTagNumber");
        this.add(motherTagLabel, 0, 2);
        this.add(motherTagText, 1, 2);

        Label fatherTagLabel=new Label("Father:");
        fatherTagText=new Text();
        fatherTagText.setId("textIDSelectedMouseFatherTagNumber");
        this.add(fatherTagLabel, 0, 3);
        this.add(fatherTagText, 1, 3);

        Label genotypeLabel=new Label("Genotype:");
        genotypeText=new Text();
        genotypeText.setId("textIDSelectedMouseGenotype");
        this.add(genotypeLabel, 0, 4);
        this.add(genotypeText, 1, 4);

        Label birthDateLabel=new Label("Birth Date:");
        birthDateText=new Text();
        birthDateText.setId("textIDSelectedMouseBirthDate");
        this.add(birthDateLabel, 0, 5);
        this.add(birthDateText, 1, 5);

        Label genderLabel=new Label("Gender:");
        genderText=new Text();
        genderText.setId("textIDSelectedMouseGender");
        this.add(genderLabel, 0, 6);
        this.add(genderText, 1, 6);

        Label strainLabel=new Label("Strain:");
        strainText=new Text();
        strainText.setId("textIDSelectedMouseStrain");
        this.add(strainLabel, 0, 7);
        this.add(strainText, 1, 7);

        Label coatColorLabel=new Label("Coat Color:");
        coatColorText=new Text();
        coatColorText.setId("textIDSelectedMouseCoatColor");
        this.add(coatColorLabel, 0, 8);
        this.add(coatColorText, 1, 8);

        Label weanDateLabel=new Label("Wean Date:");
        weanDateText=new Text();
        weanDateText.setId("textIDSelectedMouseWeanDate");
        this.add(weanDateLabel, 0, 9);
        this.add(weanDateText, 1, 9);

        Label cageNumberLabel=new Label("Cage Number:");
        cageNumberText=new Text();
        cageNumberText.setId("textIDSelectedMouseCageNumber");
        this.add(cageNumberLabel, 0, 10);
        this.add(cageNumberText, 1, 10);

        Label statusLabel=new Label("Status:");
        statusText=new Text();
        statusText.setId("textIDSelectedMouseStatus");
        this.add(statusLabel, 0, 11);
        this.add(statusText, 1, 11);

        Label notesLabel=new Label("Notes:");
        notesText=new Text();
        notesText.setId("textIDSelectedMouseNotes");
        this.add(notesLabel, 0, 12);
        this.add(notesText, 1, 12);

        Button updateButton =new Button("_Update");
        this.add(updateButton, 1, 14);
    }

    public void setSelectedMouseAttribute(Mouse mouse){
          tagNumberText.setText(mouse.getTagNumber());
          motherTagText.setText(mouse.getMaternalTagNumber());
          fatherTagText.setText(mouse.getPaternalTagNumber());
          genotypeText.setText(mouse.getGenotype());
          birthDateText.setText(mouse.getBirthDate());
          genderText.setText(mouse.getGender());
          strainText.setText(mouse.getStrain());
          coatColorText.setText(mouse.getCoatColour());
          weanDateText.setText(mouse.getWeanDate());
          cageNumberText.setText(mouse.getCageNumber());
          statusText.setText(mouse.getStatus());
          notesText.setText(mouse.getNotes());
    }
}
