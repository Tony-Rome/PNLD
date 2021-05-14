package com.react.pnld.model;

import java.sql.Timestamp;

public class ExitSatisfactionQuestionnaire {

    private int id;
    private int loadedFileId;
    private int teacherId;
    private int responseId;
    private Timestamp sendDate;
    private String answers;
    private int numberId;
    private String course;
    private String group;

    public ExitSatisfactionQuestionnaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoadedFileId() {
        return loadedFileId;
    }

    public void setLoadedFileId(int loadedFileId) {
        this.loadedFileId = loadedFileId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public int getNumberId() {
        return numberId;
    }

    public void setNumberId(int numberId) {
        this.numberId = numberId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "ExitQuestionnaire{" +
                "id=" + id +
                ", loadedFileId=" + loadedFileId +
                ", teacherId=" + teacherId +
                ", responseId=" + responseId +
                ", sendDate=" + sendDate +
                ", answers='" + answers + '\'' +
                ", numberId=" + numberId +
                ", course='" + course + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
