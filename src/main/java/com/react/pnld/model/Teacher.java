package com.react.pnld.model;

import java.io.Serializable;

public class Teacher implements Serializable {

    private String rut;
    private String name;
    private int age;
    private int genderId;
    private String email;
    private String department;
    private String teachesInLevels;
    private boolean isTrainingApproved;
    private int trainingYear;
    private boolean attendsInPerson;
    private int schoolRbd;

    public Teacher() {
        super();
    }

    public Teacher(String rut, String name, int age, int genderId, String email, String department, String teachesInLevels,
                   boolean isTrainingApproved, int trainingYear, boolean attendsInPerson, int schoolRbd) {
        super();
        this.rut = rut;
        this.name = name;
        this.age = age;
        this.genderId = genderId;
        this.email = email;
        this.department = department;
        this.teachesInLevels = teachesInLevels;
        this.isTrainingApproved = isTrainingApproved;
        this.trainingYear = trainingYear;
        this.attendsInPerson = attendsInPerson;
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

    public boolean isAttendsInPerson() {
        return attendsInPerson;
    }

    public void setAttendsInPerson(boolean attendsInPerson) {
        this.attendsInPerson = attendsInPerson;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "rut='" + rut + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", genderId=" + genderId +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", teachesInLevels='" + teachesInLevels + '\'' +
                ", isTrainingApproved=" + isTrainingApproved +
                ", trainingYear=" + trainingYear +
                ", attendsInPerson=" + attendsInPerson +
                ", schoolRbd=" + schoolRbd +
                '}';
    }
}
