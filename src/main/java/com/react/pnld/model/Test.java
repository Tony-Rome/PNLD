package com.react.pnld.model;

import org.postgresql.util.PGInterval;

import java.io.Serializable;

public class Test implements Serializable {

    private int id;
    private int teacherId;
    private int loadedFileId;
    private String type;
    private String state;
    private String initDate;
    private String endDate;
    private PGInterval duration;
    private float score;

    public Test() {
        super();
    }

    public Test(int id, int teacherId, int loadedFileId, String type, String state, String initDate, String endDate,
                PGInterval duration, float score) {
        super();
        this.id = id;
        this.teacherId = teacherId;
        this.loadedFileId = loadedFileId;
        this.type = type;
        this.state = state;
        this.initDate = initDate;
        this.endDate = endDate;
        this.duration = duration;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getLoadedFileId() {
        return loadedFileId;
    }

    public void setLoadedFileId(int loadedFileId) {
        this.loadedFileId = loadedFileId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public PGInterval getDuration() {
        return duration;
    }

    public void setDuration(PGInterval duration) {
        this.duration = duration;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", loadedFileId=" + loadedFileId +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", initDate='" + initDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", duration='" + duration + '\'' +
                ", score=" + score +
                '}';
    }
}
