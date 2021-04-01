package com.react.pnld.dto;

import java.util.Arrays;
import java.util.List;

public class ParsedFileDTO {

    private String loadedFileName;
    private String fileType;
    private String[] headers;
    private List<String[]> rows;

    public ParsedFileDTO() {
        super();
    }

    public ParsedFileDTO(String loadedFileName, String fileType, String[] headers, List<String[]> rows) {
        this.loadedFileName = loadedFileName;
        this.fileType = fileType;
        this.headers = headers;
        this.rows = rows;
    }

    public String getLoadedFileName() {
        return loadedFileName;
    }

    public void setLoadedFileName(String loadedFileName) {
        this.loadedFileName = loadedFileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<String[]> getRows() {
        return rows;
    }

    public void setRows(List<String[]> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "ParsedFileDTO{" +
                "loadedFileId=" + loadedFileName +
                ", fileType='" + fileType + '\'' +
                ", headers=" + Arrays.toString(headers) +
                ", rows=" + rows +
                '}';
    }
}
