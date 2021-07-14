package com.react.pnld;

import com.react.pnld.dto.ScheduleFileLoadDTO;
import com.react.pnld.dto.*;
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

    private final String teacherRosterHeaderString = "firstname,prefname,lastname,email,schoolname,schoolcity,grade,coursename,studentsincourse,highestunitstudents,onlinecsfcourse,onlinecsfhighestunit,index";
    private final String teacherOptInHeaderString = "firstname,prefname,lastname,email,altemail,gender,schoolname,schoolcity,subjectstaught,agetaught,csrescources,roboticsrescources,workshoporganizer,workshopdate,workshopfacilitator,index";
    private final String studentLevelHeaderString = "teachername,teacheremail,schoolname,schoolcity,course,sectionid,organizer,students,studentswithprojects,index,avgcoursecompleted,maxcoursecompleted,medianlevelsattempted,projects";
    private final String signInPerCourseHeaderString = "weekofsigninat,coursename,distinctcountofuseridcsfstarted";
    private final String signInsHeaderString = "weekofsigninat,distinctcountofuserid,differenceindistinctcountofuserid";
    private final String generalResumeHeaderString = "RUT;REGION;RBD;NOMBRES;ASISTE JORNADA;ANNO CAPACITACION;ESTADO FINAL";
    private final String satisfactionHeaderString = "respuesta\tenviadoel\tinstitucin\tdepartamento\tcurso\tgrupo\tid\tnombrecompleto\trunej123456789\tq01utilizandounaescalade1a4meveoamimismoacomounapersonacercanaalaprogramacin" +
            "\tq01utilizandounaescalade1a4esimportanteparamilaborprofesionalelaprendersobreprogramacin\tq01utilizandounaescalade1a4laenseanzadelaprogramacinesrelevanteparalaeducacindemisestudiantes" +
            "\tq01utilizandounaescalade1a4laenseanzadelaprogramacinesrelevanteparaelfuturolaboraldemisestudiantes\tq01utilizandounaescalade1a4laprogramacinespropiadelasclasesdecomputacintecnologaosimilar" +
            "\tq01utilizandounaescalade1a4laprogramacinpuedevincularsealasasignaturasqueimparto\tq02evalesuniveldeconocimientpensamientocomputacional\tq02evalesuniveldeconocimientprogramacin" +
            "\tq03enunaescalade1a7dondeactualmentemesientocapazdeimplementarunaclaseincorporandoprogramacin\tq03enunaescalade1a7dondeactualmentemesientomotivadoparaimplementarunaclaseincorporandoprogramacin" +
            "\tq04ndiquetodoslosconceptosquealgoritmo\tq04ndiquetodoslosconceptosqueabstraccin\tq04ndiquetodoslosconceptosquedescomposicin\tq04ndiquetodoslosconceptosqueiteracinciclobucleloop" +
            "\tq04ndiquetodoslosconceptosquelenguajedeprogramacin\tq04ndiquetodoslosconceptosqueprograma\tq04ndiquetodoslosconceptosqueninguno\tq05sealetodoslosconceptosdealgoritmo\tq05sealetodoslosconceptosdeabstraccin" +
            "\tq05sealetodoslosconceptosdedescomposicin\tq05sealetodoslosconceptosdeiteracinciclobucleloop\tq05sealetodoslosconceptosdelenguajedeprogramacin\tq05sealetodoslosconceptosdeprograma\tq05sealetodoslosconceptosdeninguno" +
            "\tq06situviesequeelegirunaopci\tq07p30\tq08segnsuopininenculde\tq09quesunalgoritmo\tq10paraquseusanlosalgoritm";
    private final String ctTestTeacher = "timestamp,score,rut,nombre,apellidos,correoelectrnico,sexo,edad,establecimientoeducacional,nivelesenqueenseaohaceclases,participenelplannacionaldelenguajesdigitales,conoceuocupalapginacodeorg," +
            "conocesoocupalaplataformascratch,indiquelahoraactualenformatohhmm,pregunta1,pregunta2,pregunta3,pregunta4,pregunta5,pregunta6,pregunta7,pregunta8,pregunta9,pregunta10,pregunta11,pregunta12,pregunta13,pregunta14,pregunta15," +
            "indiquelahoraactualalllegaraestaseccinenformatohhmm,de1a7cmoconsideraquelefueeneltest,de1a7qutancercanoocercanasesientealoscomputadoresylatecnologa";

    private final String ctTestGroupA = "timestamp,score,nombre,apellidos,sexo,edad,establecimientoeducacional,curso,hasocupadolapginacodeorg,hasocupadolaplataformascratch,indiquelahoraactual,enformatohhmm,ejemploi,ejemploii,ejemploiii," +
            "pregunta1,pregunta2,pregunta3,pregunta4,pregunta5,pregunta6,pregunta7,pregunta8,pregunta9,pregunta10,pregunta11,pregunta12,pregunta13,pregunta14,pregunta15,pregunta16,pregunta17,pregunta18,pregunta19,pregunta20,pregunta21," +
            "pregunta22,pregunta23,pregunta24,pregunta25,indicalahoraactual,enformatohhmm,de1a7,cmoconsiderasquetefueeneltest,de1a7,qutantoteinteresanloscomputadoresylatecnologa,cuntanossobreelapoyoquetuvistealhacereltest," +
            "cuntanosacercadecualquierproblemaquetuvisteparacompletareltest,correoelectrnicoparafuturocontacto";

    @Test
    void contextLoads() {
        Assert.assertNotNull(fileUtilService);
    }

    @Test
    void selectedHeaders_Equals_TeacherRosterType() {
        String[] teacherRosterHeadersMock = teacherRosterHeaderString.split(",");
        String[] teacherRosterHeaders = fileUtilService.selectedHeadersArray("teacher-roster");

        Arrays.sort(teacherRosterHeaders);
        Arrays.sort(teacherRosterHeadersMock);
        Assert.assertEquals(teacherRosterHeaders, teacherRosterHeadersMock);
    }

    @Test
    void selectedHeaders_Equals_TeacherOptInType() {
        String[] teacherOptInHeadersMock = teacherOptInHeaderString.split(",");
        String[] teacherOptInHeaders = fileUtilService.selectedHeadersArray("teacher-opt-in");

        Arrays.sort(teacherOptInHeaders);
        Arrays.sort(teacherOptInHeadersMock);
        Assert.assertEquals(teacherOptInHeaders, teacherOptInHeadersMock);
    }

    @Test
    void selectedHeaders_Equals_StudentLevelType() {
        String[] studentLevelHeadersMock = studentLevelHeaderString.split(",");
        String[] studentLevelHeaders = fileUtilService.selectedHeadersArray("student-level");

        Arrays.sort(studentLevelHeaders);
        Arrays.sort(studentLevelHeadersMock);
        Assert.assertEquals(studentLevelHeaders, studentLevelHeadersMock);
    }

    @Test
    void selectedHeaders_Equals_SignInPerCourseType() {
        String[] signInPerCourseHeadersMock = signInPerCourseHeaderString.split(",");
        String[] signInPerCourseHeaders = fileUtilService.selectedHeadersArray("signin-per-course");

        Arrays.sort(signInPerCourseHeaders);
        Arrays.sort(signInPerCourseHeadersMock);
        Assert.assertEquals(signInPerCourseHeaders, signInPerCourseHeadersMock);
    }

    @Test
    void selectedHeaders_Equals_SignInsHeadersType() {
        String[] signInsHeadersMock = signInsHeaderString.split(",");
        String[] signInsHeaders = fileUtilService.selectedHeadersArray("sign-ins");

        Arrays.sort(signInsHeaders);
        Arrays.sort(signInsHeadersMock);
        Assert.assertEquals(signInsHeaders, signInsHeadersMock);
    }

    @Test
    void selectHeaders_Equals_NotDefined() {
        String[] fooBarHeaders = fileUtilService.selectedHeadersArray("foo-bar");
        Assert.assertEquals(fooBarHeaders.length, 1);
    }

    @Test
    void isStringArraysEquals_WhenFalse() {
        String[] first = fileUtilService.selectedHeadersArray("signin-per-course");
        String[] second = fileUtilService.selectedHeadersArray("sign-ins");
        Assert.assertFalse(fileUtilService.isStringArraysEquals(first, second));
    }

    @Test
    void getFileExtension_When_CsvFile() {
        String fileCsvName = "test.csv";
        Assert.assertEquals(fileUtilService.getExtension(fileCsvName), ".csv");
    }

    @Test
    void getFileExtension_When_PdfFile() {
        String filePdfName = "test.pdf";
        Assert.assertEquals(fileUtilService.getExtension(filePdfName), ".pdf");
    }

    @Test
    void getLoadedFileName_When_Is_2021_03_01() {
        ScheduleFileLoadDTO fooScheduleFileLoadDTO = new ScheduleFileLoadDTO();

        fooScheduleFileLoadDTO.setName("name_by_user");

        MultipartFile multipartFile = new MockMultipartFile("foo_file", "foo_file.csv",
                "text/plain", "foo bar".getBytes());
        fooScheduleFileLoadDTO.setUploadFile(multipartFile);

        Instant instant = Instant.parse("2021-03-01T00:00:00.21Z");
        fooScheduleFileLoadDTO.setLoadedOnDateTime(LocalDateTime.ofInstant(instant, ZoneId.of("UTC")));

        Assert.assertEquals("2021-03-01T00-00-00-name_by_user.csv",
                fileUtilService.getLoadedFileName(fooScheduleFileLoadDTO));
    }

    @Test
    public void getTrainingRequiredInterval_When_MinutesAndSeconds() {
        TrainingFileDTO trainingFileDTO = new TrainingFileDTO();
        trainingFileDTO.setStartIn(LocalDateTime.of(2021, 5, 1, 16, 50, 00));
        trainingFileDTO.setFinishIn(LocalDateTime.of(2021, 5, 1, 17, 2, 31));

        PGInterval duration = trainingFileDTO.getRequiredInterval();
        Assert.assertEquals(12, duration.getMinutes());
        Assert.assertEquals(31, duration.getSeconds());
    }

    @Test
    public void getTrainingRequiredInterval_When_HourAndMinutes() {
        TrainingFileDTO trainingFileDTO = new TrainingFileDTO();
        trainingFileDTO.setStartIn(LocalDateTime.of(2021, 5, 1, 10, 00, 00));
        trainingFileDTO.setFinishIn(LocalDateTime.of(2021, 5, 1, 11, 32, 00));

        PGInterval duration = trainingFileDTO.getRequiredInterval();
        Assert.assertEquals(32, duration.getMinutes());
        Assert.assertEquals(1, duration.getHours());
    }

    @Test
    public void getTrainingRequiredInterval_When_DaysAndHours() {
        TrainingFileDTO trainingFileDTO = new TrainingFileDTO();
        trainingFileDTO.setStartIn(LocalDateTime.of(2021, 5, 1, 1, 00, 00));
        trainingFileDTO.setFinishIn(LocalDateTime.of(2021, 5, 7, 20, 00, 00));

        PGInterval duration = trainingFileDTO.getRequiredInterval();
        Assert.assertEquals(6, duration.getDays());
        Assert.assertEquals(19, duration.getHours());
    }

    @Test
    public void getTrainingRequiredInterval_When_MonthsDaysAndHours() {
        TrainingFileDTO trainingFileDTO = new TrainingFileDTO();
        trainingFileDTO.setStartIn(LocalDateTime.of(2021, 5, 6, 10, 00, 00));
        trainingFileDTO.setFinishIn(LocalDateTime.of(2021, 6, 5, 9, 00, 00));

        PGInterval duration = trainingFileDTO.getRequiredInterval();
        Assert.assertEquals(0, duration.getMonths());
        Assert.assertEquals(29, duration.getDays());
        Assert.assertEquals(23, duration.getHours());
    }

    @Test
    public void getTrainingRequiredInterval_When_EqualsOrNotDifferences() {
        TrainingFileDTO trainingFileDTO = new TrainingFileDTO();
        trainingFileDTO.setStartIn(LocalDateTime.of(2021, 5, 7, 1, 00, 00));
        trainingFileDTO.setFinishIn(LocalDateTime.of(2021, 5, 7, 1, 00, 00));

        PGInterval duration = trainingFileDTO.getRequiredInterval();
        Assert.assertEquals(0, duration.getMinutes());
        Assert.assertEquals(0, duration.getSeconds());
    }

    @Test
    public void selectedHeaders_Equals_GeneralResumeType() {
        String[] generalResumeHeadersMock = generalResumeHeaderString.toLowerCase().replaceAll("\t", ";").replaceAll("(, |[^a-zA-Z0-9,;])", "").split(";");
        String[] generalResumeHeaders = fileUtilService.selectedHeadersArray("general-resume");

        Arrays.sort(generalResumeHeaders);
        Arrays.sort(generalResumeHeadersMock);
        Assert.assertEquals(generalResumeHeaders, generalResumeHeadersMock);
    }

    @Test
    public void misc() {
        Assert.assertEquals("1.234.567-k", "1.234.567-K".toLowerCase());
    }

    @Test
    public void removeAccents() {
        String schoolName = "colegio ñuble del río";
        Assert.assertEquals(FileUtilService.removeAccents(schoolName), "colegio nuble del rio");
    }


    @Test
    public void selectedHeaders_Equals_SatisfactionType() {
        String[] satisfactionHeadersMock = satisfactionHeaderString.toLowerCase().replaceAll("(, |[^a-zA-Z0-9,;\t])", "").split("\\t");
        String[] satisfactionHeaders = fileUtilService.selectedHeadersArray("satis");

        Arrays.sort(satisfactionHeaders);
        Arrays.sort(satisfactionHeadersMock);
        Assert.assertEquals(satisfactionHeaders, satisfactionHeadersMock);
    }

    @Test
    void selectHeaders_Equals_CTTestTeacher(){
        String[] ctTestTeacherHeadersMock = ctTestTeacher.toLowerCase().replaceAll("(, |[^a-zA-Z0-9,;\t])", "").split(",");
        String[] ctTestTeacherHeaders = fileUtilService.selectedHeadersArray("ct-teachers");

        Arrays.sort(ctTestTeacherHeaders);
        Arrays.sort(ctTestTeacherHeadersMock);
        Assert.assertEquals(ctTestTeacherHeaders, ctTestTeacherHeadersMock);
    }

    @Test
    void selectHeaders_Equals_CTTestGroupA(){
        String[] ctTestGroupOneHeadersMock = ctTestGroupA.toLowerCase().replaceAll("(, |[^a-zA-Z0-9,;\t])", "").split(",");
        String[] ctTestGroupOneHeaders = fileUtilService.selectedHeadersArray("ct-students-group-one");

        Arrays.sort(ctTestGroupOneHeaders);
        Arrays.sort(ctTestGroupOneHeadersMock);
        Assert.assertEquals(ctTestGroupOneHeaders, ctTestGroupOneHeadersMock);
    }

}