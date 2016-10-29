package com.hackathon.model;

import com.hackathon.constant.EEmotion;
import com.hackathon.entity.EmotionCustomerEntity;
import com.hackathon.entity.TransactionEntity;

import java.util.Date;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class EmotionHistoryModel {
    private Date date;
    private EEmotion emotionBefore;
    private EEmotion emotionEnd;

    public EmotionHistoryModel() {
    }

    public EmotionHistoryModel(TransactionEntity entity) {
        if (entity != null) {
            this.date = entity.getBeginTime();
            if (entity.getEmotion().size() >0){
                this.emotionBefore = entity.getEmotion().get(0).getEmotionMost();
                this.emotionEnd = entity.getEmotion().get(entity.getEmotion().size() -1).getEmotionMost();
            }
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmotionBefore() {
        return emotionBefore.getName();
    }

    public void setEmotionBefore(EEmotion emotionBefore) {
        this.emotionBefore = emotionBefore;
    }

    public String getEmotionEnd() {
        return emotionEnd.getName();
    }

    public void setEmotionEnd(EEmotion emotionEnd) {
        this.emotionEnd = emotionEnd;
    }
}
