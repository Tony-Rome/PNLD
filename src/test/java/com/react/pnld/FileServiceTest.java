package com.react.pnld;

import com.react.pnld.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    private String teacherRosterHeaderString = "firstname,prefname,lastname,email,schoolname,schoolcity,grade,coursename,studentsincourse,highestunitstudents,onlinecsfcourse,onlinecsfhighestunit,index";
    private String teacherOptInHeaderString = "firstname,prefname,lastname,email,altemail,gender,schoolname,schoolcity,subjectstaught,agetaught,csrescources,roboticsrescources,workshoporganizer,workshopdate,workshopfacilitator,index";
    private String studentLevelHeaderString = "teachername,teacheremail,schoolname,schoolcity,course,sectionid,organizer,students,studentswithprojects,index,avgcoursecompletedmaxcoursecompleted,medianlevelsattempted,projects";
    private String signInPerCourseHeaderString = "weekofsigninat,coursename,distinctcountofuseridcsfstarted";
    private String signInsHeaderString = "weekofsigninat,distinctcountofuserid,differenceindistinctcountofuserid";


    @Test
    void contextLoads() {
        assertThat(fileService).isNotNull();
    }

    @Test
    void removeSymbols(){
        String input = "First Name,Pref Name, # Students in Course,Highest Unit (Students),Online CSF  Course";
        String expected = "firstname,prefname,studentsincourse,highestunitstudents,onlinecsfcourse";
        Assert.assertEquals(fileService.removeSymbols(input), expected);
    }

    @Test
    void selectedHeadersTeacherRoster(){
        String[] teacherRosterHeadersMock = teacherRosterHeaderString.split(",");
        String[] teacherRosterHeaders = fileService.selectedHeadersArray("teacher-roster");

        Arrays.sort(teacherRosterHeaders);
        Arrays.sort(teacherRosterHeadersMock);
        Assert.assertEquals(teacherRosterHeaders, teacherRosterHeadersMock);
    }

    @Test
    void selectedHeadersTeacherOptIn(){
        String[] teacherOptInHeadersMock = teacherOptInHeaderString.split(",");
        String[] teacherOptInHeaders = fileService.selectedHeadersArray("teacher-opt-in");

        Arrays.sort(teacherOptInHeaders);
        Arrays.sort(teacherOptInHeadersMock);
        Assert.assertEquals(teacherOptInHeaders, teacherOptInHeadersMock);
    }

    @Test
    void select_studentLevelHeaders(){
        String[] studentLevelHeadersMock = studentLevelHeaderString.split(",");
        String[] studentLevelHeaders = fileService.selectedHeadersArray("student-level");

        Arrays.sort(studentLevelHeaders);
        Arrays.sort(studentLevelHeadersMock);
        Assert.assertEquals(studentLevelHeaders, studentLevelHeadersMock);
    }

    @Test
    void select_signInPerCourseHeaders(){
        String[] signInPerCourseHeadersMock = signInPerCourseHeaderString.split(",");
        String[] signInPerCourseHeaders = fileService.selectedHeadersArray("signin-per-course");

        Arrays.sort(signInPerCourseHeaders);
        Arrays.sort(signInPerCourseHeadersMock);
        Assert.assertEquals(signInPerCourseHeaders, signInPerCourseHeadersMock);
    }

    @Test
    void select_signInsHeaders(){
        String[] signInsHeadersMock = signInsHeaderString.split(",");
        String[] signInsHeaders = fileService.selectedHeadersArray("sign-ins");

        Arrays.sort(signInsHeaders);
        Arrays.sort(signInsHeadersMock);
        Assert.assertEquals(signInsHeaders, signInsHeadersMock);
    }

    @Test
    void select_Not_Found_Headers(){
        String[] fooBarHeaders = fileService.selectedHeadersArray("foo-bar");

        Assert.assertNull(fooBarHeaders);
    }

    @Test
    void is_stringArrays_Not_Equals(){
        String[] first = fileService.selectedHeadersArray("signin-per-course");
        String[] second = fileService.selectedHeadersArray("sign-ins");
        Assert.assertFalse(fileService.isStringArraysEquals(first, second));
    }
}