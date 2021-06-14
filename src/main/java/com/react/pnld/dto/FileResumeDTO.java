package com.react.pnld.dto;

public class FileResumeDTO {
    private int totalRecords;
    private int newRecords;
    private int duplicatedRecords;
    private int invalidRecords;

    public FileResumeDTO() {
        super();
    }

    public FileResumeDTO(int totalRecords, int newRecords, int duplicatedRecords, int invalidRecords) {
        this.totalRecords = totalRecords;
        this.newRecords = newRecords;
        this.duplicatedRecords = duplicatedRecords;
        this.invalidRecords = invalidRecords;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getNewRecords() {
        return newRecords;
    }

    public void setNewRecords(int newRecords) {
        this.newRecords = newRecords;
    }

    public int getDuplicatedRecords() {
        return duplicatedRecords;
    }

    public void setDuplicatedRecords(int duplicatedRecords) {
        this.duplicatedRecords = duplicatedRecords;
    }

    public int getInvalidRecords() {
        return invalidRecords;
    }

    public void setInvalidRecords(int invalidRecords) {
        this.invalidRecords = invalidRecords;
    }

    @Override
    public String toString() {
        return "FileResumeDTO{" +
                "totalRecords=" + totalRecords +
                ", newRecords=" + newRecords +
                ", duplicatedRecords=" + duplicatedRecords +
                ", invalidRecords=" + invalidRecords +
                '}';
    }
}
