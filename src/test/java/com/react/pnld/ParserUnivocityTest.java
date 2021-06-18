package com.react.pnld;

import com.react.pnld.model.CSVHeadersProperties;
import com.react.pnld.services.FileUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.react.pnld.dto.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@SpringBootTest
public class ParserUnivocityTest extends AbstractTestNGSpringContextTests {

    @Autowired
    CSVHeadersProperties csvHeadersProperties;

    @Autowired
    FileUtilService fileUtilService;

    private String getDummyTrainingFileLikeString() {
        String postTrainingHeaders = Arrays.toString(csvHeadersProperties.getPostTraining()).
                replace("[", "").replace("]", "");

        String dummyTeacher = "IBARRA UBEDA,PATRICIA,15.098.834-9,,,PATTYIBARRAUBEDA@HOTMAIL.COM,Finalizado," +
                "\"9 de octubre de 2019  14:24\",\"9 de octubre de 2019  14:30\",\"6 minutos 24 segundos\",\"8,00\"," +
                "\"B y C son correctas\",\"Pensamiento computacional implica el proceso mental...\",\"A, B y C son correctas\"," +
                "\"Descomponer el problema inicial...\",\"Al introducir el pensamiento computacional en el aula,...\"," +
                "\"Pensar en cómo dividir ...\",Descomposición,\"Todas son falsas.\",\"Los estudiantes a cargo de ...\"," +
                "\"Determinar que la distribución ...\"";

        return postTrainingHeaders.concat("\n").concat(dummyTeacher);
    }

    private String getDummyCTStudentsGroupOne() {
        //TODO fix real headers.
        String headers = "Timestamp\tNombre\tApellidos\tSexo\tEdad\tEstablecimiento Educacional\tCurso\t¿Has ocupado la página Code.org?" +
                "\t¿Has ocupado la plataforma Scratch?\tIndique la hora actual, en formato HH:MM\tEjemplo I\tEjemplo II\tEjemplo III" +
                "\tPregunta 1\tPregunta 2\tPregunta 3\tPregunta 4\tPregunta 5\tPregunta 6\tPregunta 7\tPregunta 8\tPregunta 9\tPregunta 10" +
                "\tPregunta 11\tPregunta 12\tPregunta 13\tPregunta 14\tPregunta 15\tPregunta 16\tPregunta 17\tPregunta 18\tPregunta 19\tPregunta 20" +
                "\tPregunta 21\tPregunta 22\tPregunta 23\tPregunta 24\tPregunta 25\tIndica la hora actual, en formato HH:MM" +
                "\tDe 1 a 7, ¿cómo consideras que te fue en el Test?\tDe 1 a 7, ¿qué tanto te interesan los computadores y la tecnología?" +
                "\tCuéntanos sobre el apoyo que tuviste al hacer el test\tCuéntanos acerca de cualquier problema que tuviste  para completar el test" +
                "\tCorreo electrónico para futuro contacto";
        String dummyResponse = "04-01-21 18:54\tJocelyn\tSimmonds\tMujer\t6\tMi casa\t3º básico\tNo\tSí\t6:52:00 PM\tB\tD\tC\tB\tB\tC\tD\tA\tB\tA\tC" +
                "\tD\tD\t\t\t\tC\t\tC\tD\tA\t\t\tC\tD\t\t\t\t6:53:00 PM\t5\t7\tlas lei solita :-)\ttengo tuto\ta@aaa.com";
        return headers.concat("\n").concat(dummyResponse);
    }

    @Test
    public void moodleTimestampFormat_parser_to_DateTime() throws UnsupportedEncodingException {
        InputStream inputStream = new ByteArrayInputStream(getDummyTrainingFileLikeString().getBytes());
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        List<TrainingFileDTO> dummyList = fileUtilService.parseRowsToBeans(reader, TrainingFileDTO.class);
        Assert.assertEquals(6, dummyList.get(0).getRequiredInterval().getMinutes());
        Assert.assertEquals(0, dummyList.get(0).getRequiredInterval().getSeconds());
    }

    @Test
    public void moodleTrainingTest_parser_StartInDateTime() throws UnsupportedEncodingException {

        InputStream inputStream = new ByteArrayInputStream(getDummyTrainingFileLikeString().getBytes());
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        List<TrainingFileDTO> dummyList = fileUtilService.parseRowsToBeans(reader, TrainingFileDTO.class);

        Assert.assertEquals(9, dummyList.get(0).getStartIn().getDayOfMonth());
        Assert.assertEquals(10, dummyList.get(0).getStartIn().getMonth().getValue());
        Assert.assertEquals(14, dummyList.get(0).getStartIn().getHour());
        Assert.assertEquals(24, dummyList.get(0).getStartIn().getMinute());
    }


    @Test
    public void moodleTrainingTest_parser_FinishInDateTime() throws UnsupportedEncodingException {

        InputStream inputStream = new ByteArrayInputStream(getDummyTrainingFileLikeString().getBytes());
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        List<TrainingFileDTO> dummyList = fileUtilService.parseRowsToBeans(reader, TrainingFileDTO.class);

        Assert.assertEquals(9, dummyList.get(0).getFinishIn().getDayOfMonth());
        Assert.assertEquals(10, dummyList.get(0).getFinishIn().getMonth().getValue());
        Assert.assertEquals(14, dummyList.get(0).getFinishIn().getHour());
        Assert.assertEquals(30, dummyList.get(0).getFinishIn().getMinute());
    }


    @Test
    public void ctStudentGroupOne_when_parsingOk() throws UnsupportedEncodingException {
        InputStream inputStream = new ByteArrayInputStream(getDummyCTStudentsGroupOne().getBytes());
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        List<CTGroupOneRowDTO> ctFirstGroupStudentsRows = fileUtilService.parseRowsToBeans(reader,
                CTGroupOneRowDTO.class);

        Assert.assertEquals("Jocelyn", ctFirstGroupStudentsRows.get(0).getName());
        Assert.assertEquals("Simmonds", ctFirstGroupStudentsRows.get(0).getLastNames());
        Assert.assertEquals("6:53:00 PM", ctFirstGroupStudentsRows.get(0).getFinishTime());
    }

    @Test
    public void testParseDate() {

        String inputClean = "1 de octubre de 2021 19:35".replaceAll(" de ", "/").replaceAll("\\s+|\\t", " ");
        String formatPattern = "d/MMMM/yyyy H:m";
        LocalDateTime localDateTime = LocalDateTime.parse(inputClean,
                DateTimeFormatter.ofPattern(formatPattern, new Locale("es", "ES")));

        Assert.assertEquals(localDateTime.getDayOfMonth(), 1);
    }
}
