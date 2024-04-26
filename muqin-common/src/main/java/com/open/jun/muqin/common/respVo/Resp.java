package com.open.jun.muqin.common.respVo;
import com.open.jun.muqin.common.exception.ErrorCodeEnum;
import lombok.Data;

/***
 *@description: 全局统一封装类型
 *@author: jun
 *version: 1.0
 *time: 2023/2/10 8:38
 **/
@Data
public class Resp<T,E> {

    /**
     * 请求处理状态
     */
    private Boolean status;

    /**
     * 请求最后处理状态码 模块码+模块处理标识+状态码
     */
    private Integer code;

    /**
     * 通知消息
     */
    private String message;


    /**
     * 数据
     */
    private T data;

    /**
     * tranceId
     */
    private String traceId;


    private Resp(){}

    /**
     * 不能返回trace到前端
     * @return
     */
    public static Resp success(){
        Resp resp = new Resp<>();
        resp.setStatus(Boolean.TRUE);
        resp.setMessage(ErrorCodeEnum.SUCCESS.getShowMsg());
        return resp;
    }

    public static Resp success(String traceId){
        Resp resp = new Resp<>();
        resp.setStatus(Boolean.TRUE);
        resp.setCode(ErrorCodeEnum.SUCCESS.getShowCode());
        resp.setMessage(ErrorCodeEnum.SUCCESS.getShowMsg());
        resp.setTraceId(traceId);
        return resp;
    }

    public static <T,E> Resp success(T data,String traceId){

        Resp<T,E> resp = new Resp<>();

        resp.setStatus(Boolean.TRUE);
        resp.setCode(ErrorCodeEnum.SUCCESS.getShowCode());
        resp.setMessage(ErrorCodeEnum.SUCCESS.getShowMsg());
        resp.setTraceId(traceId);
        resp.setData(data);

        return resp;

    }

    public static <T,E extends ErrorCodeEnum> Resp fail(E e) {

        Resp<T,E> resp = new Resp<>();

        resp.setStatus(Boolean.FALSE);
        resp.setCode(ErrorCodeEnum.SUCCESS.getShowCode());
        resp.setMessage(e.getShowMsg());

        return resp;

    }

    public static <T,E extends ErrorCodeEnum> Resp fail(E e,String traceId) {

        Resp<T,E> resp = new Resp<>();

        resp.setStatus(Boolean.FALSE);
        resp.setCode(ErrorCodeEnum.SUCCESS.getShowCode());
        resp.setMessage(e.getShowMsg());
        resp.setTraceId(traceId);

        return resp;

    }


}
