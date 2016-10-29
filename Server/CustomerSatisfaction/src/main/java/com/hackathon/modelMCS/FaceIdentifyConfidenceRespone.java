package com.hackathon.modelMCS;

import java.util.List;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class FaceIdentifyConfidenceRespone {

    private String faceId;
    private List<FaceIdentityCandidate> candidates;

    public FaceIdentifyConfidenceRespone() {
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public List<FaceIdentityCandidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<FaceIdentityCandidate> candidates) {
        this.candidates = candidates;
    }

}
