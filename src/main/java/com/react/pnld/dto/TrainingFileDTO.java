package com.react.pnld.dto;

import com.react.pnld.services.FileUtilService;
import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.LowerCase;
import com.univocity.parsers.annotations.Parsed;
import org.postgresql.util.PGInterval;

public class TrainingFileDTO {

    @Parsed(index = 0)
    @LowerCase
    private String lastNames;

    @Parsed(index = 1)
    @LowerCase
    private String name;

    @Parsed(index = 2)
    @LowerCase
    private String rut;

    @Parsed(index = 3)
    @LowerCase
    private String institution;

    @Parsed(index = 4)
    @LowerCase
    private String department;

    @Parsed(index = 5)
    @LowerCase
    private String email;

    @Parsed(index = 6)
    @LowerCase
    private String testState;

    @Parsed(index = 7)
    @LowerCase
    private String startIn;

    @Parsed(index = 8)
    @LowerCase
    private String finishIn;

    private PGInterval requiredInterval;

    @Parsed(index = 10)
    @Format(formats = {"#0,00"}, options = "decimalSeparator=,")
    private float score;

    @Parsed(index = 11)
    @LowerCase
    private String answerOne;

    @Parsed(index = 12)
    @LowerCase
    private String answerTwo;

    @Parsed(index = 13)
    @LowerCase
    private String answerThree;

    @Parsed(index = 14)
    @LowerCase
    private String answerFour;

    @Parsed(index = 15)
    @LowerCase
    private String answerFive;

    @Parsed(index = 16)
    @LowerCase
    private String answerSix;

    @Parsed(index = 17)
    @LowerCase
    private String answerSeven;

    @Parsed(index = 18)
    @LowerCase
    private String answerEight;

    @Parsed(index = 19)
    @LowerCase
    private String answerNine;

    @Parsed(index = 20)
    @LowerCase
    private String answerTen;

    public TrainingFileDTO() {
        super();
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

    public PGInterval getRequiredInterval() {
        return requiredInterval;
    }

    public void setRequiredInterval(PGInterval requiredInterval) {
        this.requiredInterval = requiredInterval;
    }

    @Parsed(index = 9)
    public void setIntervalFromString(String requiredInterval){
        this.requiredInterval = FileUtilService.getRequiredTrainingInterval(requiredInterval);
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
                ", requiredInterval=" + requiredInterval +
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
