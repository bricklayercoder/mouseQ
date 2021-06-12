package com.models;

import com.cagezz.CageZZ;

import javax.swing.table.AbstractTableModel;

public class CageZTableModel extends AbstractTableModel {
    CageZZ cageZZ=new CageZZ();
    String[][] dataArray;
    public CageZTableModel() {
        cageZZ.loadMiceRecords();
        cageZZ.getCageZZ();
        dataArray=cageZZ.getArrayRepresentationCages();
    }


    @Override
    public int getRowCount() {
        return cageZZ.getCageZZ().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        String value=dataArray[rowIndex][columnIndex];
        return value;
    }

    @Override
    public String getColumnName(int column) {
        String colName=null;
        switch (column){
            case 0: colName="Cage-Number"; break;
            case 1: colName="Strain";      break;
            case 2: colName="Status";      break;
            case 3: colName="Size";        break;
            case 4: colName="Notes";       break;
        }
        return colName;
    }

}
