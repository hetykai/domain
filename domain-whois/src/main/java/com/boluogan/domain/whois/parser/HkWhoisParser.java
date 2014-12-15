package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.domain.whois.utils.WhoisStringUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by boluogan.com on 12/13/14.
 */
public class HkWhoisParser implements WhoisParser {
    @Override
    public void parse(DomainWhoisInfo domainWhoisInfo, String whoisSource) {
        RegisterStatusChecker registerStatusChecker = new RegisterStatusChecker(domainWhoisInfo,whoisSource);


        if(!registerStatusChecker.registerStatus()){
            return;
        }
        List<String> lines = WhoisStringUtil.linesOfSubstringContent(whoisSource,"Domain Name:","Domain Prohibit Status:");

        Map<String,List<String>> map = WhoisStringUtil.linesToMap(lines);

        WhoisMainInfoFiller whoisMainInfoFiller = new WhoisMainInfoFiller(domainWhoisInfo,map);

        whoisMainInfoFiller.registrar("Registrar Name");
        whoisMainInfoFiller.registrant("Company Name");
        whoisMainInfoFiller.registrantEmail("Email");

        String dateFormate = "dd-MM-yyyy";
        whoisMainInfoFiller.creationDate("Domain Name Commencement Date",dateFormate);
        whoisMainInfoFiller.expirationDate("Expiry Date",dateFormate);
    }
}
