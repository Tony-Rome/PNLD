package com.react.pnld;

import com.react.pnld.services.FileService;
import com.react.pnld.services.FileUtilService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class FileUtilServiceTest {

    @Autowired
    private FileUtilService fileUtilService;

    private String teacherRosterHeaderString = "firstname,prefname,lastname,email,schoolname,schoolcity,grade,coursename,studentsincourse,highestunitstudents,onlinecsfcourse,onlinecsfhighestunit,index";
    private String teacherOptInHeaderString = "firstname,prefname,lastname,email,altemail,gender,schoolname,schoolcity,subjectstaught,agetaught,csrescources,roboticsrescources,workshoporganizer,workshopdate,workshopfacilitator,index";
    private String studentLevelHeaderString = "teachername,teacheremail,schoolname,schoolcity,course,sectionid,organizer,students,studentswithprojects,index,avgcoursecompleted,maxcoursecompleted,medianlevelsattempted,projects";
    private String signInPerCourseHeaderString = "weekofsigninat,coursename,distinctcountofuseridcsfstarted";
    private String signInsHeaderString = "weekofsigninat,distinctcountofuserid,differenceindistinctcountofuserid";

    @Test
    void contextLoads() {
        assertThat(fileUtilService).isNotNull();
    }

    @Test
    void removeSymbols(){
        String input = "First Name,Pref Name, # Students in Course,Highest Unit (Students),Online CSF  Course";
        String expected = "firstname,prefname,studentsincourse,highestunitstudents,onlinecsfcourse";
        Assert.assertEquals(fileUtilService.removeSymbols(input), expected);
    }

    @Test
    void selectedHeadersTeacherRoster(){
        String[] teacherRosterHeadersMock = teacherRosterHeaderString.split(",");
        String[] teacherRosterHeaders = fileUtilService.selectedHeadersArray("teacher-roster");

        Arrays.sort(teacherRosterHeaders);
        Arrays.sort(teacherRosterHeadersMock);
        Assert.assertEquals(teacherRosterHeaders, teacherRosterHeadersMock);
    }

    @Test
    void selectedHeadersTeacherOptIn(){
        String[] teacherOptInHeadersMock = teacherOptInHeaderString.split(",");
        String[] teacherOptInHeaders = fileUtilService.selectedHeadersArray("teacher-opt-in");

        Arrays.sort(teacherOptInHeaders);
        Arrays.sort(teacherOptInHeadersMock);
        Assert.assertEquals(teacherOptInHeaders, teacherOptInHeadersMock);
    }

    @Test
    void select_studentLevelHeaders(){
        String[] studentLevelHeadersMock = studentLevelHeaderString.split(",");
        String[] studentLevelHeaders = fileUtilService.selectedHeadersArray("student-level");

        Arrays.sort(studentLevelHeaders);
        Arrays.sort(studentLevelHeadersMock);
        Assert.assertEquals(studentLevelHeaders, studentLevelHeadersMock);
    }

    @Test
    void select_signInPerCourseHeaders(){
        String[] signInPerCourseHeadersMock = signInPerCourseHeaderString.split(",");
        String[] signInPerCourseHeaders = fileUtilService.selectedHeadersArray("signin-per-course");

        Arrays.sort(signInPerCourseHeaders);
        Arrays.sort(signInPerCourseHeadersMock);
        Assert.assertEquals(signInPerCourseHeaders, signInPerCourseHeadersMock);
    }

    @Test
    void select_signInsHeaders(){
        String[] signInsHeadersMock = signInsHeaderString.split(",");
        String[] signInsHeaders = fileUtilService.selectedHeadersArray("sign-ins");

        Arrays.sort(signInsHeaders);
        Arrays.sort(signInsHeadersMock);
        Assert.assertEquals(signInsHeaders, signInsHeadersMock);
    }

    @Test
    void select_Not_Found_Headers(){
        String[] fooBarHeaders = fileUtilService.selectedHeadersArray("foo-bar");
        Assert.assertEquals(fooBarHeaders.length, 1);
    }

    @Test
    void is_stringArrays_Not_Equals(){
        String[] first = fileUtilService.selectedHeadersArray("signin-per-course");
        String[] second = fileUtilService.selectedHeadersArray("sign-ins");
        Assert.assertFalse(fileUtilService.isStringArraysEquals(first, second));
    }

    @Test
    void get_csv_extension_file(){
        String fileCsvName = "test.csv";
        Assert.assertEquals(fileUtilService.getFileExtension(fileCsvName),".csv");
    }

    @Test
    void get_pdf_extension_file(){
        String filePdfName = "test.pdf";
        Assert.assertEquals(fileUtilService.getFileExtension(filePdfName),".pdf");
    }
}