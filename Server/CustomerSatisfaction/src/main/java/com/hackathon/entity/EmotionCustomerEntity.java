package com.hackathon.entity;

import com.hackathon.constant.EEmotion;
import com.hackathon.constant.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */

@Entity
@Table(name = "emotion", schema = "hackathon")
public class EmotionCustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "create_time")
    private Timestamp createTime = new Timestamp(new Date().getTime());


    @Column(name = "anger")
    private Double anger;

    @Column(name = "contempt")
    private Double contempt;

    @Column(name = "disgust")
    private Double disgust;

    @Column(name = "fear")
    private Double fear;

    @Column(name = "happiness")
    private Double happiness;

    @Column(name = "neutral")
    private Double neutral;

    @Column(name = "sadness")
    private Double sadness;

    @Column(name = "surprise")
    private Double surprise;

    @Column(name = "age")
    private Double age;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "smile")
    private Double smile;

    @Column(name = "emotion_most")
    private EEmotion emotionMost;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = true)
    private TransactionEntity transaction;

    public EmotionCustomerEntity() {
    }

    public EmotionCustomerEntity(Double anger, Double contempt, Double disgust, Double fear, Double happiness, Double neutral, Double sadness, Double surprise, Double age, Gender gender, Double smile) {
        this.anger = anger;
        this.contempt = contempt;
        this.disgust = disgust;
        this.fear = fear;
        this.happiness = happiness;
        this.neutral = neutral;
        this.sadness = sadness;
        this.surprise = surprise;
        this.age = age;
        this.gender = gender;
        this.smile = smile;
    }

//    public EmotionCustomerEntity(EmotionAnalysisModel analysisModel, CustomerServiceEntity customer) {
//        if (analysisModel != null) {
//            if (analysisModel.getEmotion() != null) {
//                EmotionRecognizeScores emotion = analysisModel.getEmotion();
//                this.anger = emotion.getAnger();
//                this.contempt = emotion.getContempt();
//                this.disgust = emotion.getDisgust();
//                this.fear = emotion.getFear();
//                this.happiness = emotion.getHappiness();
//                this.neutral = emotion.getNeutral();
//                this.sadness = emotion.getSadness();
//                this.surprise = emotion.getSurprise();
//            }
//            this.age = analysisModel.getAge();
//            this.gender = analysisModel.getGender();
//            this.smile = analysisModel.getSmile();
//            this.emotionMost = analysisModel.getEmotionMost();
//            this.transaction = customer;
//        }
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Double getAnger() {
        return anger;
    }

    public void setAnger(Double anger) {
        this.anger = anger;
    }

    public Double getContempt() {
        return contempt;
    }

    public void setContempt(Double contempt) {
        this.contempt = contempt;
    }

    public Double getDisgust() {
        return disgust;
    }

    public void setDisgust(Double disgust) {
        this.disgust = disgust;
    }

    public Double getFear() {
        return fear;
    }

    public void setFear(Double fear) {
        this.fear = fear;
    }

    public Double getHappiness() {
        return happiness;
    }

    public void setHappiness(Double happiness) {
        this.happiness = happiness;
    }

    public Double getNeutral() {
        return neutral;
    }

    public void setNeutral(Double neutral) {
        this.neutral = neutral;
    }

    public Double getSadness() {
        return sadness;
    }

    public void setSadness(Double sadness) {
        this.sadness = sadness;
    }

    public Double getSurprise() {
        return surprise;
    }

    public void setSurprise(Double surprise) {
        this.surprise = surprise;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getSmile() {
        return smile;
    }

    public void setSmile(Double smile) {
        this.smile = smile;
    }

    public EEmotion getEmotionMost() {
        return emotionMost;
    }

    public void setEmotionMost(EEmotion emotionMost) {
        this.emotionMost = emotionMost;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transactionEntity) {
        this.transaction = transactionEntity;
    }
}