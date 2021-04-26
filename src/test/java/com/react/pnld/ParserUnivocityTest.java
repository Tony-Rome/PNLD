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
import java.util.Arrays;
import java.util.List;

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
                "\"9 de octubre de 2019  14:24\",\"9 de octubre de 2019  14:30\",\"6 minutos 24 segundos\",\"8,00\",";

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
        Assert.assertEquals(24, dummyList.get(0).getRequiredInterval().getSeconds());
    }
}
