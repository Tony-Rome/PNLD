package com.react.pnld.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "csv.gender")
public class GenderProperties {

    private List<String> female;
    private List<String> male;
    private List<String> other;
    private List<String> notSpecify;

    public static String GENDER_TYPE_FEMALE = "femenino";
    public static String GENDER_TYPE_MALE = "masculino";
    public static String GENDER_TYPE_OTHER = "otro";
    public static String GENDER_TYPE_NOT_ESPECIFY = "no especificado";

    public List<String> getFemale() {
        return female;
    }

    public void setFemale(List<String> female) {
        this.female = female;
    }

    public List<String> getMale() {
        return male;
    }

    public void setMale(List<String> male) {
        this.male = male;
    }

    public List<String> getOther() {
        return other;
    }

    public void setOther(List<String> other) {
        this.other = other;
    }

    public List<String> getNotSpecify() {
        return notSpecify;
    }

    public void setNotSpecify(List<String> notSpecify) {
        this.notSpecify = notSpecify;
    }
}
