package com.react.pnld.model.dto;

import java.util.Arrays;
import java.util.List;

public class ParsedFileDTO {

    private String[] headers;
    private List<String[]> rows;
    private String fileType;

    public ParsedFileDTO() {
        super();
    }

    public ParsedFileDTO(String[] headers, List<String[]> rows, String fileType) {
        super();
        this.headers = headers;
        this.rows = rows;
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "ParsedFileDTO{" +
                "headers=" + Arrays.toString(headers) +
                ", rows=" + rows +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
