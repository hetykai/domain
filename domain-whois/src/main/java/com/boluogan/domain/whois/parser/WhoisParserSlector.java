package com.boluogan.domain.whois.parser;

import com.boluogan.domain.whois.exception.UnSupportedWhoisInfoParserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by boluogan.com on 12/12/14.
 */
final public class   WhoisParserSlector {
    private static boolean init=false;


    private static Map<String,WhoisParser> map = new ConcurrentHashMap<String, WhoisParser>();

    /**
     * 禁止实例化
     */
    private WhoisParserSlector(){

    }

    /**
     * 推入根后缀对应的解析器
     * @param rootTld
     * @param whoisParserOfTLD
     */
    private static void put(String rootTld,WhoisParser whoisParserOfTLD){
        map.put(rootTld.toUpperCase(), whoisParserOfTLD);
    }


    /**
     * 根据根后缀选择对应的whois解析器
     * @param rootTld
     * @return
     * @throws UnSupportedWhoisInfoParserException
     */
    public static WhoisParser selectParser(String rootTld) throws UnSupportedWhoisInfoParserException {
        String key = rootTld.toUpperCase();
        if(map.containsKey(key)){
            return map.get(key);
        }else{
            throw new UnSupportedWhoisInfoParserException(rootTld) ;
        }




    }

    /**
     * 根据后缀名拼出对应解析器的类名，生成实例放入map
     * @param rootTlds
     */
    public static synchronized List<String> initWhoisParsers(Set<String> rootTlds){
        List<String> list = new ArrayList<String>();
        if(!init){
            init=true;
            for(String tld:rootTlds){
                try {
                    String firstLetter = tld.substring(0,1).toUpperCase();
                    String otherLetters = tld.substring(1).toLowerCase();
                    String clsname = String.format("com.boluogan.domain.whois.parser.%s%sWhoisParser",firstLetter,otherLetters);
                    //System.out.println(String.format("[%s] %s",tld,clsname));
                    Class<?> cls =Class.forName(clsname);
                    WhoisParser whoisParser =(WhoisParser) cls.newInstance();
                    map.put(tld.toUpperCase(),whoisParser);
                    list.add(tld.toLowerCase());
                }  catch (ClassCastException e){
                    e.printStackTrace();
                }catch (Exception e) {
                    //e.printStackTrace();
                }

            }
        }

        return list;
    }


}
