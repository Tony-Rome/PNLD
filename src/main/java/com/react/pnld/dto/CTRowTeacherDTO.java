package com.react.pnld.dto;

import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.LowerCase;
import com.univocity.parsers.annotations.Parsed;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CTRowTeacherDTO {


    private Timestamp timeStamp;

    @Parsed(index = 1)
    @LowerCase
    private String rut;

    @Parsed(index = 2)
    @LowerCase
    private String name;

    @Parsed(index = 3)
    @LowerCase
    private String lastNames;

    @Parsed(index = 4)
    @LowerCase
    private String email;

    @Parsed(index = 5)
    @LowerCase
    private String gender;

    @Parsed(index = 6)
    private int age;

    @Parsed(index = 7)
    @LowerCase
    private String educationalInstitution;

    @Parsed(index = 8)
    @LowerCase
    private String teachesInLevels;

    @Parsed(index = 9)
    @LowerCase
    private String participatedInPNLD;

    @Parsed(index = 10)
    @LowerCase
    private String youKnowCode;

    @Parsed(index = 11)
    @LowerCase
    private String youKnowScratch;

    @Parsed(index = 27)
    @LowerCase
    private int howDoYouThinkYouDidInTheTest;

    @Parsed(index = 28)
    @LowerCase
    private int howInterestedAreYouInComputers;

    @Parsed(index = 29)
    private String finishTime;

    private List<String> answers;

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    @Parsed(index = 0)
    @Format(formats = {"M-dd-yy HH:mm"}, options = "locale=en;lenient=false")
    public void setTimeStamp(Date timeStampString) {
        this.timeStamp = new Timestamp(timeStampString.getTime());
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public String getYouKnowScratch() {
        return youKnowScratch;
    }

    public void setYouKnowScratch(String youKnowScratch) {
        this.youKnowScratch = youKnowScratch;
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
                ", youKnowScratch='" + youKnowScratch + '\'' +
                ", howDoYouThinkYouDidInTheTest=" + howDoYouThinkYouDidInTheTest +
                ", howInterestedAreYouInComputers=" + howInterestedAreYouInComputers +
                ", finishTime='" + finishTime + '\'' +
                ", answers=" + answers +
                '}';
    }
}
