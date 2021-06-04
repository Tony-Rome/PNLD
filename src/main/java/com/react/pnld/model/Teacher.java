package com.react.pnld.model;

import java.io.Serializable;

public class Teacher implements Serializable {

    private String rut;
    private int schoolId;
    private int genderId;
    private String name;
    private String email;
    private int age;
    private String department;
    private boolean participatedInPNLD;
    private String teachesInLevels;
    private boolean isTrainingApproved;
    private int trainingYear;

    public Teacher() {
        super();
    }

    public Teacher(String rut, int schoolId, int genderId, String name, String email, int age,
                   String department, boolean participatedInPNLD, String teachesInLevels, boolean isTrainingApproved,
                   int trainingYear) {
        this.rut = rut;
        this.schoolId = schoolId;
        this.genderId = genderId;
        this.name = name;
        this.email = email;
        this.age = age;
        this.department = department;
        this.participatedInPNLD = participatedInPNLD;
        this.teachesInLevels = teachesInLevels;
        this.isTrainingApproved = isTrainingApproved;
        this.trainingYear = trainingYear;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
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
                "rut='" + rut + '\'' +
                ", schoolId=" + schoolId +
                ", genderId=" + genderId +
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
