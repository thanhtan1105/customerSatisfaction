package com.hackathon.entity;

import com.hackathon.constant.EEmotion;
import com.hackathon.constant.ETransaction;
import com.hackathon.model.EmotionAnalysisModel;
import com.hackathon.modelMCS.EmotionRecognizeScores;
import com.hackathon.util.ValidateUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@Entity
@Table(name = "transaction", schema = "hackathon")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "begin_time")
    private Timestamp beginTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "emotion_most")
    private EEmotion emotionMost;

    @Column(name = "grade")
    private Double grade = 0d;

    @Basic
    @Column(name = "customer_code")
    private String CustomerCode;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "transaction")
    private Set<EmotionCustomerEntity> emotion;

    @Basic
    @Column(name = "status")
    private ETransaction status = ETransaction.BEGIN;

    public TransactionEntity() {
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

    public Set<EmotionCustomerEntity> getEmotion() {
        return emotion;
    }

    public void setEmotion(Set<EmotionCustomerEntity> emotion) {
        this.emotion = emotion;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
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

    public void calculateGrade() {
        if (ValidateUtil.isNotEmpty(emotion)) {
            double sum = 0d;
            for (EmotionCustomerEntity emotion : this.emotion) {
                sum += emotion.getEmotionMost().getGrade();
            }
            this.grade = sum / emotion.size();
        }

    }
}
