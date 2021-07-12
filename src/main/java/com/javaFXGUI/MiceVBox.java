package com.javaFXGUI;

import com.cagezz.CageZZ;
import com.mouse.Mouse;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Predicate;


public class MiceVBox extends VBox {

    TextField searchField=new TextField();

    ObservableList<Mouse> miceRecordList;
    FilteredList<Mouse> filteredListOfMice;
    SortedList<Mouse> sortedListofMice;

    HBox searchMiceHBox=new SearchMiceHBox();
    TableView miceTableView=new MiceTableView();


    public MiceVBox() {
        this.getChildren().addAll(searchMiceHBox, miceTableView);
    }


    class SearchMiceHBox extends HBox {

        Label searchLabel=new Label("Search");


        public SearchMiceHBox() {
            this.setSpacing(15);
            this.setPadding(new Insets(5, 5, 5,5));
            this.setAlignment(Pos.CENTER);
            this.getChildren().addAll(searchLabel, searchField);
            this.getStylesheets().add(MainFrame.class.getResource("/stylesheet.css").toExternalForm());


        }
    }



    class MiceTableView extends TableView {



        public void  loadMiceRecordList(){
            miceRecordList= FXCollections.observableArrayList();
            CageZZ cageZZ=new CageZZ();
            cageZZ.loadMiceRecords();
            for (Mouse mouse : cageZZ.getMiceList()){
                miceRecordList.add(mouse);
            }
        }

        public void loadFilteredListOfMice(){
            filteredListOfMice=new FilteredList<>(miceRecordList, p -> true);

            searchField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent e) {
                    searchField.textProperty().addListener((observable, oldValue, newValue) -> {

                        filteredListOfMice.setPredicate( (Predicate<? super Mouse>) mouse -> {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }

                            // Compare each mouse attribute
                            String lowerCaseFilter = newValue.toLowerCase();

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
            notesColumn.setPrefWidth(400);

            loadMiceRecordList();
            loadFilteredListOfMice();
            sortedListofMice=new SortedList<>(filteredListOfMice);
            sortedListofMice.comparatorProperty().bind(this.comparatorProperty());
            this.setItems(sortedListofMice);


        }

    }





}

