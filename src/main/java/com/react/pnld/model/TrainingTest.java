package com.react.pnld.model;

import org.postgresql.util.PGInterval;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TrainingTest implements Serializable {

    private int id;
    private String type;
    private int loadedFileId;
    private int teacherId;
    private LocalDateTime initDate;
    private LocalDateTime endDate;
    private PGInterval requiredInterval;
    private String state;
    private float score;
    private String answers; //TODO to json

    public TrainingTest() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public LocalDateTime getInitDate() {
        return initDate;
    }

    public void setInitDate(LocalDateTime initDate) {
        this.initDate = initDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public PGInterval getRequiredInterval() {
        return requiredInterval;
    }

    public void setRequiredInterval(PGInterval requiredInterval) {
        this.requiredInterval = requiredInterval;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "TrainingTest{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", loadedFileId=" + loadedFileId +
                ", teacherId=" + teacherId +
                ", initDate='" + initDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", requiredInterval=" + requiredInterval +
                ", state='" + state + '\'' +
                ", score=" + score +
                ", answers='" + answers + '\'' +
                '}';
    }
}
