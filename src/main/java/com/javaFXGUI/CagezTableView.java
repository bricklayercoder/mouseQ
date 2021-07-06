package com.javaFXGUI;

import com.cagezz.Cage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class CagezTableView extends TableView {

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




        /*
        test code to populate the cagez table
         */
        this.getItems().add(new Cage("cage32", "MAINTAINING", "Test Add", "C57", "4"));

    }




}
