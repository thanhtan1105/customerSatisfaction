package com.hackathon.constant;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public enum Gender {

    MALE(0, "MALE"),
    FEMALE(1, "FEMALE");

    private int index;
    private String name;

    Gender(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static Gender fromIndex(int index){
        for (Gender ex : values()){
            if (ex.getIndex() == index){
                return ex;
            }
        }
        return null;
    }

    @JsonValue
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
