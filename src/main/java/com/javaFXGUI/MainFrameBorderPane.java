package com.javaFXGUI;


import com.mouse.Mouse;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class MainFrameBorderPane extends BorderPane {

    MenuBar menuBar=new MouseQMenuBar();
    MiceVBox miceVBox=new MiceVBox();
    Text processText=new Text("Process will be shown here");

    public MouseQMenuBar getMouseQMenuBar(){
        return (MouseQMenuBar) menuBar;
    }

    public Text getProcessText(){

        return processText;
    }

    public MainFrameBorderPane() {
        processText.setId("processText");
        this.setPadding(new Insets(2, 5, 5, 5));
        this.setTop(menuBar);
        this.setCenter(miceVBox);
        this.setBottom(processText);

    }


}
