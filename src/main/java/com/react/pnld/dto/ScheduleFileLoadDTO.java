package com.react.pnld.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class ScheduleFileLoadDTO {

    private String name;
    private String selectedType;
    private MultipartFile uploadFile;
    private String loadedBy;
    private LocalDateTime loadedOnDateTime;
    private String nameInFileSystem;

    public ScheduleFileLoadDTO() {
        super();
    }

    public ScheduleFileLoadDTO(String name, String selectedType, MultipartFile uploadFile, String loadedBy,
                               LocalDateTime loadedOnDateTime) {
        super();
        this.name = name;
        this.selectedType = selectedType;
        this.uploadFile = uploadFile;
        this.loadedBy = loadedBy;
        this.loadedOnDateTime = loadedOnDateTime;
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

    public LocalDateTime getLoadedOnDateTime() {
        return loadedOnDateTime;
    }

    public void setLoadedOnDateTime(LocalDateTime loadedOnDateTime) {
        this.loadedOnDateTime = loadedOnDateTime;
    }

    public String getNameInFileSystem() {
        return nameInFileSystem;
    }

    public void setNameInFileSystem(String nameInFileSystem) {
        this.nameInFileSystem = nameInFileSystem;
    }

    @Override
    public String toString() {
        return "ScheduleFileLoadDTO{" +
                "name='" + name + '\'' +
                ", selectedType='" + selectedType + '\'' +
                ", uploadFile=" + uploadFile +
                ", loadedBy='" + loadedBy + '\'' +
                ", loadedOnDateTime=" + loadedOnDateTime +
                ", nameInFileSystem='" + nameInFileSystem + '\'' +
                '}';
    }
}