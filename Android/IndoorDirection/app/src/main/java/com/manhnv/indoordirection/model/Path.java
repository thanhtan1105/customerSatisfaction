package com.manhnv.indoordirection.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ManhNV on 10/29/16.
 */

public class Path {
    @SerializedName("floor")
    private int floor;
    @SerializedName("path")
    private List<Point> paths;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public List<Point> getPaths() {
        return paths;
    }

    public void setPaths(List<Point> paths) {
        this.paths = paths;
    }
}
