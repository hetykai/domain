package com.boluogan.domain.whois;

import com.boluogan.domain.whois.parser.WhoisParser;
import com.boluogan.domain.whois.parser.WhoisParserSlector;
import com.boluogan.domain.whois.utils.DomainWhoisInfoBuilder;

import java.io.IOException;

/**
 * Created by boluogan.com on 12/10/14.
 */
public class WhoisQuery {


    /**
     * 返回标准格式的域名whois 信息
     * @param domainname
     * @return
     * @throws Exception
     */
    public static DomainWhoisInfo parsedWhoisInfo(String domainname)throws Exception{
        DomainWhoisInfo domainWhoisInfo = DomainWhoisInfoBuilder.build(domainname);
        String rootTld = domainWhoisInfo.getRoottld();
        WhoisServer whoisServer = Nics.selectServer(rootTld);
        WhoisParser whoisParser = WhoisParserSlector.selectParser(rootTld);
        String content = null;
        try {
            content = whoisServer.query(domainname);
        } catch (IOException e) {
            e.printStackTrace();
            domainWhoisInfo.setRegisterStatus(DomainRegisterStatus.QUERYFAILED);
            return domainWhoisInfo;
        }
        whoisParser.parse(domainWhoisInfo,content);
        return domainWhoisInfo;
    }

    /**
     * 返回从whois服务器返回的域名原始whois 信息
     * @param domainname
     * @return
     * @throws Exception
     */
    public static String originalWhoisInfo(String domainname)throws Exception{
        DomainWhoisInfo domainWhoisInfo = DomainWhoisInfoBuilder.build(domainname);
        WhoisServer whoisServer = Nics.selectServer(domainWhoisInfo.getRoottld());
        String content = whoisServer.query(domainname);
        //System.out.println(content);
        return content;
    }


}
