package com.example.danjam.data;

public class Usermodel {
    private int Status;
    private String message;
    private boolean success;
    private String accessToken;
    private String refreshToken;

    public Usermodel(int status, String message, boolean success, String accessToken, String refreshToken) {
        Status = status;
        this.message = message;
        this.success = success;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
