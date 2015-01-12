package com.boluogan.domain.domaintool.constant;

import com.boluogan.domain.domaintool.model.ResponseData;

/**
 * Created by boluogan.com on 12/31/14.
 */
public enum ResponseStatus {

    SUCCESS("处理成功"),FAILED("处理失败"),BEGIN("开始查询...") ,QUERYING("查询中..."),DONE("处理结束");

    private String message;

    ResponseStatus(String message) {
        this.message = message;
    }

    public ResponseData<String> toResponseData(){
        return new ResponseData<String>(this ,this.message);
    }

    /*@Override
    public String toString() {
        return this.name().toLowerCase();
    }*/
}
