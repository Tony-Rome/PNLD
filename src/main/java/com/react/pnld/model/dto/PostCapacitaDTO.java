package com.react.pnld.model.dto;

import com.univocity.parsers.annotations.Parsed;

import java.sql.Timestamp;
import java.time.LocalTime;

public class PostCapacitaDTO extends FileResumeDTO{

    @Parsed(index = 1)
    private String lastNames;

    @Parsed(index = 2)
    private String firstName;

    @Parsed(index = 3)
    private String rut;

    @Parsed(index = 4)
    private String institution;

    @Parsed(index = 5)
    private String department;

    @Parsed(index = 6)
    private String email;

    @Parsed(index = 7)
    private String testState;

    @Parsed(index = 8)
    private Timestamp startIn;

    @Parsed(index = 9)
    private Timestamp finishIn;

    @Parsed(index = 10)
    private LocalTime duration;

    @Parsed(index = 11)
    private float score;

    @Parsed(index = 12)
    private String answerOne;

    @Parsed(index = 13)
    private String answerTwo;

    @Parsed(index = 14)
    private String answerThree;

    @Parsed(index = 15)
    private String answerFour;

    @Parsed(index = 16)
    private String answerFive;

    @Parsed(index = 17)
    private String answerSix;

    @Parsed(index = 18)
    private String answerSeven;

    @Parsed(index = 19)
    private String answerEight;

    @Parsed(index = 20)
    private String answerNine;

    @Parsed(index = 21)
    private String answerTen;



    @Override
    public String toString() {
        return "PostCapacitaDTO{" +
                "lastNames='" + lastNames + '\'' +
                ", firstName='" + firstName + '\'' +
                ", rut='" + rut + '\'' +
                ", institution='" + institution + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", testState='" + testState + '\'' +
                ", startIn=" + startIn +
                ", finishIn=" + finishIn +
                ", duration=" + duration +
                ", score=" + score +
                ", answerOne='" + answerOne + '\'' +
                ", answerTwo='" + answerTwo + '\'' +
                ", answerThree='" + answerThree + '\'' +
                ", answerFour='" + answerFour + '\'' +
                ", answerFive='" + answerFive + '\'' +
                ", answerSix='" + answerSix + '\'' +
                ", answerSeven='" + answerSeven + '\'' +
                ", answerEight='" + answerEight + '\'' +
                ", answerNine='" + answerNine + '\'' +
                ", answerTen='" + answerTen + '\'' +
                '}';
    }
}
