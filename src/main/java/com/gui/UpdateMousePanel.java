package com.gui;

import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;

public class UpdateMousePanel extends JPanel {

    JLabel weanDateLabel=new JLabel("Wean Date"),
           genotypeLabel=new JLabel("Genotype"),
           statusLabel=new JLabel("Status"),
           hostCageLabel=new JLabel("Host Cage"),
           notesLabel=new JLabel("Notes");

    MyDatePicker myDatePicker=new MyDatePicker(); JDatePickerImpl weanDatePicker=myDatePicker.getMyDatePicker();
    JTextField genotypeTxt=new JTextField(20);
           String[] statusString={"Mating","Weaning", "Maintaining", "Gestating", "Nursing", "Sacrificed" };
    JComboBox statusChooser=new JComboBox(statusString);
    JTextField hostCageTxt=new JTextField(20);
    JTextField notesTxt=new JTextField(20);


    JButton clearWeanDateButton =new JButton("Clear");
    JButton clearGenotypeButton=new JButton("Clear");
    JButton clearHostCageButton=new JButton("Clear");
    JButton clearNotesButton=new JButton("clear");

    JButton submitButton=new JButton("Submit");

    public UpdateMousePanel() {
        setPreferredSize(new Dimension(250, 375));
        this.add(weanDateLabel); this.add(weanDatePicker); this.add(clearWeanDateButton);
        this.add(statusLabel);   this.add(statusChooser);
        this.add(genotypeLabel); this.add(genotypeTxt);    this.add(clearGenotypeButton);
        this.add(hostCageLabel); this.add(hostCageTxt);    this.add(clearHostCageButton);
        this.add(notesLabel);    this.add(notesTxt);     this.add(clearNotesButton);
        this.add(submitButton);

        this.setPreferredSize(new Dimension(480, 375));
        this.makePanel();
        this.setOpaque(true);
    }

    public void makePanel(){
        SpringLayout layout=new SpringLayout();
        setLayout(layout);

        int xPad=10; int yPad=5;  int max=15;
        Spring xPadSpring=Spring.constant(xPad);
        Spring yPadSpring=Spring.constant(yPad);

        Spring maxWidthSpring = layout.getConstraints(this.getComponent(0)).
                getWidth();
        Spring maxHeightSpring = layout.getConstraints(this.getComponent(0)).
                getHeight();

        for (int i = 0; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                    this.getComponent(i));

            maxWidthSpring = Spring.max(maxWidthSpring, cons.getWidth());
            maxHeightSpring = Spring.max(maxHeightSpring, cons.getHeight());
        }
        for (int i = 0; i < max; i++) {
            SpringLayout.Constraints cons = layout.getConstraints(
                    this.getComponent(i));

            cons.setWidth(maxWidthSpring);
            cons.setHeight(maxHeightSpring);
        }



        /*
        set the first row of wean date fields in position
         */
        SpringLayout.Constraints weanDateLabelCons=layout.getConstraints(weanDateLabel);
        weanDateLabelCons.setX(xPadSpring);
        weanDateLabelCons.setY(yPadSpring);

        SpringLayout.Constraints weanDatePickerCons=layout.getConstraints(weanDatePicker);
        weanDatePickerCons.setX(Spring.sum(xPadSpring, weanDateLabelCons.getConstraint("East")));
        weanDatePickerCons.setY(yPadSpring);

        SpringLayout.Constraints clearWeanDateButtonCons=layout.getConstraints(clearWeanDateButton);
        clearWeanDateButtonCons.setX(Spring.sum(xPadSpring, weanDatePickerCons.getConstraint("East")));
        clearWeanDateButtonCons.setY(yPadSpring);

        /*
        set the 2nd row of status fields in position
         */
        SpringLayout.Constraints statusLabelCons=layout.getConstraints(statusLabel);
        statusLabelCons.setX(xPadSpring);
        statusLabelCons.setY(Spring.sum(yPadSpring, weanDateLabelCons.getConstraint("South")));

        SpringLayout.Constraints statusChooseCons=layout.getConstraints(statusChooser);
        statusChooseCons.setX(Spring.sum(xPadSpring, statusLabelCons.getConstraint("East")));
        statusChooseCons.setY(Spring.sum(yPadSpring, weanDatePickerCons.getConstraint("South")));

        /*
        set the 3rd row of genotype fields in position
         */
        SpringLayout.Constraints genotypeLabelCons=layout.getConstraints(genotypeLabel);
        genotypeLabelCons.setX(xPadSpring);
        genotypeLabelCons.setY(Spring.sum(yPadSpring, statusLabelCons.getConstraint("South")));

        SpringLayout.Constraints genotypeTxtCons=layout.getConstraints(genotypeTxt);
        genotypeTxtCons.setX(Spring.sum(xPadSpring, genotypeLabelCons.getConstraint("East")));
        genotypeTxtCons.setY(Spring.sum(yPadSpring, statusChooseCons.getConstraint("South")));

        SpringLayout.Constraints clearGenotypeButtonCons=layout.getConstraints(clearGenotypeButton);
        clearGenotypeButtonCons.setX(Spring.sum(xPadSpring, genotypeTxtCons.getConstraint("East")));
        clearGenotypeButtonCons.setY(Spring.sum(yPadSpring, statusChooseCons.getConstraint("South")));

        /*
        set the 4th row of host cage fields in position
         */
        SpringLayout.Constraints hostCageLabelCons=layout.getConstraints(hostCageLabel);
        hostCageLabelCons.setX(xPadSpring);
        hostCageLabelCons.setY(Spring.sum(yPadSpring, genotypeLabelCons.getConstraint("South")));

        SpringLayout.Constraints hostCageTxtCons=layout.getConstraints(hostCageTxt);
        hostCageTxtCons.setX(Spring.sum(xPadSpring, hostCageLabelCons.getConstraint("East")));
        hostCageTxtCons.setY(Spring.sum(yPadSpring, genotypeTxtCons.getConstraint("South")));

        SpringLayout.Constraints clearHostCageButtonCons=layout.getConstraints(clearHostCageButton);
        clearHostCageButtonCons.setX(Spring.sum(xPadSpring, hostCageTxtCons.getConstraint("East")));
        clearHostCageButtonCons.setY(Spring.sum(yPadSpring, clearGenotypeButtonCons.getConstraint("South")));

        /*
        set the 5th row of notes fields in position
         */
        SpringLayout.Constraints notesLabelCons=layout.getConstraints(notesLabel);
        notesLabelCons.setX(xPadSpring);
        notesLabelCons.setY(Spring.sum(yPadSpring, hostCageLabelCons.getConstraint("South")));

        SpringLayout.Constraints notesTxtCons=layout.getConstraints(notesTxt);
        notesTxtCons.setX(Spring.sum(xPadSpring, notesLabelCons.getConstraint("East")));
        notesTxtCons.setY(Spring.sum(yPadSpring, hostCageTxtCons.getConstraint("South")));

        SpringLayout.Constraints clearNotesButtonCons=layout.getConstraints(clearNotesButton);
        clearNotesButtonCons.setX(Spring.sum(xPadSpring, notesTxtCons.getConstraint("East")));
        clearNotesButtonCons.setY(Spring.sum(yPadSpring, clearHostCageButtonCons.getConstraint("South")));

        /*
        set the submit button in position
         */
        SpringLayout.Constraints submitButtonCons=layout.getConstraints(submitButton);
        submitButtonCons.setX(Spring.sum(xPadSpring, notesLabelCons.getConstraint("East")));
        submitButtonCons.setY(Spring.sum(yPadSpring, notesTxtCons.getConstraint("South")));

        SpringLayout.Constraints panelCons=layout.getConstraints(this);
        panelCons.setConstraint("East", Spring.sum(xPadSpring, clearWeanDateButtonCons.getConstraint("East")));
        panelCons.setConstraint("South", Spring.sum(yPadSpring, submitButtonCons.getConstraint("South")));

    }



    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame=new JFrame("update mouse");
                UpdateMousePanel updateMousePanel =new UpdateMousePanel();
                frame.add(updateMousePanel);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }


}
