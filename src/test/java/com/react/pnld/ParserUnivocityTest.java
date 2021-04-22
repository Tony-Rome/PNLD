package com.react.pnld;

import com.react.pnld.dto.TrainingFileDTO;
import com.react.pnld.model.CSVHeadersProperties;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class ParserUnivocityTest extends AbstractTestNGSpringContextTests {

    @Autowired
    CSVHeadersProperties csvHeadersProperties;

    private String getDummyPostTrainingFileLikeString(){
        String postTrainingHeaders = Arrays.toString(csvHeadersProperties.getPostTraining()).
                replace("[","").replace("]","");

        String dummyTeacher = "IBARRA UBEDA,PATRICIA,15.098.834-9,,,PATTYIBARRAUBEDA@HOTMAIL.COM,Finalizado," +
                "\"9 de octubre de 2019  14:24\",\"9 de octubre de 2019  14:30\",\"6 minutos 24 segundos\",\"8,00\",";

        return postTrainingHeaders.concat("\n").concat(dummyTeacher);
    }


    private List<TrainingFileDTO> getListTrainingBeans(){
        InputStream inputStream = new ByteArrayInputStream(getDummyPostTrainingFileLikeString().getBytes());

        try {
            Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

            BeanListProcessor<TrainingFileDTO> rowProcessor = new BeanListProcessor<TrainingFileDTO>(TrainingFileDTO.class);

            CsvParserSettings parserSettings = new CsvParserSettings();
            parserSettings.setLineSeparatorDetectionEnabled(true);
            parserSettings.setProcessor(rowProcessor);
            parserSettings.setHeaderExtractionEnabled(true);

            CsvParser parser = new CsvParser(parserSettings);
            parser.parse(inputStreamReader);

            // The BeanListProcessor provides a list of objects extracted from the input.

            List<TrainingFileDTO> beans = rowProcessor.getBeans();
            return beans;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    @Test
    public void moodleTimestampFormat_parser_to_DateTime(){
        //TODO move logic getReader from parseRowsToBeans
        //TODO Parsear solo un objeto y no solo una lista
        List<TrainingFileDTO> dummyList = this.getListTrainingBeans();
        Assert.assertEquals(6, dummyList.get(0).getDuration().getMinutes());
        Assert.assertEquals(24, dummyList.get(0).getDuration().getSeconds());
    }
}
