package com.javaFXGUI.SecondaryGUI;

import com.cagezz.Cage;
import com.javaFXGUI.*;
import com.mouse.Mouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class AddNewMouseStage extends Stage{

    MainFrameBorderPane workingMainFrameBorderPane=(MainFrameBorderPane) AppLaunch.mainFrameBorderPane;
    TablesVBox tablesVBox=workingMainFrameBorderPane.getTablesVBox();
    MiceVBox miceVBox=tablesVBox.getMiceVBox();
    CagezVBox cagezVBox=tablesVBox.getCagezVBox();

    HBox root=new HBox();
    AddingNewMouseGridPane addingNewMouseGridPane=new AddingNewMouseGridPane();

    ChooseFatherOrMotherOrCageTabPane chooseFatherOrMotherOrCageTabPane=new ChooseFatherOrMotherOrCageTabPane();

    Scene scene;


    public AddNewMouseStage() {
        this.setTitle("Add new mouse record");
        root.getChildren().addAll(chooseFatherOrMotherOrCageTabPane, addingNewMouseGridPane);
        scene=new Scene(root);
        scene.getStylesheets().add(AddNewMouseStage.class.getResource("/addMouseStyle.css").toExternalForm());
        this.setScene(scene);
        this.setAlwaysOnTop(true);
        this.setResizable(false);

    }

    public class MalesListView extends ListView<String> {
        ObservableList<String> malesTagNumberObservableList=FXCollections.observableArrayList();

        public MalesListView() {
            for(Mouse mouse : miceVBox.getSortedListofMice()){
                if (mouse.getGender().toLowerCase().equals("male")){
                    malesTagNumberObservableList.add(mouse.getTagNumber());
                }
            }
            this.setItems(malesTagNumberObservableList);
        }

    }

    public class FemalesListView extends ListView<String>{
        ObservableList<String> femalesTagNumberObservableList=FXCollections.observableArrayList();

        public FemalesListView() {
            for (Mouse mouse : miceVBox.getSortedListofMice()){
                if(mouse.getGender().toLowerCase().equals("female")){
                    femalesTagNumberObservableList.add(mouse.getTagNumber());
                }
            }
            this.setItems(femalesTagNumberObservableList);
        }
    }

    public class CagesListView extends ListView<String>{
        ObservableList<String> cageTagNumberObservableList=FXCollections.observableArrayList();

        public CagesListView() {
            for(Cage cage : cagezVBox.getSortedListOfCage()){
                cageTagNumberObservableList.add(cage.getCageNumber());
            }
            this.setItems(cageTagNumberObservableList);
        }
    }

    public class ChooseFatherOrMotherOrCageTabPane extends TabPane{
        Tab chooseFatherTab=new Tab("Males");
        Tab chooseMotherTab=new Tab("Females");
        Tab chooseCageTab=new Tab("Cages");

        FemalesListView femalesListView=new FemalesListView();
        MalesListView malesListView=new MalesListView();
        CagesListView cagesListView=new CagesListView();

        /**
        VBox femalesListContainerVBox=new VBox(femalesListView);
        VBox malesListContainerVBox=new VBox(malesListView);
        VBox cagesListContainerVBox=new VBox(cagesListView);
        */

        public ChooseFatherOrMotherOrCageTabPane() {
            chooseFatherTab.setContent(malesListView);
            chooseMotherTab.setContent(femalesListView);
            chooseCageTab.setContent(cagesListView);
            this.getTabs().addAll(chooseMotherTab, chooseFatherTab, chooseCageTab);
            this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        }
    }


    class AddingNewMouseGridPane extends GridPane{

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

        ObservableList<String> genderOptions= FXCollections.observableArrayList("Male", "Female");
        ComboBox genderComboBox=new ComboBox(genderOptions);

        TextField strainField=new TextField(),
                coatColorField=new TextField();
        DatePicker weanDatePicker=new DatePicker();
        TextField cageNumberField=new TextField(),
                notesField=new TextField();

        ObservableList<String> statusOptions=
                FXCollections.observableArrayList("MATING", "WEANING", "MAINTAINING", "GESTATING", "NURSING", "SACRIFICED");
        ComboBox  statusComboBox=new ComboBox(statusOptions);


        Button clearTagNumber=new Button("Clear"),
                clearMother=new Button("Clear"),
                clearFather=new Button("Clear"),
                clearGenotype=new Button("Clear"),
                clearStrain=new Button("Clear"),
                clearCoatColor=new Button("Clear"),
                clearCageNumber=new Button("Clear"),
                clearNotes=new Button("Clear");

        Button submitButton=new Button("Submit");

        public AddingNewMouseGridPane() {
            this.setAlignment(Pos.CENTER);
            this.setHgap(30);
            this.setVgap(10);
            this.setPadding(new Insets(5,5,15, 5));
            this.setPrefWidth(425);

            this.add(tagNumberLabel,0, 0, 1, 1 );
            this.add(tagText, 1, 0, 1, 1);
            this.add(clearTagNumber, 2, 0, 1, 1);

            this.add(motherLabel, 0, 1, 1, 1);
            this.add(motherTagField, 1, 1, 1, 1);
            this.add(clearMother, 2, 1, 1,1);

            this.add(fatherLabel, 0, 2, 1, 1);
            this.add(fatherTagField, 1, 2, 1, 1);
            this.add(clearFather, 2, 2, 1, 1);

            this.add(genotypeLabel, 0, 3, 1, 1);
            this.add(genotypeField, 1, 3, 1, 1);
            this.add(clearGenotype, 2, 3, 1, 1);

            this.add(birthDateLabel, 0, 4, 1, 1);
            this.add(dobPicker, 1, 4, 1, 1);
//        root.add(clearDoB, 2, 4, 1, 1);

            this.add(genderLabel, 0, 5, 1, 1);
            this.add(genderComboBox, 1, 5, 1, 1);
//        root.add(clearGender, 2, 5, 1, 1);

            this.add(strainLabel, 0, 6, 1, 1);
            this.add(strainField, 1, 6, 1,1);
            this.add(clearStrain, 2, 6, 1, 1);

            this.add(coatColorLabel, 0, 7, 1,1);
            this.add(coatColorField, 1, 7, 1,1);
            this.add(clearCoatColor, 2, 7, 1,1);

            this.add(weanDateLabel, 0, 8, 1,1);
            this.add(weanDatePicker, 1, 8, 1,1);
//        root.add(clearWeanDate, 2, 8, 1, 1);

            this.add(cageNumberLabel, 0, 9, 1,1 );
            this.add(cageNumberField, 1, 9, 1,1);
            this.add(clearCageNumber, 2, 9, 1,1);

            this.add(statusLabel, 0, 10, 1,1);
            this.add(statusComboBox, 1, 10, 1, 1);
//        root.add(clearStatus, 2, 10, 1,1);

            this.add(notesLabel, 0, 11, 1, 1);
            this.add(notesField, 1, 11, 1,1);
            this.add(clearNotes, 2, 11, 1, 1);

            genderComboBox.setPrefWidth(188);
            statusComboBox.setPrefWidth(188);
            submitButton.setPrefWidth(188);
            this.add(submitButton, 1, 13, 2, 1);

            submitButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    MainFrameBorderPane workingMainFrameBorderPane=(MainFrameBorderPane) AppLaunch.mainFrameBorderPane;
                    TablesVBox tablesVBox=workingMainFrameBorderPane.getTablesVBox();
                    MiceVBox miceVBox=tablesVBox.getMiceVBox();

                    System.out.println("Inside submit button");

                    miceVBox.refreshMiceTable();
                }
            });

        }
    }

}
