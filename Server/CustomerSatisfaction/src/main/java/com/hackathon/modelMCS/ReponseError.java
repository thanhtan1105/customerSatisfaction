package com.hackathon.modelMCS;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class ReponseError {

    private String code;
    private String message;

    public ReponseError() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReponseError{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
