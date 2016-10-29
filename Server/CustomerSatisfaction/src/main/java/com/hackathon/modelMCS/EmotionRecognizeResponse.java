package com.hackathon.modelMCS;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class EmotionRecognizeResponse {
    private RectangleImage faceRectangle;
    private EmotionRecognizeScores scores;

    public EmotionRecognizeResponse() {
    }

    public RectangleImage getFaceRectangle() {
        return faceRectangle;
    }

    public void setFaceRectangle(RectangleImage faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    public EmotionRecognizeScores getScores() {
        return scores;
    }

    public void setScores(EmotionRecognizeScores scores) {
        this.scores = scores;
    }
}
