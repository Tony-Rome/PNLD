package com.react.pnld.controller.response;

import java.io.Serializable;

public class DimensionResponse implements Serializable {
    private String dimensionName;

    public DimensionResponse(){
        super();
    }

    public DimensionResponse(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    @Override
    public String toString() {
        return "DimensionResponse{" +
                "dimensionName='" + dimensionName + '\'' +
                '}';
    }
}
