package com.react.pnld.controller.response;

import java.io.Serializable;

public class RegionTrainingInfo implements Serializable {

    private int regionId;
    private String regionName;
    private int numberInstitutionsInPNLD;
    private float percentageInstitutions;
    private float percentageFirstTimeInPNLD;

    public RegionTrainingInfo(){
        super();
    }

    public RegionTrainingInfo(int regionId, String regionName, int numberInstitutionsInPNLD, float percentageInstitutions,
                              float percentageFirstTimeInPNLD) {
        super();
        this.regionId = regionId;
        this.regionName = regionName;
        this.numberInstitutionsInPNLD = numberInstitutionsInPNLD;
        this.percentageInstitutions = percentageInstitutions;
        this.percentageFirstTimeInPNLD = percentageFirstTimeInPNLD;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getNumberInstitutionsInPNLD() {
        return numberInstitutionsInPNLD;
    }

    public void setNumberInstitutionsInPNLD(int numberInstitutionsInPNLD) {
        this.numberInstitutionsInPNLD = numberInstitutionsInPNLD;
    }

    public float getPercentageInstitutions() {
        return percentageInstitutions;
    }

    public void setPercentageInstitutions(float percentageInstitutions) {
        this.percentageInstitutions = percentageInstitutions;
    }

    public float getPercentageFirstTimeInPNLD() {
        return percentageFirstTimeInPNLD;
    }

    public void setPercentageFirstTimeInPNLD(float percentageFirstTimeInPNLD) {
        this.percentageFirstTimeInPNLD = percentageFirstTimeInPNLD;
    }

    @Override
    public String toString() {
        return "RegionTrainingInfo{" +
                "regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
                ", numberInstitutionsInPNLD=" + numberInstitutionsInPNLD +
                ", percentageInstitutions=" + percentageInstitutions +
                ", percentageFirstTimeInPNLD=" + percentageFirstTimeInPNLD +
                '}';
    }
}
