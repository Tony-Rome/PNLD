package com.react.pnld.model;

public class TestQuestion {

    private int id;
    private int testId;
    private String ansOne;
    private String ansTwo;
    private String ansThree;
    private String ansFour;
    private String ansFive;
    private String ansSix;
    private String ansSeven;
    private String ansEight;
    private String ansNine;
    private String ansTen;

    public TestQuestion(){
        super();
    }

    public TestQuestion(int id, int testId, String ansOne, String ansTwo, String ansThree, String ansFour,
                        String ansFive, String ansSix, String ansSeven, String ansEight, String ansNine, String ansTen) {
        this.id = id;
        this.testId = testId;
        this.ansOne = ansOne;
        this.ansTwo = ansTwo;
        this.ansThree = ansThree;
        this.ansFour = ansFour;
        this.ansFive = ansFive;
        this.ansSix = ansSix;
        this.ansSeven = ansSeven;
        this.ansEight = ansEight;
        this.ansNine = ansNine;
        this.ansTen = ansTen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getAnsOne() {
        return ansOne;
    }

    public void setAnsOne(String ansOne) {
        this.ansOne = ansOne;
    }

    public String getAnsTwo() {
        return ansTwo;
    }

    public void setAnsTwo(String ansTwo) {
        this.ansTwo = ansTwo;
    }

    public String getAnsThree() {
        return ansThree;
    }

    public void setAnsThree(String ansThree) {
        this.ansThree = ansThree;
    }

    public String getAnsFour() {
        return ansFour;
    }

    public void setAnsFour(String ansFour) {
        this.ansFour = ansFour;
    }

    public String getAnsFive() {
        return ansFive;
    }

    public void setAnsFive(String ansFive) {
        this.ansFive = ansFive;
    }

    public String getAnsSix() {
        return ansSix;
    }

    public void setAnsSix(String ansSix) {
        this.ansSix = ansSix;
    }

    public String getAnsSeven() {
        return ansSeven;
    }

    public void setAnsSeven(String ansSeven) {
        this.ansSeven = ansSeven;
    }

    public String getAnsEight() {
        return ansEight;
    }

    public void setAnsEight(String ansEight) {
        this.ansEight = ansEight;
    }

    public String getAnsNine() {
        return ansNine;
    }

    public void setAnsNine(String ansNine) {
        this.ansNine = ansNine;
    }

    public String getAnsTen() {
        return ansTen;
    }

    public void setAnsTen(String ansTen) {
        this.ansTen = ansTen;
    }

    @Override
    public String toString() {
        return "TestQuestion{" +
                "id=" + id +
                ", testId=" + testId +
                ", ansOne=" + ansOne +
                ", ansTwo=" + ansTwo +
                ", ansThree=" + ansThree +
                ", ansFour=" + ansFour +
                ", ansFive=" + ansFive +
                ", ansSix=" + ansSix +
                ", ansSeven=" + ansSeven +
                ", ansEight=" + ansEight +
                ", ansNine=" + ansNine +
                ", ansTen=" + ansTen +
                '}';
    }
}
