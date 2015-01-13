package com.boluogan.domain.whois.utils;

import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.domain.whois.exception.InvalidDomainFormatException;

/**
 * Created by boluogan.com on 12/31/14.
 */
public class DomainWhoisInfoBuilder {
    public static DomainWhoisInfo build(String domainname) throws Exception {
        DomainWhoisInfo domainWhoisInfo= new DomainWhoisInfo();
        domainWhoisInfo.setDomainname(domainname);
        initDomainWhoisInfo(domainWhoisInfo);

        return domainWhoisInfo;
    }

    public static void initDomainWhoisInfo(DomainWhoisInfo domainWhoisInfo) throws Exception {
        String domainname = domainWhoisInfo.getDomainname();
        int index = domainname.indexOf(".");
        int lastIndex = domainname.lastIndexOf(".");
        if(lastIndex>=index && index>0){
            String keyword = domainname.substring(0,index);
            String fulltld = domainname.substring(index+1);
            String roottld = domainname.substring(lastIndex+1);

            domainWhoisInfo.setKeyword(keyword);
            domainWhoisInfo.setFulltld(fulltld);
            domainWhoisInfo.setRoottld(roottld);
            domainWhoisInfo.setDomainname(domainname);
        }else{
            throw new InvalidDomainFormatException(domainname);
        }


    }
}
