package com.react.pnld.controller.response;

import com.react.pnld.dto.indicator.TrainingIndicatorDTO;

import java.io.Serializable;
import java.util.List;

public class TrainingIndicatorResponse implements Serializable {

    private int fromYear;
    private int toYear;
    private List<TrainingIndicatorDTO> trainingIndicatorDTOList;

    public TrainingIndicatorResponse() {
        super();
    }

    public TrainingIndicatorResponse(int fromYear, int toYear, List<TrainingIndicatorDTO> trainingIndicatorDTOList) {
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.trainingIndicatorDTOList = trainingIndicatorDTOList;
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

    public List<TrainingIndicatorDTO> getTrainingIndicatorDTOList() {
        return trainingIndicatorDTOList;
    }

    public void setTrainingIndicatorDTOList(List<TrainingIndicatorDTO> trainingIndicatorDTOList) {
        this.trainingIndicatorDTOList = trainingIndicatorDTOList;
    }

    @Override
    public String toString() {
        return "TrainingIndicatorResponse{" +
                "fromYear=" + fromYear +
                ", toYear=" + toYear +
                ", trainingIndicatorDTOList=" + trainingIndicatorDTOList +
                '}';
    }
}
