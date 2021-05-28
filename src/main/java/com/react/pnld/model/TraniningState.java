package com.react.pnld.model;

public enum TraniningState {
    APPROVED(1,"aprobado"),
    FLUNKED(2,"reprobado"),
    NOT_FOUND(3,"not found");;

    public final int id;
    public final String type;


    private TraniningState(int id, String type){
        this.id = id;
        this.type = type;
    }

    public static TraniningState valueOfId(int id) {

        for(TraniningState traniningState : values()){
            if (traniningState.id == id) {
                return traniningState;
            }
        }
        return NOT_FOUND;
    }

    public static TraniningState valueOfType(String type) {

        for(TraniningState traniningState : values()){
            if (traniningState.type.equals(type)) {
                return traniningState;
            }
        }
        return NOT_FOUND;
    }
}
