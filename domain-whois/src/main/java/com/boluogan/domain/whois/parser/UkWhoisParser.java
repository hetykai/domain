package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.domain.whois.utils.WhoisStringUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by boluogan.com on 12/13/14.
 */
public class UkWhoisParser implements WhoisParser {
    @Override
    public void parse(DomainWhoisInfo domainWhoisInfo, String whoisSource) {
        RegisterStatusChecker registerStatusChecker = new RegisterStatusChecker(domainWhoisInfo,whoisSource);


        if(!registerStatusChecker.registerStatus()){
            return;
        }

        List<String> lines = WhoisStringUtil.linesOfFullContent(whoisSource);
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()){
            try {
                String line = iterator.next().trim();
                if(line.indexOf("Registrant:")>=0){
                    /**
                     * Registrant:的下一行是注册人信息
                     */
                    String registrant = iterator.next();
                    domainWhoisInfo.setRegistrant(registrant.trim());
                    break;

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String,List<String>> map = WhoisStringUtil.linesToMap(lines);

        WhoisMainInfoFiller whoisMainInfoFiller = new WhoisMainInfoFiller(domainWhoisInfo,map);
        whoisMainInfoFiller.registrar("URL");

        String dateFormate = "dd-MMM-yyyy";
        whoisMainInfoFiller.creationDate("Registered on",dateFormate);
        whoisMainInfoFiller.expirationDate("Expiry date",dateFormate);


    }



}
