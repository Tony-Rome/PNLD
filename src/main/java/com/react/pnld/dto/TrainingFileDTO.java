package com.react.pnld.dto;

import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.Parsed;

public class TrainingFileDTO {

    @Parsed(index = 0)
    private String lastNames;

    @Parsed(index = 1)
    private String name;

    @Parsed(index = 2)
    private String rut;

    @Parsed(index = 3)
    private String institution;

    @Parsed(index = 4)
    private String department;

    @Parsed(index = 5)
    private String email;

    @Parsed(index = 6)
    private String testState;

    @Parsed(index = 7)
    private String startIn;

    @Parsed(index = 8)
    private String finishIn;

    private String duration;

    @Parsed(index = 10)
    @Format(formats = {"#0,00"}, options = "decimalSeparator=,")
    private float score;

    @Parsed(index = 11)
    private String answerOne;

    @Parsed(index = 12)
    private String answerTwo;

    @Parsed(index = 13)
    private String answerThree;

    @Parsed(index = 14)
    private String answerFour;

    @Parsed(index = 15)
    private String answerFive;

    @Parsed(index = 16)
    private String answerSix;

    @Parsed(index = 17)
    private String answerSeven;

    @Parsed(index = 18)
    private String answerEight;

    @Parsed(index = 19)
    private String answerNine;

    @Parsed(index = 20)
    private String answerTen;

    public TrainingFileDTO() {
        super();
    }

    public TrainingFileDTO(String lastNames, String name, String rut, String institution, String department,
                           String email, String testState, String startIn, String finishIn, String duration, float score,
                           String answerOne, String answerTwo, String answerThree, String answerFour, String answerFive,
                           String answerSix, String answerSeven, String answerEight, String answerNine, String answerTen) {
        super();
        this.lastNames = lastNames;
        this.name = name;
        this.rut = rut;
        this.institution = institution;
        this.department = department;
        this.email = email;
        this.testState = testState;
        this.startIn = startIn;
        this.finishIn = finishIn;
        this.duration = duration;
        this.score = score;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.answerFive = answerFive;
        this.answerSix = answerSix;
        this.answerSeven = answerSeven;
        this.answerEight = answerEight;
        this.answerNine = answerNine;
        this.answerTen = answerTen;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTestState() {
        return testState;
    }

    public void setTestState(String testState) {
        this.testState = testState;
    }

    public String getStartIn() {
        return startIn;
    }

    public void setStartIn(String startIn) {
        this.startIn = startIn;
    }

    public String getFinishIn() {
        return finishIn;
    }

    public void setFinishIn(String finishIn) {
        this.finishIn = finishIn;
    }

    public String getDuration() {
        return duration;
    }

    @Parsed(index = 9)
    public void setDuration(String duration) {
        //TODO implement string to Duration
        //LocalTime localTimeDuration =  duration.toInstant().atZone(ZoneId.of("UTC")).toLocalTime();
        //this.duration = localTimeDuration.withHour(0);
        this.duration = duration;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    public String getAnswerFive() {
        return answerFive;
    }

    public void setAnswerFive(String answerFive) {
        this.answerFive = answerFive;
    }

    public String getAnswerSix() {
        return answerSix;
    }

    public void setAnswerSix(String answerSix) {
        this.answerSix = answerSix;
    }

    public String getAnswerSeven() {
        return answerSeven;
    }

    public void setAnswerSeven(String answerSeven) {
        this.answerSeven = answerSeven;
    }

    public String getAnswerEight() {
        return answerEight;
    }

    public void setAnswerEight(String answerEight) {
        this.answerEight = answerEight;
    }

    public String getAnswerNine() {
        return answerNine;
    }

    public void setAnswerNine(String answerNine) {
        this.answerNine = answerNine;
    }

    public String getAnswerTen() {
        return answerTen;
    }

    public void setAnswerTen(String answerTen) {
        this.answerTen = answerTen;
    }

    @Override
    public String toString() {
        return "PostTrainingDTO{" +
                "lastNames='" + lastNames + '\'' +
                ", name='" + name + '\'' +
                ", rut='" + rut + '\'' +
                ", institution='" + institution + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", testState='" + testState + '\'' +
                ", startIn=" + startIn +
                ", finishIn=" + finishIn +
                ", duration=" + duration +
                ", score=" + score +
                ", answerOne='" + answerOne + '\'' +
                ", answerTwo='" + answerTwo + '\'' +
                ", answerThree='" + answerThree + '\'' +
                ", answerFour='" + answerFour + '\'' +
                ", answerFive='" + answerFive + '\'' +
                ", answerSix='" + answerSix + '\'' +
                ", answerSeven='" + answerSeven + '\'' +
                ", answerEight='" + answerEight + '\'' +
                ", answerNine='" + answerNine + '\'' +
                ", answerTen='" + answerTen + '\'' +
                '}';
    }
}
