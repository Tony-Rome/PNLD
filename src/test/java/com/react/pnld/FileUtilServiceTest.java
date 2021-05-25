package com.react.pnld;

import com.react.pnld.dto.ScheduleFileLoadDTO;
import com.react.pnld.dto.TrainingFileDTO;
import com.react.pnld.services.EntityAttributeUtilService;
import com.react.pnld.services.FileUtilService;
import org.postgresql.util.PGInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.multipart.MultipartFile;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    private String generalResumeHeaderString = "RUT;REGIÓN;RBD;NOMBRES;ÍTEM;ASISTE JORNADA ;Año de Capacitacion;FECHA ASISTENCIA 1;FECHA ASISTENCIA 2;EVIDENCIA;ACTIVIDAD EN PLATAFORMA;REVISIÓN EVIDENCIAS ENTREGADAS;ESTADO PLATAFORMA;ESTADO FINAL;PAGO;MONTO ITEM";

    @Test
    void contextLoads() {
        Assert.assertNotNull(fileUtilService);
    }

    @Test
    void removeSymbols(){
        String input = "First Name,Pref Name,# Students in Course,Highest Unit (Students),Online CSF  Course";
        String expected = "firstname,prefname,studentsincourse,highestunitstudents,onlinecsfcourse";
        Assert.assertEquals(EntityAttributeUtilService.removeSymbols(input), expected);
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
    public void getTrainingRequiredInterval_When_MinutesAndSeconds(){
        TrainingFileDTO trainingFileDTO = new TrainingFileDTO();
        trainingFileDTO.setStartIn(LocalDateTime.of(2021, 5,1,16,50, 00));
        trainingFileDTO.setFinishIn(LocalDateTime.of(2021, 5,1,17,2, 31));

        PGInterval duration = trainingFileDTO.getRequiredInterval();
        Assert.assertEquals(12, duration.getMinutes());
        Assert.assertEquals(31, duration.getSeconds());
    }

    @Test
    public void getTrainingRequiredInterval_When_HourAndMinutes(){
        TrainingFileDTO trainingFileDTO = new TrainingFileDTO();
        trainingFileDTO.setStartIn(LocalDateTime.of(2021, 5,1,10,00, 00));
        trainingFileDTO.setFinishIn(LocalDateTime.of(2021, 5,1,11,32, 00));

        PGInterval duration = trainingFileDTO.getRequiredInterval();
        Assert.assertEquals(32, duration.getMinutes());
        Assert.assertEquals(1, duration.getHours());
    }

    @Test
    public void getTrainingRequiredInterval_When_DaysAndHours(){
        TrainingFileDTO trainingFileDTO = new TrainingFileDTO();
        trainingFileDTO.setStartIn(LocalDateTime.of(2021, 5,1,1,00, 00));
        trainingFileDTO.setFinishIn(LocalDateTime.of(2021, 5,7,20,00, 00));

        PGInterval duration = trainingFileDTO.getRequiredInterval();
        Assert.assertEquals(6, duration.getDays());
        Assert.assertEquals(19, duration.getHours());
    }

    @Test
    public void getTrainingRequiredInterval_When_MonthsDaysAndHours(){
        TrainingFileDTO trainingFileDTO = new TrainingFileDTO();
        trainingFileDTO.setStartIn(LocalDateTime.of(2021, 5,6,10,00, 00));
        trainingFileDTO.setFinishIn(LocalDateTime.of(2021, 6,5,9,00, 00));

        PGInterval duration = trainingFileDTO.getRequiredInterval();
        Assert.assertEquals(0, duration.getMonths());
        Assert.assertEquals(29, duration.getDays());
        Assert.assertEquals(23, duration.getHours());
    }

    @Test
    public void getTrainingRequiredInterval_When_EqualsOrNotDifferences(){
        TrainingFileDTO trainingFileDTO = new TrainingFileDTO();
        trainingFileDTO.setStartIn(LocalDateTime.of(2021, 5,7,1,00, 00));
        trainingFileDTO.setFinishIn(LocalDateTime.of(2021, 5,7,1,00, 00));

        PGInterval duration = trainingFileDTO.getRequiredInterval();
        Assert.assertEquals(0, duration.getMinutes());
        Assert.assertEquals(0, duration.getSeconds());
    }

    @Test
    public void normalizeRegion(){
        String valparaiso = "región de valparaíso";
        String valparaisoExpected = "valparaiso";
        Assert.assertEquals(EntityAttributeUtilService.normalizeRegion(valparaiso), valparaisoExpected);

        String maule = "región del maule";
        String mauleExpected = "maule";
        Assert.assertEquals(EntityAttributeUtilService.normalizeRegion(maule), mauleExpected);

        String rm = "región metropolitana";
        String rmExpected = "metropolitana";
        Assert.assertEquals(EntityAttributeUtilService.normalizeRegion(rm),rmExpected);

        String a = "región de la araucanía";
        String aExpected = "araucania";
        Assert.assertEquals(EntityAttributeUtilService.normalizeRegion(a),aExpected);
    }

    @Test
    public void validateEmail(){
        String email1 = "correoprueba@test.es";
        Assert.assertEquals(EntityAttributeUtilService.emailValidator(email1), true);
        String email2 = "emai123.456@dominio";
        Assert.assertEquals(EntityAttributeUtilService.emailValidator(email2), false);
        String email3 = "correono@valido@";
        Assert.assertEquals(EntityAttributeUtilService.emailValidator(email3), false);
    }

    @Test
    public void selectedHeaders_Equals_GeneralResumeType(){
        String[] generalResumeHeadersMock = EntityAttributeUtilService.removeSymbols(generalResumeHeaderString).split(";");
        String[] generalResumeHeaders = fileUtilService.selectedHeadersArray("general-resume");

        Arrays.sort(generalResumeHeaders);
        Arrays.sort(generalResumeHeadersMock);
        Assert.assertEquals(generalResumeHeaders, generalResumeHeadersMock);
    }
}