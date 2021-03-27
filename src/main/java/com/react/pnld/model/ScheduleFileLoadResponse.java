package com.react.pnld.model;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ScheduleFileLoadResponse implements Serializable {

    private HttpStatus response;
    private String description;

    public ScheduleFileLoadResponse() {
        super();
    }

    public ScheduleFileLoadResponse(HttpStatus response) {
        super();
        this.response = response;
    }

    public ScheduleFileLoadResponse(HttpStatus response, String description) {
        super();
        this.response = response;
        this.description = description;
    }

    public HttpStatus getResponse() {
        return response;
    }

    public void setResponse(HttpStatus response) {
        this.response = response;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ScheduleFileLoadResponse{" +
                "response='" + response + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
