package com.hackathon.model;

import java.util.List;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class EmotionCustomerResponse {

    private String customerCode;
    private EmotionAnalysisModel analyzes;
    private MessageModel messages;
    private List<EmotionHistoryModel> historyModels;

    public EmotionCustomerResponse() {
    }

    public EmotionCustomerResponse(String customerCode, EmotionAnalysisModel analyzes, MessageModel messages, List<EmotionHistoryModel> historyModels) {
        this.customerCode = customerCode;
        this.analyzes = analyzes;
        this.messages = messages;
        this.historyModels = historyModels;
    }

    public EmotionCustomerResponse(String customerCode) {
        this.customerCode = customerCode;
    }




    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public EmotionAnalysisModel getAnalyzes() {
        return analyzes;
    }

    public void setAnalyzes(EmotionAnalysisModel analyzes) {
        this.analyzes = analyzes;
    }

    public MessageModel getMessages() {
        return messages;
    }

    public void setMessages(MessageModel messages) {
        this.messages = messages;
    }

    public List<EmotionHistoryModel> getHistoryModels() {
        return historyModels;
    }

    public void setHistoryModels(List<EmotionHistoryModel> historyModels) {
        this.historyModels = historyModels;
    }
}
