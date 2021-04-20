package com.react.pnld;

import com.react.pnld.services.FileUtilService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

@SpringBootTest
public class ParserUnivocityTest extends AbstractTestNGSpringContextTests {

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
