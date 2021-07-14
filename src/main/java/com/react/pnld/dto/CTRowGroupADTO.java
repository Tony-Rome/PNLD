package com.react.pnld.dto;

import com.univocity.parsers.annotations.Parsed;

public class CTRowGroupADTO extends CTRowStudentsDTO {

    @Parsed(index = 39)
    private String finishTime;

    @Parsed(index = 40)
    private int howDoYouThinkYouDidInTheTest;

    @Parsed(index = 41)
    private int howInterestedAreYouInComputers;

    @Parsed(index = 42)
    private String aboutSupportYouReceived;

    @Parsed(index = 43)
    private String aboutProblemsYouHadCompletingTest;

    @Parsed(index = 44)
    private String email;

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public int getHowDoYouThinkYouDidInTheTest() {
        return howDoYouThinkYouDidInTheTest;
    }

    public void setHowDoYouThinkYouDidInTheTest(int howDoYouThinkYouDidInTheTest) {
        this.howDoYouThinkYouDidInTheTest = howDoYouThinkYouDidInTheTest;
    }

    public int getHowInterestedAreYouInComputers() {
        return howInterestedAreYouInComputers;
    }

    public void setHowInterestedAreYouInComputers(int howInterestedAreYouInComputers) {
        this.howInterestedAreYouInComputers = howInterestedAreYouInComputers;
    }

    public String getAboutSupportYouReceived() {
        return aboutSupportYouReceived;
    }

    public void setAboutSupportYouReceived(String aboutSupportYouReceived) {
        this.aboutSupportYouReceived = aboutSupportYouReceived;
    }

    public String getAboutProblemsYouHadCompletingTest() {
        return aboutProblemsYouHadCompletingTest;
    }

    public void setAboutProblemsYouHadCompletingTest(String aboutProblemsYouHadCompletingTest) {
        this.aboutProblemsYouHadCompletingTest = aboutProblemsYouHadCompletingTest;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CTStudentsGroupOne{" +
                "finishTime='" + finishTime + '\'' +
                ", howDoYouThinkYouDidInTheTest=" + howDoYouThinkYouDidInTheTest +
                ", howInterestedAreYouInComputers=" + howInterestedAreYouInComputers +
                ", aboutSupportYouReceived='" + aboutSupportYouReceived + '\'' +
                ", aboutProblemsYouHadCompletingTest='" + aboutProblemsYouHadCompletingTest + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
