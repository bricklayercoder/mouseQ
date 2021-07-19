package com.javaFXGUI.SecondaryGUI;

import com.cagezz.Status;
import com.javaFXGUI.AppLaunch;
import com.models.ModelBreeders;
import com.mouse.Mouse;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Locale;

public class SetUpBreedersStage extends Stage {
    ObservableList<Mouse> miceList= AppLaunch.mainFrameBorderPane.miceVBox.getObservableListOfMouse();

    public ObservableList<String> malesTagNumberObservableList= FXCollections.observableArrayList();
    public ObservableList<String> femalesTagNumberObservableList=FXCollections.observableArrayList();
    public ObservableList<String> cageNumberObservableList =FXCollections.observableArrayList();

    public final BreederPickerTabPane breederPickerTabPane=new BreederPickerTabPane();


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
               femaleIndicatorText=new Text("Female Breeders:");

    final Button submitButton=new Button("Submit");

    HBox root=new HBox();
    public void refreshCageListView(){
        breederPickerTabPane.cagesListView.refresh();
    }
    public void refreshMalesListView(){
        breederPickerTabPane.malesListView.refresh();
    }
    public void refreshFemalesListView(){
        breederPickerTabPane.femalesListView.refresh();
    }


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
            cageNumberObservableList.add("Anonym");
            for(Mouse mouse : miceList){
                if(!cageNumberObservableList.contains(mouse.getCageNumber()))
                    cageNumberObservableList.add(mouse.getCageNumber());
            }
            this.setItems(cageNumberObservableList);
        }
    }

    class FemaleBreedersListView extends ListView<String> {
        ObservableList<String> femaleBreedersObservableList =FXCollections.observableArrayList();

        public FemaleBreedersListView() {
            femaleBreedersObservableList.add("");
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

            this.getTabs().add(cagePickerTab);
            this.getTabs().add(malePickerTab);
            this.getTabs().add(femalePickerTab);

            this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        }
    }

    private String  validateUserInput(){
        String errorMsg=null;
        if (cageField.getText().trim().isEmpty()){
            errorMsg="Error Breeding. The cage-number is empty.";
            return errorMsg;
        }
        if(maleBreederField.getText().trim().isEmpty()){
            errorMsg="Error Breeding. The male breeder tag-number is empty.";
            return errorMsg;
        }

        if (femaleBreedersListView.femaleBreedersObservableList.size() ==1){
            errorMsg="Error Breeding. The female breeders are empty.";
        }

        return errorMsg;
    }

    private ModelBreeders collectUserInput(){
        String cageNumber=cageField.getText().trim();
        String maleTagNumber=maleBreederField.getText().trim();
        ArrayList<String> femaleBreedersTagNumbers=new ArrayList<>();
        for (String tagNumber : femaleBreedersListView.femaleBreedersObservableList){
            if (!tagNumber.isEmpty()){
                femaleBreedersTagNumbers.add(tagNumber);
            }
        }
        return new ModelBreeders(cageNumber, maleTagNumber, femaleBreedersTagNumbers);

    }

    public SetUpBreedersStage() {

        selectCageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String cageNumber=breederPickerTabPane.cagesListView.getSelectionModel().getSelectedItem();
                if(cageNumber != null){
                    cageField.setText(cageNumber);
                }
            }
        });

        selectMaleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedMale=breederPickerTabPane.malesListView.getSelectionModel().getSelectedItem();
                if (selectedMale !=null){
                    maleBreederField.setText(selectedMale);
                }
            }
        });

        selectFemaleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedFemale=breederPickerTabPane.femalesListView.getSelectionModel().getSelectedItem();
                if (selectedFemale !=null && !femaleBreedersListView.femaleBreedersObservableList.contains(selectedFemale)){
                    femaleBreedersListView.femaleBreedersObservableList.add(selectedFemale);
                }
                femaleBreedersListView.refresh();
            }
        });

        removeFemaleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedFemaleBreeder=femaleBreedersListView.getSelectionModel().getSelectedItem();
                if (!selectedFemaleBreeder.isEmpty()){
                    femaleBreedersListView.femaleBreedersObservableList.remove(selectedFemaleBreeder);
                }
                femaleBreedersListView.refresh();
            }
        });

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String errorMsg=validateUserInput();
                if (errorMsg!=null){
                    AppLaunch.mainFrameBorderPane.getProcessText().setText(errorMsg);
                    AppLaunch.mainFrameBorderPane.getProcessText().setStyle("-fx-fill: red; -fx-font-size: 13pt;");
                    return;
                }
                ModelBreeders modelBreeders=collectUserInput();
                for (Mouse mouse : AppLaunch.mainFrameBorderPane.miceVBox.getObservableListOfMouse()){

                    if (mouse.getTagNumber().equals(modelBreeders.getMaleBreederTagNumber())){
                        mouse.setStatus(Status.MATING.toString());
                        mouse.setCageNumber(modelBreeders.getCageNumber());
                    }
                    for (String femaleBreeder : modelBreeders.getFemaleBreedersList()){
                        if(mouse.getTagNumber().equals(femaleBreeder)){
                            mouse.setStatus(Status.MATING.toString());
                            mouse.setCageNumber(modelBreeders.getCageNumber());
                        }
                    }
                }

                AppLaunch.mainFrameBorderPane.miceVBox.refreshMiceTable();

                ObservableList<String> observableListOfCageNumbersForAddMouse=
                AppLaunch.mainFrameBorderPane.miceVBox.addNewMouseAndUpdateMouseHBox.chooseVariousDataTabPane.cagesListView.cageNumberObservableList;
                if (!observableListOfCageNumbersForAddMouse.contains(modelBreeders.getCageNumber())){
                    observableListOfCageNumbersForAddMouse.add(modelBreeders.getCageNumber());
                    AppLaunch.mainFrameBorderPane.miceVBox.addNewMouseAndUpdateMouseHBox.chooseVariousDataTabPane.cagesListView.refresh();
                }

                ObservableList<String> observableListOfCageNumbersForUpdateMouse=
                AppLaunch.mainFrameBorderPane.miceVBox.addNewMouseAndUpdateMouseHBox.cageAndNotesPickerTabPane.cagesListView.cageNumberObservableList;
                if(!observableListOfCageNumbersForUpdateMouse.contains(modelBreeders.getCageNumber())){
                    observableListOfCageNumbersForUpdateMouse.add(modelBreeders.getCageNumber());
                    AppLaunch.mainFrameBorderPane.miceVBox.addNewMouseAndUpdateMouseHBox.cageAndNotesPickerTabPane.cagesListView.refresh();
                }

                if (!cageNumberObservableList.contains(modelBreeders.getCageNumber())){
                    cageNumberObservableList.add(modelBreeders.getCageNumber());
                    refreshCageListView();
                }

                AppLaunch.mainFrameBorderPane.getProcessText().setText("Set up breeding cage " + modelBreeders.getCageNumber()+ " successfully.");
                AppLaunch.mainFrameBorderPane.getProcessText().setStyle("-fx-fill: white; -fx-font-size: 13pt;");
                AppLaunch.historyStage.sbCounter++;
                String s=AppLaunch.historyStage.sbCounter + ",   Set up breeding cage ------" + modelBreeders.getCageNumber() +"------ successfully.\n";
                     s+="                    Male breeder:           " + modelBreeders.getMaleBreederTagNumber()+"\n";
                for (String femaleBreederTagNumber : modelBreeders.getFemaleBreedersList()){
                     s+= "                    Female breeder:      " + femaleBreederTagNumber +"\n";
                }
                s+="\n";
                AppLaunch.historyStage.sb.append(s);
                AppLaunch.historyStage.setHistoryText(AppLaunch.historyStage.sb.toString());
            }
        });

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
        maleBreederField.setEditable(false);
        selectedFieldsVBox.getChildren().add(femaleIndicatorText);
        selectedFieldsVBox.getChildren().add(femaleBreedersListView);
        selectedFieldsVBox.getChildren().add(submitButton);

        cageIndicatorText.setId("cageIndicatorText");
        cageField.setId("cageField");
        maleIndicatorText.setId("maleIndicatorText");
        maleBreederField.setId("maleBreederField");
        femaleIndicatorText.setId("femaleIndicatorText");

        root.setPrefWidth(700);
        root.setPrefHeight(635);
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
        root.getStylesheets().add(SetUpBreedersStage.class.getResource("/SetUpBreedersStyleSheet.css").toExternalForm());
        Scene scene=new Scene(root);
        this.setScene(scene);
        this.setTitle("Breeding");
        this.setAlwaysOnTop(true);
        this.setResizable(false);

    }
}
