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

    @Test
    public void moodleTimestampFormat_parser_to_DateTime() throws UnsupportedEncodingException {
        InputStream inputStream = new ByteArrayInputStream(getDummyTrainingFileLikeString().getBytes());
        Reader reader = new InputStreamReader(inputStream, "UTF-8");

        List<TrainingFileDTO> dummyList = fileUtilService.parseRowsToBeans(reader, TrainingFileDTO.class);
        Assert.assertEquals(6, dummyList.get(0).getRequiredInterval().getMinutes());
        Assert.assertEquals(24, dummyList.get(0).getRequiredInterval().getSeconds());
    }
}
