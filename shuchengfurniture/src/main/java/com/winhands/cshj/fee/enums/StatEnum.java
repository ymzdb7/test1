package com.winhands.cshj.fee.enums;

/**
 * @author guojun
 */
public enum StatEnum {
    OUT_DATE(1,"已失效"),
    NOT_IN_EFFECT(2,"未生效"),
    IN_USE(3,"使用中");

    private int state;

    private String stateInfo;

    StatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static StatEnum stateOf(int index) {
        for (StatEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
