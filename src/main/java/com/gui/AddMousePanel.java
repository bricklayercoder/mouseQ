package com.gui;

import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;

public class AddMousePanel extends JPanel {
    JLabel tagLabel=new JLabel("Tag Number:"),
            genderLabel=new JLabel("Gender:"),
            fatherLabel=new JLabel("Father:"),
            motherLabel=new JLabel("Mother:"),
            dobLabel=new JLabel("Birth Date:"),
            cageLabel=new JLabel("Host Cage:"),
            strainLabel=new JLabel("Strain:"),
            coatColorLabel=new JLabel("Coat Color:"),
            genotypeLabel=new JLabel("Genotype:");


    JTextField tagField=new JTextField(20);
    String[] genders={"Male", "Female"};
    JComboBox genderBox=new JComboBox(genders);
    JTextField fatherField=new JTextField(20);
    JTextField motherField=new JTextField(20);
    MyDatePicker myDatePicker=new MyDatePicker(); JDatePickerImpl dobPicker=myDatePicker.getMyDatePicker();
    JTextField cageField=new JTextField(20);
    JTextField strainField=new JTextField(20);
    JTextField coatColorField=new JTextField(20);
    JTextField genotypeField=new JTextField(20);

    JButton clearTagBtn=new JButton("Clear"),
            pickFatherBtn=new JButton("Pick"),
            pickMotherBtn=new JButton("Pick"),
            clearDOBBtn=new JButton("Clear"),
            clearCageBtn =new JButton("Clear"),
            clearStrainBtn=new JButton("Clear"),
            clearCoatColorBtn=new JButton("Clear"),
            clearGenotypeBtn=new JButton("Clear");

    JButton submitBtn=new JButton("Submit");

    public AddMousePanel() {

        this.add(tagLabel); this.add(tagField); this.add(clearTagBtn);
        this.add(genderLabel); this.add(genderBox);
        this.add(fatherLabel); this.add(fatherField); this.add(pickFatherBtn);
        this.add(motherLabel); this.add(motherField); this.add(pickMotherBtn);
        this.add(dobLabel); this.add(dobPicker); this.add(clearDOBBtn);
        this.add(cageLabel); this.add(cageField); this.add(clearCageBtn);
        this.add(strainLabel); this.add(strainField); this.add(clearStrainBtn);
        this.add(coatColorLabel); this.add(coatColorField); this.add(clearCoatColorBtn);
        this.add(genotypeLabel); this.add(genotypeField); this.add(clearGenotypeBtn);
        this.add(submitBtn);
        setPreferredSize(new Dimension(250, 375));
        makePanel();
        setOpaque(true);


    }
    private void makePanel(){
        SpringLayout layout=new SpringLayout();
        setLayout(layout);
        int xPad=10; int yPad=5;  int max=27;
        Spring xPadSpring=Spring.constant(xPad);
        Spring yPadSpring=Spring.constant(yPad);

        Spring maxWidthSpring = layout.getConstraints(this.getComponent(0)).
                getWidth();
        Spring maxHeightSpring = layout.getConstraints(this.getComponent(0)).
                getHeight();
        for (int i = 1; i < max; i++) {
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

        SpringLayout.Constraints tgLblCons=layout.getConstraints(tagLabel);
        tgLblCons.setX(Spring.constant(xPad));
        tgLblCons.setY(Spring.constant(yPad));

        SpringLayout.Constraints tgTxtCons=layout.getConstraints(tagField);
        tgTxtCons.setX(Spring.sum(Spring.constant(xPad), tgLblCons.getConstraint("East")));
        tgTxtCons.setY(Spring.constant(yPad));

        SpringLayout.Constraints clrTagBtnCons=layout.getConstraints(clearTagBtn);
        clrTagBtnCons.setX(Spring.sum(Spring.constant(xPad), tgTxtCons.getConstraint("East")));
        clrTagBtnCons.setY(Spring.constant(yPad));


        /*
       set second row of gender fields
         */
        SpringLayout.Constraints genderLabelCons=layout.getConstraints(genderLabel);
        genderLabelCons.setX(Spring.constant(xPad));
        genderLabelCons.setY(Spring.sum(Spring.constant(yPad), tgLblCons.getConstraint("South")));

        SpringLayout.Constraints genderBoxCons=layout.getConstraints(genderBox);
        genderBoxCons.setX(Spring.sum(Spring.constant(xPad), genderLabelCons.getConstraint("East")));
        genderBoxCons.setY(Spring.sum(Spring.constant(yPad), tgTxtCons.getConstraint("South")));

        /*
        set third row of father tag fields.
         */
        SpringLayout.Constraints fatherLablCons=layout.getConstraints(fatherLabel);
        fatherLablCons.setX(xPadSpring);
        fatherLablCons.setY(Spring.sum(yPadSpring, genderBoxCons.getConstraint("South")));

        SpringLayout.Constraints fatherTxtcons=layout.getConstraints(fatherField);
        fatherTxtcons.setX(Spring.sum(xPadSpring, fatherLablCons.getConstraint("East")));
        fatherTxtcons.setY(Spring.sum(yPadSpring, genderBoxCons.getConstraint("South")));

        SpringLayout.Constraints pickFatherBtnCons=layout.getConstraints(pickFatherBtn);
        pickFatherBtnCons.setX(Spring.sum(xPadSpring, fatherTxtcons.getConstraint("East")));
        pickFatherBtnCons.setY(Spring.sum(yPadSpring, genderBoxCons.getConstraint("South")));

        /*
        set fourth row of mother tag fields
         */
        SpringLayout.Constraints motherLabelCons=layout.getConstraints(motherLabel);
        motherLabelCons.setX(xPadSpring);
        motherLabelCons.setY(Spring.sum(yPadSpring, fatherLablCons.getConstraint("South")));

        SpringLayout.Constraints motherTxtCons=layout.getConstraints(motherField);
        motherTxtCons.setX(Spring.sum(xPadSpring, motherLabelCons.getConstraint("East")));
        motherTxtCons.setY(Spring.sum(yPadSpring,fatherTxtcons.getConstraint("South")));

        SpringLayout.Constraints pickMotherBtnCons=layout.getConstraints(pickMotherBtn);
        pickMotherBtnCons.setX(Spring.sum(xPadSpring, motherTxtCons.getConstraint("East")));
        pickMotherBtnCons.setY(Spring.sum(yPadSpring, pickFatherBtnCons.getConstraint("South")));

        /*
        set fifth row of DOB fields
         */
        SpringLayout.Constraints dobLabelCons=layout.getConstraints(dobLabel);
        dobLabelCons.setX(xPadSpring);
        dobLabelCons.setY(Spring.sum(yPadSpring, motherLabelCons.getConstraint("South")));

        SpringLayout.Constraints dobPickerCons=layout.getConstraints(dobPicker);
        dobPickerCons.setX(Spring.sum(xPadSpring, dobLabelCons.getConstraint("East")));
        dobPickerCons.setY(Spring.sum(yPadSpring, motherTxtCons.getConstraint("South")));

        SpringLayout.Constraints clearDoBButtonCons=layout.getConstraints(clearDOBBtn);
        clearDoBButtonCons.setX(Spring.sum(xPadSpring, dobPickerCons.getConstraint("East")));
        clearDoBButtonCons.setY(Spring.sum(yPadSpring, pickMotherBtnCons.getConstraint("South")));

        /*
        set the sixth row of cage fields
         */
        SpringLayout.Constraints cageLabelCons=layout.getConstraints(cageLabel);
        cageLabelCons.setX(xPadSpring);
        cageLabelCons.setY(Spring.sum(yPadSpring, dobLabelCons.getConstraint("South")));

        SpringLayout.Constraints cageTxtCons=layout.getConstraints(cageField);
        cageTxtCons.setX(Spring.sum(xPadSpring, cageLabelCons.getConstraint("East")));
        cageTxtCons.setY(Spring.sum(yPadSpring, dobPickerCons.getConstraint("South")));

        SpringLayout.Constraints clearCageButtonCons=layout.getConstraints(clearCageBtn);
        clearCageButtonCons.setX(Spring.sum(xPadSpring, cageTxtCons.getConstraint("East")));
        clearCageButtonCons.setY(Spring.sum(yPadSpring, clearDoBButtonCons.getConstraint("South")));
        /*
        set the seventh row of strain fields
         */
        SpringLayout.Constraints strainLabelCons=layout.getConstraints(strainLabel);
        strainLabelCons.setX(xPadSpring);
        strainLabelCons.setY(Spring.sum(yPadSpring, cageLabelCons.getConstraint("South")));

        SpringLayout.Constraints strainTxtCons=layout.getConstraints(strainField);
        strainTxtCons.setX(Spring.sum(xPadSpring, strainLabelCons.getConstraint("East")));
        strainTxtCons.setY(Spring.sum(yPadSpring, cageTxtCons.getConstraint("South")));

        SpringLayout.Constraints clearStrainButtonCons=layout.getConstraints(clearStrainBtn);
        clearStrainButtonCons.setX(Spring.sum(xPadSpring, strainTxtCons.getConstraint("East")));
        clearStrainButtonCons.setY(Spring.sum(yPadSpring, clearCageButtonCons.getConstraint("South")));

        /*
        set the 8th row of coat color fields
         */
        SpringLayout.Constraints coatColorLabelCons=layout.getConstraints(coatColorLabel);
        coatColorLabelCons.setX(xPadSpring);
        coatColorLabelCons.setY(Spring.sum(yPadSpring, strainLabelCons.getConstraint("South")));

        SpringLayout.Constraints coatColortxtCons=layout.getConstraints(coatColorField);
        coatColortxtCons.setX(Spring.sum(xPadSpring, coatColorLabelCons.getConstraint("East")));
        coatColortxtCons.setY(Spring.sum(yPadSpring, strainTxtCons.getConstraint("South")));

        SpringLayout.Constraints clearCoatColorButtonCons=layout.getConstraints(clearCoatColorBtn);
        clearCoatColorButtonCons.setX(Spring.sum(xPadSpring, coatColortxtCons.getConstraint("East")));
        clearCoatColorButtonCons.setY(Spring.sum(yPadSpring, clearStrainButtonCons.getConstraint("South")));

        /*
        set the 9th row of genotype fields
         */

        SpringLayout.Constraints genotypeLabelCons=layout.getConstraints(genotypeLabel);
        genotypeLabelCons.setX(xPadSpring);
        genotypeLabelCons.setY(Spring.sum(yPadSpring, coatColorLabelCons.getConstraint("South")));

        SpringLayout.Constraints genotypeTxtCons=layout.getConstraints(genotypeField);
        genotypeTxtCons.setX(Spring.sum(xPadSpring, genotypeLabelCons.getConstraint("East")));
        genotypeTxtCons.setY(Spring.sum(yPadSpring, coatColortxtCons.getConstraint("South")));

        SpringLayout.Constraints clearGenotypeButtonCons=layout.getConstraints(clearGenotypeBtn);
        clearGenotypeButtonCons.setX(Spring.sum(xPadSpring, genotypeTxtCons.getConstraint("East")));
        clearGenotypeButtonCons.setY(Spring.sum(yPadSpring, clearCoatColorButtonCons.getConstraint("South")));

        /*
        the submit button
         */
        SpringLayout.Constraints submitButtonCons=layout.getConstraints(submitBtn);
        submitButtonCons.setX(Spring.sum(xPadSpring, genotypeLabelCons.getConstraint("East")));
        submitButtonCons.setY(Spring.sum(yPadSpring, genotypeTxtCons.getConstraint("South")));

        /*
        set the jpanel
         */
        SpringLayout.Constraints panelCons=layout.getConstraints(this);
        panelCons.setConstraint("East", Spring.sum(Spring.constant(xPad), clrTagBtnCons.getConstraint("East")));
        panelCons.setConstraint("South", Spring.sum(yPadSpring, submitButtonCons.getConstraint("South")));


    }


    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame=new JFrame("Add mouse");
                AddMousePanel addMousePanel=new AddMousePanel();
                frame.add(addMousePanel);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });


    }


}
