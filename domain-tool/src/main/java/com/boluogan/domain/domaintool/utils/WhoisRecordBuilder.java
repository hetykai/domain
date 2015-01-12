package com.boluogan.domain.domaintool.utils;

import com.boluogan.domain.domaintool.model.WhoisRecord;
import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.util.date.MyDateHelper;

/**
 * Created by boluogan.com on 12/31/14.
 */
public class WhoisRecordBuilder {
    public static WhoisRecord build(DomainWhoisInfo domainWhoisInfo){
        WhoisRecord whoisRecord= new WhoisRecord();
        whoisRecord.setSid(domainWhoisInfo.getSid());
        whoisRecord.setDomainname(domainWhoisInfo.getDomainname());
        whoisRecord.setRegisterStatus(domainWhoisInfo.getRegisterStatus().toString());
        whoisRecord.setCreateDate(MyDateHelper.dateToString(domainWhoisInfo.getCreationDate()));
        whoisRecord.setExpirationDate(MyDateHelper.dateToString(domainWhoisInfo.getExpirationDate()));
        whoisRecord.setRegistrant(domainWhoisInfo.getRegistrant());
        whoisRecord.setRegistrantEmail(domainWhoisInfo.getRegistrantEmail());
        whoisRecord.setRegistrar(domainWhoisInfo.getRegistrar());

        return whoisRecord;
    }
}
