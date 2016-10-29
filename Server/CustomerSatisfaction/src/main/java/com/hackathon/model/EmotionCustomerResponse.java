package com.hackathon.model;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class EmotionCustomerResponse {

    private String customerCode;
    private EmotionAnalysisModel analyzes;
    private MessageModel messages;

    public EmotionCustomerResponse() {
    }

    public EmotionCustomerResponse(String customerCode, EmotionAnalysisModel analyzes, MessageModel messages) {
        this.customerCode = customerCode;
        this.analyzes = analyzes;
        this.messages = messages;
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
}
