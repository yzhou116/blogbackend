package com.yizhou.yiblog.response;

import lombok.Data;


public enum ReponseState {
    SUCCESS(true, "SUCCESS", 200),
    LOG_IN_SUCCESS(true, "LOG IN SUCCESS", 201),
    ACCOUNT_NOT_LOG_IN(false, "NOT LOG IN", 407),
    REGISTER_IN_SUCCESS(true, "REGISTER IN SUCCESS", 202),
    FAIL(false, "FAIL", 400),
    GET_DATA_FAIL(false, "GET DATA FAIL", 401),
    LOG_IN_FAIL(false, "LOG IN FAIL", 402),
    PERMISSION_DENIED(false, "No Permission", 406);

    ReponseState(boolean success, String message, int code) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    private int code;
    private String message;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
