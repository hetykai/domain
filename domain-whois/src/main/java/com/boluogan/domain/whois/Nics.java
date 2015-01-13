package com.boluogan.domain.whois;

import com.boluogan.domain.whois.exception.UnSupportedRootTldException;
import com.boluogan.domain.whois.parser.WhoisParserSlector;
import com.boluogan.domain.whois.utils.ConfigFileReader;

import java.util.*;

/**
 * Created by boluogan.com on 12/10/14.
 */
public class Nics {
    private static Set<String> gtlds;
    private static Set<String> rootCctlds;
    private static Set<String> allCctlds;
    private static Map<String, WhoisServer> serverMap;
    private static Set<String> rootTlds;
    private static Set<String> allTlds;
    private static Set<String> parseableRootTlds;

    static {

        init();
    }

    private static void init(){
        try {
            gtlds = new HashSet(ConfigFileReader.read(ConfigFile.GTLDS));
            rootCctlds = new HashSet(ConfigFileReader.read(ConfigFile.CCTLDS));
            allCctlds = new HashSet(ConfigFileReader.read(ConfigFile.ALLCCTLDS));
            serverMap = ConfigFileReader.loadWhoisServer();
            rootTlds = serverMap.keySet();
            List<String> parseableRootTldsList= WhoisParserSlector.initWhoisParsers(rootTlds);
            parseableRootTlds = new HashSet<String>(parseableRootTldsList);

            List<String> list = new ArrayList<String>(gtlds);
            list.addAll(new ArrayList<String>(allCctlds));
            allTlds = new HashSet<String>(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * whois server
     * @return
     */
    public static List<WhoisServer> servers() {
        List<WhoisServer> list = new ArrayList<WhoisServer>();
        for (String key : serverMap.keySet()) {
            list.add(serverMap.get(key));
        }
        return list;

    }

    public static List<String> parseableRootTlds(){
        return new ArrayList<String>(parseableRootTlds);
    }

    /**
     * 根据根后缀返回whois服务器对象
     * @param rootTld
     * @return
     * @throws com.boluogan.domain.whois.exception.UnSupportedRootTldException
     */
    public static WhoisServer selectServer(String rootTld) throws UnSupportedRootTldException {
        if (serverMap.containsKey(rootTld)) {
            return serverMap.get(rootTld);
        } else {
            throw new UnSupportedRootTldException(rootTld);
        }
    }

    public static List<String> rootTlds() {
        return new ArrayList<String>(rootTlds);
    }

    public static List<String> allTlds() {
        return new ArrayList<String>(allTlds);
    }

    public static void load() {

    }
}
