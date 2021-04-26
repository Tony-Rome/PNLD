package com.react.pnld.dto;

import com.univocity.parsers.annotations.Parsed;

public class CTFileFirstGroupStudentsDTO extends ComputationalThinkingFileDTO{

    @Parsed(field = "Curso")
    private String level;

    @Parsed(field = "¿Has ocupado la página Code.org?")
    private String useCodeOrgPage;//TODO a boolean

    @Parsed(field = "¿Has ocupado la plataforma Scratch?")
    private String useScratch; //TODO a boolean

    @Parsed(field = "Indique la hora actual, en formato HH:MM")
    private String actualTime;

    @Parsed(field = "Ejemplo I")
    private String exampleI;

    @Parsed(field = "Ejemplo II")
    private String exampleII;

    @Parsed(field = "Ejemplo III")
    private String exampleIII;

    @Parsed(field = "Pregunta 1")
    private String questionOne;

    @Parsed(field = "Pregunta 2")
    private String questionTwo;

    @Parsed(field = "Pregunta 3")
    private String questionThree;

    @Parsed(field = "Pregunta 4")
    private String questionFour;

    @Parsed(field = "Pregunta 5")
    private String questionFive;

    @Parsed(field = "Pregunta 6")
    private String questionSix;

    @Parsed(field = "Pregunta 7")
    private String questionSeven;

    @Parsed(field = "Pregunta 8")
    private String questionEight;

    @Parsed(field = "Pregunta 9")
    private String questionNine;

    @Parsed(field = "Pregunta 10")
    private String questionTen;

    @Parsed(field = "Pregunta 11")
    private String questionEleven;

    @Parsed(field = "Pregunta 12")
    private String questionTwelve;

    @Parsed(field = "Pregunta 13")
    private String questionThirteen;

    @Parsed(field = "Pregunta 14")
    private String questionFourteen;

    @Parsed(field = "Pregunta 15")
    private String questionFiveteen;

    @Parsed(field = "Pregunta 16")
    private String questionSixteen;

    @Parsed(field = "Pregunta 17")
    private String questionSeventeen;

    @Parsed(field = "Pregunta 18")
    private String questionEighteen;

    @Parsed(field = "Pregunta 19")
    private String questionNineteen;

    @Parsed(field = "Pregunta 20")
    private String questionTwenty;

    @Parsed(field = "Pregunta 21")
    private String questionTwentyOne;

    @Parsed(field = "Pregunta 22")
    private String questionTwentyTwo;

    @Parsed(field = "Pregunta 23")
    private String questionTwentyThree;

    @Parsed(field = "Pregunta 24")
    private String questionTwentyFour;

    @Parsed(field = "Pregunta 25")
    private String questionTwentyFive;

    @Parsed(field = "")
    private String lastActualTime;

    @Parsed(field = "De 1 a 7, ¿cómo consideras que te fue en el Test?")
    private int howDoYouThinkYouDidInTheTest;

    @Parsed(field = "De 1 a 7, ¿qué tanto te interesan los computadores y la tecnología?")
    private int howInterestedAreYouInComputersAndTechnology;

    @Parsed(field = "Cuéntanos sobre el apoyo que tuviste al hacer el test")
    private String tellUsAboutSupportYouReceivedWhileTakingTest;

    @Parsed(field = "Cuéntanos acerca de cualquier problema que tuviste  para completar el test")
    private String tellUsAboutAnyProblemsYouHadInCompletingTheTest;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUseCodeOrgPage() {
        return useCodeOrgPage;
    }

    public void setUseCodeOrgPage(String useCodeOrgPage) {
        this.useCodeOrgPage = useCodeOrgPage;
    }

    public String getUseScratch() {
        return useScratch;
    }

    public void setUseScratch(String useScratch) {
        this.useScratch = useScratch;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getExampleI() {
        return exampleI;
    }

    public void setExampleI(String exampleI) {
        this.exampleI = exampleI;
    }

    public String getExampleII() {
        return exampleII;
    }

    public void setExampleII(String exampleII) {
        this.exampleII = exampleII;
    }

    public String getExampleIII() {
        return exampleIII;
    }

    public void setExampleIII(String exampleIII) {
        this.exampleIII = exampleIII;
    }

    public String getQuestionOne() {
        return questionOne;
    }

    public void setQuestionOne(String questionOne) {
        this.questionOne = questionOne;
    }

    public String getQuestionTwo() {
        return questionTwo;
    }

    public void setQuestionTwo(String questionTwo) {
        this.questionTwo = questionTwo;
    }

    public String getQuestionThree() {
        return questionThree;
    }

    public void setQuestionThree(String questionThree) {
        this.questionThree = questionThree;
    }

    public String getQuestionFour() {
        return questionFour;
    }

    public void setQuestionFour(String questionFour) {
        this.questionFour = questionFour;
    }

    public String getQuestionFive() {
        return questionFive;
    }

    public void setQuestionFive(String questionFive) {
        this.questionFive = questionFive;
    }

    public String getQuestionSix() {
        return questionSix;
    }

    public void setQuestionSix(String questionSix) {
        this.questionSix = questionSix;
    }

    public String getQuestionSeven() {
        return questionSeven;
    }

    public void setQuestionSeven(String questionSeven) {
        this.questionSeven = questionSeven;
    }

    public String getQuestionEight() {
        return questionEight;
    }

    public void setQuestionEight(String questionEight) {
        this.questionEight = questionEight;
    }

    public String getQuestionNine() {
        return questionNine;
    }

    public void setQuestionNine(String questionNine) {
        this.questionNine = questionNine;
    }

    public String getQuestionTen() {
        return questionTen;
    }

    public void setQuestionTen(String questionTen) {
        this.questionTen = questionTen;
    }

    public String getQuestionEleven() {
        return questionEleven;
    }

    public void setQuestionEleven(String questionEleven) {
        this.questionEleven = questionEleven;
    }

    public String getQuestionTwelve() {
        return questionTwelve;
    }

    public void setQuestionTwelve(String questionTwelve) {
        this.questionTwelve = questionTwelve;
    }

    public String getQuestionThirteen() {
        return questionThirteen;
    }

    public void setQuestionThirteen(String questionThirteen) {
        this.questionThirteen = questionThirteen;
    }

    public String getQuestionFourteen() {
        return questionFourteen;
    }

    public void setQuestionFourteen(String questionFourteen) {
        this.questionFourteen = questionFourteen;
    }

    public String getQuestionFiveteen() {
        return questionFiveteen;
    }

    public void setQuestionFiveteen(String questionFiveteen) {
        this.questionFiveteen = questionFiveteen;
    }

    public String getQuestionSixteen() {
        return questionSixteen;
    }

    public void setQuestionSixteen(String questionSixteen) {
        this.questionSixteen = questionSixteen;
    }

    public String getQuestionSeventeen() {
        return questionSeventeen;
    }

    public void setQuestionSeventeen(String questionSeventeen) {
        this.questionSeventeen = questionSeventeen;
    }

    public String getQuestionEighteen() {
        return questionEighteen;
    }

    public void setQuestionEighteen(String questionEighteen) {
        this.questionEighteen = questionEighteen;
    }

    public String getQuestionNineteen() {
        return questionNineteen;
    }

    public void setQuestionNineteen(String questionNineteen) {
        this.questionNineteen = questionNineteen;
    }

    public String getQuestionTwenty() {
        return questionTwenty;
    }

    public void setQuestionTwenty(String questionTwenty) {
        this.questionTwenty = questionTwenty;
    }

    public String getQuestionTwentyOne() {
        return questionTwentyOne;
    }

    public void setQuestionTwentyOne(String questionTwentyOne) {
        this.questionTwentyOne = questionTwentyOne;
    }

    public String getQuestionTwentyTwo() {
        return questionTwentyTwo;
    }

    public void setQuestionTwentyTwo(String questionTwentyTwo) {
        this.questionTwentyTwo = questionTwentyTwo;
    }

    public String getQuestionTwentyThree() {
        return questionTwentyThree;
    }

    public void setQuestionTwentyThree(String questionTwentyThree) {
        this.questionTwentyThree = questionTwentyThree;
    }

    public String getQuestionTwentyFour() {
        return questionTwentyFour;
    }

    public void setQuestionTwentyFour(String questionTwentyFour) {
        this.questionTwentyFour = questionTwentyFour;
    }

    public String getQuestionTwentyFive() {
        return questionTwentyFive;
    }

    public void setQuestionTwentyFive(String questionTwentyFive) {
        this.questionTwentyFive = questionTwentyFive;
    }

    public String getLastActualTime() {
        return lastActualTime;
    }

    public void setLastActualTime(String lastActualTime) {
        this.lastActualTime = lastActualTime;
    }

    public int getHowDoYouThinkYouDidInTheTest() {
        return howDoYouThinkYouDidInTheTest;
    }

    public void setHowDoYouThinkYouDidInTheTest(int howDoYouThinkYouDidInTheTest) {
        this.howDoYouThinkYouDidInTheTest = howDoYouThinkYouDidInTheTest;
    }

    public int getHowInterestedAreYouInComputersAndTechnology() {
        return howInterestedAreYouInComputersAndTechnology;
    }

    public void setHowInterestedAreYouInComputersAndTechnology(int howInterestedAreYouInComputersAndTechnology) {
        this.howInterestedAreYouInComputersAndTechnology = howInterestedAreYouInComputersAndTechnology;
    }

    public String getTellUsAboutSupportYouReceivedWhileTakingTest() {
        return tellUsAboutSupportYouReceivedWhileTakingTest;
    }

    public void setTellUsAboutSupportYouReceivedWhileTakingTest(String tellUsAboutSupportYouReceivedWhileTakingTest) {
        this.tellUsAboutSupportYouReceivedWhileTakingTest = tellUsAboutSupportYouReceivedWhileTakingTest;
    }

    public String getTellUsAboutAnyProblemsYouHadInCompletingTheTest() {
        return tellUsAboutAnyProblemsYouHadInCompletingTheTest;
    }

    public void setTellUsAboutAnyProblemsYouHadInCompletingTheTest(String tellUsAboutAnyProblemsYouHadInCompletingTheTest) {
        this.tellUsAboutAnyProblemsYouHadInCompletingTheTest = tellUsAboutAnyProblemsYouHadInCompletingTheTest;
    }
}
