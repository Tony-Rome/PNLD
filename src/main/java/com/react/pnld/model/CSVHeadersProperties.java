package com.react.pnld.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "csv.headers")
public class CSVHeadersProperties {

    private String[] teacherRoster;
    private String[] teacherOptIn;
    private String[] studentLevel;
    private String[] signInPerCourse;
    private String[] signIns;
    private String[] diagnosis;
    private String[] preTraining;
    private String[] postTraining;
    private String[] ctTestGroupA;
    private String[] ctTestGroupB;
    private String[] ctTestTeacher;
    private String[] satisfaction;
    private String generalResume;
    private String delimiters;

    public String[] getTeacherRoster() {
        return teacherRoster;
    }

    public void setTeacherRoster(String[] teacherRoster) {
        this.teacherRoster = teacherRoster;
    }

    public String[] getTeacherOptIn() {
        return teacherOptIn;
    }

    public void setTeacherOptIn(String[] teacherOptIn) {
        this.teacherOptIn = teacherOptIn;
    }

    public String[] getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(String[] studentLevel) {
        this.studentLevel = studentLevel;
    }

    public String[] getSignInPerCourse() {
        return signInPerCourse;
    }

    public void setSignInPerCourse(String[] signInPerCourse) {
        this.signInPerCourse = signInPerCourse;
    }

    public String[] getSignIns() {
        return signIns;
    }

    public void setSignIns(String[] signIns) {
        this.signIns = signIns;
    }

    public String[] getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String[] diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String[] getPreTraining() {
        return preTraining;
    }

    public void setPreTraining(String[] preTraining) {
        this.preTraining = preTraining;
    }

    public String[] getPostTraining() {
        return postTraining;
    }

    public void setPostTraining(String[] postTraining) {
        this.postTraining = postTraining;
    }

    public String[] getCTTestGroupA() {
        return ctTestGroupA;
    }

    public void setCTTestGroupA(String[] ctTestGroupA) {
        this.ctTestGroupA = ctTestGroupA;
    }

    public String[] getCTTestGroupB() {
        return ctTestGroupB;
    }

    public void setCTTestGroupB(String[] ctTestGroupB) {
        this.ctTestGroupB = ctTestGroupB;
    }

    public String[] getCTTestTeacher() {
        return ctTestTeacher;
    }

    public void setCTTestTeacher(String[] testCT3) {
        this.ctTestTeacher = testCT3;
    }

    public String[] getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String[] exitSatisfaction) {
        this.satisfaction = exitSatisfaction;
    }

    public String getGeneralResume() {
        return generalResume;
    }

    public void setGeneralResume(String generalResume) {
        this.generalResume = generalResume;
    }

    public String[] getGeneralResumeArray() {
        return generalResume.replaceAll("(, |[^a-zA-Z0-9,;])", "").toLowerCase().split(this.delimiters);
    }

    public String getDelimiters() {
        return delimiters;
    }

    public void setDelimiters(String delimiters) {
        this.delimiters = delimiters;
    }
}
