package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.domain.whois.utils.WhoisStringUtil;

import java.util.*;

/**
 * Created by boluogan.com on 12/12/14.
 */
public class TwWhoisParser  implements WhoisParser{

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

                }
                if(line.indexOf("Contact:")>=0){
                    /**
                     * Contact:下一行有注册人的email信息
                     */
                    String emailLineStrs[] = iterator.next().trim().split(" ");
                    String email = emailLineStrs[emailLineStrs.length-1];
                    domainWhoisInfo.setRegistrantEmail(email.trim());
                }

                if(line.indexOf("Registered on")>=0){
                    /**
                     * 注册时间
                     */
                    domainWhoisInfo.setCreationDate(date(line));
                }

                if(line.indexOf("Expiry date")>=0){
                    /**
                     * 过期时间
                     */
                    domainWhoisInfo.setExpirationDate(date(line));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 日期所在行格式：2014-12-12 (YYYY-MM-DD)
     * @param line
     * @return
     */
    private Date date(String line){
        try {
            String strs[] = line.split(" ");
            String source = strs[strs.length-2];
            String temp = strs[strs.length-1];
            temp = temp.replace("YYYY","yyyy").replace("DD","dd");
            String formate = temp.substring(1,temp.length()-1);



            return WhoisStringUtil.str2Date(source, formate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
