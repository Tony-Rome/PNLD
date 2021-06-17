package com.react.pnld.dto;

import com.univocity.parsers.annotations.Parsed;

import java.util.List;

public class CTTeacherDTO {

    @Parsed(index = 0)
    private String timeStamp;

    @Parsed(index = 1)
    private String rut;

    @Parsed(index = 2)
    private String name;

    @Parsed(index = 3)
    private String lastNames;

    @Parsed(index = 4)
    private String email;

    @Parsed(index = 5)
    private String gender;

    @Parsed(index = 6)
    private String age;

    @Parsed(index = 7)
    private String educationalInstitution;

    @Parsed(index = 8)
    private String teachesInLevels;

    @Parsed(index = 9)
    private String participatedInPNLD;

    @Parsed(index = 10)
    private String youKnowCode;

    @Parsed(index = 11)
    private String younKnowScratch;

    @Parsed(index = 27)
    private int howDoYouThinkYouDidInTheTest;

    @Parsed(index = 28)
    private int howInterestedAreYouInComputers;

    @Parsed(index = 29)
    private String finishTime;

    private List<String> answers;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
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

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(String educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public String getTeachesInLevels() {
        return teachesInLevels;
    }

    public void setTeachesInLevels(String teachesInLevels) {
        this.teachesInLevels = teachesInLevels;
    }

    public String getParticipatedInPNLD() {
        return participatedInPNLD;
    }

    public void setParticipatedInPNLD(String participatedInPNLD) {
        this.participatedInPNLD = participatedInPNLD;
    }

    public String getYouKnowCode() {
        return youKnowCode;
    }

    public void setYouKnowCode(String youKnowCode) {
        this.youKnowCode = youKnowCode;
    }

    public String getYounKnowScratch() {
        return younKnowScratch;
    }

    public void setYounKnowScratch(String younKnowScratch) {
        this.younKnowScratch = younKnowScratch;
    }

    public int getHowDoYouThinkYouDidInTheTest() {
        return howDoYouThinkYouDidInTheTest;
    }

    public void setHowDoYouThinkYouDidInTheTest(int howDoYouThinkYouDidInTheTest) {
        this.howDoYouThinkYouDidInTheTest = howDoYouThinkYouDidInTheTest;
    }

    public int getHowInterestedAreYouInComputers() {
        return howInterestedAreYouInComputers;
    }

    public void setHowInterestedAreYouInComputers(int howInterestedAreYouInComputers) {
        this.howInterestedAreYouInComputers = howInterestedAreYouInComputers;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "CTTeacherDTO{" +
                "timeStamp='" + timeStamp + '\'' +
                ", rut='" + rut + '\'' +
                ", name='" + name + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", educationalInstitution='" + educationalInstitution + '\'' +
                ", teachesInLevels='" + teachesInLevels + '\'' +
                ", participatedInPNLD='" + participatedInPNLD + '\'' +
                ", youKnowCode='" + youKnowCode + '\'' +
                ", younKnowScratch='" + younKnowScratch + '\'' +
                ", howDoYouThinkYouDidInTheTest=" + howDoYouThinkYouDidInTheTest +
                ", howInterestedAreYouInComputers=" + howInterestedAreYouInComputers +
                ", finishTime='" + finishTime + '\'' +
                ", answers=" + answers +
                '}';
    }
}
