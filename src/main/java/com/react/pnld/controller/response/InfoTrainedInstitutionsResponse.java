package com.react.pnld.controller.response;

import java.io.Serializable;
import java.util.List;

public class InfoTrainedInstitutionsResponse implements Serializable {
    private int fromYear;
    private int toYear;
    private List<TrainingInfoRegion> trainingInfoRegions;

    public InfoTrainedInstitutionsResponse(){
        super();
    }

    public InfoTrainedInstitutionsResponse(int fromYear, int toYear, List<TrainingInfoRegion> trainingInfoRegions) {
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.trainingInfoRegions = trainingInfoRegions;
    }

    public int getFromYear() {
        return fromYear;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public List<TrainingInfoRegion> getTrainingInfoRegions() {
        return trainingInfoRegions;
    }

    public void setTrainingInfoRegions(List<TrainingInfoRegion> trainingInfoRegions) {
        this.trainingInfoRegions = trainingInfoRegions;
    }

    @Override
    public String toString() {
        return "TrainedInstitutionsResponse{" +
                "fromYear=" + fromYear +
                ", toYear=" + toYear +
                ", trainingInfoRegions=" + trainingInfoRegions +
                '}';
    }
}
