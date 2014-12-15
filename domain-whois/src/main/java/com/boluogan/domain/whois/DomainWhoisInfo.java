package com.boluogan.domain.whois;

import com.boluogan.domain.whois.exception.InvalidDomainFormatException;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Created by boluogan.com on 12/9/14.
 */
public class DomainWhoisInfo {
    /**
     * 域名前缀
     */
    private String keyword;
    /**
     * 后缀，如com,cn,com.cn
     */
    private String fulltld;
    /**
     * 根后缀，如com.cn为cn
     */
    private String roottld="";

    /**
     * 域名，不带www
     */
    private String domainname;
   // private boolean register=false;

    private DomainRegisterStatus registerStatus=DomainRegisterStatus.UNKNOW;

    private String whoisServer;
    private List<String> nameServers;
    private List<String> statusList;
    private String registrar;
    private String registrant;
    private String registrantEmail;
    private Date creationDate;
    private Date expirationDate;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFulltld() {
        return fulltld;
    }

    public void setFulltld(String fulltld) {
        this.fulltld = fulltld;
    }

    public String getRoottld() {
        return roottld;
    }

    public void setRoottld(String roottld) {
        this.roottld = roottld;
    }

    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }

    public DomainRegisterStatus getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(DomainRegisterStatus registerStatus) {
        this.registerStatus = registerStatus;
    }



    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getRegistrantEmail() {
        return registrantEmail;
    }

    public void setRegistrantEmail(String registrantEmail) {
        this.registrantEmail = registrantEmail;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public Date getExpirationDate() {
        return expirationDate;
    }


    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getWhoisServer() {
        return whoisServer;
    }

    public void setWhoisServer(String whoisServer) {
        this.whoisServer = whoisServer;
    }

    public List<String> getNameServers() {
        return nameServers;
    }

    public void setNameServers(List<String> nameServers) {
        this.nameServers = nameServers;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Field field:this.getClass().getDeclaredFields()){
            try {
                String fieldname = field.getName();
                Object fieldvalue = field.get(this);
                String str = String.format("%s:%s,\n",fieldname,fieldvalue);
                sb.append(str);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String str = String.format("{\n%s\n}",sb.toString());

        return str;
    }

    public static DomainWhoisInfo factory(String domainname) throws Exception {
        DomainWhoisInfo domainWhoisInfo= new DomainWhoisInfo();
        int index = domainname.indexOf(".");
        int lastIndex = domainname.lastIndexOf(".");
        if(lastIndex>=index && index>0){
            String keyword = domainname.substring(0,index);
            String fulltld = domainname.substring(index+1);
            String roottld = domainname.substring(lastIndex+1);

            domainWhoisInfo.setKeyword(keyword);
            domainWhoisInfo.setFulltld(fulltld);
            domainWhoisInfo.setRoottld(roottld);
            domainWhoisInfo.setDomainname(domainname);
        }else{
            throw new InvalidDomainFormatException(domainname);
        }

        return domainWhoisInfo;
    }
}
