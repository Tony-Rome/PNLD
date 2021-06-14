package com.react.pnld.dto;

import java.io.Serializable;
import java.util.List;

public class TrainingTeacherIndicatorDTO implements Serializable {

    private int id;
    private String regionName;
    private List<TrainingTeacherIndicatorDataByTeacherDTO> trainingTeacherIndicatorDataByTeacherDTOList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<TrainingTeacherIndicatorDataByTeacherDTO> getTrainingTeacherIndicatorDataByTeacherDTOList() {
        return trainingTeacherIndicatorDataByTeacherDTOList;
    }

    public void setTrainingTeacherIndicatorDataByTeacherDTOList(List<TrainingTeacherIndicatorDataByTeacherDTO> trainingTeacherIndicatorDataByTeacherDTOList) {
        this.trainingTeacherIndicatorDataByTeacherDTOList = trainingTeacherIndicatorDataByTeacherDTOList;
    }

    @Override
    public String toString() {
        return "TrainingTeacherIndicatorDTO{" +
                "id=" + id +
                ", regionName='" + regionName + '\'' +
                ", trainingTeacherIndicatorDataByTeacherDTOList=" + trainingTeacherIndicatorDataByTeacherDTOList +
                '}';
    }
}
