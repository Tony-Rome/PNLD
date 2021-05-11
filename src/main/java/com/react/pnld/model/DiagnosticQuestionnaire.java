package com.react.pnld.model;

import java.sql.Timestamp;

public class DiagnosticQuestionnaire {

    private int id;
    private int loadedFileId;
    private int teacherId;
    private long respondentId;
    private long collectorId;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String answers;

    public DiagnosticQuestionnaire(int id, int loadedFileId, int teacherId, long respondentId, long collectorId, Timestamp createdDate, Timestamp modifiedDate, String answers) {
        this.id = id;
        this.loadedFileId = loadedFileId;
        this.teacherId = teacherId;
        this.respondentId = respondentId;
        this.collectorId = collectorId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.answers = answers;
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

    public long getRespondentId() {
        return respondentId;
    }

    public void setRespondentId(long respondentId) {
        this.respondentId = respondentId;
    }

    public long getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(long collectorId) {
        this.collectorId = collectorId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "DiagnosticQuestionnaire{" +
                "id=" + id +
                ", loadedFileId=" + loadedFileId +
                ", teacherId=" + teacherId +
                ", respondentId=" + respondentId +
                ", collectorId=" + collectorId +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", answers='" + answers + '\'' +
                '}';
    }
}
