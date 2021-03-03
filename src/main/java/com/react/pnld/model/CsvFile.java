package com.react.pnld.model;

import org.springframework.web.multipart.MultipartFile;

public class CsvFile {

    private String name;
    private String selectedType;
    private MultipartFile uploadFile;
    private String loadedBy;

    public CsvFile() {
        super();
    }

    public CsvFile(String name, String selectedType, MultipartFile uploadFile, String loadedBy) {
        super();
        this.name = name;
        this.selectedType = selectedType;
        this.uploadFile = uploadFile;
        this.loadedBy = loadedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getLoadedBy() {
        return loadedBy;
    }

    public void setLoadedBy(String loadedBy) {
        this.loadedBy = loadedBy;
    }

    @Override
    public String toString() {
        return "CsvFile{" +
                "name='" + name + '\'' +
                ", selectedType='" + selectedType + '\'' +
                ", uploadFile=" + uploadFile +
                ", loadedBy='" + loadedBy + '\'' +
                '}';
    }
}