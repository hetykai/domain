package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.ConfigFile;
import com.boluogan.domain.whois.DomainRegisterStatus;
import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.domain.whois.utils.ConfigFileReader;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by boluogan.com on 12/12/14.
 */
 public class RegisterStatusChecker {
    /*private final static String[] REGISTERED_TAGS={"Domain Name:","Registrant:"};

    private final static String[] UNREGISTERED_TAGS={"The domain has not been registered","Not found","No match for"
    ,"no matching record","NOT FOUND","No Found"
    };
    private final static String[] PRESERVEEDD_TAGS={};*/

    private final static Set<String> REGISTERED_TAGS=new HashSet(ConfigFileReader.read(ConfigFile.REGISTERED_TAGS));
    private final static Set<String> UNREGISTERED_TAGS=new HashSet(ConfigFileReader.read(ConfigFile.UNREGISTERED_TAGS));
    private final static Set<String> PRESERVEEDD_TAGS=new HashSet(ConfigFileReader.read(ConfigFile.PRESERVED_TAGS));



    private DomainWhoisInfo domainWhoisInfo;
    private String whoisSource;

    public RegisterStatusChecker(DomainWhoisInfo domainWhoisInfo, String whoisSource) {
        this.domainWhoisInfo = domainWhoisInfo;
        this.whoisSource = whoisSource;
    }

    public boolean registerStatus(){


        if(containsTag(UNREGISTERED_TAGS)){
            domainWhoisInfo.setRegisterStatus(DomainRegisterStatus.UNREGISTERED);
            return false;
        }

        if(containsTag(PRESERVEEDD_TAGS)){
            domainWhoisInfo.setRegisterStatus(DomainRegisterStatus.PRESERVED);
            return false;
        }

        if(containsTag(REGISTERED_TAGS)){
            domainWhoisInfo.setRegisterStatus(DomainRegisterStatus.REGISTERED);
            return true;
        }

        domainWhoisInfo.setRegisterStatus(DomainRegisterStatus.QUERYFAILED);


        return false;
    }

    /**
     * 判断数组的字符串是否在whoisSource中出现
     * @param tags
     * @return
     */
    private boolean containsTag(Set<String> tags){
        //System.out.println(tags);
        for(String str:tags){
            if(whoisSource.indexOf(str)>=0){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param unregisterTag
     * @return
     */
    //public boolean registerStatus(String unregisterTag){ return registerStatus(unregisterTag,REGISTERED_TAG);  }

    /**
     *
     * @param unregisterTag
     * @param registerTag
     * @return
     */
    public boolean registerStatus2(String unregisterTag,String registerTag){
        if(whoisSource.indexOf(unregisterTag)>=0){
            domainWhoisInfo.setRegisterStatus(DomainRegisterStatus.UNREGISTERED);
            return false;
        }

        if(whoisSource.indexOf(registerTag)<0){
            domainWhoisInfo.setRegisterStatus(DomainRegisterStatus.PRESERVED);
            return false;
        }

        domainWhoisInfo.setRegisterStatus(DomainRegisterStatus.REGISTERED);

        return true;

    }

    /*public List<String> linesOfFullContent(){
        return new ArrayList<String>(Arrays.asList(whoisSource.split("\n")));
    }*/

}
