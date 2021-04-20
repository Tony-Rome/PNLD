package com.react.pnld.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "csv.headers")
public class CSVHeadersProperties {

    private String[] teacherRoster;
    private String[] teacherOptIn;
    private String[] studentLevel;
    private String[] signinPerCourse;
    private String[] signIns;
    private String[] diagnostico;
    private String[] preCapacita;
    private String[] postCapacita;
    private String[] testPc1;
    private String[] testPc2;
    private String[] testPc3;
    private String[] salida;
    private String[] satis;

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

    public String[] getSigninPerCourse() {
        return signinPerCourse;
    }

    public void setSigninPerCourse(String[] signinPerCourse) {
        this.signinPerCourse = signinPerCourse;
    }

    public String[] getSignIns() {
        return signIns;
    }

    public void setSignIns(String[] signIns) {
        this.signIns = signIns;
    }

    public String[] getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String[] diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String[] getPreCapacita() {
        return preCapacita;
    }

    public void setPreCapacita(String[] preCapacita) {
        this.preCapacita = preCapacita;
    }

    public String[] getPostCapacita() {
        return postCapacita;
    }

    public void setPostCapacita(String[] postCapacita) {
        this.postCapacita = postCapacita;
    }

    public String[] getTestPc1() {
        return testPc1;
    }

    public void setTestPc1(String[] testPc1) {
        this.testPc1 = testPc1;
    }

    public String[] getTestPc2() {
        return testPc2;
    }

    public void setTestPc2(String[] testPc2) {
        this.testPc2 = testPc2;
    }

    public String[] getTestPc3() {
        return testPc3;
    }

    public void setTestPc3(String[] testPc3) {
        this.testPc3 = testPc3;
    }

    public String[] getSalida() {
        return salida;
    }

    public void setSalida(String[] salida) {
        this.salida = salida;
    }

    public String[] getSatis() {
        return satis;
    }

    public void setSatis(String[] satis) {
        this.satis = satis;
    }
}
