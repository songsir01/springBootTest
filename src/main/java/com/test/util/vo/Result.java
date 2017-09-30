package com.test.util.vo;

public class Result<T> {

    /**
     * 是否调用成功 默认为false 所以在每次调用都必须设置这个值为true
     */
    private boolean isSuccess=false;

    private String from="/";

    /**
     * 调用的业务成功结果 如果调用失败 这个将是空值
     */
    private T businessResult;

    /**
     * 错误信息
     */
    private ErrorCode errorCode;

    public boolean isSuccess(){
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess){
        this.isSuccess=isSuccess;
    }

    public String getFrom(){
        return from;
    }

    public void setFrom(String from){
        this.from=from;
    }

    public T getBusinessResult(){
        return businessResult;
    }

    public void setBusinessResult(T businessResult){
        this.businessResult=businessResult;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode){
        this.errorCode=errorCode;
    }

}
