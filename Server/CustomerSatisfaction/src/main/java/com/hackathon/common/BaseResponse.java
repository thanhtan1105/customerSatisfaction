package com.hackathon.common;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class BaseResponse {

    private boolean success = false;
    private String message;
    private Object data;
    private String errorCode;

    public BaseResponse() {
        this.success = false;
    }

    public BaseResponse(boolean success) {
        this.success = success;
    }

    public BaseResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public BaseResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(boolean success, String message, Object data, String errorCode) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errorCode = errorCode;
    }

    public BaseResponse(Exception e) {
        this.success = false;
        this.message = e.getMessage();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
    
}
