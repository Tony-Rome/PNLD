package com.react.pnld.controller.response;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;

import java.io.Serializable;
import java.util.List;

public class TrainingIndicatorResponse implements Serializable {

    private List<?> trainingIndicatorData;

    public TrainingIndicatorResponse() {
        super();
    }

    public TrainingIndicatorResponse(List<?> trainingIndicatorData) {
        this.trainingIndicatorData = trainingIndicatorData;
    }

    public List<?> getTrainingIndicatorData() {
        return trainingIndicatorData;
    }

    public void setTrainingIndicatorData(List<TrainingInstitutionIndicatorDTO> trainingIndicatorData) {
        this.trainingIndicatorData = trainingIndicatorData;
    }

    @Override
    public String toString() {
        return "TrainingIndicatorResponse{" +
                ", trainingIndicatorData=" + trainingIndicatorData +
                '}';
    }
}
