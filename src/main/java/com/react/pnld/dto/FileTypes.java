package com.react.pnld.dto;

public enum FileTypes {
    TEACHER_ROSTER("teacher-roster"),
    TEACHER_OPT_IN("teacher-opt-in"),
    STUDENT_LEVEL("student-level"),
    SIGNIN_PER_COURSE("signin-per-course"),
    SIGN_INS("sign-ins"),
    DIAGNOSIS("diagnostico"),
    PRE_TRAINING("pre-capacita"),
    POST_TRAINING("post-capacita"),
    TEST_CT_1("test-pc-1"),
    TEST_CT_2("test-pc-2"),
    TEST_CT_3("test-pc-3"),
    SALIDA("salida"),
    SATISFACTION("satis"),
    NOT_DEFINED("");

    public final String label;

    private FileTypes(String label) {
        this.label = label;
    }

    public static FileTypes valueOfLabel(String label) {
        for (FileTypes e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return NOT_DEFINED;
    }

    public static final int STATE_SCHEDULED = 1;
    public static final int STATE_IN_PROCESS = 2;
    public static final int FILE_STATE_PROCESSED = 3;
}
