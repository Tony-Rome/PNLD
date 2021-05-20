package com.react.pnld.controller.response;

import java.io.Serializable;
import java.util.List;

public class TrainedInstitutionsResponse implements Serializable {
    private int fromYear;
    private int toYear;
    private List<RegionTrainingInfo> regionsInfo;

    public TrainedInstitutionsResponse(){
        super();
    }

    public TrainedInstitutionsResponse(int fromYear, int toYear, List<RegionTrainingInfo> regionsInfo) {
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.regionsInfo = regionsInfo;
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

    public List<RegionTrainingInfo> getRegionsInfo() {
        return regionsInfo;
    }

    public void setRegionsInfo(List<RegionTrainingInfo> regionsInfo) {
        this.regionsInfo = regionsInfo;
    }

    @Override
    public String toString() {
        return "TrainedInstitutionsResponse{" +
                "fromYear=" + fromYear +
                ", toYear=" + toYear +
                ", regionsInfo=" + regionsInfo +
                '}';
    }
}
