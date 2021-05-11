package com.react.pnld.model;

import java.io.Serializable;

public class Teacher extends Person implements Serializable {

    private int teacherId;
    private String rut;
    private int age;
    private String department;
    private boolean participatedInPNLD;
    private String teachesSubjects;
    private String teachesInLevels;
    private String csResources;
    private String roboticsResources;
    private int trainingId;

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

    public String getTeachesSubjects() {
        return teachesSubjects;
    }

    public void setTeachesSubjects(String teachesSubjects) {
        this.teachesSubjects = teachesSubjects;
    }

    public String getTeachesInLevels() {
        return teachesInLevels;
    }

    public void setTeachesInLevels(String teachesInLevels) {
        this.teachesInLevels = teachesInLevels;
    }

    public String getCsResources() {
        return csResources;
    }

    public void setCsResources(String csResources) {
        this.csResources = csResources;
    }

    public String getRoboticsResources() {
        return roboticsResources;
    }

    public void setRoboticsResources(String roboticsResources) {
        this.roboticsResources = roboticsResources;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", rut='" + rut + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", participatedInPNLD=" + participatedInPNLD +
                ", teachesSubjects='" + teachesSubjects + '\'' +
                ", teachesInLevels='" + teachesInLevels + '\'' +
                ", csResources='" + csResources + '\'' +
                ", roboticsResources='" + roboticsResources + '\'' +
                ", trainingId='" + trainingId + '\'' +
                '}';
    }
}
