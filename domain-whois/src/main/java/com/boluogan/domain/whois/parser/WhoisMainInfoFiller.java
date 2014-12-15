package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.DomainRegisterStatus;
import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.domain.whois.utils.WhoisStringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by boluogan.com on 12/13/14.
 */
public class WhoisMainInfoFiller {
    private DomainWhoisInfo domainWhoisInfo;
    private Map<String,List<String>> map;

    public WhoisMainInfoFiller(DomainWhoisInfo domainWhoisInfo, Map<String, List<String>> map) {
        this.domainWhoisInfo = domainWhoisInfo;
        this.map = map;
    }



    /**
     * 根据key获取map中List的第一个元素
     * @param key
     * @return
     */
    public String getStringBykey(String key){
        try {
            if(map.containsKey(key)){
                return map.get(key).get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据key获取list
     * @param key
     * @return
     */
    public List<String> getListByKey(String key){
        if(map.containsKey(key)){
            return map.get(key);
        }

        return new ArrayList<String>();
    }

    /**
     * 根据key和格式转换日期
     * @param key
     * @param dateFormate
     * @return
     */
    public Date getDateByKey(String key,String dateFormate){
        String source = getStringBykey(key) ;
        if(source!=null){
            Date date = WhoisStringUtil.str2Date(source,dateFormate);
            return date;
        }

        return null;
    }


    public void registrar(String tag){
        domainWhoisInfo.setRegistrar(getStringBykey(tag));
    }

    public void registrant(String tag){
        domainWhoisInfo.setRegistrant(getStringBykey(tag));
    }

    public void registrantEmail(String tag){
        domainWhoisInfo.setRegistrantEmail(getStringBykey(tag));
    }

    public void creationDate(String tag,String dateFormate){
        domainWhoisInfo.setCreationDate(getDateByKey(tag,dateFormate));
    }


    public void expirationDate(String tag,String dateFormate){
        domainWhoisInfo.setExpirationDate(getDateByKey(tag,dateFormate));
    }

    public void whoisServer(String tag){
        domainWhoisInfo.setWhoisServer(getStringBykey(tag));
    }

    public void nameServers(String tag){
        domainWhoisInfo.setNameServers(getListByKey(tag));
    }

    public void statusList(String tag){
        domainWhoisInfo.setStatusList(getListByKey(tag));
    }


}
