package com.javaFXGUI;

import com.cagezz.Cage;
import com.cagezz.CageZZ;
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

import java.util.Map;
import java.util.function.Predicate;

public class CagezVBox extends VBox {

    TextField searchField=new TextField();

    ObservableList<Cage> observableListOfCage;
    FilteredList<Cage> filteredListOfCage;
    SortedList<Cage>    sortedListOfCage;

    HBox searchCagezVBox=new SearchCagezHBox();
    TableView cagezTableView=new CagezTableView();

    public CagezVBox() {
        this.getChildren().addAll(searchCagezVBox, cagezTableView);
    }



    class SearchCagezHBox extends HBox {
        Label searchLabel=new Label("Search");


        public SearchCagezHBox() {
            this.setSpacing(15);
            this.setPadding(new Insets(5, 5, 5, 5));
            this.setAlignment(Pos.CENTER);
            this.getChildren().addAll(searchLabel, searchField);
        }

    }


    class CagezTableView extends TableView {

        public void loadCagezRecordList(){
            observableListOfCage= FXCollections.observableArrayList();
            CageZZ cageZZ=new CageZZ();
            cageZZ.loadMiceRecords();
            for (Map.Entry<String, Cage> entry : cageZZ.getCageZZ().entrySet()){
                observableListOfCage.add(entry.getValue());
            }
        }

        public void loadFilteredListOfCage(){
            filteredListOfCage=new FilteredList<>(observableListOfCage, c -> {
                return true;
            });

            searchField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    searchField.textProperty().addListener((observable, oldValue, newValue)->{
                        filteredListOfCage.setPredicate((Predicate<? super Cage>) cage -> {
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }
                            String lowerCaseFilter=newValue.toLowerCase().trim();
                            if (cage.getCageNumber().toLowerCase().contains(lowerCaseFilter)){
                                return true;
                            } else if(cage.getStatus().toLowerCase().contains(lowerCaseFilter)){
                                return true;
                            }else if(cage.getStrain().toLowerCase().contains(lowerCaseFilter)){
                                return true;
                            }else if(cage.getStrain().toLowerCase().contains(lowerCaseFilter)){
                                return true;
                            }else if(cage.getNotes().toLowerCase().contains(lowerCaseFilter)){
                                return true;
                            }else if(cage.getSize().toLowerCase().contains(lowerCaseFilter)){
                                return true;
                            }
                            return false;
                        });
                    });
                }
            });
        }



        public CagezTableView() {
            TableColumn<Cage, String> cageNumberColumn=new TableColumn<>("Cage-Number");
            cageNumberColumn.setCellValueFactory(new PropertyValueFactory<>("cageNumber"));

            TableColumn<Cage, String> statusColumn=new TableColumn<>("Status");
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

            TableColumn<Cage, String> notesColumn=new TableColumn<>("Notes");
            notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

            TableColumn<Cage, String> strainColumn=new TableColumn<>("Strain");
            strainColumn.setCellValueFactory(new PropertyValueFactory<>("strain"));

            TableColumn<Cage, String> sizeColumn=new TableColumn<>("Size");
            sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));

            this.getColumns().add(cageNumberColumn);
            this.getColumns().add(strainColumn);
            this.getColumns().add(statusColumn);
            this.getColumns().add(sizeColumn);
            this.getColumns().add(notesColumn);

            cageNumberColumn.setPrefWidth(165);
            strainColumn.setPrefWidth(165);
            statusColumn.setPrefWidth(165);
            sizeColumn.setPrefWidth(165);
            notesColumn.setPrefWidth(750);

            loadCagezRecordList();
            loadFilteredListOfCage();
            sortedListOfCage=new SortedList<>(filteredListOfCage);
            sortedListOfCage.comparatorProperty().bind(this.comparatorProperty());
            this.setItems(sortedListOfCage);


        }




    }


}
