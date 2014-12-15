package com.boluogan.domain.whois.exception;

/**
 * Created by boluogan.com on 12/11/14.
 */
public class UnSupportedRootTldException extends Exception {
    private String rootTld;

    public UnSupportedRootTldException(String rootTld) {
        this.rootTld = rootTld;
    }

    public String toString(){
        return "[异常]不支持查询的根后缀:"+rootTld;
    }
}
