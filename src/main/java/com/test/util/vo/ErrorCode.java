package com.test.util.vo;

/**
 * 错误代码
 */
public class ErrorCode {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误说明
     */
    private String msg;

    public ErrorCode(){
    }

    public ErrorCode(String code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code=code;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }

}
