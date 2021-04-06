package com.react.pnld.model;

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String motherLastName;
    private String rut;
    private String email;
    private int genderId;

    public Person(){
        super();
    }

    public Person(int id, String firstName, String lastName, String motherLastName,
                  String rut, String email, int genderId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.rut = rut;
        this.email = email;
        this.genderId = genderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", motherLastName='" + motherLastName + '\'' +
                ", rut='" + rut + '\'' +
                ", email='" + email + '\'' +
                ", genderId=" + genderId +
                '}';
    }
}
