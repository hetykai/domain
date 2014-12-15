package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.DomainWhoisInfo;

/**
 * Created by boluogan.com on 12/13/14.
 */
public class EduWhoisParser implements WhoisParser {
    private WhoisParser comWhoisParser = new ComWhoisParser();
    @Override
    public void parse(DomainWhoisInfo domainWhoisInfo, String content) {
        comWhoisParser.parse(domainWhoisInfo,content);
    }
}
