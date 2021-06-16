package com.react.pnld.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Training implements Serializable {
    private int id;
    private String organizer;
    private String facilitator;
    private LocalDateTime trainingDate;

    public Training() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = facilitator;
    }

    public LocalDateTime getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(LocalDateTime trainingDate) {
        this.trainingDate = trainingDate;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", organizer='" + organizer + '\'' +
                ", facilitator='" + facilitator + '\'' +
                ", trainingDate=" + trainingDate +
                '}';
    }
}
