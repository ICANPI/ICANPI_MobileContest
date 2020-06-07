package com.example.danjam.data;

public class Usermodel {
    private int Status;
    private String message;
    private String token;

    public Usermodel(int status, String message, String token) {
        Status = status;
        this.message = message;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
