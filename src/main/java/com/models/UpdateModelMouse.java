package com.models;

public class UpdateModelMouse {
    String cageNumber;
    String status;
    String notes;

    public UpdateModelMouse(String cageNumber, String status, String notes) {
        this.cageNumber = cageNumber;
        this.status = status;
        this.notes = notes;
    }

    public String getCageNumber() {
        return cageNumber;
    }

    public void setCageNumber(String cageNumber) {
        this.cageNumber = cageNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
