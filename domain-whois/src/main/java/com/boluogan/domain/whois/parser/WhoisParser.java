package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.DomainWhoisInfo;

/**
 * Created by boluogan.com on 12/12/14.
 */
public interface WhoisParser {
    public void parse(DomainWhoisInfo domainWhoisInfo, String whoisSource);

}
