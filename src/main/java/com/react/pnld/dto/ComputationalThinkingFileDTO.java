package com.react.pnld.dto;

import com.univocity.parsers.annotations.Parsed;

public class ComputationalThinkingFileDTO {

    @Parsed(field = "Timestamp")
    private String timeStamp;

    @Parsed(field = "Nombre")
    private String name;

    @Parsed(field = "Apellidos")
    private String lastNames;

    @Parsed(field = "Sexo")
    private String gender;

    @Parsed(field = "Edad")
    private String age;

    @Parsed(field = "Establecimiento Educacional")
    private String educationalInstitution;

    @Parsed(field = {"Correo electrónico para futuro contacto", "Correo electrónico"})
    private String email;

    public ComputationalThinkingFileDTO(String timeStamp, String name, String lastNames, String gender, String age,
                                        String educationalInstitution, String email) {
        this.timeStamp = timeStamp;
        this.name = name;
        this.lastNames = lastNames;
        this.gender = gender;
        this.age = age;
        this.educationalInstitution = educationalInstitution;
        this.email = email;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(String educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ComputationalThinkingFileDTO{" +
                "timeStamp='" + timeStamp + '\'' +
                ", name='" + name + '\'' +
                ", lastNames='" + lastNames + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", educationalInstitution='" + educationalInstitution + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
