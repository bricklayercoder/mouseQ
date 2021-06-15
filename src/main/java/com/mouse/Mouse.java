package com.mouse;

import com.cagezz.Status;

public class Mouse {
    public Mouse(String tagNumber,
                 String maternalTagNumber,
                 String paterNalTagNumber,
                 String birthDate,
                 String gender,
                 String strain) {
        this.tagNumber = tagNumber;
        this.maternalTagNumber=maternalTagNumber;
        this.paterNalTagNumber=paterNalTagNumber;
        this.birthDate=birthDate;
        this.gender= gender;
        this.strain=strain;
    }

    private String        tagNumber;
    private String        maternalTagNumber;
    private String        paterNalTagNumber;
    private String        genotype="Not entered";
    private String        birthDate;
    private String        gender;
    private String        strain;
    private String        coatColour="not entered";
    private String        weanDate="not entered";
    private String        cageNumber="Not entered";
    private String        notes="newly added";
    private String        status=Status.MAINTAINING.toString();



    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes=notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status=status;
    }

    public String getCageNumber() {
        return cageNumber;
    }

    public void setCageNumber(String cageNumber) {
        this.cageNumber = cageNumber;
    }

    public String getGenotype() {
        return genotype;
    }

    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public String getMaternalTagNumber() {
        return maternalTagNumber;
    }

    public String getPaternalTagNumber() {
        return paterNalTagNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return String.valueOf(gender);
    }

    public String getCoatColour() {
        return coatColour;
    }

    public void setCoatColour(String coatColour) {
        this.coatColour = coatColour;
    }

    public String getWeanDate() {
        return weanDate;
    }

    public void setWeanDate(String weanDate) {
        this.weanDate = weanDate;
    }

    public String getStrain() {
        return strain;
    }


}
