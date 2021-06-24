package com.react.pnld.dto.indicator.training;

import java.io.Serializable;

public class TrainingIndicatorTeacherDataByGender implements Serializable {

    private String gender;
    private int approvedTrainingCounter;
    private int notApprovedTrainingCounter;
    private int assistanceCounter;
    private int notAssistanceCounter;
    private int preTestCompletedCounter;
    private int preTestNotCompletedCounter;
    private int postTestCompletedCounter;
    private int postTestNotCompletedCounter;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getApprovedTrainingCounter() {
        return approvedTrainingCounter;
    }

    public void setApprovedTrainingCounter(int approvedTrainingCounter) {
        this.approvedTrainingCounter = approvedTrainingCounter;
    }

    public int getNotApprovedTrainingCounter() {
        return notApprovedTrainingCounter;
    }

    public void setNotApprovedTrainingCounter(int notApprovedTrainingCounter) {
        this.notApprovedTrainingCounter = notApprovedTrainingCounter;
    }

    public int getAssistanceCounter() {
        return assistanceCounter;
    }

    public void setAssistanceCounter(int assistanceCounter) {
        this.assistanceCounter = assistanceCounter;
    }

    public int getNotAssistanceCounter() {
        return notAssistanceCounter;
    }

    public void setNotAssistanceCounter(int notAssistanceCounter) {
        this.notAssistanceCounter = notAssistanceCounter;
    }

    public int getPreTestCompletedCounter() {
        return preTestCompletedCounter;
    }

    public void setPreTestCompletedCounter(int preTestCompletedCounter) {
        this.preTestCompletedCounter = preTestCompletedCounter;
    }

    public int getPreTestNotCompletedCounter() {
        return preTestNotCompletedCounter;
    }

    public void setPreTestNotCompletedCounter(int preTestNotCompletedCounter) {
        this.preTestNotCompletedCounter = preTestNotCompletedCounter;
    }

    public int getPostTestCompletedCounter() {
        return postTestCompletedCounter;
    }

    public void setPostTestCompletedCounter(int postTestCompletedCounter) {
        this.postTestCompletedCounter = postTestCompletedCounter;
    }

    public int getPostTestNotCompletedCounter() {
        return postTestNotCompletedCounter;
    }

    public void setPostTestNotCompletedCounter(int postTestNotCompletedCounter) {
        this.postTestNotCompletedCounter = postTestNotCompletedCounter;
    }

    @Override
    public String toString() {
        return "TrainingIndicatorTeacherDataByGender{" +
                "gender='" + gender + '\'' +
                ", approvedTrainingCounter=" + approvedTrainingCounter +
                ", notApprovedTrainingCounter=" + notApprovedTrainingCounter +
                ", assistanceCounter=" + assistanceCounter +
                ", notAssistanceCounter=" + notAssistanceCounter +
                ", preTestCompletedCounter=" + preTestCompletedCounter +
                ", preTestNotCompletedCounter=" + preTestNotCompletedCounter +
                ", postTestCompletedCounter=" + postTestCompletedCounter +
                ", postTestNotCompletedCounter=" + postTestNotCompletedCounter +
                '}';
    }
}
