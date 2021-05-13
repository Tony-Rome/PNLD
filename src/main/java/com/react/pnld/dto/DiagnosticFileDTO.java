package com.react.pnld.dto;

import com.univocity.parsers.annotations.LowerCase;
import com.univocity.parsers.annotations.Parsed;

import java.sql.Timestamp;

public class DiagnosticFileDTO implements TeacherPersonDTO {

    @Parsed(index = 0)
    private long respondentId;
    @Parsed(index = 1)
    private long collectorId;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    @Parsed(index = 4)
    private String ipAddress;
    @Parsed(index = 5)
    @LowerCase
    private String region;
    @Parsed(index = 6)
    @LowerCase
    private String commune;
    @Parsed(index = 7)
    private String rbd;
    @Parsed(index = 8)
    @LowerCase
    private String schoolName;
    @Parsed(index = 9)
    @LowerCase
    private String email;
    @Parsed(index = 10)
    @LowerCase
    private String rut;
    @Parsed(index = 11)
    @LowerCase
    private String name;
    @Parsed(index = 12)
    @LowerCase
    private String lastNames;
    @Parsed(index = 13)
    private int age;
    @Parsed(index = 14)
    @LowerCase
    private String gender;
    private String answers;


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
    @Parsed(index = 2)
    public void setCreatedDate(String createdDate) {this.createdDate = Timestamp.valueOf(createdDate);}

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }
    @Parsed(index = 3)
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = Timestamp.valueOf(modifiedDate);
    }

    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    public String getCommune() {
        return commune;
    }
    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getRbd() {
        return rbd;
    }
    public void setRbd(String rbd) {
        this.rbd = rbd;
    }

    @Override
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }

    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLastNames() {
        return lastNames;
    }
    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    @Override
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAnswers(){return answers;}
    public void setAnswers(String answers){this.answers = answers;}

    @Override
    public String getDepartment() {return null;}

    @Override
    public boolean getParticipatedInPNLD() {return true;}

    @Override
    public String getInLevels() {return null;}

    @Override
    public String getSubjects() {return null;}

    @Override
    public String getCsResources() {return null;}

    @Override
    public String getRoboticsResources() {return null;}

}
