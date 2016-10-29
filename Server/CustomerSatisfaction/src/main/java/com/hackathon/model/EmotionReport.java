package com.hackathon.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TrungNN on 10/29/2016.
 */
public class EmotionReport implements Serializable {

    private List<String> listEmployees;
    private List<Integer> listExcellent;
    private List<Integer> listGood;
    private List<Integer> listNormal;
    private List<Integer> listMedium;
    private List<Integer> listBad;

    public EmotionReport() {
    }

    public List<String> getListEmployees() {
        return listEmployees;
    }

    public void setListEmployees(List<String> listEmployees) {
        this.listEmployees = listEmployees;
    }

    public List<Integer> getListExcellent() {
        return listExcellent;
    }

    public void setListExcellent(List<Integer> listExcellent) {
        this.listExcellent = listExcellent;
    }

    public List<Integer> getListGood() {
        return listGood;
    }

    public void setListGood(List<Integer> listGood) {
        this.listGood = listGood;
    }

    public List<Integer> getListNormal() {
        return listNormal;
    }

    public void setListNormal(List<Integer> listNormal) {
        this.listNormal = listNormal;
    }

    public List<Integer> getListMedium() {
        return listMedium;
    }

    public void setListMedium(List<Integer> listMedium) {
        this.listMedium = listMedium;
    }

    public List<Integer> getListBad() {
        return listBad;
    }

    public void setListBad(List<Integer> listBad) {
        this.listBad = listBad;
    }

    @Override
    public String toString() {
        return "EmotionReport{" +
                "listEmployees=" + listEmployees +
                ", listExcellent=" + listExcellent +
                ", listGood=" + listGood +
                ", listNormal=" + listNormal +
                ", listMedium=" + listMedium +
                ", listBad=" + listBad +
                '}';
    }
}
