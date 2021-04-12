package com.react.pnld.dto;

public class FileResumeDTO {
    private int totalRecords;
    private int newRecords;
    private int duplicatedRecords;

    public FileResumeDTO() {
        super();
    }

    public FileResumeDTO(int totalRecords, int newRecords, int duplicatedRecords) {
        super();
        this.totalRecords = totalRecords;
        this.newRecords = newRecords;
        this.duplicatedRecords = duplicatedRecords;
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

    @Override
    public String toString() {
        return "ProcessedParsedFileResumeDTO{" +
                "totalRecords=" + totalRecords +
                ", newRecords=" + newRecords +
                ", duplicatedRecords=" + duplicatedRecords +
                '}';
    }
}
