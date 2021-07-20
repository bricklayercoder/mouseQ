package com.models;

import com.mouse.DataSanity;

public class CageSanityModel {
    String cageNumber;
    String dataSanityString;

    public CageSanityModel(String cageNumber, String dataSanityString) {
        this.cageNumber = cageNumber;
        this.dataSanityString = dataSanityString;
    }

    public String getCageNumber() {
        return cageNumber;
    }

    public void setCageNumber(String cageNumber) {
        this.cageNumber = cageNumber;
    }

    public String getDataSanityString() {
        return dataSanityString;
    }

    public void setDataSanityString(String dataSanityString) {
        this.dataSanityString = dataSanityString;
    }
}
