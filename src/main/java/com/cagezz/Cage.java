package com.cagezz;

import com.mouse.Mouse;

import java.util.ArrayList;

public class Cage  {

    private String  cageNumber;
    private String  status=Status.MAINTAINING.toString();
    private String  notes="Not entered";
    private String  strain;
    private String  size;
    private ArrayList<Mouse> miceInfoContainer =new ArrayList<Mouse>();

    public ArrayList<Mouse> getMiceInfoContainer(){
        return miceInfoContainer;
    }


    public String getSize() {
        return Integer.valueOf(miceInfoContainer.size());
    }


    public Cage(String cageNumber, String status, String notes, String strain, String size) {
        this.cageNumber = cageNumber;
        this.status = status;
        this.notes = notes;
        this.strain = strain;
        this.size = size;
    }

    public Cage(){

    }


/*
the setSize() is for dev test purposes
 */
    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCageNumber() {
        return cageNumber;
    }

    public void setCageNumber(String cageNumber) {
        this.cageNumber = cageNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    @Override
    public String toString() {
        return cageNumber;
    }

}
