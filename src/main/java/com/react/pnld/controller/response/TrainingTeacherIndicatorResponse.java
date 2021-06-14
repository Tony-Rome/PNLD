package com.react.pnld.controller.response;

import com.react.pnld.dto.TrainingTeacherIndicatorDTO;

import java.io.Serializable;
import java.util.List;

public class TrainingTeacherIndicatorResponse implements Serializable {

    private int fromYear;
    private int toYear;
    private List<TrainingTeacherIndicatorDTO> trainingTeacherIndicatorDTOList;

    public TrainingTeacherIndicatorResponse() { super(); }

    public TrainingTeacherIndicatorResponse(int fromYear, int toYear, List<TrainingTeacherIndicatorDTO> trainingTeacherIndicatorDTOList) {
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.trainingTeacherIndicatorDTOList = trainingTeacherIndicatorDTOList;
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

    public List<TrainingTeacherIndicatorDTO> getTrainingTeacherIndicatorDTOList() {
        return trainingTeacherIndicatorDTOList;
    }

    public void setTrainingTeacherIndicatorDTOList(List<TrainingTeacherIndicatorDTO> trainingTeacherIndicatorDTOList) {
        this.trainingTeacherIndicatorDTOList = trainingTeacherIndicatorDTOList;
    }

    @Override
    public String toString() {
        return "TrainingTeacherIndicatorResponse{" +
                "fromYear=" + fromYear +
                ", toYear=" + toYear +
                ", trainingTeacherIndicatorDTOList=" + trainingTeacherIndicatorDTOList +
                '}';
    }
}
