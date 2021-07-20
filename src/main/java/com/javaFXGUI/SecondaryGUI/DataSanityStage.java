package com.javaFXGUI.SecondaryGUI;

import com.cagezz.Cage;
import com.cagezz.CageZZ;
import com.javaFXGUI.AppLaunch;
import com.models.CageSanityModel;
import com.mouse.DataSanity;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class DataSanityStage extends Stage {
    CageZZ cageZZ=new CageZZ();
    HashMap<String, Cage> cageZZMapInMainTable;
    HashMap<String, DataSanity[]> dataSanityHashMap;

    ObservableList<CageSanityModel> observableListOfCageSanities= FXCollections.observableArrayList();
    FilteredList<CageSanityModel>   filteredListOfCagesSanities;
    SortedList<CageSanityModel>     sortedListOfCageSanities;


    Label searchLabel=new Label("Search:");
    TextField searchTextField=new TextField();
    HBox searchHBox=new HBox();
    BorderPane containerBorderPane =new BorderPane();

    SanitiesTableView sanitiesTableView=new SanitiesTableView();


    public DataSanityStage() {


/**
        sb.append("      --------------------- ---------------------\n");
        sb.append("           Cage-Number        |        Sanity         \n");
        sb.append("      --------------------- ---------------------\n");
        for (Map.Entry<String, DataSanity[]> dataSanityEntry : dataSanityHashMap.entrySet()){
            String cageNumber= Utilities.rightPadWhiteSpaces(dataSanityEntry.getKey(), 20);
            DataSanity[] dataSanities=dataSanityEntry.getValue();
            for (int i=0; i< 2; i++){
                if (dataSanities[i] !=null ){
                    System.out.println(dataSanities[i].toString());
                    String sanityStatus=Utilities.rightPadWhiteSpaces(dataSanities[i].toString(), 20);
                    sb.append("         "+cageNumber  + sanityStatus + "\n");
                    sb.append("      --------------------- ---------------------\n");

                }
            }
        }
        sanityContent.setText(sb.toString());
        containerPane.setContent(sanityContent);
*/


        searchHBox.setSpacing(15);
        searchHBox.setPadding(new Insets(5, 5, 5,5));
        searchHBox.setAlignment(Pos.CENTER);
        searchTextField.setId("searchTextField");
        searchHBox.getChildren().add(searchLabel);
        searchHBox.getChildren().add(searchTextField);

        containerBorderPane.setTop(searchHBox);
        containerBorderPane.setCenter(sanitiesTableView);
        containerBorderPane.setPrefWidth(400);
        containerBorderPane.setPrefHeight(500);
        containerBorderPane.getStylesheets().add(DataSanityStage.class.getResource("/CageSanityStage.css").toExternalForm());
        this.setResizable(false);

        Scene scene=new Scene(containerBorderPane);
        this.setScene(scene);
        this.setAlwaysOnTop(true);

    }

    class SanitiesTableView extends TableView{


        public SanitiesTableView() {
            ArrayList<Mouse> mouseArrayList=new ArrayList<>();

            for (Mouse mouse : AppLaunch.mainFrameBorderPane.miceVBox.getObservableListOfMouse()){
                mouseArrayList.add(mouse);
            }
            cageZZMapInMainTable =cageZZ.getCageZZ(mouseArrayList);
            dataSanityHashMap=cageZZ.getCageDataSanities(cageZZMapInMainTable);

            for (Map.Entry<String, DataSanity[]> dataSanityEntry : dataSanityHashMap.entrySet()){
                DataSanity[] thisCagesDataSanities=dataSanityEntry.getValue();
                for(int i=0; i<2; i++){
                    if(thisCagesDataSanities[i] !=null){
                        CageSanityModel cageSanityModel=new CageSanityModel(dataSanityEntry.getKey(),
                                                                            thisCagesDataSanities[i].toString());
                        observableListOfCageSanities.add(cageSanityModel);
                    }
                }
            }

            filteredListOfCagesSanities=new FilteredList<>(observableListOfCageSanities, p->true);

            searchTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    searchTextField.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            filteredListOfCagesSanities.setPredicate(new Predicate<CageSanityModel>() {
                                @Override
                                public boolean test(CageSanityModel cageSanityModel) {

                                    if (newValue == null || newValue.isEmpty()) {
                                        return true;
                                    }

                                    // Compare each mouse attribute
                                    String lowerCaseFilter = newValue.toLowerCase().trim();

                                    if (cageSanityModel.getCageNumber().toLowerCase().contains(lowerCaseFilter)) {
                                        return true;
                                    } else if (cageSanityModel.getDataSanityString().toLowerCase().contains(lowerCaseFilter)) {
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

            sortedListOfCageSanities=new SortedList<>(filteredListOfCagesSanities);

            TableColumn cageNumberColumn=new TableColumn("Cage-Number");
            cageNumberColumn.setCellValueFactory(new PropertyValueFactory<>("cageNumber"));
            cageNumberColumn.setPrefWidth(200);


            TableColumn dataSanityStringColumn=new TableColumn("Sanity-Status");
            dataSanityStringColumn.setCellValueFactory(new PropertyValueFactory<>("dataSanityString"));
            dataSanityStringColumn.setPrefWidth(200);

            this.getColumns().add(cageNumberColumn);
            this.getColumns().add(dataSanityStringColumn);
            this.setItems(sortedListOfCageSanities);
        }
    }

    public void reloadSanitiesTableView(){
        ArrayList<Mouse> mouseArrayList=new ArrayList<>();

        for (Mouse mouse : AppLaunch.mainFrameBorderPane.miceVBox.getObservableListOfMouse()){
            mouseArrayList.add(mouse);
        }
        cageZZMapInMainTable =cageZZ.getCageZZ(mouseArrayList);
        dataSanityHashMap=cageZZ.getCageDataSanities(cageZZMapInMainTable);

        observableListOfCageSanities.removeAll(observableListOfCageSanities);
        System.out.println(observableListOfCageSanities);


        for (Map.Entry<String, DataSanity[]> dataSanityEntry : dataSanityHashMap.entrySet()){
            DataSanity[] thisCagesDataSanities=dataSanityEntry.getValue();
            for(int i=0; i<2; i++){
                if(thisCagesDataSanities[i] !=null){
                    CageSanityModel cageSanityModel=new CageSanityModel(dataSanityEntry.getKey(),
                            thisCagesDataSanities[i].toString());
                    observableListOfCageSanities.add(cageSanityModel);
                }
            }
        }


        sanitiesTableView.refresh();
    }
}
