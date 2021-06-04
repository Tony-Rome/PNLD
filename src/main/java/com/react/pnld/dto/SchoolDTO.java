package com.react.pnld.dto;

public class SchoolDTO {

    private String name;
    private String commune;
    private String city;
    private int regionId;
    private int rbd;

    public SchoolDTO() {
        super();
    }

    public SchoolDTO(String name, String commune, String city, int regionId, int rbd) {
        super();
        this.name = name;
        this.commune = commune;
        this.city = city;
        this.regionId = regionId;
        this.rbd = rbd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
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
        return "SchoolDTO{" +
                "name='" + name + '\'' +
                ", commune='" + commune + '\'' +
                ", city='" + city + '\'' +
                ", regionId=" + regionId +
                ", rbd=" + rbd +
                '}';
    }
}
