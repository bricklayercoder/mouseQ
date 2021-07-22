package com.javaFXGUI.SecondaryGUI;

import com.cagezz.ReadMiceTables;
import com.javaFXGUI.AppLaunch;
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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class OpenMiceTableStage extends Stage {
        Label searchLabel=new Label("Search:");
        TextField searchTextField=new TextField();
        HBox searchHBox=new HBox();
        MiceTableView miceTableView;
        BorderPane containerBorderPane =new BorderPane();

        File miceTableFile;
        ObservableList<Mouse> miceRecordObservableList;
        FilteredList<Mouse> filteredListOfMice;
        SortedList<Mouse>   sortedListofMice;

        class MiceTableView extends TableView {

                public void loadFilteredListOfMice() throws IOException {
                        ArrayList<String> miceStringList= ReadMiceTables.readMiceTable(miceTableFile);
                        ArrayList<Mouse>  arrayListOfMouse=ReadMiceTables.generateMiceArrayList(miceStringList);

                        miceRecordObservableList=FXCollections.observableArrayList();
                        for(Mouse mouse : arrayListOfMouse){
                               miceRecordObservableList.add(mouse);
                        }
                        filteredListOfMice=new FilteredList<>(miceRecordObservableList, p -> true);
                        searchTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                                @Override
                                public void handle(KeyEvent e) {
                                        searchTextField.textProperty().addListener(new ChangeListener<String>() {
                                                @Override
                                                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                                                        filteredListOfMice.setPredicate(new Predicate<Mouse>() {
                                                                @Override
                                                                public boolean test(Mouse mouse) {
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
                                                                }
                                                        });
                                                }
                                        });

                                }
                        });

                }


                public MiceTableView () throws IOException {

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


                        tagNumberColomn.setPrefWidth(120);
                        motherNumberColomn.setPrefWidth(120);
                        fatherNumberColomn.setPrefWidth(120);
                        genotypeColomn.setPrefWidth(120);
                        birthDateColumn.setPrefWidth(90);
                        genderColumn.setPrefWidth(90);
                        strainColumn.setPrefWidth(90);
                        coatColorColumn.setPrefWidth(90);
                        weanDateColumn.setPrefWidth(90);
                        cageNumberColumn.setPrefWidth(120);
                        statusColumn.setPrefWidth(90);
                        notesColumn.setPrefWidth(325);

                        loadFilteredListOfMice();
                        sortedListofMice=new SortedList<>(filteredListOfMice);
                        sortedListofMice.comparatorProperty().bind(this.comparatorProperty());
                        this.setItems(sortedListofMice);


                }

        }

        public OpenMiceTableStage (File miceTableFile) throws IOException {

                searchTextField.setId("searchTextField");
                this.miceTableFile=miceTableFile;
                searchHBox.setSpacing(15);
                searchHBox.setPadding(new Insets(5, 5, 5,5));
                searchHBox.setAlignment(Pos.CENTER);
                searchHBox.getChildren().addAll(searchLabel, searchTextField);

                containerBorderPane.setTop(searchHBox);
                miceTableView=new MiceTableView();
                containerBorderPane.setCenter(miceTableView);
                containerBorderPane.setPrefWidth(1475);
                containerBorderPane.setPrefHeight(750);
                containerBorderPane.getStylesheets().add(OpenMiceTableStage.class.getResource("/openMiceTable.css").toExternalForm());
                this.setResizable(false);

                Scene scene= new Scene(containerBorderPane);
                this.setScene(scene);
                this.setTitle(miceTableFile.getName());

                this.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                                AppLaunch.mainFrameBorderPane.getProcessText().setText(miceTableFile.getName()+" closed!");
                                AppLaunch.mainFrameBorderPane.getProcessText().setStyle("-fx-fill: white; -fx-font-size: 13pt;");
                        }
                });

                this.setAlwaysOnTop(true);
        }

}
