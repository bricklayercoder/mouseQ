package com.javaFXGUI;

import com.mouse.Mouse;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class MiceTableView extends TableView {


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


        /**
         * test code to populate the table
         */
        this.getItems().add(new Mouse("n1345",
                "m3214",
                "f6543",
                "2021-06-08",
                "Male",
                "c57"));

    }

}
