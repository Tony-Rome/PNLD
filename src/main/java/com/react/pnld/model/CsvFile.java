package com.react.pnld.model;

import org.springframework.web.multipart.MultipartFile;

public class CsvFile {

    private String name;

    private String selectedType;

    private MultipartFile uploadFile;

    public CsvFile(){
        super();
    }

    public CsvFile(String name, String selectedType, MultipartFile multipartFile){
        super();
        this.name = name;
        this.selectedType = selectedType;
        this.uploadFile = multipartFile;
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

    @Override
    public String toString() {
        return "CsvFile{" +
                "name='" + name + '\'' +
                ", selectedType='" + selectedType + '\'' +
                ", uploadFile=" + uploadFile +
                '}';
    }
}