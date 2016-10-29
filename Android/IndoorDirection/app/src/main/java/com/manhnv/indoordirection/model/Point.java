package com.manhnv.indoordirection.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ManhNV on 10/29/16.
 */

public class Point {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("floor")
    private int floor;
    @SerializedName("latitude")
    private float x;
    @SerializedName("longitude")
    private float y;

    public Point(float x, float y, float mul) {
        this.x = x * mul;
        this.y = y * mul;
    }

    public Point(int id, String name, int floor, float x, float y) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
