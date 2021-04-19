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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import java.io.*;
import java.time.LocalTime;
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
                "\"9 de octubre de 2019  14:24\",\"9 de octubre de 2019  14:30\",\"06 minutos 24 segundos\",\"8,00\",";

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
        //TODO test minutes number and seconds
        LocalTime duration = dummyList.get(0).getDuration();
        Assert.assertEquals(duration.getMinute(), 6);
        Assert.assertEquals(duration.getSecond(), 24);
        //Assert.assertTrue(true);
    }

    @Test
    public void testFormmaterTime(){
        String DAYS_STRING = "día";
        String HOURS_STRING = "hora";
        String MINUTES_STRING = "minutos";
        String SECONDS_STRING = "segundos";

        String timeString = "8 horas 6 minutos";
        String timeWithoutWords = timeString.replaceAll("[a-zA-Z]","");
        String timeWithoutSpace = timeWithoutWords.trim().replaceAll("[ \\t\\n\\x0B\\f\\r]",";");
        String[] time = timeWithoutSpace.split(";");


        int days = 0;
        int hour = 0;
        int mins = 0;
        int secs = 0;
        if(timeString.contains(DAYS_STRING) && timeString.contains(HOURS_STRING)){
            days = Integer.parseInt(time[0].trim());
            hour = Integer.parseInt(time[1].trim());

        }

        if(timeString.contains(HOURS_STRING) && timeString.contains(MINUTES_STRING)){
            hour = Integer.parseInt(time[0].trim());
            mins = Integer.parseInt(time[1].trim());

        }

        if(timeString.contains(MINUTES_STRING) && timeString.contains(SECONDS_STRING)) {
            mins = Integer.parseInt(time[0].trim());
            secs = Integer.parseInt(time[1].trim());
        }

        try {
            Duration duration = DatatypeFactory.newInstance().newDurationDayTime(true, days, hour, mins, secs);


        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }

}
