package com.boluogan.domain.whois.exception;

/**
 * Created by boluogan.com on 12/9/14.
 */
public class InvalidDomainFormatException extends Exception {
    private String domainname;

    public InvalidDomainFormatException(String domainname){
        this.domainname=domainname;
    }

    public String toString(){
        return "[异常]错误的域名格式:"+domainname;
    }
}
