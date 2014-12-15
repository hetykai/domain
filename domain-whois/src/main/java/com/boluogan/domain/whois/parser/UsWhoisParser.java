package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.DomainWhoisInfo;

/**
 * Created by boluogan.com on 12/13/14.
 */
public class UsWhoisParser implements WhoisParser {
    private BizWhoisParser bizWhoisParser = new BizWhoisParser();
    @Override
    public void parse(DomainWhoisInfo domainWhoisInfo, String whoisSource) {
        bizWhoisParser.parse(domainWhoisInfo,whoisSource);
    }
}
