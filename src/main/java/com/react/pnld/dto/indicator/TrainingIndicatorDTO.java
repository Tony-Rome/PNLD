package com.react.pnld.dto.indicator;

import java.io.Serializable;
import java.util.List;

public class TrainingIndicatorDTO implements Serializable {

    private int id;
    private String regionName;
    private List<?> trainingIndicatorDataList;

    public TrainingIndicatorDTO() {
    }

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

    public List<?> getTrainingIndicatorDataList() {
        return trainingIndicatorDataList;
    }

    public void setTrainingIndicatorDataList(List<?> trainingIndicatorDataList) {
        this.trainingIndicatorDataList = trainingIndicatorDataList;
    }

    @Override
    public String toString() {
        return "TrainingIndicatorDTO{" +
                "id=" + id +
                ", regionName='" + regionName + '\'' +
                ", trainingIndicatorDataList=" + trainingIndicatorDataList +
                '}';
    }
}
