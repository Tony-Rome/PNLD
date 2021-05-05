package com.react.pnld.model;

import java.io.Serializable;

public class School implements Serializable {

    private int id;
    private String name;
    private String city;
    private int regionId;
    private int rbd;

    public School(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", regionId=" + regionId +
                ", rbd=" + rbd +
                '}';
    }
}
