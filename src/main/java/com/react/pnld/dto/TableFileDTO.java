package com.react.pnld.dto;

import java.time.OffsetDateTime;

public class TableFileDTO {

    private String loadedBy;
    private String name;
    private String fileType;
    private OffsetDateTime loadedOnDateTime;
    private int totalRecords;
    private int duplicateRecords;

    public void setLoadedBy(String loadedBy){
        this.loadedBy = loadedBy;
    }

    public String getLoadedBy(){return loadedBy;}

    public void setName(String name){
        this.name = name;
    }

    public String getName(){return name;}

    public void setFileType(String fileType){
        this.fileType = fileType;
    }

    public String getFileType(){return fileType;}

    public void setLoadedOnDateTime(OffsetDateTime loadedOnDateTime){
        this.loadedOnDateTime = loadedOnDateTime;
    }

    public OffsetDateTime getLoadedOnDateTime(){return loadedOnDateTime;}

    public void setTotalRecords(int totalRecords){
        this.totalRecords = totalRecords;
    }

    public int getTotalRecords(){return totalRecords;}

    public void setDuplicateRecords(int duplicateRecords){
        this.duplicateRecords = duplicateRecords;
    }

    public int getDuplicateRecords(){return duplicateRecords;}

    @Override
    public String toString() {
        return "TableFileDTO{" +
                "loadedBy=" + loadedBy +
                ", loadedOnDateTime=" + loadedOnDateTime +
                ", name='" + name + '\'' +
                ", fileType='" + fileType + '\'' +
                ", totalRecords=" + totalRecords +
                ", duplicateRecords=" + duplicateRecords +
                '}';
    }
}
