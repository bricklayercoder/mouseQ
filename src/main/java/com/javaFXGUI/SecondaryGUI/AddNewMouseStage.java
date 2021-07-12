package com.javaFXGUI.SecondaryGUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddNewMouseStage extends Stage{
    GridPane root=new GridPane();
    Scene scene;
    Label tagNumberLabel=new Label("Tag-Number");
    Label motherLabel=new Label("Mother");
    Label fatherLabel=new Label("Father");
    Label genotypeLabel=new Label("Genotype");
    Label birthDateLabel=new Label("Birth-Date");
    Label genderLabel=new Label("Gender");
    Label strainLabel=new Label("Strain");
    Label coatColorLabel=new Label("Coat-Color");
    Label weanDateLabel=new Label("Wean-Date");
    Label cageNumberLabel=new Label("Cage-Number");
    Label statusLabel=new Label("Status");
    Label notesLabel=new Label("Notes");

    TextField tagText=new TextField();
    TextField motherTagField=new TextField();
    TextField fatherTagField=new TextField();
    TextField genotypeField=new TextField();
    DatePicker dobPicker=new DatePicker();
    TextField genderField=new TextField(),
              strainField=new TextField(),
              coatColorField=new TextField();
    DatePicker weanDatePicker=new DatePicker();
    TextField cageNumberField=new TextField(),
              statusField=new TextField(),
              notesField=new TextField();

    Button clearTagNumber=new Button("Clear"),
           clearMother=new Button("Clear"),
           clearFather=new Button("Clear"),
           clearGenotype=new Button("Clear"),
           clearDoB=new Button("Clear"),
           clearGender=new Button("Clear"),
           clearStrain=new Button("Clear"),
           clearCoatColor=new Button("Clear"),
           clearWeanDate=new Button("Clear"),
           clearCageNumber=new Button("Clear"),
           clearStatus=new Button("Clear"),
           clearNotes=new Button("Clear");

    Button submitButton=new Button("Submit");

    public AddNewMouseStage() {
        root.setAlignment(Pos.CENTER);
        root.setHgap(30);
        root.setVgap(10);
        root.setPadding(new Insets(5,5,15, 5));
        root.setPrefWidth(425);
        this.setTitle("Add new mouse record");

        root.add(tagNumberLabel,0, 0, 1, 1 );
        root.add(tagText, 1, 0, 1, 1);
        root.add(clearTagNumber, 2, 0, 1, 1);

        root.add(motherLabel, 0, 1, 1, 1);
        root.add(motherTagField, 1, 1, 1, 1);
        root.add(clearMother, 2, 1, 1,1);

        root.add(fatherLabel, 0, 2, 1, 1);
        root.add(fatherTagField, 1, 2, 1, 1);
        root.add(clearFather, 2, 2, 1, 1);

        root.add(genotypeLabel, 0, 3, 1, 1);
        root.add(genotypeField, 1, 3, 1, 1);
        root.add(clearGenotype, 2, 3, 1, 1);

        root.add(birthDateLabel, 0, 4, 1, 1);
        root.add(dobPicker, 1, 4, 1, 1);
        root.add(clearDoB, 2, 4, 1, 1);

        root.add(genderLabel, 0, 5, 1, 1);
        root.add(genderField, 1, 5, 1, 1);
        root.add(clearGender, 2, 5, 1, 1);

        root.add(strainLabel, 0, 6, 1, 1);
        root.add(strainField, 1, 6, 1,1);
        root.add(clearStrain, 2, 6, 1, 1);

        root.add(coatColorLabel, 0, 7, 1,1);
        root.add(coatColorField, 1, 7, 1,1);
        root.add(clearCoatColor, 2, 7, 1,1);

        root.add(weanDateLabel, 0, 8, 1,1);
        root.add(weanDatePicker, 1, 8, 1,1);
        root.add(clearWeanDate, 2, 8, 1, 1);

        root.add(cageNumberLabel, 0, 9, 1,1 );
        root.add(cageNumberField, 1, 9, 1,1);
        root.add(clearCageNumber, 2, 9, 1,1);

        root.add(statusLabel, 0, 10, 1,1);
        root.add(statusField, 1, 10, 1, 1);
        root.add(clearStatus, 2, 10, 1,1);

        root.add(notesLabel, 0, 11, 1, 1);
        root.add(notesField, 1, 11, 1,1);
        root.add(clearNotes, 2, 11, 1, 1);

        submitButton.setPrefWidth(188);
        root.add(submitButton, 1, 13, 2, 1);

        makePanel();
    }

    public void makePanel(){
        scene=new Scene(root);
        scene.getStylesheets().add(AddNewMouseStage.class.getResource("/addMouseStyle.css").toExternalForm());
        this.setScene(scene);
        this.setAlwaysOnTop(true);
    }
}
