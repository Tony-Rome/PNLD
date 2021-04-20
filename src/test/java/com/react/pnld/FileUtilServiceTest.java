package com.react.pnld;

import com.react.pnld.dto.ScheduleFileLoadDTO;
import com.react.pnld.services.FileUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.multipart.MultipartFile;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

@SpringBootTest
public class FileUtilServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private FileUtilService fileUtilService;

    private String teacherRosterHeaderString = "firstname,prefname,lastname,email,schoolname,schoolcity,grade,coursename,studentsincourse,highestunitstudents,onlinecsfcourse,onlinecsfhighestunit,index";
    private String teacherOptInHeaderString = "firstname,prefname,lastname,email,altemail,gender,schoolname,schoolcity,subjectstaught,agetaught,csrescources,roboticsrescources,workshoporganizer,workshopdate,workshopfacilitator,index";
    private String studentLevelHeaderString = "teachername,teacheremail,schoolname,schoolcity,course,sectionid,organizer,students,studentswithprojects,index,avgcoursecompleted,maxcoursecompleted,medianlevelsattempted,projects";
    private String signInPerCourseHeaderString = "weekofsigninat,coursename,distinctcountofuseridcsfstarted";
    private String signInsHeaderString = "weekofsigninat,distinctcountofuserid,differenceindistinctcountofuserid";

    @Test
    void contextLoads() {
        Assert.assertNotNull(fileUtilService);
    }

    @Test
    void removeSymbols(){
        String input = "First Name,Pref Name, # Students in Course,Highest Unit (Students),Online CSF  Course";
        String expected = "firstname,prefname,studentsincourse,highestunitstudents,onlinecsfcourse";
        Assert.assertEquals(fileUtilService.removeSymbols(input), expected);
    }

    @Test
    void selectedHeaders_Equals_TeacherRosterType(){
        String[] teacherRosterHeadersMock = teacherRosterHeaderString.split(",");
        String[] teacherRosterHeaders = fileUtilService.selectedHeadersArray("teacher-roster");

        Arrays.sort(teacherRosterHeaders);
        Arrays.sort(teacherRosterHeadersMock);
        Assert.assertEquals(teacherRosterHeaders, teacherRosterHeadersMock);
    }

    @Test
    void selectedHeaders_Equals_TeacherOptInType(){
        String[] teacherOptInHeadersMock = teacherOptInHeaderString.split(",");
        String[] teacherOptInHeaders = fileUtilService.selectedHeadersArray("teacher-opt-in");

        Arrays.sort(teacherOptInHeaders);
        Arrays.sort(teacherOptInHeadersMock);
        Assert.assertEquals(teacherOptInHeaders, teacherOptInHeadersMock);
    }

    @Test
    void selectedHeaders_Equals_StudentLevelType(){
        String[] studentLevelHeadersMock = studentLevelHeaderString.split(",");
        String[] studentLevelHeaders = fileUtilService.selectedHeadersArray("student-level");

        Arrays.sort(studentLevelHeaders);
        Arrays.sort(studentLevelHeadersMock);
        Assert.assertEquals(studentLevelHeaders, studentLevelHeadersMock);
    }

    @Test
    void selectedHeaders_Equals_SignInPerCourseType(){
        String[] signInPerCourseHeadersMock = signInPerCourseHeaderString.split(",");
        String[] signInPerCourseHeaders = fileUtilService.selectedHeadersArray("signin-per-course");

        Arrays.sort(signInPerCourseHeaders);
        Arrays.sort(signInPerCourseHeadersMock);
        Assert.assertEquals(signInPerCourseHeaders, signInPerCourseHeadersMock);
    }

    @Test
    void selectedHeaders_Equals_SignInsHeadersType(){
        String[] signInsHeadersMock = signInsHeaderString.split(",");
        String[] signInsHeaders = fileUtilService.selectedHeadersArray("sign-ins");

        Arrays.sort(signInsHeaders);
        Arrays.sort(signInsHeadersMock);
        Assert.assertEquals(signInsHeaders, signInsHeadersMock);
    }

    @Test
    void selectHeaders_Equals_NotDefined(){
        String[] fooBarHeaders = fileUtilService.selectedHeadersArray("foo-bar");
        Assert.assertEquals(fooBarHeaders.length, 1);
    }

    @Test
    void isStringArraysEquals_WhenFalse(){
        String[] first = fileUtilService.selectedHeadersArray("signin-per-course");
        String[] second = fileUtilService.selectedHeadersArray("sign-ins");
        Assert.assertFalse(fileUtilService.isStringArraysEquals(first, second));
    }

    @Test
    void getFileExtension_When_CsvFile(){
        String fileCsvName = "test.csv";
        Assert.assertEquals(fileUtilService.getExtension(fileCsvName),".csv");
    }

    @Test
    void getFileExtension_When_PdfFile(){
        String filePdfName = "test.pdf";
        Assert.assertEquals(fileUtilService.getExtension(filePdfName),".pdf");
    }

    @Test
    void getLoadedFileName_When_Is_2021_03_01(){
        ScheduleFileLoadDTO fooScheduleFileLoadDTO = new ScheduleFileLoadDTO();

        fooScheduleFileLoadDTO.setName("name_by_user");

        MultipartFile multipartFile = new MockMultipartFile("foo_file", "foo_file.csv",
                "text/plain","foo bar".getBytes());
        fooScheduleFileLoadDTO.setUploadFile(multipartFile);

        Instant instant = Instant.parse("2021-03-01T00:00:00.21Z");
        fooScheduleFileLoadDTO.setLoadedOnDateTime(LocalDateTime.ofInstant(instant,ZoneId.of("UTC")));

        Assert.assertEquals("2021-03-01T00-00-00-name_by_user.csv",
                fileUtilService.getLoadedFileName(fooScheduleFileLoadDTO));
    }

    @Test
    public void getTrainingDuration_When_string_duration_MinsAndSecs(){
        Duration duration = FileUtilService.getTrainingDuration("12 minutos 31 segundos");
        long totalSeconds = 12 * 60 + 31;
        Assert.assertEquals(totalSeconds, duration.getSeconds());
    }

    @Test
    public void getTrainingDuration_When_string_duration_HourAndMins(){
        Duration duration = FileUtilService.getTrainingDuration("1 hora 32 minutos");
        long totalSeconds = 1 * 60 * 60 + 32 * 60;
        Assert.assertEquals(totalSeconds, duration.getSeconds());
    }

    @Test
    public void getTrainingDuration_When_string_duration_DaysAndHours(){
        Duration duration = FileUtilService.getTrainingDuration("6 días 19 horas");
        long totalSeconds = 6 * 24 * 60 * 60 + 19 * 60 * 60;
        Assert.assertEquals(totalSeconds, duration.getSeconds());
    }

    @Test
    public void getTrainingDuration_When_string_duration_empty(){
        Duration duration = FileUtilService.getTrainingDuration("");
        Assert.assertEquals(0, duration.getSeconds());
    }

    @Test
    public void getTrainingDuration_When_string_duration_other_format(){
        Duration duration = FileUtilService.getTrainingDuration("-- --");
        Assert.assertEquals(0, duration.getSeconds());
    }

    @Test
    public void getTrainingDuration_When_string_duration_unusual_format(){
        Duration duration = FileUtilService.getTrainingDuration("2 houses 2 dogs");
        Assert.assertEquals(0, duration.getSeconds());
    }
}