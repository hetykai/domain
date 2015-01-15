package com.boluogan.domain.domaintool.controller;

import com.boluogan.domain.domaintool.model.ResponseData;
import com.boluogan.domain.domaintool.constant.ResponseStatus;
import com.boluogan.domain.domaintool.model.WhoisRecord;
import com.boluogan.domain.domaintool.utils.WhoisRecordBuilder;
import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.domain.whois.WhoisQuery;
import com.boluogan.util.date.MyDateHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by boluogan.com on 12/17/14.
 */
@RestController
public class WhoisController {
    /**
     * 查询域名
     * @param domainname
     * @return
     */
    @RequestMapping(value="/whois/{domainname}", method= RequestMethod.GET)
    public ResponseData whois(@PathVariable String domainname){
        System.out.println("domainname:"+domainname);
        try {
            DomainWhoisInfo domainWhoisInfo = WhoisQuery.parsedWhoisInfo(domainname);

            WhoisRecord whoisRecord= WhoisRecordBuilder.build(domainWhoisInfo);
            System.out.println(domainWhoisInfo.getCreationDate());
            System.out.println(MyDateHelper.dateToString(domainWhoisInfo.getCreationDate()));
            return new ResponseData<WhoisRecord>(ResponseStatus.SUCCESS,whoisRecord);
        } catch (Exception e) {
            return new ResponseData<String>(ResponseStatus.FAILED,e.getMessage());
        }


    }
}
