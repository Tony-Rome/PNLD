package com.react.pnld.dto;

import java.io.Serializable;

public class TrainingTeacherIndicatorDataByTeacherDTO implements Serializable {

    private int year;
    private String gender;
    private boolean trainingState; //TODO: Con este se calcula el porcentaje aprobado
    private String assistanceState;
    private boolean preTestState;
    private boolean postTestState;
    private boolean onlineCourseState;

    //TODO: agregar puntajes pre y post-test
    //TODO: Por definir si es el total de test o por pregunta


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isTrainingState() {
        return trainingState;
    }

    public void setTrainingState(boolean trainingState) {
        this.trainingState = trainingState;
    }

    public String isAssistanceState() {
        return assistanceState;
    }

    public void setAssistanceState(String assistanceState) {
        this.assistanceState = assistanceState;
    }

    public boolean isPreTestState() {
        return preTestState;
    }

    public void setPreTestState(boolean preTestState) {
        this.preTestState = preTestState;
    }

    public boolean isPostTestState() {
        return postTestState;
    }

    public void setPostTestState(boolean postTestState) {
        this.postTestState = postTestState;
    }

    public boolean isOnlineCourseState() {
        return onlineCourseState;
    }

    public void setOnlineCourseState(boolean onlineCourseState) {
        this.onlineCourseState = onlineCourseState;
    }

    @Override
    public String toString() {
        return "TrainingTeacherIndicatorDataByTeacherDTO{" +
                "year=" + year +
                ", gender='" + gender + '\'' +
                ", trainingState=" + trainingState +
                ", assistanceState=" + assistanceState +
                ", preTestState=" + preTestState +
                ", postTestState=" + postTestState +
                ", onlineCourseState=" + onlineCourseState +
                '}';
    }
}
