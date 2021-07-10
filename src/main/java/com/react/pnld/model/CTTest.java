package com.react.pnld.model;

import java.sql.Timestamp;
import java.time.LocalTime;

public class CTTest {
    private int id;
    private int idLoadedFile;
    private Timestamp testDate;
    private int score;
    private String teacherRut;
    private String youKnowCode;
    private String youKnowScratch;
    private LocalTime initTime;
    private LocalTime finishTime;
    private int howDidInTheTest;
    private int howInterestedInTech;
    private String answers;

    public CTTest(){
        super();
    }

    public CTTest(int id, int idLoadedFile, Timestamp testDate, int score, String teacherRut, String youKnowCode,
                  String youKnowScratch, LocalTime initTime, LocalTime finishTime, int howDidInTheTest,
                  int howInterestedInTech, String answers) {
        super();
        this.id = id;
        this.idLoadedFile = idLoadedFile;
        this.testDate = testDate;
        this.score = score;
        this.teacherRut = teacherRut;
        this.youKnowCode = youKnowCode;
        this.youKnowScratch = youKnowScratch;
        this.initTime = initTime;
        this.finishTime = finishTime;
        this.howDidInTheTest = howDidInTheTest;
        this.howInterestedInTech = howInterestedInTech;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLoadedFile() {
        return idLoadedFile;
    }

    public void setIdLoadedFile(int idLoadedFile) {
        this.idLoadedFile = idLoadedFile;
    }

    public Timestamp getTestDate() {
        return testDate;
    }

    public void setTestDate(Timestamp testDate) {
        this.testDate = testDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTeacherRut() {
        return teacherRut;
    }

    public void setTeacherRut(String teacherRut) {
        this.teacherRut = teacherRut;
    }

    public String isYouKnowCode() {
        return youKnowCode;
    }

    public void setYouKnowCode(String youKnowCode) {
        this.youKnowCode = youKnowCode;
    }

    public String isYouKnowScratch() {
        return youKnowScratch;
    }

    public void setYouKnowScratch(String youKnowScratch) {
        this.youKnowScratch = youKnowScratch;
    }

    public LocalTime getInitTime() {
        return initTime;
    }

    public void setInitTime(LocalTime initTime) {
        this.initTime = initTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }

    public int getHowDidInTheTest() {
        return howDidInTheTest;
    }

    public void setHowDidInTheTest(int howDidInTheTest) {
        this.howDidInTheTest = howDidInTheTest;
    }

    public int getHowInterestedInTech() {
        return howInterestedInTech;
    }

    public void setHowInterestedInTech(int howInterestedInTech) {
        this.howInterestedInTech = howInterestedInTech;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "CTTest{" +
                "id=" + id +
                ", idLoadedFile=" + idLoadedFile +
                ", testDate=" + testDate +
                ", score=" + score +
                ", teacherRut='" + teacherRut + '\'' +
                ", youKnowCode=" + youKnowCode +
                ", youKnowScratch=" + youKnowScratch +
                ", initTime=" + initTime +
                ", finishTime=" + finishTime +
                ", howDidInTheTest=" + howDidInTheTest +
                ", howInterestedInTech=" + howInterestedInTech +
                ", answers='" + answers + '\'' +
                '}';
    }
}
