package com.boluogan.domain.whois;

import com.boluogan.domain.whois.exception.UnSupportedWhoisInfoParserException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by boluogan.com on 12/9/14.
 */
public enum  WhoisInfoParser {
    COM{
        @Override
        public void parse(DomainWhoisInfo domainWhoisInfo, String content) {
            //
            //DomainWhoisInfoFiller filler = new DomainWhoisInfoFiller(domainWhoisInfo,content);

            //assert domainWhoisInfo.getRegisterStatus()==DomainRegisterStatus.REGISTERED;
        }
    }

    ,
    NET{
        @Override
        public void parse(DomainWhoisInfo domainWhoisInfo, String content) {
            WhoisInfoParser.COM.parse(domainWhoisInfo,content);
        }
    },

    ORG{
        @Override
        public void parse(DomainWhoisInfo domainWhoisInfo, String content) {

        }
    },

    CO{
        @Override
        public void parse(DomainWhoisInfo domainWhoisInfo, String content) {

        }
    },

    CC{
        @Override
        public void parse(DomainWhoisInfo domainWhoisInfo, String content) {

        }
    },

    IN{
        @Override
        public void parse(DomainWhoisInfo domainWhoisInfo, String content) {

        }
    },

    UK{
        @Override
        public void parse(DomainWhoisInfo domainWhoisInfo, String content) {

        }
    },


    CN{
        @Override
        public void parse(DomainWhoisInfo domainWhoisInfo, String content) {

        }
    },


    ME{
        @Override
        public void parse(DomainWhoisInfo domainWhoisInfo, String content) {

        }
    }

    ,
    TW{
        @Override
        public void parse(DomainWhoisInfo domainWhoisInfo, String content) {
            if(content.indexOf("No Found")>=0){
                domainWhoisInfo.setRegisterStatus(DomainRegisterStatus.UNREGISTERED);
                return;
            }

            if(content.indexOf("網域名稱不合規定")>=0){
                domainWhoisInfo.setRegisterStatus(DomainRegisterStatus.PRESERVED);
                return;
            }

            List<String> lines = new ArrayList<String>(Arrays.asList(content.split("\n")));
            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()){
                try {
                    String line = iterator.next();
                    if(line.indexOf("Registrant:")>=0){
                        String registrant = iterator.next();
                        domainWhoisInfo.setRegistrant(registrant);
                        continue;
                    }
                    if(line.indexOf("Contact:")>=0){
                        String emailLineStrs[] = iterator.next().split(" ");
                        String email = emailLineStrs[emailLineStrs.length-1];
                        domainWhoisInfo.setRegistrantEmail(email);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
    ;


    private Date str2Date(String source,String formate){

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formate);
            Date date = simpleDateFormat.parse(source);
            return date;
        } catch (ParseException e) {
            return null;
        }

    }

    public abstract void parse(DomainWhoisInfo domainWhoisInfo,String content);

    public static WhoisInfoParser selectByRootTld(String rootTld) throws UnSupportedWhoisInfoParserException {

        try {
            return Enum.valueOf(WhoisInfoParser.class,rootTld.toUpperCase());
        } catch (Exception e) {
            throw new UnSupportedWhoisInfoParserException(rootTld) ;
        }
    }
}
