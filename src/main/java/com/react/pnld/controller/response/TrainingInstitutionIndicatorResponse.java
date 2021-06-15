package com.react.pnld.controller.response;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;

import java.io.Serializable;
import java.util.List;

public class TrainingInstitutionIndicatorResponse implements Serializable {
    private int fromYear;
    private int toYear;
    private List<TrainingInstitutionIndicatorDTO> trainingIndicatorData; //TODO: Cambiar anombre variable, también en js.

    public TrainingInstitutionIndicatorResponse() {
        super();
    }

    public TrainingInstitutionIndicatorResponse(int fromYear, int toYear, List<TrainingInstitutionIndicatorDTO> trainingIndicatorData) {
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.trainingIndicatorData = trainingIndicatorData;
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

    public List<TrainingInstitutionIndicatorDTO> getTrainingIndicatorData() {
        return trainingIndicatorData;
    }

    public void setTrainingIndicatorData(List<TrainingInstitutionIndicatorDTO> trainingIndicatorData) {
        this.trainingIndicatorData = trainingIndicatorData;
    }

    @Override
    public String toString() {
        return "TrainingInstitutionIndicatorResponse{" +
                "fromYear=" + fromYear +
                ", toYear=" + toYear +
                ", trainingIndicatorData=" + trainingIndicatorData +
                '}';
    }
}
