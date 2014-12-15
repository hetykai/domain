package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.domain.whois.utils.WhoisStringUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by boluogan.com on 12/13/14.
 */
public class MeWhoisParser implements WhoisParser {
    @Override
    public void parse(DomainWhoisInfo domainWhoisInfo, String whoisSource) {
        RegisterStatusChecker registerStatusChecker = new RegisterStatusChecker(domainWhoisInfo,whoisSource);


        if(!registerStatusChecker.registerStatus( )){
            return;
        }
        List<String> lines = WhoisStringUtil.linesOfFullContent(whoisSource);

        Map<String,List<String>> map = WhoisStringUtil.linesToMap(lines);

        WhoisMainInfoFiller whoisMainInfoFiller = new WhoisMainInfoFiller(domainWhoisInfo,map);
        whoisMainInfoFiller.nameServers("Nameservers");
        whoisMainInfoFiller.statusList("Domain Status");
        whoisMainInfoFiller.registrar("Sponsoring Registrar");
        whoisMainInfoFiller.registrant("Registrant Name");
        whoisMainInfoFiller.registrantEmail("Registrant E-mail");

        String dateFormate = "dd-MMM-yyyy HH:mm:ss z";
        whoisMainInfoFiller.creationDate("Domain Create Date",dateFormate);
        whoisMainInfoFiller.expirationDate("Domain Expiration Date",dateFormate);
    }
}
