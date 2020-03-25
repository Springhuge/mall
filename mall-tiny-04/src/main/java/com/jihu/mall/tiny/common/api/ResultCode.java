package com.jihu.mall.tiny.common.api;

public enum ResultCode implements IErrorCode {

    SUCCESS(200,true,"操作成功"),
    FAILED(500,false,"操作失败"),
    TRUECODE(200,true,"验证码正确"),
    FAILCODE(200,true,"验证码错误"),


    VALIDATE_FAILED(404,false, "参数检验失败"),
    //用户
    UNAUTHORIZED(10001, false,"暂未登录或token已经过期"),
    ERRORUSERNAMEPASSWORD(10002,false,"用户名或密码错误"),
    ERRORREGISTER(10003,false,"注册失败"),
    FORBIDDEN(10002, false,"没有相关权限");


    private long code;

    boolean success;

    private String message;

    private ResultCode(long code,boolean success,String message){
        this.code = code;
        this.message = message;
        this.success = success;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }
}
