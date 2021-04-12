package com.react.pnld.dto;

import com.univocity.parsers.annotations.Parsed;

public class TeacherRosterDTO {

    @Parsed(index = 0)
    private String firstName;

    @Parsed(index = 1)
    private String prefName;

    @Parsed(index = 2)
    private String lastName;

    @Parsed(index = 3)
    private String email;

    @Parsed(index = 4)
    private String schoolName;

    @Parsed(index = 5)
    private String schoolCity;

    @Parsed(index = 6)
    private String grade;

    @Parsed(index = 7)
    private String courseName;

    @Parsed(index = 8)
    private int quantityStudentInCourse;

    @Parsed(index = 9)
    private int highestUnit;

    @Parsed(index = 10)
    private String onlineCSFCourse;

    @Parsed(index = 11)
    private int onlineCSFHighestUnit;

    @Parsed(index = 12)
    private int index;

    @Override
    public String toString() {
        return "TeacherRosterDTO{" +
                "firstName='" + firstName + '\'' +
                ", prefName='" + prefName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", schoolCity='" + schoolCity + '\'' +
                ", grade=" + grade +
                ", courseName='" + courseName + '\'' +
                ", quantityStudentInCourse=" + quantityStudentInCourse +
                ", highestUnit=" + highestUnit +
                ", onlineCSFCourse='" + onlineCSFCourse + '\'' +
                ", onlineCSFHighestUnit=" + onlineCSFHighestUnit +
                ", index=" + index +
                '}';
    }
}
