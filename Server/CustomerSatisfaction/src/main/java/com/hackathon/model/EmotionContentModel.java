package com.hackathon.model;

import com.hackathon.entity.EmotionContentEntity;

/**
 * Created by lethanhtan on 10/29/16.
 */
public class EmotionContentModel {
    private Long id;
    private String message;
    private Long vote;

    public EmotionContentModel() {
    }

    public EmotionContentModel(EmotionContentEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.message = entity.getMessage();
            this.vote = entity.getVote();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getVote() {
        return vote;
    }

    public void setVote(Long vote) {
        this.vote = vote;
    }
}
