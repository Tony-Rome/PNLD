package com.react.pnld.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileTableResumeDTO implements Serializable {

    private String loadedBy;
    private String name;
    private String type;
    private String uploadedDateTime;
    private String state;
    private int totalRecords;
    private int duplicateRecords;

    public void setLoadedBy(String loadedBy) {
        this.loadedBy = loadedBy;
    }

    public String getLoadedBy() {
        return loadedBy;
    }

    public void setName(String rawName) {
        String newName = rawName.split("-")[5];
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUploadedDateTime(LocalDateTime localDateTime) {

        String uploadedDateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));
        this.uploadedDateTime = uploadedDateTime;
    }

    public String getUploadedDateTime() {
        return uploadedDateTime;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setDuplicateRecords(int duplicateRecords) {
        this.duplicateRecords = duplicateRecords;
    }

    public int getDuplicateRecords() {
        return duplicateRecords;
    }

    @Override
    public String toString() {
        return "TableFileDTO{" +
                "loadedBy=" + loadedBy +
                ", uploadedDateTime=" + uploadedDateTime +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", totalRecords=" + totalRecords +
                ", duplicateRecords=" + duplicateRecords +
                '}';
    }
}
