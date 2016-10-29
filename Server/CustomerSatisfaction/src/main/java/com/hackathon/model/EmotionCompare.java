package com.hackathon.model;

import com.hackathon.constant.EEmotion;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class EmotionCompare {
    private EEmotion emotion;
    private Double value;

    public EmotionCompare(EEmotion emotion, Double value) {
        this.emotion = emotion;
        this.value = value;
    }

    public EEmotion getEmotion() {
        return emotion;
    }

    public void setEmotion(EEmotion emotion) {
        this.emotion = emotion;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EmotionCompare{" +
                "emotion=" + emotion +
                ", value=" + value +
                '}';
    }
}
