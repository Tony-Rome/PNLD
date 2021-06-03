package com.react.pnld.controller.response;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;

import java.io.Serializable;
import java.util.List;

public class TrainingInstitutionIndicatorResponse implements Serializable {
    private int fromYear;
    private int toYear;
    private List<TrainingInstitutionIndicatorDTO> trainingInfoRegions;

    public TrainingInstitutionIndicatorResponse(){
        super();
    }

    public TrainingInstitutionIndicatorResponse(int fromYear, int toYear, List<TrainingInstitutionIndicatorDTO> trainingInfoRegions) {
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

    public List<TrainingInstitutionIndicatorDTO> getTrainingInfoRegions() {
        return trainingInfoRegions;
    }

    public void setTrainingInfoRegions(List<TrainingInstitutionIndicatorDTO> trainingInfoRegions) {
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
