package com.react.pnld;

import com.react.pnld.services.FileService;
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
        String input = "Timestamp,RUT,Nombre,Apellidos,Correo electrónico,Sexo,Edad,Establecimiento Educacional,Niveles en que enseña o hace clases,¿Participó en el Plan Nacional de Lenguajes Digitales?,¿Conoce u ocupa la página Code.org?,¿Conoces o ocupa la plataforma Scratch?,Pregunta 1,Pregunta 2,Pregunta 3,Pregunta 4,Pregunta 5,Pregunta 6,Pregunta 7,Pregunta 8,Pregunta 9,Pregunta 10,Pregunta 11,Pregunta 12,Pregunta 13,Pregunta 14,Pregunta 15,De 1 a 7, ¿cómo considera que le fue en el Test?,De 1 a 7, ¿qué tan cercano o cercana se siente a los computadores y la tecnología?,Indique la hora actual, en formato HH:MM,Indique la hora actual, en formato HH:MM";//"Respuesta,Enviado el:,Institución,Departamento,Curso,Grupo,ID,Nombre completo,RUN (Ej: 12.345.678-9),Q01_Utilizando una escala de 1 a 4->Me veo a mi mismo/a como una persona cercana a la programación,Q01_Utilizando una escala de 1 a 4->Es importante para mi labor profesional el aprender sobre programación,Q01_Utilizando una escala de 1 a 4->La enseñanza de la programación es relevante para la educación de mis estudiantes,Q01_Utilizando una escala de 1 a 4->La enseñanza de la programación es relevante para el futuro laboral de mis estudiantes,Q01_Utilizando una escala de 1 a 4->La programación es propia de las clases de computación, tecnología o similar,Q01_Utilizando una escala de 1 a 4->La programación puede vincularse a la/s asignatura/s que imparto,Q02_Evalúe su nivel de conocimient->Pensamiento Computacional,Q02_Evalúe su nivel de conocimient->Programación,Q03_En una escala de 1 a 7, donde->Actualmente me siento capaz de implementar una clase incorporando programación.,Q03_En una escala de 1 a 7, donde->Actualmente me siento motivado para implementar una clase incorporando programación.,Q04_ndique todos los conceptos que->Algoritmo,Q04_ndique todos los conceptos que->Abstracción,Q04_ndique todos los conceptos que->Descomposición,Q04_ndique todos los conceptos que->Iteración (Ciclo/Bucle/Loop),Q04_ndique todos los conceptos que->Lenguaje de Programación,Q04_ndique todos los conceptos que->Programa,Q04_ndique todos los conceptos que->Ninguno,Q05_Señale todos los conceptos de->Algoritmo,Q05_Señale todos los conceptos de->Abstracción,Q05_Señale todos los conceptos de->Descomposición,Q05_Señale todos los conceptos de->Iteración (Ciclo/Bucle/Loop),Q05_Señale todos los conceptos de->Lenguaje de Programación,Q05_Señale todos los conceptos de->Programa,Q05_Señale todos los conceptos de->Ninguno,Q06_Si tuviese que elegir una opci,Q07_P30,Q08_Según su opinión, ¿en cuál de,Q09_¿Qué es un algoritmo?,Q10_¿Para qué se usan los algoritm";//"First Name,Pref Name, # Students in Course,Highest Unit (Students),Online CSF  Course";
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

    @Test
    void get_csv_extension_file(){
        String fileCsvName = "test.csv";
        Assert.assertEquals(fileService.getFileExtension(fileCsvName),".csv");
    }

    @Test
    void get_pdf_extension_file(){
        String filePdfName = "test.pdf";
        Assert.assertEquals(fileService.getFileExtension(filePdfName),".pdf");
    }
}