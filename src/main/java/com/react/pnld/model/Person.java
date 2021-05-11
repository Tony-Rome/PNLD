package com.react.pnld.model;

public class Person {
    private int personId;
    private String name;
    private String paternalLastName;
    private String maternalLastName;
    private String email;
    private int genderId;
    private int schoolId;

    public Person(){
        super();
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternalLastName() {
        return paternalLastName;
    }

    public void setPaternalLastName(String paternalLastName) {
        this.paternalLastName = paternalLastName;
    }

    public String getMaternalLastName() {
        return maternalLastName;
    }

    public void setMaternalLastName(String maternalLastName) {
        this.maternalLastName = maternalLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", paternalLastName='" + paternalLastName + '\'' +
                ", maternalLastName='" + maternalLastName + '\'' +
                ", email='" + email + '\'' +
                ", genderId=" + genderId +
                ", schoolId=" + schoolId +
                '}';
    }
}
