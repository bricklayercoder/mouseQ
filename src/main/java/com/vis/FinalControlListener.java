package com.vis;

import prefuse.controls.Control;
import prefuse.controls.ControlAdapter;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class FinalControlListener extends ControlAdapter
        implements Control {


    /**
     *
     * String queryNodes="select Id, Tag_Number, HostCage, Gender, Genotype, Status, " +
     " strain, Father_TagNum, Mother_TagNum, " +
     " datediff(curdate(), birth_date) as Age_In_Days, Coat_Colour," +
     " numberOflitters , " +
     " Modified_On,  Notes from mouse " ;

     *
     */

    Color oddRowsBackGround=new Color(0xECFFE4);

    Color evenRowsBackGround=Color.WHITE;



    @Override
    public void itemClicked(VisualItem item, MouseEvent e) {

        if(item instanceof NodeItem){

            JPopupMenu jpub=new JPopupMenu();
/**
            String tagNumber= (String) item.get("Tag_Number");
            JMenuItem tagItem=new       JMenuItem("Tag:             "+tagNumber);
            tagItem.setBackground(oddRowsBackGround);
            JMenuItem genderItem=new    JMenuItem("Gender:       "+gender);
            genderItem.setBackground(evenRowsBackGround);
 */
  //          jpub.add(notesItem);

            String tagNumber=(String) item.get("Tag-Number");
            String mother= (String) item.get("Mother");
            String father= (String) item.get("Father");
            String genotype=(String) item.get("Genotype");
            String birthDate= (String) item.get("Birth-Date");
            String gender = (String) item.get("Gender");
            String strain= (String) item.get("Strain");
            String coatColor=(String) item.get("Coat-Color");
            String weanDate= (String) item.get("Wean-Date");
            String cageNumber= (String) item.get("Cage-Number");
            String status=(String) item.get("Status");
            String notes = (String) item.get("Notes");

            JMenuItem tagMI = new JMenuItem("Tag-Number:       "+ tagNumber);
            JMenuItem motherMI = new JMenuItem("Mother:                 " + mother);
            JMenuItem fatherMI = new JMenuItem("Father:                  "+ father);
            JMenuItem genotypeMI = new JMenuItem("Genotype:             " + genotype);
            JMenuItem birthDateMI = new JMenuItem("Birth-Date:           "+ birthDate);
            JMenuItem genderMI = new JMenuItem("Gender:                " + gender);
            JMenuItem strainMI = new JMenuItem("Strain:                  " + strain);
            JMenuItem coatColorMI = new JMenuItem("Coat-Color:         " + coatColor);
            JMenuItem weanDateMI = new JMenuItem("Wean-Date:         " + weanDate);
            JMenuItem cageNumberMI= new JMenuItem("Cage-Number:    " + cageNumber);
            JMenuItem statusMI = new JMenuItem("Status:                  " + status);
            JMenuItem notesMI = new JMenuItem("Notes:                   " + notes);

            tagMI.setBackground(oddRowsBackGround);
            motherMI.setBackground(evenRowsBackGround);
            fatherMI.setBackground(oddRowsBackGround);
            genotypeMI.setBackground(evenRowsBackGround);
            birthDateMI.setBackground(oddRowsBackGround);
            genderMI.setBackground(evenRowsBackGround);
            strainMI.setBackground(oddRowsBackGround);
            coatColorMI.setBackground(evenRowsBackGround);
            weanDateMI.setBackground(oddRowsBackGround);
            cageNumberMI.setBackground(evenRowsBackGround);
            statusMI.setBackground(oddRowsBackGround);
            notesMI.setBackground(evenRowsBackGround);

            jpub.add(tagMI); jpub.add(motherMI); jpub.add(fatherMI); jpub.add(genotypeMI);
            jpub.add(birthDateMI); jpub.add(genderMI); jpub.add(strainMI); jpub.add(coatColorMI);
            jpub.add(weanDateMI); jpub.add(cageNumberMI); jpub.add(statusMI); jpub.add(notesMI);

            jpub.show(e.getComponent(), (int)item.getX(), (int) item.getY());
        }


    }
}

