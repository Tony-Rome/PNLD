package com.react.pnld.controller.response;

import com.react.pnld.dto.TrainingIndicatorDTO;

import java.io.Serializable;
import java.util.List;

public class TrainingIndicatorResponse implements Serializable {

    private List<TrainingIndicatorDTO> trainingIndicatorDTOList;

    public TrainingIndicatorResponse() {
        super();
    }

    public TrainingIndicatorResponse(List<TrainingIndicatorDTO> trainingIndicatorDTOList) {
        this.trainingIndicatorDTOList = trainingIndicatorDTOList;
    }

    public List<?> getTrainingIndicatorData() {
        return trainingIndicatorDTOList;
    }

    public void setTrainingIndicatorData(List<TrainingIndicatorDTO> trainingIndicatorData) {
        this.trainingIndicatorDTOList = trainingIndicatorData;
    }

    @Override
    public String toString() {
        return "TrainingIndicatorResponse{" +
                ", trainingIndicatorDTOList=" + trainingIndicatorDTOList +
                '}';
    }
}
