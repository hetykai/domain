package com.boluogan.domain.whois.exception;

/**
 * Created by boluogan.com on 12/11/14.
 */
public class UnSupportedWhoisInfoParserException extends Exception {
    private String rootTld;

    public UnSupportedWhoisInfoParserException(String rootTld) {
        super("[异常]尚未提供此根后缀的whois信息解析:"+rootTld);

        this.rootTld = rootTld;
    }

    /*public String toString(){
        return "[异常]尚未提供此根后缀的whois信息解析:"+rootTld;
    }*/
}
