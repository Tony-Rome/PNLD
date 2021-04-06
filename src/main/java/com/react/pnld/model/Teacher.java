package com.react.pnld.model;

public class Teacher {
    private int id;
    private int idPerson;
    private String email;

    public Teacher(){
        super();
    }

    public Teacher(int id, int idPerson, String email) {
        this.id = id;
        this.idPerson = idPerson;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", idPerson=" + idPerson +
                ", email='" + email + '\'' +
                '}';
    }
}
