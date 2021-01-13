package com.yizhou.yiblog.response;

public class ResponseResult {
    private boolean result;
    private int code;
    private String message;
    private Object data;

    public ResponseResult(ReponseState reponseState) {
        this.result = reponseState.isSuccess();
        this.code = reponseState.getCode();
        this.message = reponseState.getMessage();
    }

    public static ResponseResult GETREPONSESTATE(ReponseState reponseState) {
        return new ResponseResult(reponseState);
    }

    public static ResponseResult SUCCESS() {
        return new ResponseResult(ReponseState.SUCCESS);
    }

    public static ResponseResult ACCOUNT_NOT_LOGIN() {
        return new ResponseResult(ReponseState.ACCOUNT_NOT_LOG_IN);
    }

    public static ResponseResult PERMISSION_DENIED() {
        return new ResponseResult(ReponseState.PERMISSION_DENIED);
    }

    public static ResponseResult SUCCESS(String message) {
        ResponseResult responseResult = new ResponseResult(ReponseState.SUCCESS);
        responseResult.setMessage(message);
        return responseResult;
    }

    public static ResponseResult FAIL() {
        return new ResponseResult(ReponseState.FAIL);
    }

    public static ResponseResult FAIL(String msg) {

        ResponseResult responseResult = new ResponseResult(ReponseState.FAIL);
        responseResult.setMessage(msg);
        return responseResult;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

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

    public Object getData() {
        return data;
    }

    public ResponseResult setData(Object data) {
        this.data = data;
        return this;
    }
}
