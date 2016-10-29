package com.hackathon.model;

import com.hackathon.constant.EEmotion;
import com.hackathon.constant.ETransaction;
import com.hackathon.entity.CustomerEntity;
import com.hackathon.entity.TransactionEntity;

import java.sql.Timestamp;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class TransactionModel {

    private Integer id;

    private Timestamp beginTime;

    private Timestamp endTime;

    private EEmotion emotionMost;

    private Double grade = 0d;



    private String CustomerCode;

    private ETransaction status = ETransaction.BEGIN;

    public TransactionModel(TransactionEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.beginTime = entity.getBeginTime();
            this.endTime = entity.getEndTime();
            this.emotionMost = entity.getEmotionMost();
            this.grade = entity.getGrade();
            this.status = entity.getStatus();
            this.CustomerCode = entity.getCustomerCode();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public EEmotion getEmotionMost() {
        return emotionMost;
    }

    public void setEmotionMost(EEmotion emotionMost) {
        this.emotionMost = emotionMost;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }


    public ETransaction getStatus() {
        return status;
    }

    public void setStatus(ETransaction status) {
        this.status = status;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }
}
