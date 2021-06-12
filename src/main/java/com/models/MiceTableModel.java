package com.models;

import com.cagezz.CageZZ;
import com.mouse.Mouse;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MiceTableModel extends AbstractTableModel {

    CageZZ cageZZ=new CageZZ();
    String[][] miceDataArray;
    public MiceTableModel() {
        cageZZ.loadMiceRecords();
        miceDataArray=cageZZ.getArrayRepresentationOfMiceData();
    }

    @Override
    public int getRowCount() {
        return miceDataArray.length;
    }

    @Override
    public int getColumnCount() {
        return 12;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return miceDataArray[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        String colName=null;
        switch (column){
            case 0: colName= "Tag-Number";                 break;
            case 1: colName= "Gender";                     break;
            case 2: colName= "Birth-Date";                 break;
            case 3: colName= "Strain";                     break;
            case 4: colName= "Father";                     break;
            case 5: colName= "Mother";                     break;
            case 6: colName= "Coat-Color";                 break;
            case 7: colName= "Wean-Date";                  break;
            case 8: colName= "Genotype";                   break;
            case 9: colName= "Status";                     break;
            case 10: colName= "Cage-Number";                break;
            case 11: colName= "Notes";                     break;
        }
        return colName;
    }
}
