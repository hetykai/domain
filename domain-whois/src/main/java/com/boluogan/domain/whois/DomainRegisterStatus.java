package com.boluogan.domain.whois;

/**
 * Created by boluogan.com on 12/11/14.
 */
public enum DomainRegisterStatus {
    UNKNOW("未知"),UNREGISTERED("未注册"), REGISTERED("已注册"), PRESERVED("被保留")
    ,QUERYFAILED("查询失败")
    ;

    DomainRegisterStatus(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String toString(){
        return desc;
    }
}
