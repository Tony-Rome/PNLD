package com.react.pnld;

import com.react.pnld.dto.TrainingFileDTO;
import com.react.pnld.model.CSVHeadersProperties;
import com.react.pnld.services.FileUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
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

    private String getDummyTrainingFileLikeString(){
        String postTrainingHeaders = Arrays.toString(csvHeadersProperties.getPostTraining()).
                replace("[","").replace("]","");

        String dummyTeacher = "IBARRA UBEDA,PATRICIA,15.098.834-9,,,PATTYIBARRAUBEDA@HOTMAIL.COM,Finalizado," +
                "\"9 de octubre de 2019  14:24\",\"9 de octubre de 2019  14:30\",\"6 minutos 24 segundos\",\"8,00\"," +
                "\"B y C son correctas\",\"Pensamiento computacional implica el proceso mental...\",\"A, B y C son correctas\"," +
                "\"Descomponer el problema inicial...\",\"Al introducir el pensamiento computacional en el aula,...\"," +
                "\"Pensar en cómo dividir ...\",Descomposición,\"Todas son falsas.\",\"Los estudiantes a cargo de ...\"," +
                "\"Determinar que la distribución ...\"";

        return postTrainingHeaders.concat("\n").concat(dummyTeacher);
    }

    private String getDummyCTtestFirstGroupStudents(){
        //TODO fix real headers.
        String headers = "TO_FIX";
        String dummyResponse = "04-01-21 18:54;Jocelyn;Simmonds;Mujer;6;Mi casa;3º básico;No;Sí;6:52:00 PM;B;D;C;B;B;C;" +
                "D;A;B;A;C;D;D;;;;C;;C;D;A;;;C;D;;;;6:53:00 PM;5;7;las lei solita :-);tengo tuto;a@aaa.com";
        return headers.concat("\n").concat(dummyResponse);
    }

    @Test
    public void moodleTimestampFormat_parser_to_DateTime() throws UnsupportedEncodingException {
        InputStream inputStream = new ByteArrayInputStream(getDummyTrainingFileLikeString().getBytes());
        Reader reader = new InputStreamReader(inputStream, "UTF-8");

        List<TrainingFileDTO> dummyList = fileUtilService.parseRowsToBeans(reader, TrainingFileDTO.class);
        Assert.assertEquals(6, dummyList.get(0).getRequiredInterval().getMinutes());
        Assert.assertEquals(0, dummyList.get(0).getRequiredInterval().getSeconds());
    }

    @Test
    public void moodleTrainingTest_parser_StartInDateTime() throws UnsupportedEncodingException {

        InputStream inputStream = new ByteArrayInputStream(getDummyTrainingFileLikeString().getBytes());
        Reader reader = new InputStreamReader(inputStream, "UTF-8");

        List<TrainingFileDTO> dummyList = fileUtilService.parseRowsToBeans(reader, TrainingFileDTO.class);

        Assert.assertEquals(9, dummyList.get(0).getStartIn().getDayOfMonth());
        Assert.assertEquals(10, dummyList.get(0).getStartIn().getMonth().getValue());
        Assert.assertEquals(14, dummyList.get(0).getStartIn().getHour());
        Assert.assertEquals(24, dummyList.get(0).getStartIn().getMinute());
    }


    @Test
    public void moodleTrainingTest_parser_FinishInDateTime() throws UnsupportedEncodingException {

        InputStream inputStream = new ByteArrayInputStream(getDummyTrainingFileLikeString().getBytes());
        Reader reader = new InputStreamReader(inputStream, "UTF-8");

        List<TrainingFileDTO> dummyList = fileUtilService.parseRowsToBeans(reader, TrainingFileDTO.class);

        Assert.assertEquals(9, dummyList.get(0).getFinishIn().getDayOfMonth());
        Assert.assertEquals(10, dummyList.get(0).getFinishIn().getMonth().getValue());
        Assert.assertEquals(14, dummyList.get(0).getFinishIn().getHour());
        Assert.assertEquals(30, dummyList.get(0).getFinishIn().getMinute());
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
