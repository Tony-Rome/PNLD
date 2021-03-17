package com.react.pnld.model;

import java.time.OffsetDateTime;

public class LoadedFile {

    private int idLoadedFile;
    private OffsetDateTime loadedDate;
    private String fileName;
    private String fileType;
    private int loadedByUserId;
    private int stateId;
    private OffsetDateTime processDate;
    private int totalRecords;
    private int newRecords;
    private int duplicateRecords;

    public LoadedFile() {
        super();
    }

    public LoadedFile(int idLoadedFile, OffsetDateTime loadedDate, String fileName, String fileType, int loadedByUserId, int stateId, OffsetDateTime processDate, int totalRecords, int newRecords, int duplicateRecords) {
        super();
        this.idLoadedFile = idLoadedFile;
        this.loadedDate = loadedDate;
        this.fileName = fileName;
        this.fileType = fileType;
        this.loadedByUserId = loadedByUserId;
        this.stateId = stateId;
        this.processDate = processDate;
        this.totalRecords = totalRecords;
        this.newRecords = newRecords;
        this.duplicateRecords = duplicateRecords;
    }

    public int getIdLoadedFile() {
        return idLoadedFile;
    }

    public void setIdLoadedFile(int idLoadedFile) {
        this.idLoadedFile = idLoadedFile;
    }

    public OffsetDateTime getLoadedDate() {
        return loadedDate;
    }

    public void setLoadedDate(OffsetDateTime loadedDate) {
        this.loadedDate = loadedDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getLoadedByUserId() {
        return loadedByUserId;
    }

    public void setLoadedByUserId(int loadedByUserId) {
        this.loadedByUserId = loadedByUserId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public OffsetDateTime getProcessDate() {
        return processDate;
    }

    public void setProcessDate(OffsetDateTime processDate) {
        this.processDate = processDate;
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

    public int getDuplicateRecords() {
        return duplicateRecords;
    }

    public void setDuplicateRecords(int duplicateRecords) {
        this.duplicateRecords = duplicateRecords;
    }

    @Override
    public String toString() {
        return "LoadedFile{" +
                "idLoadedFile=" + idLoadedFile +
                ", loadedDate=" + loadedDate +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", loadedByUserId=" + loadedByUserId +
                ", stateId=" + stateId +
                ", processDate=" + processDate +
                ", totalRecords=" + totalRecords +
                ", newRecords=" + newRecords +
                ", duplicateRecords=" + duplicateRecords +
                '}';
    }
}
