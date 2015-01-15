package com.boluogan.domain.whois.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by boluogan.com on 12/12/14.
 */
public class WhoisStringUtil {
    /**
     * 将字符串转为日期
     *
     * @param source
     * @param formate
     * @return
     */
    public static Date str2Date(String source, String formate) {

       return str2Date(source,formate,Locale.getDefault(Locale.Category.FORMAT));

    }

    public static Date str2Date(String source, String formate,Locale locale) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formate,locale);
            Date date = simpleDateFormat.parse(source);
            //System.out.printf("\n[%s,%s] %s\n",source,formate,simpleDateFormat.format(date));
            return date;
        } catch (ParseException e) {
            System.out.println("exception-->source:"+source+",formate:"+formate);
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 将每一行内容放入List返回
     * @param whoisSource
     * @return
     */
    public static List<String> linesOfFullContent(String whoisSource) {
        return new ArrayList<String>(Arrays.asList(whoisSource.split("\n")));
    }

    /**
     * 根据开始和结束标签，截取目标数据并转换为List
     * @param whoisSource
     * @param beginTag
     * @param endTag
     * @return
     */
    public static List<String> linesOfSubstringContent(String whoisSource, String beginTag, String endTag) {
        try {
            int begin=whoisSource.indexOf(beginTag);
            int end = whoisSource.indexOf(endTag);

            String mainContent = whoisSource.substring(begin,end);
            return linesOfFullContent(mainContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    public static List<String> linesOfSubstringContent(String whoisSource) {
        return linesOfSubstringContent(whoisSource, "Domain Name:", ">>> Last update of");
    }

    /**
     * 将每行的数据用":"号分组，第一个为key,第二个存入对应value的list
     *
     * @param lines
     * @return
     */
    public static Map<String, List<String>> linesToMap(List<String> lines) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String line : lines) {
            try {
                /**
                 * bun不能直接用split,因为像时间这些有":"符号
                 */
                //String strs[] = line.split(":");
                int splitIndex =line.indexOf(":");
                if(splitIndex<0){
                    continue;
                }
                String key = line.substring(0,splitIndex).trim();
                String value = line.substring(splitIndex+1).trim();
                if(value==null ||value.length()==0){
                    continue;
                }
                if (map.containsKey(key)) {
                    List<String> list = map.get(key);
                    list.add(value);

                } else {
                    List<String> list = new ArrayList<String>();
                    list.add(value);
                    map.put(key, list);
                }
            } catch (Exception e) {
                //System.out.println(line);
                e.printStackTrace();
            }
        }

        return map;
    }
}
