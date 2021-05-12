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
    @Parsed(index = 15)
    private String job;
    @Parsed(index = 16)
    private String mainGrade;
    @Parsed(index = 17)
    private String anotherGrade;
    @Parsed(index = 18)
    private String ebasign1;
    @Parsed(index = 19)
    private String ebasign2;
    @Parsed(index = 20)
    private String ebasign3;
    @Parsed(index = 21)
    private String ebasign4;
    @Parsed(index = 22)
    private String ebasign5;
    @Parsed(index = 23)
    private String ebasign6;
    @Parsed(index = 24)
    private String ebasign7;
    @Parsed(index = 25)
    private String ebasign8;
    @Parsed(index = 26)
    private String ebasign9;
    @Parsed(index = 27)
    private String ebasign10;
    @Parsed(index = 28)
    private String ebasign11;
    @Parsed(index = 29)
    private String ebasign12;
    @Parsed(index = 30)
    private String ebasign13;
    @Parsed(index = 31)
    private String mhcasign1;
    @Parsed(index = 32)
    private String mhcasign2;
    @Parsed(index = 33)
    private String mhcasign3;
    @Parsed(index = 34)
    private String mhcasign4;
    @Parsed(index = 35)
    private String mhcasign5;
    @Parsed(index = 36)
    private String mhcasign6;
    @Parsed(index = 37)
    private String mhcasign7;
    @Parsed(index = 38)
    private String mhcasign8;
    @Parsed(index = 39)
    private String mhcasign9;
    @Parsed(index = 40)
    private String mhcasign10;
    @Parsed(index = 41)
    private String mhcasign11;
    @Parsed(index = 42)
    private String mhcasign12;
    @Parsed(index = 43)
    private String mhcasign13;
    @Parsed(index = 44)
    private String mtp1;
    @Parsed(index = 45)
    private String mtp2;
    @Parsed(index = 46)
    private String mtp3;
    @Parsed(index = 47)
    private String mtp4;
    @Parsed(index = 48)
    private String mtp5;
    @Parsed(index = 49)
    private String mtp6;
    @Parsed(index = 50)
    private String mtp7;
    @Parsed(index = 51)
    private String mtp8;
    @Parsed(index = 52)
    private String mtp9;
    @Parsed(index = 53)
    private String mtp10;
    @Parsed(index = 54)
    private String mtp11;
    @Parsed(index = 55)
    private String mtp12;
    @Parsed(index = 56)
    private String mtp13;
    @Parsed(index = 57)
    private String mtp14;
    @Parsed(index = 58)
    private String mtp15;
    @Parsed(index = 59)
    private String usageFrequencyOfComputerLab;
    @Parsed(index = 60)
    private String HaveYouParticipatedInAnyTrainingToLearnProgramming;
    @Parsed(index = 61)
    private String HaveYouParticipatedInAnyTrainingToTeachProgramming;
    @Parsed(index = 62)
    private String ini1;
    @Parsed(index = 63)
    private String ini2;
    @Parsed(index = 64)
    private String ini3;
    @Parsed(index = 65)
    private String ini4;
    @Parsed(index = 66)
    private String ini5;
    @Parsed(index = 67)
    private String ini6;
    @Parsed(index = 68)
    private String ini7;
    @Parsed(index = 69)
    private String her1;
    @Parsed(index = 70)
    private String her2;
    @Parsed(index = 71)
    private String her3;
    @Parsed(index = 72)
    private String her4;
    @Parsed(index = 73)
    private String her5;
    @Parsed(index = 74)
    private String her6;
    @Parsed(index = 75)
    private String her7;
    @Parsed(index = 76)
    private String her8;
    @Parsed(index = 77)
    private String her9;
    @Parsed(index = 78)
    private String her10;
    @Parsed(index = 79)
    private String her11;
    @Parsed(index = 80)
    private String her12;
    @Parsed(index = 81)
    private String her13;
    @Parsed(index = 82)
    private String her14;
    @Parsed(index = 83)
    private String haveYouTaughtProgramming;
    @Parsed(index = 84)
    private String haveYouTaughtRobotic;
    @Parsed(index = 85)
    private String iSeeMyselfCloseToProgramming;
    @Parsed(index = 86)
    private String isItImportantToLearnProgramming;
    @Parsed(index = 87)
    private String isItImportantToTeachProgramming;
    @Parsed(index = 88)
    private String isProgrammingImportantForTheFuture;
    @Parsed(index = 89)
    private String isProgrammingOnlyForProgrammingClass;
    @Parsed(index = 90)
    private String isProgrammingCompatibleWithOtherClasses;
    @Parsed(index = 91)
    private String programmingIsUtilForMyCourse;
    @Parsed(index = 92)
    private int computationalThinking;
    @Parsed(index = 93)
    private int programming;
    @Parsed(index = 94)
    private int iCanImplementProgrammingClasses;
    @Parsed(index = 95)
    private int iWantToImplementProgrammingClasses;
    @Parsed(index = 96)
    private String algorithm;
    @Parsed(index = 97)
    private String abstraction;
    @Parsed(index = 98)
    private String decomposition;
    @Parsed(index = 99)
    private String iteration;
    @Parsed(index = 100)
    private String languageProgramming;
    @Parsed(index = 101)
    private String program;
    @Parsed(index = 102)
    private String none;
    @Parsed(index = 103)
    private String algorithm2;
    @Parsed(index = 104)
    private String abstraction2;
    @Parsed(index = 105)
    private String decomposition2;
    @Parsed(index = 106)
    private String iteration2;
    @Parsed(index = 107)
    private String languageProgramming2;
    @Parsed(index = 108)
    private String program2;
    @Parsed(index = 109)
    private String none2;
    @Parsed(index = 110)
    private String whatIsComputationalThinking;
    @Parsed(index = 111)
    private String whyIsItNecessaryToThinkComputationally;
    @Parsed(index = 112)
    private String situationsToThinkComputationally;
    @Parsed(index = 113)
    private String whatIsAlgorithm;
    @Parsed(index = 114)
    private String usageOfAlgorithm;

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
    public void setCreatedDate(String createdDate) {

        this.createdDate = Timestamp.valueOf(createdDate);
    }

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMainGrade() {
        return mainGrade;
    }

    public void setMainGrade(String mainGrade) {
        this.mainGrade = mainGrade;
    }

    public String getAnotherGrade() {
        return anotherGrade;
    }

    public void setAnotherGrade(String anotherGrade) {
        this.anotherGrade = anotherGrade;
    }

    public String getEbasign1() {
        return ebasign1;
    }

    public void setEbasign1(String ebasign1) {
        this.ebasign1 = ebasign1;
    }

    public String getEbasign2() {
        return ebasign2;
    }

    public void setEbasign2(String ebasign2) {
        this.ebasign2 = ebasign2;
    }

    public String getEbasign3() {
        return ebasign3;
    }

    public void setEbasign3(String ebasign3) {
        this.ebasign3 = ebasign3;
    }

    public String getEbasign4() {
        return ebasign4;
    }

    public void setEbasign4(String ebasign4) {
        this.ebasign4 = ebasign4;
    }

    public String getEbasign5() {
        return ebasign5;
    }

    public void setEbasign5(String ebasign5) {
        this.ebasign5 = ebasign5;
    }

    public String getEbasign6() {
        return ebasign6;
    }

    public void setEbasign6(String ebasign6) {
        this.ebasign6 = ebasign6;
    }

    public String getEbasign7() {
        return ebasign7;
    }

    public void setEbasign7(String ebasign7) {
        this.ebasign7 = ebasign7;
    }

    public String getEbasign8() {
        return ebasign8;
    }

    public void setEbasign8(String ebasign8) {
        this.ebasign8 = ebasign8;
    }

    public String getEbasign9() {
        return ebasign9;
    }

    public void setEbasign9(String ebasign9) {
        this.ebasign9 = ebasign9;
    }

    public String getEbasign10() {
        return ebasign10;
    }

    public void setEbasign10(String ebasign10) {
        this.ebasign10 = ebasign10;
    }

    public String getEbasign11() {
        return ebasign11;
    }

    public void setEbasign11(String ebasign11) {
        this.ebasign11 = ebasign11;
    }

    public String getEbasign12() {
        return ebasign12;
    }

    public void setEbasign12(String ebasign12) {
        this.ebasign12 = ebasign12;
    }

    public String getEbasign13() {
        return ebasign13;
    }

    public void setEbasign13(String ebasign13) {
        this.ebasign13 = ebasign13;
    }

    public String getMhcasign1() {
        return mhcasign1;
    }

    public void setMhcasign1(String mhcasign1) {
        this.mhcasign1 = mhcasign1;
    }

    public String getMhcasign2() {
        return mhcasign2;
    }

    public void setMhcasign2(String mhcasign2) {
        this.mhcasign2 = mhcasign2;
    }

    public String getMhcasign3() {
        return mhcasign3;
    }

    public void setMhcasign3(String mhcasign3) {
        this.mhcasign3 = mhcasign3;
    }

    public String getMhcasign4() {
        return mhcasign4;
    }

    public void setMhcasign4(String mhcasign4) {
        this.mhcasign4 = mhcasign4;
    }

    public String getMhcasign5() {
        return mhcasign5;
    }

    public void setMhcasign5(String mhcasign5) {
        this.mhcasign5 = mhcasign5;
    }

    public String getMhcasign6() {
        return mhcasign6;
    }

    public void setMhcasign6(String mhcasign6) {
        this.mhcasign6 = mhcasign6;
    }

    public String getMhcasign7() {
        return mhcasign7;
    }

    public void setMhcasign7(String mhcasign7) {
        this.mhcasign7 = mhcasign7;
    }

    public String getMhcasign8() {
        return mhcasign8;
    }

    public void setMhcasign8(String mhcasign8) {
        this.mhcasign8 = mhcasign8;
    }

    public String getMhcasign9() {
        return mhcasign9;
    }

    public void setMhcasign9(String mhcasign9) {
        this.mhcasign9 = mhcasign9;
    }

    public String getMhcasign10() {
        return mhcasign10;
    }

    public void setMhcasign10(String mhcasign10) {
        this.mhcasign10 = mhcasign10;
    }

    public String getMhcasign11() {
        return mhcasign11;
    }

    public void setMhcasign11(String mhcasign11) {
        this.mhcasign11 = mhcasign11;
    }

    public String getMhcasign12() {
        return mhcasign12;
    }

    public void setMhcasign12(String mhcasign12) {
        this.mhcasign12 = mhcasign12;
    }

    public String getMhcasign13() {
        return mhcasign13;
    }

    public void setMhcasign13(String mhcasign13) {
        this.mhcasign13 = mhcasign13;
    }

    public String getMtp1() {
        return mtp1;
    }

    public void setMtp1(String mtp1) {
        this.mtp1 = mtp1;
    }

    public String getMtp2() {
        return mtp2;
    }

    public void setMtp2(String mtp2) {
        this.mtp2 = mtp2;
    }

    public String getMtp3() {
        return mtp3;
    }

    public void setMtp3(String mtp3) {
        this.mtp3 = mtp3;
    }

    public String getMtp4() {
        return mtp4;
    }

    public void setMtp4(String mtp4) {
        this.mtp4 = mtp4;
    }

    public String getMtp5() {
        return mtp5;
    }

    public void setMtp5(String mtp5) {
        this.mtp5 = mtp5;
    }

    public String getMtp6() {
        return mtp6;
    }

    public void setMtp6(String mtp6) {
        this.mtp6 = mtp6;
    }

    public String getMtp7() {
        return mtp7;
    }

    public void setMtp7(String mtp7) {
        this.mtp7 = mtp7;
    }

    public String getMtp8() {
        return mtp8;
    }

    public void setMtp8(String mtp8) {
        this.mtp8 = mtp8;
    }

    public String getMtp9() {
        return mtp9;
    }

    public void setMtp9(String mtp9) {
        this.mtp9 = mtp9;
    }

    public String getMtp10() {
        return mtp10;
    }

    public void setMtp10(String mtp10) {
        this.mtp10 = mtp10;
    }

    public String getMtp11() {
        return mtp11;
    }

    public void setMtp11(String mtp11) {
        this.mtp11 = mtp11;
    }

    public String getMtp12() {
        return mtp12;
    }

    public void setMtp12(String mtp12) {
        this.mtp12 = mtp12;
    }

    public String getMtp13() {
        return mtp13;
    }

    public void setMtp13(String mtp13) {
        this.mtp13 = mtp13;
    }

    public String getMtp14() {
        return mtp14;
    }

    public void setMtp14(String mtp14) {
        this.mtp14 = mtp14;
    }

    public String getMtp15() {
        return mtp15;
    }

    public void setMtp15(String mtp15) {
        this.mtp15 = mtp15;
    }

    public String getUsageFrequencyOfComputerLab() {
        return usageFrequencyOfComputerLab;
    }

    public void setUsageFrequencyOfComputerLab(String usageFrequencyOfComputerLab) {
        this.usageFrequencyOfComputerLab = usageFrequencyOfComputerLab;
    }

    public String getHaveYouParticipatedInAnyTrainingToLearnProgramming() {
        return HaveYouParticipatedInAnyTrainingToLearnProgramming;
    }

    public void setHaveYouParticipatedInAnyTrainingToLearnProgramming(String haveYouParticipatedInAnyTrainingToLearnProgramming) {
        HaveYouParticipatedInAnyTrainingToLearnProgramming = haveYouParticipatedInAnyTrainingToLearnProgramming;
    }

    public String getHaveYouParticipatedInAnyTrainingToTeachProgramming() {
        return HaveYouParticipatedInAnyTrainingToTeachProgramming;
    }

    public void setHaveYouParticipatedInAnyTrainingToTeachProgramming(String haveYouParticipatedInAnyTrainingToTeachProgramming) {
        HaveYouParticipatedInAnyTrainingToTeachProgramming = haveYouParticipatedInAnyTrainingToTeachProgramming;
    }

    public String getIni1() {
        return ini1;
    }

    public void setIni1(String ini1) {
        this.ini1 = ini1;
    }

    public String getIni2() {
        return ini2;
    }

    public void setIni2(String ini2) {
        this.ini2 = ini2;
    }

    public String getIni3() {
        return ini3;
    }

    public void setIni3(String ini3) {
        this.ini3 = ini3;
    }

    public String getIni4() {
        return ini4;
    }

    public void setIni4(String ini4) {
        this.ini4 = ini4;
    }

    public String getIni5() {
        return ini5;
    }

    public void setIni5(String ini5) {
        this.ini5 = ini5;
    }

    public String getIni6() {
        return ini6;
    }

    public void setIni6(String ini6) {
        this.ini6 = ini6;
    }

    public String getIni7() {
        return ini7;
    }

    public void setIni7(String ini7) {
        this.ini7 = ini7;
    }

    public String getHer1() {
        return her1;
    }

    public void setHer1(String her1) {
        this.her1 = her1;
    }

    public String getHer2() {
        return her2;
    }

    public void setHer2(String her2) {
        this.her2 = her2;
    }

    public String getHer3() {
        return her3;
    }

    public void setHer3(String her3) {
        this.her3 = her3;
    }

    public String getHer4() {
        return her4;
    }

    public void setHer4(String her4) {
        this.her4 = her4;
    }

    public String getHer5() {
        return her5;
    }

    public void setHer5(String her5) {
        this.her5 = her5;
    }

    public String getHer6() {
        return her6;
    }

    public void setHer6(String her6) {
        this.her6 = her6;
    }

    public String getHer7() {
        return her7;
    }

    public void setHer7(String her7) {
        this.her7 = her7;
    }

    public String getHer8() {
        return her8;
    }

    public void setHer8(String her8) {
        this.her8 = her8;
    }

    public String getHer9() {
        return her9;
    }

    public void setHer9(String her9) {
        this.her9 = her9;
    }

    public String getHer10() {
        return her10;
    }

    public void setHer10(String her10) {
        this.her10 = her10;
    }

    public String getHer11() {
        return her11;
    }

    public void setHer11(String her11) {
        this.her11 = her11;
    }

    public String getHer12() {
        return her12;
    }

    public void setHer12(String her12) {
        this.her12 = her12;
    }

    public String getHer13() {
        return her13;
    }

    public void setHer13(String her13) {
        this.her13 = her13;
    }

    public String getHer14() {
        return her14;
    }

    public void setHer14(String her14) {
        this.her14 = her14;
    }

    public String getHaveYouTaughtProgramming() {
        return haveYouTaughtProgramming;
    }

    public void setHaveYouTaughtProgramming(String haveYouTaughtProgramming) {
        this.haveYouTaughtProgramming = haveYouTaughtProgramming;
    }

    public String getHaveYouTaughtRobotic() {
        return haveYouTaughtRobotic;
    }

    public void setHaveYouTaughtRobotic(String haveYouTaughtRobotic) {
        this.haveYouTaughtRobotic = haveYouTaughtRobotic;
    }

    public String getiSeeMyselfCloseToProgramming() {
        return iSeeMyselfCloseToProgramming;
    }

    public void setiSeeMyselfCloseToProgramming(String iSeeMyselfCloseToProgramming) {
        this.iSeeMyselfCloseToProgramming = iSeeMyselfCloseToProgramming;
    }

    public String getIsItImportantToLearnProgramming() {
        return isItImportantToLearnProgramming;
    }

    public void setIsItImportantToLearnProgramming(String isItImportantToLearnProgramming) {
        this.isItImportantToLearnProgramming = isItImportantToLearnProgramming;
    }

    public String getIsItImportantToTeachProgramming() {
        return isItImportantToTeachProgramming;
    }

    public void setIsItImportantToTeachProgramming(String isItImportantToTeachProgramming) {
        this.isItImportantToTeachProgramming = isItImportantToTeachProgramming;
    }

    public String getIsProgrammingImportantForTheFuture() {
        return isProgrammingImportantForTheFuture;
    }

    public void setIsProgrammingImportantForTheFuture(String isProgrammingImportantForTheFuture) {
        this.isProgrammingImportantForTheFuture = isProgrammingImportantForTheFuture;
    }

    public String getIsProgrammingOnlyForProgrammingClass() {
        return isProgrammingOnlyForProgrammingClass;
    }

    public void setIsProgrammingOnlyForProgrammingClass(String isProgrammingOnlyForProgrammingClass) {
        this.isProgrammingOnlyForProgrammingClass = isProgrammingOnlyForProgrammingClass;
    }

    public String getIsProgrammingCompatibleWithOtherClasses() {
        return isProgrammingCompatibleWithOtherClasses;
    }

    public void setIsProgrammingCompatibleWithOtherClasses(String isProgrammingCompatibleWithOtherClasses) {
        this.isProgrammingCompatibleWithOtherClasses = isProgrammingCompatibleWithOtherClasses;
    }

    public String getProgrammingIsUtilForMyCourse() {
        return programmingIsUtilForMyCourse;
    }

    public void setProgrammingIsUtilForMyCourse(String programmingIsUtilForMyCourse) {
        this.programmingIsUtilForMyCourse = programmingIsUtilForMyCourse;
    }

    public int getComputationalThinking() {
        return computationalThinking;
    }

    public void setComputationalThinking(int computationalThinking) {
        this.computationalThinking = computationalThinking;
    }

    public int getProgramming() {
        return programming;
    }

    public void setProgramming(int programming) {
        this.programming = programming;
    }

    public int getiCanImplementProgrammingClasses() {
        return iCanImplementProgrammingClasses;
    }

    public void setiCanImplementProgrammingClasses(int iCanImplementProgrammingClasses) {
        this.iCanImplementProgrammingClasses = iCanImplementProgrammingClasses;
    }

    public int getiWantToImplementProgrammingClasses() {
        return iWantToImplementProgrammingClasses;
    }

    public void setiWantToImplementProgrammingClasses(int iWantToImplementProgrammingClasses) {
        this.iWantToImplementProgrammingClasses = iWantToImplementProgrammingClasses;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAbstraction() {
        return abstraction;
    }

    public void setAbstraction(String abstraction) {
        this.abstraction = abstraction;
    }

    public String getDecomposition() {
        return decomposition;
    }

    public void setDecomposition(String decomposition) {
        this.decomposition = decomposition;
    }

    public String getIteration() {
        return iteration;
    }

    public void setIteration(String iteration) {
        this.iteration = iteration;
    }

    public String getLanguageProgramming() {
        return languageProgramming;
    }

    public void setLanguageProgramming(String languageProgramming) {
        this.languageProgramming = languageProgramming;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getNone() {
        return none;
    }

    public void setNone(String none) {
        this.none = none;
    }

    public String getAlgorithm2() {
        return algorithm2;
    }

    public void setAlgorithm2(String algorithm2) {
        this.algorithm2 = algorithm2;
    }

    public String getAbstraction2() {
        return abstraction2;
    }

    public void setAbstraction2(String abstraction2) {
        this.abstraction2 = abstraction2;
    }

    public String getDecomposition2() {
        return decomposition2;
    }

    public void setDecomposition2(String decomposition2) {
        this.decomposition2 = decomposition2;
    }

    public String getIteration2() {
        return iteration2;
    }

    public void setIteration2(String iteration2) {
        this.iteration2 = iteration2;
    }

    public String getLanguageProgramming2() {
        return languageProgramming2;
    }

    public void setLanguageProgramming2(String languageProgramming2) {
        this.languageProgramming2 = languageProgramming2;
    }

    public String getProgram2() {
        return program2;
    }

    public void setProgram2(String program2) {
        this.program2 = program2;
    }

    public String getNone2() {
        return none2;
    }

    public void setNone2(String none2) {
        this.none2 = none2;
    }

    public String getWhatIsComputationalThinking() {
        return whatIsComputationalThinking;
    }

    public void setWhatIsComputationalThinking(String whatIsComputationalThinking) {
        this.whatIsComputationalThinking = whatIsComputationalThinking;
    }

    public String getWhyIsItNecessaryToThinkComputationally() {
        return whyIsItNecessaryToThinkComputationally;
    }

    public void setWhyIsItNecessaryToThinkComputationally(String whyIsItNecessaryToThinkComputationally) {
        this.whyIsItNecessaryToThinkComputationally = whyIsItNecessaryToThinkComputationally;
    }

    public String getSituationsToThinkComputationally() {
        return situationsToThinkComputationally;
    }

    public void setSituationsToThinkComputationally(String situationsToThinkComputationally) {
        this.situationsToThinkComputationally = situationsToThinkComputationally;
    }

    public String getWhatIsAlgorithm() {
        return whatIsAlgorithm;
    }

    public void setWhatIsAlgorithm(String whatIsAlgorithm) {
        this.whatIsAlgorithm = whatIsAlgorithm;
    }

    public String getUsageOfAlgorithm() {
        return usageOfAlgorithm;
    }

    public void setUsageOfAlgorithm(String usageOfAlgorithm) {
        this.usageOfAlgorithm = usageOfAlgorithm;
    }


    @Override
    public String getDepartment() {
        return null;
    }

    @Override
    public boolean getParticipatedInPNLD() {
        return true;
    }

    @Override
    public String getInLevels() {
        return null;
    }

    @Override
    public String getSubjects() {
        return null;
    }

    @Override
    public String getCsResources() {
        return null;
    }

    @Override
    public String getRoboticsResources() {
        return null;
    }

}
