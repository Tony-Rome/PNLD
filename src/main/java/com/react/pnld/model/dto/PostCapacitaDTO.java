package com.react.pnld.model.dto;

import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.Parsed;

public class PostCapacitaDTO extends FileResumeDTO{

    @Parsed(index = 0)
    private String lastNames;

    @Parsed(index = 1)
    private String firstName;

    @Parsed(index = 2)
    private String rut;

    @Parsed(index = 3)
    private String institution;

    @Parsed(index = 4)
    private String department;

    @Parsed(index = 5)
    private String email;

    @Parsed(index = 6)
    private String testState;

    @Parsed(index = 7)
    private String startIn;

    @Parsed(index = 8)
    private String finishIn; //9 de octubre de 2019  14:24

    @Parsed(index = 9)
    private String duration;

    @Parsed(index = 10)
    @Format(formats = {"#0,00"}, options = "decimalSeparator=,")
    private float score;

    @Parsed(index = 11)
    private String answerOne;

    @Parsed(index = 12)
    private String answerTwo;

    @Parsed(index = 13)
    private String answerThree;

    @Parsed(index = 14)
    private String answerFour;

    @Parsed(index = 15)
    private String answerFive;

    @Parsed(index = 16)
    private String answerSix;

    @Parsed(index = 17)
    private String answerSeven;

    @Parsed(index = 18)
    private String answerEight;

    @Parsed(index = 19)
    private String answerNine;

    @Parsed(index = 20)
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
