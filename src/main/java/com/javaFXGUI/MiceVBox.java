package com.javaFXGUI;

import com.cagezz.CageZZ;
import com.models.UpdateModelMouse;
import com.mouse.Mouse;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Predicate;


public class MiceVBox extends VBox {

    TextField searchField=new TextField();

    CageZZ workingCagez=new CageZZ();

    ObservableList<Mouse> miceRecordObservableList;
    FilteredList<Mouse> filteredListOfMice;
    SortedList<Mouse> sortedListofMice;

    SelectedMouseGridPane selectedMouseGridPane=new SelectedMouseGridPane();
    UpdateSelectedMouseGridPane updateSelectedMouseGridPane=new UpdateSelectedMouseGridPane();

    HBox searchMiceHBox=new SearchMiceHBox();

    MiceTableView miceTableView=new MiceTableView();

    HBox miceTableViewAndSelectedMouseHBox =new MiceTableViewAndSelectedMouseHBox();
    public AddNewMouseAndUpdateMouseHBox addNewMouseAndUpdateMouseHBox =new AddNewMouseAndUpdateMouseHBox();


    public ObservableList<Mouse> getObservableListOfMouse(){
        return miceRecordObservableList;
    }

    public SortedList<Mouse> getSortedListofMice(){
        return sortedListofMice;
    }

    public CageZZ getWorkingCagez(){
        return workingCagez;
    }


    public MiceVBox() {

        this.getChildren().addAll(searchMiceHBox, miceTableViewAndSelectedMouseHBox, addNewMouseAndUpdateMouseHBox);
        this.setPrefWidth(1500);
        this.setPrefHeight(600);
    }

    public void refreshMiceTable(){
        miceTableView.refresh();
    }


    class SearchMiceHBox extends HBox {

        Label searchLabel=new Label("Search:");


        public SearchMiceHBox() {
            searchField.setId("searchField");
            searchLabel.setId("searchLabel");
            this.setSpacing(15);
            this.setPadding(new Insets(5, 5, 5,5));
            this.setAlignment(Pos.CENTER);
            
            this.getChildren().addAll(searchLabel, searchField);
            this.getStylesheets().add(MiceVBox.class.getResource("/stylesheet.css").toExternalForm());


        }
    }

    class MiceTableViewAndSelectedMouseHBox extends HBox {
        HBox spaceMiddle =new HBox();
        HBox spaceEnd=new HBox();
        public MiceTableViewAndSelectedMouseHBox() {
            HBox.setHgrow(spaceMiddle, Priority.SOMETIMES);
            HBox.setHgrow(spaceEnd, Priority.SOMETIMES);
            this.getChildren().add(miceTableView);
            this.getChildren().add(spaceMiddle);
            this.getChildren().add(selectedMouseGridPane);
            this.getChildren().add(spaceEnd);
        }
    }



    class MiceTableView extends TableView {

        public void  loadMiceRecordList(){
            miceRecordObservableList = FXCollections.observableArrayList();
            try {
                workingCagez.loadMiceRecords();

            } catch (Exception e){
                System.out.println(e);
            }

            for (Mouse mouse : workingCagez.getMiceList()){
                miceRecordObservableList.add(mouse);
            }
        }

        public void loadFilteredListOfMice(){
            filteredListOfMice=new FilteredList<>(miceRecordObservableList, p -> true);

            searchField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent e) {
                    searchField.textProperty().addListener((observable, oldValue, newValue) -> {

                        filteredListOfMice.setPredicate( (Predicate<? super Mouse>) mouse -> {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }

                            // Compare each mouse attribute
                            String lowerCaseFilter = newValue.toLowerCase().trim();

                            if (mouse.getTagNumber().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (mouse.getMaternalTagNumber().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (mouse.getPaternalTagNumber().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (mouse.getGenotype().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (mouse.getBirthDate().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (mouse.getGender().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (mouse.getStrain().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (mouse.getCoatColour().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (mouse.getWeanDate().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (mouse.getCageNumber().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (mouse.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else if (mouse.getNotes().toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            } else {
                                return false;

                            }
                        });
                    });

                }
            });

        }


        public MiceTableView() {

            TableColumn<Mouse, String> tagNumberColomn= new TableColumn<>("TagNumber");
            tagNumberColomn.setCellValueFactory(new PropertyValueFactory<>("tagNumber"));

            TableColumn<Mouse, String> motherNumberColomn= new TableColumn<>("Mother");
            motherNumberColomn.setCellValueFactory(new PropertyValueFactory<>("maternalTagNumber"));

            TableColumn<Mouse, String> fatherNumberColomn= new TableColumn<>("Father");
            fatherNumberColomn.setCellValueFactory(new PropertyValueFactory<>("paternalTagNumber"));

            TableColumn<Mouse, String> genotypeColomn= new TableColumn<>("Genotype");
            genotypeColomn.setCellValueFactory(new PropertyValueFactory<>("genotype"));

            TableColumn<Mouse, String> birthDateColumn= new TableColumn<>("BirthDate");
            birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

            TableColumn<Mouse, String> genderColumn= new TableColumn<>("Gender");
            genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

            TableColumn<Mouse, String> strainColumn= new TableColumn<>("Strain");
            strainColumn.setCellValueFactory(new PropertyValueFactory<>("strain"));

            TableColumn<Mouse, String> coatColorColumn= new TableColumn<>("Coat-Color");
            coatColorColumn.setCellValueFactory(new PropertyValueFactory<>("coatColour"));

            TableColumn<Mouse, String> weanDateColumn= new TableColumn<>("Wean-Date");
            weanDateColumn.setCellValueFactory(new PropertyValueFactory<>("weanDate"));

            TableColumn<Mouse, String> cageNumberColumn= new TableColumn<>("Cage-Number");
            cageNumberColumn.setCellValueFactory(new PropertyValueFactory<>("cageNumber"));

            TableColumn<Mouse, String> statusColumn= new TableColumn<>("Status");
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

            TableColumn<Mouse, String> notesColumn= new TableColumn<>("Notes");
            notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

            this.getColumns().add(tagNumberColomn);
            this.getColumns().add(motherNumberColomn);
            this.getColumns().add(fatherNumberColomn);
            this.getColumns().add(genotypeColomn);
            this.getColumns().add(birthDateColumn);
            this.getColumns().add(genderColumn);
            this.getColumns().add(strainColumn);
            this.getColumns().add(coatColorColumn);
            this.getColumns().add(weanDateColumn);
            this.getColumns().add(cageNumberColumn);
            this.getColumns().add(statusColumn);
            this.getColumns().add(notesColumn);


            tagNumberColomn.setPrefWidth(90);
            motherNumberColomn.setPrefWidth(90);
            fatherNumberColomn.setPrefWidth(90);
            genotypeColomn.setPrefWidth(90);
            birthDateColumn.setPrefWidth(90);
            genderColumn.setPrefWidth(90);
            strainColumn.setPrefWidth(90);
            coatColorColumn.setPrefWidth(90);
            weanDateColumn.setPrefWidth(90);
            cageNumberColumn.setPrefWidth(90);
            statusColumn.setPrefWidth(90);
            notesColumn.setPrefWidth(325);

            loadMiceRecordList();
            loadFilteredListOfMice();
            sortedListofMice=new SortedList<>(filteredListOfMice);
            sortedListofMice.comparatorProperty().bind(this.comparatorProperty());
            this.setItems(sortedListofMice);

            TableView thisTableView=this;
            this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    Mouse selectedMouse= (Mouse) thisTableView.getSelectionModel().getSelectedItem();
                    selectedMouseGridPane.setSelectedMouseAttribute(selectedMouse);

                    updateSelectedMouseGridPane.setTagNumberTextField(selectedMouse.getTagNumber());
                    updateSelectedMouseGridPane.setCageNumberField(selectedMouse.getCageNumber());
                    updateSelectedMouseGridPane.setNotesTextField(selectedMouse.getNotes());
                    updateSelectedMouseGridPane.setStatusComboBox(selectedMouse.getStatus());

                }
            });
        }

    }

    public class AddNewMouseAndUpdateMouseHBox extends HBox {

        public ChooseVariousDataTabPane chooseVariousDataTabPane =new ChooseVariousDataTabPane();
        AddingNewMouseGridPane addingNewMouseGridPane=new AddingNewMouseGridPane();
        ChooserAndAddMouseHBox chooserAndAddMouseHBox=new ChooserAndAddMouseHBox();

        public CageAndNotesPickerTabPane cageAndNotesPickerTabPane=new CageAndNotesPickerTabPane();
        PickersAndUpdateHBox pickersAndUpdateHBox=new PickersAndUpdateHBox();

        /**
         *  HBox container for CageAndNotesPickerTabPane and updateSelectedMouseGridPane
         */

        class PickersAndUpdateHBox extends HBox{
            public PickersAndUpdateHBox() {
                this.getChildren().add(cageAndNotesPickerTabPane);
                this.getChildren().add(updateSelectedMouseGridPane);
            }
        }


        public class CageAndNotesPickerTabPane extends TabPane{
            Tab cagePickerTab=new Tab("Cages");
            Tab notesPickerTab=new Tab("Notes");

            public CagePickerListView cagesListView=new CagePickerListView();
            public NotesPickerListView notesListView=new NotesPickerListView();

            public CageAndNotesPickerTabPane() {
                cagePickerTab.setContent(cagesListView);
                notesPickerTab.setContent(notesListView);
                this.getTabs().add(cagePickerTab);
                this.getTabs().add(notesPickerTab);
                this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
                this.setPrefWidth(175);
            }
        }

        /**
         *  CagePickerListView specific for update mouse
         */
        public class CagePickerListView extends ListView<String>{
            public ObservableList<String> cageNumberObservableList=FXCollections.observableArrayList();

            public CagePickerListView() {
                cageNumberObservableList.add("Anonym");
                for(Mouse mouse : miceRecordObservableList){
                    if(!cageNumberObservableList.contains(mouse.getCageNumber()))
                        cageNumberObservableList.add(mouse.getCageNumber());
                }
                this.setItems(cageNumberObservableList);
                this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        updateSelectedMouseGridPane.setCageNumberField(newValue);
                    }
                });
            }
        }

        /**
         *  NotesPickerListView specific for update mouse
         */
        class NotesPickerListView extends ListView<String>{
            ObservableList<String> notesObservableList=FXCollections.observableArrayList();

            public NotesPickerListView() {
                notesObservableList.add("Anonym");
                for(Mouse mouse : miceRecordObservableList){
                    if(!notesObservableList.contains(mouse.getNotes()))
                        notesObservableList.add(mouse.getNotes());
                }
                this.setItems(notesObservableList);
                this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        updateSelectedMouseGridPane.setNotesTextField(newValue);
                    }
                });
            }
        }


        public AddNewMouseAndUpdateMouseHBox() {

            HBox space=new HBox();
            HBox.setHgrow(space, Priority.SOMETIMES);
            this.getChildren().add(chooserAndAddMouseHBox);
            this.getChildren().add(space);
            this.getChildren().add(pickersAndUpdateHBox);
            this.getStylesheets().add(MiceVBox.class.getResource("/addMouseStyle.css").toExternalForm());
        }

        class ChooserAndAddMouseHBox extends HBox {

            public ChooserAndAddMouseHBox() {
                this.getChildren().add(chooseVariousDataTabPane);
                this.getChildren().add(addingNewMouseGridPane);
            }
        }

        public class MalesListView extends ListView<String> {
            public ObservableList<String> malesTagNumberObservableList= FXCollections.observableArrayList();

            public MalesListView() {
                malesTagNumberObservableList.add("Anonym");
                for(Mouse mouse : getSortedListofMice()){
                    if (mouse.getGender().toLowerCase().equals("male")){
                        malesTagNumberObservableList.add(mouse.getTagNumber());
                    }
                }
                this.setItems(malesTagNumberObservableList);
                this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        addingNewMouseGridPane.setFatherTagField(newValue);
                    }
                });
            }

        }

        public class FemalesListView extends ListView<String>{
            ObservableList<String> femalesTagNumberObservableList=FXCollections.observableArrayList();

            public FemalesListView() {
                femalesTagNumberObservableList.add("Anonym");
                for (Mouse mouse : getSortedListofMice()){
                    if(mouse.getGender().toLowerCase().equals("female")){
                        femalesTagNumberObservableList.add(mouse.getTagNumber());
                    }
                }
                this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        addingNewMouseGridPane.setMotherTagField(newValue);
                    }
                });
                this.setItems(femalesTagNumberObservableList);
            }
        }

        public class CagesListView extends ListView<String>{
            public ObservableList<String> cageNumberObservableList =FXCollections.observableArrayList();

            public CagesListView() {
                cageNumberObservableList.add("Anonym");
                for(Mouse mouse : getObservableListOfMouse()){
                    if(!cageNumberObservableList.contains(mouse.getCageNumber()))
                        cageNumberObservableList.add(mouse.getCageNumber());
                }
                this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        addingNewMouseGridPane.setCageNumberField(newValue);
                    }
                });
                this.setItems(cageNumberObservableList);
            }
        }

        public class ChooseVariousDataTabPane extends TabPane {
            Tab chooseFatherTab=new Tab("Males");
            Tab chooseMotherTab=new Tab("Females");
            Tab chooseGenotypeTab=new Tab("Genotypes");
            Tab chooseStrainTab=new Tab("Strains");
            Tab chooseCoatColorTab=new Tab("Coat-colors");
            Tab chooseCageTab=new Tab("Cages");
            Tab chooseNotesTab=new Tab("Notes");

            FemalesListView femalesListView=new FemalesListView();
            MalesListView malesListView=new MalesListView();
            GenotypeListView genotypeListView=new GenotypeListView();
            StrainListView strainListView=new StrainListView();
            CoatColorListView coatColorListView=new CoatColorListView();
            public CagesListView cagesListView=new CagesListView();
            NotesListView notesListView=new NotesListView();

            void refreshListViews(){
                femalesListView.refresh();
                malesListView.refresh();
                genotypeListView.refresh();
                strainListView.refresh();
                coatColorListView.refresh();
                cagesListView.refresh();
                notesListView.refresh();
            }

            public ChooseVariousDataTabPane() {
                chooseFatherTab.setContent(malesListView);
                chooseMotherTab.setContent(femalesListView);
                chooseGenotypeTab.setContent(genotypeListView);
                chooseStrainTab.setContent(strainListView);
                chooseCoatColorTab.setContent(coatColorListView);
                chooseCageTab.setContent(cagesListView);
                chooseNotesTab.setContent(notesListView);
                this.getTabs().addAll(chooseMotherTab, chooseFatherTab, chooseGenotypeTab,
                        chooseStrainTab,chooseCoatColorTab, chooseCageTab, chooseNotesTab);
                this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

            }
        }

        public class GenotypeListView extends ListView<String> {
            ObservableList<String> genotypeObservableList=FXCollections.observableArrayList();

            public GenotypeListView() {
                genotypeObservableList.add("Anonym");
                for (Mouse mouse : getSortedListofMice()){
                    if(! genotypeObservableList.contains(mouse.getGenotype())){
                        genotypeObservableList.add(mouse.getGenotype());
                    }
                }
                this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        addingNewMouseGridPane.setGenotypeField(newValue);
                    }
                });
                this.setItems(genotypeObservableList);
            }

        }

        public class StrainListView extends ListView<String> {
            ObservableList<String> strainObservableList=FXCollections.observableArrayList();

            public StrainListView() {
                strainObservableList.add("Anonym");
                for (Mouse mouse : getSortedListofMice()){
                    if(! strainObservableList.contains(mouse.getStrain())){
                        strainObservableList.add(mouse.getStrain());
                    }
                }
                this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        addingNewMouseGridPane.setStrainField(newValue);
                    }
                });
                this.setItems(strainObservableList);
            }
        }

        public class CoatColorListView extends ListView<String> {
            ObservableList<String> coatColorObservableList =FXCollections.observableArrayList();

            public CoatColorListView() {
                coatColorObservableList.add("Anonym");
                for (Mouse mouse : getSortedListofMice()){
                    if(! coatColorObservableList.contains(mouse.getCoatColour())){
                        coatColorObservableList.add(mouse.getCoatColour());
                    }
                }
                this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        addingNewMouseGridPane.setCoatColorField(newValue);
                    }
                });
                this.setItems(coatColorObservableList);
            }
        }


        public class NotesListView extends ListView<String> {
            ObservableList<String> notesObservableList =FXCollections.observableArrayList();

            public NotesListView() {
                notesObservableList.add("Anonym");
                for (Mouse mouse : getSortedListofMice()){
                    if(! notesObservableList.contains(mouse.getNotes())){
                        notesObservableList.add(mouse.getNotes());
                    }
                }
                this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        addingNewMouseGridPane.setNotesField(newValue);
                    }
                });
                this.setItems(notesObservableList);
            }
        }




        class AddingNewMouseGridPane extends GridPane {

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

            Label indicatorLabel=new Label("--->>>");

            TextField tagTextField =new TextField();
            TextField motherTagField=new TextField();
            TextField fatherTagField=new TextField();
            TextField genotypeField=new TextField();
            DatePicker dobPicker=new DatePicker();

            ObservableList<String> genderOptions= FXCollections.observableArrayList("MALE", "FEMALE");
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

            Button submitButton=new Button("Add Mouse");

            public void setMotherTagField(String s){
                motherTagField.setText(s);
            }
            public void setFatherTagField(String s){
                fatherTagField.setText(s);
            }
            public void setGenotypeField(String s){
                genotypeField.setText(s);
            }
            public void setStrainField(String s){
                strainField.setText(s);
            }
            public void setCoatColorField(String s){
                coatColorField.setText(s);
            }
            public void setCageNumberField(String s){
                cageNumberField.setText(s);
            }
            public void setNotesField(String s){
                notesField.setText(s);
            }

            private boolean isMouseRecordAlreadyThere(Mouse mouse){
                boolean isAlreadythere=false;
                for (Mouse mse : getObservableListOfMouse()){
                    if (mouse.getTagNumber().toLowerCase().equals(mse.getTagNumber().toLowerCase())){
                        isAlreadythere=true;
                        return isAlreadythere;
                    }
                }
                return isAlreadythere;
            }
            private String validateUserInput(){
                String msg=null;
                if(tagTextField.getText().trim() ==null || tagTextField.getText().trim().isEmpty()){
                    msg="Error Add: the mouse's tag-number is empty.";
                    return msg;
                }
                if(motherTagField.getText().trim()==null || motherTagField.getText().trim().isEmpty()) {
                    msg = "Error Add: the mother's tag-number is empty.";
                    return msg;
                }
                if (fatherTagField.getText().trim()==null || fatherTagField.getText().trim().isEmpty()){
                    msg="Error Add: the father's tag-number is empty.";
                    return msg;
                }
                if (genotypeField.getText().trim()==null || genotypeField.getText().trim().isEmpty()){
                    msg="Error Add: the genotype is empty.";
                    return msg;
                }
                if(dobPicker.getValue()==null){
                    msg="Error Add: the mouse's birth-date is empty.";
                    return msg;
                }
                if ( genderComboBox.getSelectionModel().getSelectedItem()==null){
                        msg="Error Add: the mouse's gender is empty.";
                        return msg;
                }
                if(strainField.getText().trim()==null || strainField.getText().trim().isEmpty()){
                        msg="Error Add: the mouse's strain is empty.";
                        return msg;
                }
                if(coatColorField.getText().trim()==null || coatColorField.getText().trim().isEmpty()){
                        msg="Error Add: the mouse's coat-color is empty.";
                        return msg;
                }
                if(weanDatePicker.getValue()==null){
                        msg="Error Add: the mouse's wean-date is empty.";
                        return msg;
                }
                if(cageNumberField.getText().trim()==null || cageNumberField.getText().trim().isEmpty()){
                        msg="Error Add: the mouse's cage-number is empty.";
                        return msg;
                }
                if(statusComboBox.getSelectionModel().getSelectedItem()==null){
                        msg="Error Add: the mouse's status is empty.";
                        return msg;
                }
                if(notesField.getText().trim()==null || notesField.getText().trim().isEmpty()){
                        msg="Error Add: the mouse's notes is empty.";
                        return msg;
                }
                return msg;
            }
            private Mouse collectUserInput(){
                String inputTagNumber= tagTextField.getText().trim();
                String inputMotherTagNumber=motherTagField.getText().trim();
                String inputFatherTagNumber=fatherTagField.getText().trim();
                String inputGenotype=genotypeField.getText().trim();

                LocalDate localBirthDate=dobPicker.getValue();
                Instant instant = Instant.from(localBirthDate.atStartOfDay(ZoneId.systemDefault()));
                Date birthDate=Date.from(instant);
                DateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
                String inputBirthDateString=simpleDateFormat.format(birthDate).replace("/", "-");

                String inputGender= (String) genderComboBox.getSelectionModel().getSelectedItem();
                String inputStrain=strainField.getText().trim();
                String inputCoatColor=coatColorField.getText().trim();

                LocalDate localWeanDate=weanDatePicker.getValue();
                Instant instantWeanDate = Instant.from(localWeanDate.atStartOfDay(ZoneId.systemDefault()));
                Date weanDate=Date.from(instantWeanDate);
                String inputWeanDateString=simpleDateFormat.format(weanDate).replace("/", "-");

                String inputCageNumber=cageNumberField.getText().trim();
                String inputStatus=(String)statusComboBox.getSelectionModel().getSelectedItem();
                String inputNotes=notesField.getText().trim();

                Mouse newMouse=new Mouse(inputTagNumber,
                        inputMotherTagNumber,
                        inputFatherTagNumber,
                        inputBirthDateString,
                        inputGender,
                        inputStrain);
                newMouse.setGenotype(inputGenotype);
                newMouse.setCoatColour(inputCoatColor);
                newMouse.setWeanDate(inputWeanDateString);
                newMouse.setCageNumber(inputCageNumber);
                newMouse.setStatus(inputStatus);
                newMouse.setNotes(inputNotes);

                return newMouse;
            }

            public AddingNewMouseGridPane() {
                tagTextField.setId("tagTextId");
                motherTagField.setId("motherTagFieldId");
                fatherTagField.setId("fatherTagFieldId");
                genotypeField.setId("genotypeFieldId");
                strainField.setId("strainFieldId");
                coatColorField.setId("coatColorFieldId");
                cageNumberField.setId("cageNumberFieldId");
                notesField.setId("notesFieldId");

                dobPicker.setId("dobPickerId");
                dobPicker.setEditable(false);
                weanDatePicker.setId("weanDatePickerId");
                weanDatePicker.setEditable(false);

                genderComboBox.setId("genderComboBoxId");
                statusComboBox.setId("statusComboBoxId");


                this.setAlignment(Pos.CENTER);
                this.setHgap(30);
                this.setVgap(2);
                this.setPadding(new Insets(5,5,15, 5));
                this.setPrefWidth(475);

                this.add(tagNumberLabel,0, 0, 1, 1 );
                this.add(tagTextField, 1, 0, 1, 1);
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

                genderComboBox.setPrefWidth(255);
                statusComboBox.setPrefWidth(255);
                submitButton.setPrefWidth(255);

                this.add(indicatorLabel, 0, 13, 1, 1);
                this.add(submitButton, 1, 13, 1, 1);

                this.getStylesheets().add(MiceVBox.class.getResource("/addMouseStyle.css").toExternalForm());
                this.setStyle("-fx-background-color: #505150;");

                clearTagNumber.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        tagTextField.setText("");
                    }
                });
                clearMother.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        motherTagField.setText("");
                    }
                });
                clearFather.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        fatherTagField.setText("");
                    }
                });
                clearGenotype.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        genotypeField.setText("");
                    }
                });
                clearStrain.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        strainField.setText("");
                    }
                });
                clearCoatColor.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        coatColorField.setText("");
                    }
                });
                clearCageNumber.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        cageNumberField.setText("");
                    }
                });
                clearNotes.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        notesField.setText("");
                    }
                });

                submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String errorMsg=validateUserInput();
                        if( errorMsg !=null ){
                            AppLaunch.mainFrameBorderPane.processText.setText(errorMsg);
                            AppLaunch.mainFrameBorderPane.processText.setStyle("-fx-fill: red; -fx-font-size: 13pt;");
                            return;
                        }
                        Mouse newMouse=collectUserInput();
                        if (isMouseRecordAlreadyThere(collectUserInput())){
                            /**
                             Alert errorAlert=new Alert(Alert.AlertType.ERROR);
                             errorAlert.setTitle("Mouse Already In Records!");
                             errorAlert.setHeaderText("The inputted mouse tag-number already in records!");
                             errorAlert.setContentText("You may do the following:\n" +
                             "Delete the already-existing mouse from records, then add this new one;\n" +
                             "Or you may choose to update the already-existing mouse record. ");
                             errorAlert.showAndWait();
                             */
                            AppLaunch.mainFrameBorderPane.processText.setText("Error Add!  mouse tag-number: "+ newMouse.getTagNumber()+", already in records!" +
                                    " You may update this mouse's record!");
                            AppLaunch.mainFrameBorderPane.getProcessText().setStyle("-fx-fill: red; -fx-font-size: 13pt;");
                            return;

                        } else {
                            miceRecordObservableList.add(newMouse);

                            AppLaunch.mainFrameBorderPane.processText.setText("Mouse: "+newMouse.getTagNumber()+" record added successfully!");
                            AppLaunch.mainFrameBorderPane.getProcessText().setStyle("-fx-fill: white; -fx-font-size: 13pt;");
                            AppLaunch.historyStage.sbCounter++;
                            AppLaunch.historyStage.sb.append(AppLaunch.historyStage.sbCounter+", "+"  Add mouse ------"+newMouse.getTagNumber()+"------ into records successfully!\n");
                            String s="";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Tag-Number:                  " + newMouse.getTagNumber() +"\n";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Mother-TagNumber:   " + newMouse.getMaternalTagNumber() + "\n";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Father-TagNumber:     " + newMouse.getPaternalTagNumber() + "\n";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Genotype:                         " + newMouse.getGenotype() + "\n";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Birth-Date:                       " + newMouse.getBirthDate() + "\n";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Gender:                             " + newMouse.getGender() + "\n";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Strain:                                " + newMouse.getStrain() + "\n";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Coat-Color:                     " + newMouse.getCoatColour() + "\n";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Wean-Date:                    " + newMouse.getWeanDate() + "\n";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Cage-Number:               " + newMouse.getCageNumber() + "\n";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Status:                               " + newMouse.getStatus() + "\n";
                            s=s+"       -----------------------------------------------------\n";
                            s=s+"       Notes:                                " + newMouse.getNotes() + "\n";
                            s=s+"       -----------------------------------------------------\n\n";
                            AppLaunch.historyStage.sb.append(s);
                            AppLaunch.historyStage.setHistoryText(AppLaunch.historyStage.sb.toString());

                        }
                        refreshMiceTable();
                        if (newMouse.getGender().toLowerCase().equals("male")){
                            if(!AppLaunch.setUpBreedersStage.malesTagNumberObservableList.contains(newMouse.getTagNumber())){
                                AppLaunch.setUpBreedersStage.malesTagNumberObservableList.add(newMouse.getTagNumber());
                                AppLaunch.setUpBreedersStage.refreshMalesListView();
                            }
                        }

                        if(newMouse.getGender().toLowerCase().equals("female")){
                            if(!AppLaunch.setUpBreedersStage.femalesTagNumberObservableList.contains(newMouse.getTagNumber())){
                                AppLaunch.setUpBreedersStage.femalesTagNumberObservableList.add(newMouse.getTagNumber());
                                AppLaunch.setUpBreedersStage.refreshFemalesListView();
                            }
                        }

                        if(!AppLaunch.setUpBreedersStage.cageNumberObservableList.contains(newMouse.getCageNumber())){
                            AppLaunch.setUpBreedersStage.cageNumberObservableList.add(newMouse.getCageNumber());
                            AppLaunch.setUpBreedersStage.refreshCageListView();
                        }

                        if (newMouse.getGender().toLowerCase().equals("male")) {
                            chooseVariousDataTabPane.malesListView.malesTagNumberObservableList.add(newMouse.getTagNumber());
                        } else if(newMouse.getGender().toLowerCase().equals("female")){
                            chooseVariousDataTabPane.femalesListView.femalesTagNumberObservableList.add(newMouse.getTagNumber());
                        }

                        if(!chooseVariousDataTabPane.genotypeListView.genotypeObservableList.contains(newMouse.getGenotype())){
                            chooseVariousDataTabPane.genotypeListView.genotypeObservableList.add(newMouse.getGenotype());
                        }

                        if(!chooseVariousDataTabPane.strainListView.strainObservableList.contains(newMouse.getStrain())){
                            chooseVariousDataTabPane.strainListView.strainObservableList.add(newMouse.getStrain());
                        }

                        if(!chooseVariousDataTabPane.coatColorListView.coatColorObservableList.contains(newMouse.getCoatColour())){
                            chooseVariousDataTabPane.coatColorListView.coatColorObservableList.add(newMouse.getCoatColour());
                        }

                        if (!chooseVariousDataTabPane.cagesListView.cageNumberObservableList.contains(newMouse.getCageNumber())){
                            chooseVariousDataTabPane.cagesListView.cageNumberObservableList.add(newMouse.getCageNumber());
                        }

                        if (!chooseVariousDataTabPane.notesListView.notesObservableList.contains(newMouse.getNotes())){
                            chooseVariousDataTabPane.notesListView.notesObservableList.add(newMouse.getNotes());
                        }
                        chooseVariousDataTabPane.refreshListViews();

                        if(!cageAndNotesPickerTabPane.cagesListView.cageNumberObservableList.contains(newMouse.getCageNumber())){
                            cageAndNotesPickerTabPane.cagesListView.cageNumberObservableList.add(newMouse.getCageNumber());
                            cageAndNotesPickerTabPane.cagesListView.refresh();
                        }

                        if(!cageAndNotesPickerTabPane.notesListView.notesObservableList.contains(newMouse.getNotes())){
                            cageAndNotesPickerTabPane.notesListView.notesObservableList.add(newMouse.getNotes());
                            cageAndNotesPickerTabPane.notesListView.refresh();
                        }

                    }
                });

            }
        }

    }

    class UpdateSelectedMouseGridPane extends GridPane {

        Text selectedUpdateIconText =new Text("Update Selected Mouse:");

        Label   tagNumberLabel=new Label("Tag-Number:"),
                cageNumberLabel=new Label("Cage-Number:"),
                statusLabel=new Label("Status:"),
                notesLabel=new Label("Notes:"),
                indicatorLabel=new Label("--->>>");

        TextField tagNumberTextField=new TextField(),
                  cageNumberTextField=new TextField();

        ObservableList<String> stautsOptions= FXCollections.observableArrayList("MATING", "WEANING",
                "MAINTAINING", "GESTATING", "NURSING", "SACRIFICED");
        ComboBox statusComboBox=new ComboBox(stautsOptions);

        TextField notesTextField=new TextField();

        Button  clearCageNumberButton=new Button("Clear"),
                clearNotesButton=new Button("Clear"),
                updateButton=new Button("Update Mouse");


        public void setTagNumberTextField(String s){
            tagNumberTextField.setText(s);
        }

        public void setCageNumberField(String s){
            cageNumberTextField.setText(s);
        }

        public void setNotesTextField(String s){
            notesTextField.setText(s);
        }

        public void setStatusComboBox(String statusValue){
            statusComboBox.setValue( statusValue);

        }

        private String validateUserInput(){
            String msg=null;
            if (tagNumberTextField.getText().trim() ==null || tagNumberTextField.getText().trim().isEmpty() ){
                msg="Error Update: the mouse's tag-number is empty.";
                return msg;
            }
            if (cageNumberTextField.getText().trim()==null || cageNumberTextField.getText().trim().isEmpty()){
                msg="Error Update: the mouse's cage-number is empty.";
                return msg;
            }
            if (statusComboBox.getSelectionModel().getSelectedItem()==null){
                msg="Error Update: the mouse's status is null.";
                return msg;
            }
            if (notesTextField.getText().trim()==null || notesTextField.getText().trim().isEmpty()){
                msg="Error Update: the mouse's notes is null.";
                return msg;
            }
            return msg;
        }

        private UpdateModelMouse collectUserInput(){
            UpdateModelMouse updateModelMouse;

            String inputCageNumber=cageNumberTextField.getText().trim();
            String inputStatus= (String) statusComboBox.getSelectionModel().getSelectedItem();
            String inputNotes= notesTextField.getText().trim();

            updateModelMouse=new UpdateModelMouse(inputCageNumber, inputStatus, inputNotes);
            return updateModelMouse;

        }


        UpdateSelectedMouseGridPane() {
            selectedUpdateIconText.setId("selectedUpdateIconText");

            tagNumberTextField.setEditable(false);

            tagNumberTextField.setId("tagNumberUpdateTextField");
            cageNumberTextField.setId("cageNumberUpdateTextField");
            statusComboBox.setId("statusUpdateComboBox");
            notesTextField.setId("notesUpdateTextField");

            this.setAlignment(Pos.CENTER);
            this.setHgap(10);
            this.setVgap(20);
            this.setPadding(new Insets(5,5,15, 5));
            this.setPrefWidth(450);

            tagNumberTextField.setPrefWidth(200);
            cageNumberTextField.setPrefWidth(200);
            statusComboBox.setPrefWidth(200);
            notesTextField.setPrefWidth(200);

            updateButton.setPrefWidth(200);



            this.add(selectedUpdateIconText, 0, 0, 2, 1);

            this.add(tagNumberLabel, 0, 1, 1, 1);
            this.add(tagNumberTextField, 1, 1, 1, 1);

            this.add(cageNumberLabel, 0, 2, 1, 1);
            this.add(cageNumberTextField, 1, 2, 1, 1);
            this.add(clearCageNumberButton, 2, 2, 1, 1);

            this.add(statusLabel, 0, 3, 1, 1);
            this.add(statusComboBox, 1, 3, 1, 1);

            this.add(notesLabel, 0, 4, 1, 1);
            this.add(notesTextField, 1, 4, 1, 1);
            this.add(clearNotesButton, 2, 4, 1, 1);

            this.add(indicatorLabel, 0, 6, 1, 1);
            this.add(updateButton, 1, 6, 1, 1);

            this.getStylesheets().add(MiceVBox.class.getResource("/addMouseStyle.css").toExternalForm());
            this.setStyle("-fx-background-color: #505150;");

            clearCageNumberButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    cageNumberTextField.setText("");
                }
            });

            clearNotesButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    notesTextField.setText("");
                }
            });

            updateButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Mouse selectedMouse= (Mouse) miceTableView.getSelectionModel().getSelectedItem();

                    String errorMsg=validateUserInput();
                    if (errorMsg!=null){
                        AppLaunch.mainFrameBorderPane.processText.setText(errorMsg);
                        AppLaunch.mainFrameBorderPane.getProcessText().setStyle("-fx-fill: red; -fx-font-size: 13pt;");
                        return;
                    }

                    String dataInputErrorMsg=null;
                    UpdateModelMouse updateModelMouse=collectUserInput();
                    if (selectedMouse.getCageNumber().toLowerCase().equals(updateModelMouse.getCageNumber().toLowerCase())
                        && selectedMouse.getStatus().toLowerCase().equals(updateModelMouse.getStatus().toLowerCase())
                        && selectedMouse.getNotes().toLowerCase().equals(updateModelMouse.getNotes().toLowerCase())){
                        dataInputErrorMsg="Error Update: nothing to update.";
                        AppLaunch.mainFrameBorderPane.processText.setText(dataInputErrorMsg);
                        AppLaunch.mainFrameBorderPane.getProcessText().setStyle("-fx-fill: red; -fx-font-size: 13pt;");
                        return;
                    }

                    for (Mouse mouse : miceRecordObservableList){
                        if (mouse.getTagNumber().toLowerCase().equals(selectedMouse.getTagNumber().toLowerCase())){
                            mouse.setCageNumber(updateModelMouse.getCageNumber());
                            mouse.setStatus(updateModelMouse.getStatus());
                            mouse.setNotes(updateModelMouse.getNotes());
                        }
                    }

                    refreshMiceTable();

                    if (!AppLaunch.setUpBreedersStage.cageNumberObservableList.contains(updateModelMouse.getCageNumber())){
                        AppLaunch.setUpBreedersStage.cageNumberObservableList.add(updateModelMouse.getCageNumber());
                        AppLaunch.setUpBreedersStage.refreshCageListView();
                    }

                    AppLaunch.mainFrameBorderPane.getProcessText().setText("Update mouse: "+selectedMouse.getTagNumber()+" successfully.");
                    AppLaunch.mainFrameBorderPane.getProcessText().setStyle("-fx-fill: white; -fx-font-size: 13pt;");


                    AppLaunch.historyStage.sbCounter++;
                    AppLaunch.historyStage.sb.append(AppLaunch.historyStage.sbCounter+", "+"  Update mouse ------"+selectedMouse.getTagNumber()+"------ successfully.\n");
                    String s="";
                    s=s+"       -----------------------------------------------------\n";
                    s=s+"       Tag-Number:         " + selectedMouse.getTagNumber() + "\n";
                    s=s+"       -----------------------------------------------------\n";
                    s=s+"       Cage-Number:      " + updateModelMouse.getCageNumber() + "\n";
                    s=s+"       -----------------------------------------------------\n";
                    s=s+"       Status:                      " + updateModelMouse.getStatus() + "\n";
                    s=s+"       -----------------------------------------------------\n";
                    s=s+"       Notes:                       " + updateModelMouse.getNotes() + "\n";
                    s=s+"       -----------------------------------------------------\n\n";
                    AppLaunch.historyStage.sb.append(s);


                    AppLaunch.historyStage.setHistoryText(AppLaunch.historyStage.sb.toString());

                    /**
                     *  add updated cage and notes information to AddNewMouse's chooseVariousDataTabPane's cagesListview and notesListView
                     */
                    if(!addNewMouseAndUpdateMouseHBox.chooseVariousDataTabPane.
                            cagesListView.cageNumberObservableList.contains(updateModelMouse.getCageNumber())){
                        addNewMouseAndUpdateMouseHBox.chooseVariousDataTabPane.
                                cagesListView.cageNumberObservableList.add(updateModelMouse.getCageNumber());
                        addNewMouseAndUpdateMouseHBox.chooseVariousDataTabPane.
                                cagesListView.refresh();
                    }

                    if(!addNewMouseAndUpdateMouseHBox.chooseVariousDataTabPane.
                            notesListView.notesObservableList.contains(updateModelMouse.getNotes())){
                        addNewMouseAndUpdateMouseHBox.chooseVariousDataTabPane.
                                notesListView.notesObservableList.add(updateModelMouse.getNotes());
                        addNewMouseAndUpdateMouseHBox.chooseVariousDataTabPane.
                                notesListView.refresh();
                    }

                    /**
                     * add updated cage and notes information to CageAndNotesPickerTabPane's cageListView and notesListView
                     */

                    if(!addNewMouseAndUpdateMouseHBox.cageAndNotesPickerTabPane.
                            cagesListView.cageNumberObservableList.contains(updateModelMouse.getCageNumber())){
                        addNewMouseAndUpdateMouseHBox.cageAndNotesPickerTabPane.
                                cagesListView.cageNumberObservableList.add(updateModelMouse.getCageNumber());
                        addNewMouseAndUpdateMouseHBox.cageAndNotesPickerTabPane.
                                cagesListView.refresh();
                    }

                    if(!addNewMouseAndUpdateMouseHBox.cageAndNotesPickerTabPane.notesListView.notesObservableList.contains(updateModelMouse.getNotes())){
                        addNewMouseAndUpdateMouseHBox.cageAndNotesPickerTabPane.notesListView.notesObservableList.add(updateModelMouse.getNotes());
                        addNewMouseAndUpdateMouseHBox.cageAndNotesPickerTabPane.notesListView.refresh();
                    }

                }
            });








        }
    }




}

