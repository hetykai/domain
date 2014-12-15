package com.boluogan.domain.whois;

import org.junit.Test;


/**
 * Created by boluogan.com on 12/10/14.
 */

import com.boluogan.domain.whois.WhoisQuery;
public class WhoisQueryTest {
    @Test
    public void parsedWhoisInfo() throws Exception {
        DomainWhoisInfo domainWhoisInfo = WhoisQuery.parsedWhoisInfo("boluogan.com");
        System.out.print(domainWhoisInfo);
    }


    @Test
    public void originalWhoisInfo() throws Exception {

        String whoisContent = WhoisQuery.originalWhoisInfo("boluogan.com");
        System.out.println(whoisContent);
    }


}

//com/cn/net/org/asia/info/biz/name/tv/cc/us/hk/cd/me/tw/in/co/pw/
