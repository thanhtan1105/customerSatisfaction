package com.hackathon.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by lethanhtan on 10/29/16.
 */
@Entity
@Table(name = "beacon", schema = "hackathon")
public class BeaconEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "floor")
    private Integer floor = 0;

    @Basic
    @NotNull
    @Column(name = "latitude")
    private Double latitude = 0d;

    @Basic
    @NotNull
    @Column(name = "longitude")
    private Double longitude = 0d;

    @Basic
    @NotNull
    @Column(name = "minjor")
    private int minjor;

    @Basic
    @NotNull
    @Column(name = "major")
    private int major;

    @Basic
    @NotNull
    @Column(name = "area_name")
    private String areaName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getMinjor() {
        return minjor;
    }

    public void setMinjor(int minjor) {
        this.minjor = minjor;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
