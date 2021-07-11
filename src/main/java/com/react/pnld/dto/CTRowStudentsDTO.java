package com.react.pnld.dto;

import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.LowerCase;
import com.univocity.parsers.annotations.Parsed;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CTRowStudentsDTO {

    private Timestamp timeStamp;

    @Parsed(index = 1)
    private int score;

    @Parsed(index = 2)
    @LowerCase
    private String name;

    @Parsed(index = 3)
    @LowerCase
    private String lastNames;

    @Parsed(index = 4)
    @LowerCase
    private String gender;

    @Parsed(index = 5)
    private int age;

    @Parsed(index = 6)
    @LowerCase
    private String educationalInstitution;

    @Parsed(index = 7)
    private String level;

    @Parsed(index = 8)
    private String useCodeOrgPage;//TODO a boolean

    @Parsed(index = 9)
    private String useScratch; //TODO a boolean

    @Parsed(index = 10)
    private String initTime;

    private List<String> examples;

    private List<String> answers;

    public Timestamp getTimeStamp() {
        return timeStamp;
    }
    @Parsed(index = 0)
    @Format(formats = {"M-dd-yy HH:mm"}, options = "locale=en;lenient=false")
    public void setTimeStamp(Date timeStampString) {
        this.timeStamp = new Timestamp(timeStampString.getTime());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public String getFullName() {
        return this.name.concat(" ").concat(this.lastNames);
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUseCodeOrgPage() {
        return useCodeOrgPage;
    }

    public void setUseCodeOrgPage(String useCodeOrgPage) {
        this.useCodeOrgPage = useCodeOrgPage;
    }

    public String getUseScratch() {
        return useScratch;
    }

    public void setUseScratch(String useScratch) {
        this.useScratch = useScratch;
    }

    public String getInitTime() {
        return initTime;
    }

    public void setInitTime(String initTime) {
        this.initTime = initTime;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

}
