package com.open.jun.muqin.common.reqVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 *@description: openJun请求参数父类
 *@author: jun
 *version: 1.0
 *time: 2023/2/21 18:33
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqBase {

    /**
     * 全链路追踪trance
     */
    private String traceId;



}
