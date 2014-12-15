package com.boluogan.domain.whois.exception;

/**
 * Created by yan on 12/9/14.
 */
public class UnSupportedTldException extends Exception {
    private String domainname;

    public UnSupportedTldException(String domainname){
        this.domainname=domainname;
    }

    public String toString(){
        return "[异常]不支持查询的域名后缀:"+domainname;
    }
}
