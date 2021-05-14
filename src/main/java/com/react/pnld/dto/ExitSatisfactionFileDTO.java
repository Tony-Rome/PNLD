package com.react.pnld.dto;

import com.univocity.parsers.annotations.LowerCase;
import com.univocity.parsers.annotations.Parsed;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExitSatisfactionFileDTO {

    @Parsed(index = 0)
    private int responseId;
    private Timestamp sendDate;
    @Parsed(index = 2)
    @LowerCase
    private String schoolName;
    @Parsed(index = 3)
    private String department;
    @Parsed(index = 4)
    private String course;
    @Parsed(index = 5)
    private String group;
    @Parsed(index = 6)
    private int id;
    private String name;
    private String lastNames;
    @Parsed(index = 8)
    @LowerCase
    private String rut;
    private String answers;

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    @Parsed(index = 1)
    public void setSendDate(String sendDate) {
        String sendDateFormat = sendDate.replaceAll("/", "-");
        LocalDateTime localDateTime = LocalDateTime.parse(sendDateFormat, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.sendDate = Timestamp.valueOf(localDateTime);
    }


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Parsed(index = 7)
    @LowerCase
    public void setName(String name) {
        String[] fullName = name.split(" ");

        this.name = fullName[0];

        if (fullName.length == 2) setLastNames(fullName[1]);

        if (fullName.length == 3) setLastNames(fullName[1] + " " + fullName[2]);

        if (fullName.length == 4) {
            this.name += " " + fullName[1];
            setLastNames(fullName[2] + " " + fullName[3]);
        }

        if (fullName.length == 5) {
            this.name += " " + fullName[1] + " " + fullName[2];
            setLastNames(fullName[3] + " " + fullName[4]);
        }

        if (fullName.length == 6) {
            this.name += " " + fullName[1] + " " + fullName[2] + " " + fullName[3];
            setLastNames(fullName[4] + " " + fullName[5]);
        }
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getRut() {
        return rut;
    }


}
