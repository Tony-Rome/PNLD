package com.react.pnld.model;

import java.io.Serializable;

public class School implements Serializable {

    private int rbd;
    private String name;
    private String commune;
    private String city;
    private int regionId;

    public School() {
        super();
    }

    public School(int rbd, String name, String commune, String city, int regionId) {
        this.rbd = rbd;
        this.name = name;
        this.commune = commune;
        this.city = city;
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getRbd() {
        return rbd;
    }

    public void setRbd(int rbd) {
        this.rbd = rbd;
    }

    @Override
    public String toString() {
        return "School{" +
                "rbd=" + rbd +
                ", name='" + name + '\'' +
                ", commune='" + commune + '\'' +
                ", city='" + city + '\'' +
                ", regionId=" + regionId +
                '}';
    }
}
