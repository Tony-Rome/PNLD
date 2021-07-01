package com.react.pnld.dto;

import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.LowerCase;
import com.univocity.parsers.annotations.Parsed;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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

    private int[] teachesInLevels;

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

    private LocalTime initTime;

    private LocalTime finishTime;

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

    public int[] getTeachesInLevels() {
        return teachesInLevels;
    }

    public void setTeachesInLevels(int[] teachesInLevels) {
        this.teachesInLevels = teachesInLevels;
    }

    @Parsed(index = 8)
    @LowerCase
    private void buildTeachesInLevels(String levels){
        String onlyNumberLevels = levels.replaceAll("[^0-9,]","");
        int levelsArray[] = Arrays.stream(onlyNumberLevels.split(",")).mapToInt(Integer::parseInt).toArray();
        this.setTeachesInLevels(levelsArray);
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

    public LocalTime getInitTime() {
        return initTime;
    }
    @Parsed(index = 29)
    public void setInitTime(String initTime) {

        if(initTime != null){
            LocalTime localTime = LocalTime.parse(initTime,  DateTimeFormatter.ofPattern("h:mm:ss a"));
            this.initTime = localTime;
        }
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    @Parsed(index = 30)
    public void setFinishTime(String finishTime) {
        if(finishTime != null){
            LocalTime localTime = LocalTime.parse(finishTime,  DateTimeFormatter.ofPattern("h:mm:ss a"));
            this.finishTime = localTime;
        }
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "CTRowTeacherDTO{" +
                "timeStamp=" + timeStamp +
                ", rut='" + rut + '\'' +
                ", name='" + name + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", educationalInstitution='" + educationalInstitution + '\'' +
                ", teachesInLevels=" + Arrays.toString(teachesInLevels) +
                ", youKnowCode='" + youKnowCode + '\'' +
                ", youKnowScratch='" + youKnowScratch + '\'' +
                ", howDoYouThinkYouDidInTheTest=" + howDoYouThinkYouDidInTheTest +
                ", howInterestedAreYouInComputers=" + howInterestedAreYouInComputers +
                ", initTime=" + initTime +
                ", finishTime=" + finishTime +
                ", answers=" + answers +
                '}';
    }
}
