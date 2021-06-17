package com.react.pnld.dto;

import com.univocity.parsers.annotations.Parsed;

import java.util.List;

public class CTTestStudentsDTO {

    @Parsed(index = 0)
    private String timeStamp;

    @Parsed(index = 1)
    private String name;

    @Parsed(index = 2)
    private String lastNames;

    @Parsed(index = 3)
    private String gender;

    @Parsed(index = 4)
    private String age;

    @Parsed(index = 5)
    private String educationalInstitution;

    @Parsed(index = 6)
    private String level;

    @Parsed(index = 7)
    private String useCodeOrgPage;//TODO a boolean

    @Parsed(index = 8)
    private String useScratch; //TODO a boolean

    @Parsed(index = 9)
    private String initTime;

    private List<String> examples;

    private List<String> answers;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
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

    @Override
    public String toString() {
        return "CTTestStudentsDTO{" +
                "timeStamp='" + timeStamp + '\'' +
                ", name='" + name + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", educationalInstitution='" + educationalInstitution + '\'' +
                ", level='" + level + '\'' +
                ", useCodeOrgPage='" + useCodeOrgPage + '\'' +
                ", useScratch='" + useScratch + '\'' +
                ", initTime='" + initTime + '\'' +
                ", examples=" + examples +
                ", answers=" + answers +
                '}';
    }
}
