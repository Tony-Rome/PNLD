package com.react.pnld.dto;

import java.io.Serializable;
import java.util.List;

public class TrainingInstitutionIndicatorDTO implements Serializable {

    private int id;
    private String regionName;
    private List<TrainingInstitutionDataByYearDTO> trainingInstitutionDataByYearDTOList;

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

    public List<TrainingInstitutionDataByYearDTO> getTrainingInstitutionDataByYearDTOList() {
        return trainingInstitutionDataByYearDTOList;
    }

    public void setTrainingInstitutionDataByYearDTOList(List<TrainingInstitutionDataByYearDTO> trainingInstitutionDataByYearDTOList) {
        this.trainingInstitutionDataByYearDTOList = trainingInstitutionDataByYearDTOList;
    }

    @Override
    public String toString() {
        return "TrainingInstitutionIndicatorDTO{" +
                "id=" + id +
                ", regionName='" + regionName + '\'' +
                ", trainingInstitutionDataByYearDTOList= [" + trainingInstitutionDataByYearDTOList + "]" +
                '}';
    }
}
