package com.boluogan.domain.whois.utils;

import com.boluogan.domain.whois.ConfigFile;
import com.boluogan.domain.whois.WhoisServer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by boluogan.com on 12/11/14.
 */
public class ConfigFileReader {


    public static List<String> read(ConfigFile configFile){


        try {

            InputStream inputStream=fileInputStream(configFile);
            List<String> lines = IOUtils.readLines(inputStream);
            //System.out.println(lines);
            return lines;
        } catch (Exception e) {
            System.out.println(configFile.getFilename());
            e.printStackTrace();
        }

        return new ArrayList<String>();
    }

    private static InputStream fileInputStream(ConfigFile configFile){
        String relativePath ="/"+ ConfigFile.DIR.getFilename()+"/"+configFile.getFilename();
        InputStream inputStream=ConfigFileReader.class.getResourceAsStream(relativePath);
        return inputStream;
    }

    public static Map<String,WhoisServer> loadWhoisServer(){
        Map<String,WhoisServer> servers = new ConcurrentHashMap<String, WhoisServer>();
        try {
            InputStream inputStream = fileInputStream(ConfigFile.NICS);
            Properties properties = new Properties();
            properties.load(inputStream);

            Set<Map.Entry<Object,Object>> entrySet =properties.entrySet();
            for(Map.Entry<Object,Object> entry:entrySet){
                String key = entry.getKey().toString().trim();
                String value = entry.getValue().toString().trim();
                if(key.startsWith("#")){
                    System.out.printf("[Be commented] %s=%s",key,value);
                    continue;
                }else{
                    WhoisServer whoisServer = new WhoisServer(value);
                    servers.put(key,whoisServer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return servers;
    }


}
