package com.react.pnld.model;

import java.io.Serializable;

public class Teacher implements Serializable {

    private String rut;
    private String name;
    private int age;
    private int genderId;
    private String email;
    private String department;
    private boolean participatedInPNLD;
    private String teachesInLevels;
    private boolean isTrainingApproved;
    private int trainingYear;
    private int schoolRbd;

    public Teacher() {
        super();
    }

    public Teacher(String rut, String name, int age, int genderId, String email, String department,
                   boolean participatedInPNLD, String teachesInLevels, boolean isTrainingApproved, int trainingYear,
                   int schoolRbd) {
        super();
        this.rut = rut;
        this.name = name;
        this.age = age;
        this.genderId = genderId;
        this.email = email;
        this.department = department;
        this.participatedInPNLD = participatedInPNLD;
        this.teachesInLevels = teachesInLevels;
        this.isTrainingApproved = isTrainingApproved;
        this.trainingYear = trainingYear;
        this.schoolRbd = schoolRbd;
    }

    public int getSchoolRbd() {
        return schoolRbd;
    }

    public void setSchoolRbd(int schoolRbd) {
        this.schoolRbd = schoolRbd;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isParticipatedInPNLD() {
        return participatedInPNLD;
    }

    public void setParticipatedInPNLD(boolean participatedInPNLD) {
        this.participatedInPNLD = participatedInPNLD;
    }

    public String getTeachesInLevels() {
        return teachesInLevels;
    }

    public void setTeachesInLevels(String teachesInLevels) {
        this.teachesInLevels = teachesInLevels;
    }

    public boolean isTrainingApproved() {
        return isTrainingApproved;
    }

    public void setTrainingApproved(boolean trainingApproved) {
        isTrainingApproved = trainingApproved;
    }

    public int getTrainingYear() {
        return trainingYear;
    }

    public void setTrainingYear(int trainingYear) {
        this.trainingYear = trainingYear;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "schoolRbd=" + schoolRbd +
                ", genderId=" + genderId +
                ", rut='" + rut + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", participatedInPNLD=" + participatedInPNLD +
                ", teachesInLevels='" + teachesInLevels + '\'' +
                ", isTrainingApproved=" + isTrainingApproved +
                ", trainingYear=" + trainingYear +
                '}';
    }
}
