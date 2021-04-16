package com.react.pnld.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LoadedFile implements Serializable {

    private int id;
    private LocalDateTime loadedDate;
    private String name;
    private String storedIn;
    private String type;
    private String loadedByAdmin;
    private int stateId;
    private LocalDateTime processDate;
    private int totalRecords;
    private int newRecords;
    private int duplicateRecords;

    public LoadedFile() {
        super();
    }

    public LoadedFile(int id, LocalDateTime loadedDate, String name, String storedIn, String type, String loadedByAdmin,
                      int stateId, LocalDateTime processDate, int totalRecords, int newRecords, int duplicateRecords) {
        super();
        this.id = id;
        this.loadedDate = loadedDate;
        this.name = name;
        this.storedIn = storedIn;
        this.type = type;
        this.loadedByAdmin = loadedByAdmin;
        this.stateId = stateId;
        this.processDate = processDate;
        this.totalRecords = totalRecords;
        this.newRecords = newRecords;
        this.duplicateRecords = duplicateRecords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLoadedDate() {
        return loadedDate;
    }

    public void setLoadedDate(LocalDateTime loadedDate) {
        this.loadedDate = loadedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoredIn() {
        return storedIn;
    }

    public void setStoredIn(String storedIn) {
        this.storedIn = storedIn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLoadedByAdmin() {
        return loadedByAdmin;
    }

    public void setLoadedByAdmin(String loadedByAdmin) {
        this.loadedByAdmin = loadedByAdmin;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public LocalDateTime getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDateTime processDate) {
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
                "id=" + id +
                ", loadedDate=" + loadedDate +
                ", name='" + name + '\'' +
                ", storedIn='" + storedIn + '\'' +
                ", type='" + type + '\'' +
                ", loadedByAdmin='" + loadedByAdmin + '\'' +
                ", stateId=" + stateId +
                ", processDate=" + processDate +
                ", totalRecords=" + totalRecords +
                ", newRecords=" + newRecords +
                ", duplicateRecords=" + duplicateRecords +
                '}';
    }
}
