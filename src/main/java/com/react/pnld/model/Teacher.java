package com.react.pnld.model;

import java.io.Serializable;

public class Teacher extends Person implements Serializable {

    private int id;
    private int personId;
    private String rut;
    private int age;
    private String department;
    private boolean participateInPNLD;
    private String teachesSubjects;
    private String teachesInLevels;
    private String csResources;
    private String roboticsResources;

    public Teacher() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

    public boolean isParticipateInPNLD() {
        return participateInPNLD;
    }

    public void setParticipateInPNLD(boolean participateInPNLD) {
        this.participateInPNLD = participateInPNLD;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", personId=" + personId +
                ", rut='" + rut + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", participateInPNLD=" + participateInPNLD +
                ", teachesSubjects='" + teachesSubjects + '\'' +
                ", teachesInLevels='" + teachesInLevels + '\'' +
                ", csResources='" + csResources + '\'' +
                ", roboticsResources='" + roboticsResources + '\'' +
                '}';
    }
}
