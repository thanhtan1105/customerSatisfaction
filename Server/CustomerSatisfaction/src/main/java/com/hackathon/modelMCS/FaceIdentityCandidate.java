package com.hackathon.modelMCS;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class FaceIdentityCandidate {

    private String personId;
    private Double confidence;

    public FaceIdentityCandidate() {
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

}
