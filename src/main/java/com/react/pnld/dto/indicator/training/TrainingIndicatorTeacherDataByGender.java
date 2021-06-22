package com.react.pnld.dto.indicator.training;

import java.io.Serializable;

public class TrainingIndicatorTeacherDataByGender implements Serializable {

    private String gender;
    private int approvedTrainingNumber;
    private int notApprovedTrainingNumber;
    private int assistanceNumber;
    private int notAssistanceNumber;
    private int preTestCompletedNumber;
    private int preTestNotCompletedNumber;
    private int postTestCompletedNumber;
    private int postTestNotCompletedNumber;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getApprovedTrainingNumber() {
        return approvedTrainingNumber;
    }

    public void setApprovedTrainingNumber(int approvedTrainingNumber) {
        this.approvedTrainingNumber = approvedTrainingNumber;
    }

    public int getNotApprovedTrainingNumber() {
        return notApprovedTrainingNumber;
    }

    public void setNotApprovedTrainingNumber(int notApprovedTrainingNumber) {
        this.notApprovedTrainingNumber = notApprovedTrainingNumber;
    }

    public int getAssistanceNumber() {
        return assistanceNumber;
    }

    public void setAssistanceNumber(int assistanceNumber) {
        this.assistanceNumber = assistanceNumber;
    }

    public int getNotAssistanceNumber() {
        return notAssistanceNumber;
    }

    public void setNotAssistanceNumber(int notAssistanceNumber) {
        this.notAssistanceNumber = notAssistanceNumber;
    }

    public int getPreTestCompletedNumber() {
        return preTestCompletedNumber;
    }

    public void setPreTestCompletedNumber(int preTestCompletedNumber) {
        this.preTestCompletedNumber = preTestCompletedNumber;
    }

    public int getPreTestNotCompletedNumber() {
        return preTestNotCompletedNumber;
    }

    public void setPreTestNotCompletedNumber(int preTestNotCompletedNumber) {
        this.preTestNotCompletedNumber = preTestNotCompletedNumber;
    }

    public int getPostTestCompletedNumber() {
        return postTestCompletedNumber;
    }

    public void setPostTestCompletedNumber(int postTestCompletedNumber) {
        this.postTestCompletedNumber = postTestCompletedNumber;
    }

    public int getPostTestNotCompletedNumber() {
        return postTestNotCompletedNumber;
    }

    public void setPostTestNotCompletedNumber(int postTestNotCompletedNumber) {
        this.postTestNotCompletedNumber = postTestNotCompletedNumber;
    }

    @Override
    public String toString() {
        return "TrainingIndicatorTeacherDataByGender{" +
                "gender='" + gender + '\'' +
                ", approvedTrainingNumber=" + approvedTrainingNumber +
                ", notApprovedTrainingNumber=" + notApprovedTrainingNumber +
                ", assistanceNumber=" + assistanceNumber +
                ", notAssistanceNumber=" + notAssistanceNumber +
                ", preTestCompletedNumber=" + preTestCompletedNumber +
                ", preTestNotCompletedNumber=" + preTestNotCompletedNumber +
                ", postTestCompletedNumber=" + postTestCompletedNumber +
                ", postTestNotCompletedNumber=" + postTestNotCompletedNumber +
                '}';
    }
}
