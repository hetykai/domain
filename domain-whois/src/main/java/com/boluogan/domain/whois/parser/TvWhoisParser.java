package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.domain.whois.utils.WhoisStringUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by boluogan.com on 12/13/14.
 */
public class TvWhoisParser implements WhoisParser {
    @Override
    public void parse(DomainWhoisInfo domainWhoisInfo, String whoisSource) {
        RegisterStatusChecker registerStatusChecker = new RegisterStatusChecker(domainWhoisInfo,whoisSource);


        if(!registerStatusChecker.registerStatus( )){
            return;
        }
        List<String> lines = WhoisStringUtil.linesOfFullContent(whoisSource);

        Map<String,List<String>> map = WhoisStringUtil.linesToMap(lines);

        WhoisMainInfoFiller whoisMainInfoFiller = new WhoisMainInfoFiller(domainWhoisInfo,map);
        whoisMainInfoFiller.whoisServer("Whois Server");
        whoisMainInfoFiller.nameServers("Name Server");
        whoisMainInfoFiller.statusList("Domain Status");
        whoisMainInfoFiller.registrar("Sponsoring Registrar");
        whoisMainInfoFiller.registrant("Registrant Name");
        whoisMainInfoFiller.registrantEmail("Registrant Email");

        String dateFormate = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        whoisMainInfoFiller.creationDate("Creation Date",dateFormate);
        whoisMainInfoFiller.expirationDate("Registry Expiry Date",dateFormate);
    }
}
