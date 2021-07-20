package com.models;

import java.util.ArrayList;

public class ModelBreeders {
    String cageNumber;
    String maleBreederTagNumber;
    ArrayList<String> femaleBreedersList;

    public ModelBreeders(String cageNumber, String maleBreederTagNumber, ArrayList<String> femaleBreedersList) {
        this.cageNumber = cageNumber;
        this.maleBreederTagNumber = maleBreederTagNumber;
        this.femaleBreedersList = femaleBreedersList;
    }

    public String getCageNumber() {
        return cageNumber;
    }

    public String getMaleBreederTagNumber() {
        return maleBreederTagNumber;
    }

    public ArrayList<String> getFemaleBreedersList() {
        return femaleBreedersList;
    }
}
