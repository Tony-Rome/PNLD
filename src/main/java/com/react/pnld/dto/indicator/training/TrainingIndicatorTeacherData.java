package com.react.pnld.dto.indicator.training;

import java.io.Serializable;
import java.util.List;

public class TrainingIndicatorTeacherData implements Serializable {

    private int year;
    List<TrainingIndicatorTeacherDataByGender> dataByGenderList;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<TrainingIndicatorTeacherDataByGender> getDataByGenderList() {
        return dataByGenderList;
    }

    public void setDataByGenderList(List<TrainingIndicatorTeacherDataByGender> dataByGenderList) {
        this.dataByGenderList = dataByGenderList;
    }

    @Override
    public String toString() {
        return "TrainingIndicatorTeacherData{" +
                "year=" + year +
                ", dataByGenderList=" + dataByGenderList +
                '}';
    }
}
