package com.react.pnld.model;

import java.io.Serializable;

public class ScheduleFileLoadResponse implements Serializable {
    private String response;
    private String description;

    public ScheduleFileLoadResponse() {
        super();
    }

    public ScheduleFileLoadResponse(String response, String description) {
        super();
        this.response = response;
        this.description = description;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
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
