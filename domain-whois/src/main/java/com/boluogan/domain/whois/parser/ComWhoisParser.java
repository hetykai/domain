package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.domain.whois.utils.WhoisStringUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by boluogan.com on 12/12/14.
 */
public class ComWhoisParser implements WhoisParser {
    @Override
    public void parse(DomainWhoisInfo domainWhoisInfo, String whoisSource) {
        System.out.println("whoisSource:"+whoisSource+"\n\n");
        RegisterStatusChecker registerStatusChecker = new RegisterStatusChecker(domainWhoisInfo,whoisSource);


        //String perservedTag = String.format("%s.COM.",domainWhoisInfo.getKeyword().toUpperCase());
        //System.out.printf("perservedTag:%s",perservedTag);

        if(!registerStatusChecker.registerStatus( )){
            return;
        }
        List<String> lines = WhoisStringUtil.linesOfSubstringContent(whoisSource );


        Map<String,List<String>> map = WhoisStringUtil.linesToMap(lines);

        WhoisMainInfoFiller whoisMainInfoFiller = new WhoisMainInfoFiller(domainWhoisInfo,map);
        whoisMainInfoFiller.setLocale(Locale.US);
        whoisMainInfoFiller.registrar("Registrar");
        whoisMainInfoFiller.whoisServer("Whois Server");
        whoisMainInfoFiller.nameServers("Name Server");
        whoisMainInfoFiller.statusList("Status");


        whoisMainInfoFiller.creationDate("Creation Date","dd-MMM-yyyy");
        whoisMainInfoFiller.expirationDate("Expiration Date","dd-MMM-yyyy");

    }
}
