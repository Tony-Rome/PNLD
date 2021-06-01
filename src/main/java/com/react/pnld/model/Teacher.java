package com.react.pnld.model;

import java.io.Serializable;

public class Teacher extends Person implements Serializable {

    private int teacherId;
    private String rut;
    private int age;
    private String department;
    private boolean participatedInPNLD;
    private String teachesInLevels;
    private boolean isTrainingApproved;
    private int trainingYear;

    public Teacher() {
        super();
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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
                "teacherId=" + teacherId +
                ", rut='" + rut + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", participatedInPNLD=" + participatedInPNLD +
                ", teachesInLevels='" + teachesInLevels + '\'' +
                ", isTrainingApproved=" + isTrainingApproved +
                ", trainingYear=" + trainingYear +
                '}';
    }
}
