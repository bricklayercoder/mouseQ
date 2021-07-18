package com.javaFXGUI.SecondaryGUI;

import com.javaFXGUI.AppLaunch;
import com.mouse.Mouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SetUpBreedersStage extends Stage {
    ObservableList<Mouse> miceList= AppLaunch.mainFrameBorderPane.miceVBox.getObservableListOfMouse();

    public ObservableList<String> malesTagNumberObservableList= FXCollections.observableArrayList();
    public ObservableList<String> femalesTagNumberObservableList=FXCollections.observableArrayList();
    public ObservableList<String> cageTagNumberObservableList=FXCollections.observableArrayList();

    final BreederPickerTabPane breederPickerTabPane=new BreederPickerTabPane();


    final VBox selectButtonsVBox=new VBox();
    final Button selectCageButton=new Button("Select Cage"),
                 selectMaleButton=new Button("Select Male"),
                 selectFemaleButton=new Button("Select Female"),
                 removeFemaleButton=new Button("Remove Female");

    final VBox selectedFieldsVBox=new VBox();
    final TextField cageField=new TextField(),
           maleBreederField=new TextField();
    final FemaleBreedersListView femaleBreedersListView =new FemaleBreedersListView();
    final Text cageIndicatorText=new Text("Breeding Cage:"),
               maleIndicatorText=new Text("Male Breeder:"),
               femaleIndicatorText=new Text("Female Breeder:");

    final Button submitButton=new Button("Submit");

    HBox root=new HBox();


    class MalesListView extends ListView<String> {

        public MalesListView() {
            malesTagNumberObservableList.add("Anonym");
            for(Mouse mouse : miceList){
                if (mouse.getGender().toLowerCase().equals("male")){
                    malesTagNumberObservableList.add(mouse.getTagNumber());
                }
            }
            this.setItems(malesTagNumberObservableList);
        }

    }

    class FemalesListView extends ListView<String>{

        public FemalesListView() {
            femalesTagNumberObservableList.add("Anonym");
            for (Mouse mouse : miceList){
                if(mouse.getGender().toLowerCase().equals("female")){
                    femalesTagNumberObservableList.add(mouse.getTagNumber());
                }
            }
            this.setItems(femalesTagNumberObservableList);
        }
    }

    class CagesListView extends ListView<String>{

        public CagesListView() {
            cageTagNumberObservableList.add("Anonym");
            for(Mouse mouse : miceList){
                if(!cageTagNumberObservableList.contains(mouse.getCageNumber()))
                    cageTagNumberObservableList.add(mouse.getCageNumber());
            }
            this.setItems(cageTagNumberObservableList);
        }
    }

    class FemaleBreedersListView extends ListView<String> {
        ObservableList<String> femaleBreedersObservableList =FXCollections.observableArrayList();

        public FemaleBreedersListView() {
            this.setItems(femaleBreedersObservableList);
        }
    }

    class BreederPickerTabPane extends TabPane {
        Tab malePickerTab=new Tab("Males");
        Tab femalePickerTab=new Tab("Females");
        Tab cagePickerTab=new Tab("Cages");

        public final MalesListView malesListView=new MalesListView();
        public final FemalesListView femalesListView=new FemalesListView();
        public final CagesListView cagesListView=new CagesListView();

        public BreederPickerTabPane() {
            malePickerTab.setContent(malesListView);
            femalePickerTab.setContent(femalesListView);
            cagePickerTab.setContent(cagesListView);

            this.getTabs().add(malePickerTab);
            this.getTabs().add(femalePickerTab);
            this.getTabs().add(cagePickerTab);

            this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        }
    }

    public SetUpBreedersStage() {
        VBox topSpace=new VBox();
        VBox.setVgrow(topSpace, Priority.SOMETIMES);
        VBox bottomSpace=new VBox();
        VBox.setVgrow(bottomSpace, Priority.SOMETIMES);

        selectButtonsVBox.getChildren().add(topSpace);
        selectButtonsVBox.getChildren().add(selectCageButton);
        selectButtonsVBox.getChildren().add(selectMaleButton);
        selectButtonsVBox.getChildren().add(selectFemaleButton);
        selectButtonsVBox.getChildren().add(removeFemaleButton);
        selectButtonsVBox.getChildren().add(bottomSpace);

        selectedFieldsVBox.getChildren().add(cageIndicatorText);
        selectedFieldsVBox.getChildren().add(cageField);
        selectedFieldsVBox.getChildren().add(maleIndicatorText);
        selectedFieldsVBox.getChildren().add(maleBreederField);
        selectedFieldsVBox.getChildren().add(femaleIndicatorText);
        selectedFieldsVBox.getChildren().add(femaleBreedersListView);
        selectedFieldsVBox.getChildren().add(submitButton);

        root.setPrefWidth(700);
        root.setPrefHeight(600);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        selectButtonsVBox.setSpacing(20);

        selectedFieldsVBox.setSpacing(10);

        selectCageButton.setPrefWidth(150);
        selectMaleButton.setPrefWidth(150);
        selectFemaleButton.setPrefWidth(150);
        removeFemaleButton.setPrefWidth(150);
        submitButton.setPrefWidth(250);


        root.getChildren().add(breederPickerTabPane);
        root.getChildren().add(selectButtonsVBox);
        root.getChildren().add(selectedFieldsVBox);
        Scene scene=new Scene(root);
        this.setScene(scene);
        this.setAlwaysOnTop(true);

    }
}
