package com.react.pnld.model;

public enum DimensionType {
    TRAINING("capacitaciones"),
    CODE_ORG_USE("uso-codeorg"),
    CT_TEST("test-pc"),
    NOT_FOUND("not found");

    public final String name;

    private DimensionType(String name){
        this.name = name;
    }

    public static DimensionType valueOfName(String name) {
        for (DimensionType e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return NOT_FOUND;
    }
}
