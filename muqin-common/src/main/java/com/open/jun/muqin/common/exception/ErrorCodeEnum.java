package com.open.jun.muqin.common.exception;

/***
 *@description: 公共异常枚举类
 *@author: jun
 *version: 1.0
 *time: 2023/2/9 23:10
 **/
public enum ErrorCodeEnum {

    SUCCESS(200,0,"SUCCESS","SUCCESS"),
    FIAL_SYSYTEM(500,-1,"系统异常","网络异常，请稍后重试")

    ;


    /**
     * 报警Code
     */
    private Integer warnCode;

    /**
     * 展示Code common 1**
     */
    private Integer showCode;

    /**
     * 报警消息
     */
    private String warnMessage;

    /**
     * 用户展示消息
     */
    private String showMsg;

    public Integer getWarnCode() {
        return warnCode;
    }

    public Integer getShowCode() {
        return showCode;
    }

    public String getWarnMessage() {
        return warnMessage;
    }

    public String getShowMsg() {
        return showMsg;
    }

    ErrorCodeEnum(Integer warnCode, Integer showCode, String warnMessage, String showMsg) {
        this.warnCode = warnCode;
        this.showCode = showCode;
        this.warnMessage = warnMessage;
        this.showMsg = showMsg;
    }

}
