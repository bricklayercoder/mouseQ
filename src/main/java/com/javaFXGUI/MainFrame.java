package com.javaFXGUI;

import com.cagezz.Cage;
import com.mouse.Mouse;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Wrapper;

public class MainFrame extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Welcome to mouseQ");
        BorderPane root=new BorderPane();
        root.setPadding(new Insets(2, 5, 5, 5));

        HBox topToolBox= new HBox();

        MenuBar menuBar=new MenuBar();

        Menu mouseQMenu=new Menu("mouseQ");
        Menu fileMenu=new Menu("File");
        Menu mouseMenu=new Menu("Mouse");
        Menu cageMenu=new Menu("Cage");
        Menu recordMenu=new Menu("Record");

        MenuItem aboutMouseQMenuItem=new MenuItem("About");
        MenuItem exitMouseQMenuItem=new MenuItem("Exit");

        MenuItem openFileMenuItem=new MenuItem("Open");
        MenuItem saveFileMenuItem=new MenuItem("Save");

        MenuItem addMouse=new MenuItem("Add");
        MenuItem updateMouse =new MenuItem("Update");

        MenuItem addCage=new MenuItem("Add");
        MenuItem updateCage=new MenuItem("Update");

        MenuItem syncItem=new MenuItem("Sync");
        MenuItem tipsItem=new MenuItem("Tips");

        mouseQMenu.getItems().add(aboutMouseQMenuItem);
        mouseQMenu.getItems().add(exitMouseQMenuItem);

        fileMenu.getItems().add(openFileMenuItem);
        fileMenu.getItems().add(saveFileMenuItem);

        mouseMenu.getItems().add(addMouse);
        mouseMenu.getItems().add(updateMouse);

        cageMenu.getItems().add(addCage);
        cageMenu.getItems().add(updateCage);

        recordMenu.getItems().add(syncItem);
        recordMenu.getItems().add(tipsItem);

        menuBar.getMenus().addAll(mouseQMenu, fileMenu, mouseMenu, cageMenu, recordMenu);
        topToolBox.getChildren().add(menuBar);

        makeTablesSplitPane(root);

        root.setTop(topToolBox);

        makeStageAndActionProcessVBox(root);

        Scene scene=new Scene(root, 1400, 875);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(MainFrame.class.getResource("/stylesheet.css").toExternalForm());
        primaryStage.show();

    }

    private void makeTablesSplitPane(BorderPane root){
        SplitPane tablesSplitPane=new SplitPane();
        tablesSplitPane.setOrientation(Orientation.VERTICAL);
        tablesSplitPane.setPrefHeight(1150);
        tablesSplitPane.setPrefWidth(1055);
        tablesSplitPane.setPadding(new Insets(5, 5, 5, 0.25));
        makeMiceBox(tablesSplitPane);
        /*
        Add a separator
         */
        Separator separator=new Separator();
        tablesSplitPane.getItems().add(separator);

        makeCagezBox(tablesSplitPane);
        root.setCenter(tablesSplitPane);


    }

    private void makeSearchMiceHBox(VBox miceVBox){
        HBox hBox =new HBox();
        Label searchLabel=new Label("search");
        TextField searchField=new TextField();
        hBox.setSpacing(15);
        hBox.setPadding(new Insets(5, 5, 5,5));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(searchLabel, searchField);
        hBox.getStylesheets().add(MainFrame.class.getResource("/stylesheet.css").toExternalForm());
        miceVBox.getChildren().add(hBox);

    }

    private void makeMiceBox(SplitPane tablesSplitPane){
        VBox miceVBox=new VBox();
        miceVBox.setPrefHeight(800);
        miceVBox.setPrefWidth(1400);
        makeSearchMiceHBox(miceVBox);
        makeMiceTableView(miceVBox);

        tablesSplitPane.getItems().add(miceVBox);


    }

    private void makeMiceTableView(VBox miceBox){
        TableView miceTableView=new TableView();

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

        miceTableView.getColumns().add(tagNumberColomn);
        miceTableView.getColumns().add(motherNumberColomn);
        miceTableView.getColumns().add(fatherNumberColomn);
        miceTableView.getColumns().add(genotypeColomn);
        miceTableView.getColumns().add(birthDateColumn);
        miceTableView.getColumns().add(genderColumn);
        miceTableView.getColumns().add(strainColumn);
        miceTableView.getColumns().add(coatColorColumn);
        miceTableView.getColumns().add(weanDateColumn);
        miceTableView.getColumns().add(cageNumberColumn);
        miceTableView.getColumns().add(statusColumn);
        miceTableView.getColumns().add(notesColumn);


        tagNumberColomn.setPrefWidth(110);
        motherNumberColomn.setPrefWidth(110);
        fatherNumberColomn.setPrefWidth(110);
        genotypeColomn.setPrefWidth(110);
        birthDateColumn.setPrefWidth(110);
        genderColumn.setPrefWidth(110);
        strainColumn.setPrefWidth(110);
        coatColorColumn.setPrefWidth(110);
        weanDateColumn.setPrefWidth(110);
        cageNumberColumn.setPrefWidth(110);
        statusColumn.setPrefWidth(110);
        notesColumn.setPrefWidth(200);



        VBox tableContainerVBox=new VBox(miceTableView);
        miceBox.getChildren().add(tableContainerVBox);

        /**
         * test code to populate the table
         */
        miceTableView.getItems().add(new Mouse("n1345",
                                                         "m3214",
                                                          "f6543",
                                                           "2021-06-08",
                                                            "Male",
                                                            "c57"));




    }




    private void makeCagezBox(SplitPane tablesSplitPane){
        VBox cagezVBox=new VBox();
        cagezVBox.setPrefHeight(600);
        cagezVBox.setPrefWidth(1400);
        makeSearchCagezBox(cagezVBox);
        makeCagezTableView(cagezVBox);
        tablesSplitPane.getItems().add(cagezVBox);


    }

    private void makeSearchCagezBox(VBox cagezVbox){
        HBox hBox =new HBox();
        Label searchLabel=new Label("search");
        TextField searchField=new TextField();
        hBox.setSpacing(15);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(searchLabel, searchField);
        cagezVbox.getChildren().add(hBox);
    }


    private void makeCagezTableView(VBox cagezVbox){
        TableView cagezTableView =new TableView();

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

        cagezTableView.getColumns().add(cageNumberColumn);
        cagezTableView.getColumns().add(strainColumn);
        cagezTableView.getColumns().add(statusColumn);
        cagezTableView.getColumns().add(sizeColumn);
        cagezTableView.getColumns().add(notesColumn);

        cageNumberColumn.setPrefWidth(130);
        strainColumn.setPrefWidth(130);
        statusColumn.setPrefWidth(130);
        sizeColumn.setPrefWidth(130);
        notesColumn.setPrefWidth(900);



        VBox cagesTableViewContainerVBox=new VBox(cagezTableView);
        cagezVbox.getChildren().add(cagesTableViewContainerVBox);

        /*
        test code to populate the cagez table
         */
        cagezTableView.getItems().add(new Cage("cage32", "MAINTAINING", "Test Add", "C57", "4"));
    }


    private void makeStageTabPane(VBox stageAndActionIndicatorVbox){

        TabPane tabPane=new TabPane();
        tabPane.setPrefHeight(550);
        tabPane.setPrefWidth(300);
        /*
        make mouse stage tab
         */
        GridPane mouseGrid=new GridPane();
        mouseGrid.setAlignment(Pos.CENTER);
        mouseGrid.setHgap(30); mouseGrid.setVgap(10);
        mouseGrid.setPadding(new Insets(5, 5, 15, 5));

        Text currentText=new Text("Selected");
        mouseGrid.add(currentText, 0, 0, 2, 1);

        Label tagNumberLabel =new Label("Tag-Number:");
        Text  tagNumberText=new Text();
        mouseGrid.add(tagNumberLabel, 0, 1);
        mouseGrid.add(tagNumberText, 1, 1);

        Label motherTagLabel=new Label("Mother:");
        Text  motherTagText =new Text();
        mouseGrid.add(motherTagLabel, 0, 2);
        mouseGrid.add(motherTagText, 1, 2);

        Label fatherTagLabel=new Label("Father:");
        Text fatherTagText=new Text();
        mouseGrid.add(fatherTagLabel, 0, 3);
        mouseGrid.add(fatherTagText, 1, 3);

        Label genotypeLabel=new Label("Genotype:");
        Text genotypeText=new Text();
        mouseGrid.add(genotypeLabel, 0, 4);
        mouseGrid.add(genotypeText, 1, 4);

        Label birthDateLabel=new Label("Birth Date:");
        Text birthDateText=new Text();
        mouseGrid.add(birthDateLabel, 0, 5);
        mouseGrid.add(birthDateText, 1, 5);

        Label genderLabel=new Label("Gender:");
        Text genderText=new Text();
        mouseGrid.add(genderLabel, 0, 6);
        mouseGrid.add(genderText, 1, 6);

        Label strainLabel=new Label("Strain:");
        Text strainText=new Text();
        mouseGrid.add(strainLabel, 0, 7);
        mouseGrid.add(strainText, 1, 7);

        Label coatColorLabel=new Label("Coat Color:");
        Text coatColorText=new Text();
        /*
        test code
         */
        coatColorText.setText("This is a test code");

        mouseGrid.add(coatColorLabel, 0, 8);
        mouseGrid.add(coatColorText, 1, 8);

        Label weanDateLabel=new Label("Wean Date:");
        Text weanDateText=new Text();
        mouseGrid.add(weanDateLabel, 0, 9);
        mouseGrid.add(weanDateText, 1, 9);

        Label cageNumberLabel=new Label("Cage Number:");
        Text cageNumberText=new Text();
        mouseGrid.add(cageNumberLabel, 0, 10);

        Label statusLabel=new Label("Status:");
        Text statusText=new Text();
        mouseGrid.add(statusLabel, 0, 11);
        mouseGrid.add(statusText, 1, 11);

        Label notesLabel=new Label("Notes");
        Text notesText=new Text();
        mouseGrid.add(notesLabel, 0, 12);
        mouseGrid.add(notesText, 1, 12);

        Button updateButton =new Button("_Update");
        HBox btnHBox=new HBox();
        btnHBox.setSpacing(10);
        btnHBox.setAlignment(Pos.BOTTOM_RIGHT);
        btnHBox.getChildren().add(updateButton);
        mouseGrid.add(btnHBox, 1, 14);

        Tab mouseTab=new Tab("Mouse", mouseGrid);
        mouseTab.setStyle("-fx-background-color: #505150");
        tabPane.getTabs().add(mouseTab);

        /**
         * make and add cage tab
         */
        GridPane cageGrid=new GridPane();
        cageGrid.setAlignment(Pos.CENTER);
        cageGrid.setHgap(30); cageGrid.setVgap(10);
        cageGrid.setPadding(new Insets(5, 5, 15, 5));

        Text selectedText=new Text("Selected");
        cageGrid.add(selectedText, 0, 0, 2, 1);

        Label cageNUmberLabelCageTab=new Label("Cage Number:");
        Text cageNumberTextCageTab=new Text();
        cageGrid.add(cageNUmberLabelCageTab, 0, 1);
        cageGrid.add(cageNumberTextCageTab, 1, 1);

        Label strainLabelCageTab=new Label("Strain:");
        Text strainTextCageTab=new Text();
        cageGrid.add(strainLabelCageTab, 0, 2);
        cageGrid.add(strainTextCageTab, 1, 2);

        Label statusLabelCageTab=new Label("Status:");
        Text statusTextCageTab=new Text();
        cageGrid.add(statusLabelCageTab, 0, 3);
        cageGrid.add(statusTextCageTab, 1, 3);

        Label sizeLabelCageTab=new Label("Size:");
        Text sizeTextCageTab=new Text();
        cageGrid.add(sizeLabelCageTab, 0, 4);
        cageGrid.add(sizeTextCageTab, 1, 4);

        Label notesLabelCageTab=new Label("Notes:");
        Text notesTextCageTab=new Text();
        cageGrid.add(notesLabelCageTab, 0, 5);
        cageGrid.add(notesTextCageTab, 1, 5);

        Button updateCageButton=new Button("Update");
        HBox updateCageBtnContainer=new HBox();
        updateCageBtnContainer.setSpacing(10);
        updateCageBtnContainer.setAlignment(Pos.BOTTOM_RIGHT);
        cageGrid.add(updateCageButton, 1, 7);

        Tab cageTab =new Tab("Cage", cageGrid);
        cageTab.setStyle("-fx-background-color: #505150");
        tabPane.getTabs().add(cageTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);



        stageAndActionIndicatorVbox.getChildren().add(tabPane);



    }

    private void makeStageAndActionProcessVBox(BorderPane root){
        VBox stageAndActionProcessVbox=new VBox();
        stageAndActionProcessVbox.setPrefWidth(300);
        makeStageTabPane(stageAndActionProcessVbox);
        makeActionProcessIndicator(stageAndActionProcessVbox);


        root.setRight(stageAndActionProcessVbox);
    }

    private void makeActionProcessIndicator(VBox stageAndActionProcessVBox){
        TextArea textArea=new TextArea();
        VBox wrapperVBox=new VBox();
        wrapperVBox.setSpacing(2);

        Text processText=new Text("Process");


        Region space= new Region();
        VBox.setVgrow(space, Priority.ALWAYS);



        Text copyrightInfoText=new Text("@copyrights\nmouseQ is currently under development.\n" +
                "All rights reserved.\n"+
                "For more information, \n" +
                "Please contact Biolyric at 41186531@qq.com.\n"+
                "For user guide,\n" +
                "Click the Record menu, then choose Tips option.\n" +
                "Commercial distribution legally prohibited.");
        copyrightInfoText.setId("copyright-text");

        wrapperVBox.getChildren().addAll(processText,space, textArea, copyrightInfoText);

        stageAndActionProcessVBox.getChildren().add(wrapperVBox);
    }










}
