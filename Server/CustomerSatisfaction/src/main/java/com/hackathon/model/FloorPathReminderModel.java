package com.hackathon.model;

import java.util.List;

/**
 * Created by lethanhtan on 10/29/16.
 */
public class FloorPathReminderModel {
    Integer floor;
    List<CoordinateModel> path;
    List<Long> pathId;


    public FloorPathReminderModel() {
    }

    public FloorPathReminderModel(Integer floor, List<CoordinateModel> path, List<Long> pathId) {
        this.floor = floor;
        this.path = path;
        this.pathId = pathId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public List<CoordinateModel> getPath() {
        return path;
    }

    public void setPath(List<CoordinateModel> path) {
        this.path = path;
    }

    public List<Long> getPathId() {
        return pathId;
    }

    public void setPathId(List<Long> pathId) {
        this.pathId = pathId;
    }
}
