package com.react.pnld.model;

public class Teacher {
    private int id;
    private int personId;

    public Teacher(){
        super();
    }

    public Teacher(int id, int personId) {
        this.id = id;
        this.personId = personId;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", personId=" + personId +
                '}';
    }
}
