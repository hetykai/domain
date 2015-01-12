package com.boluogan.domain.whois;

import com.boluogan.util.date.MyDateHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by boluogan.com on 1/12/15.
 */
public class JsonTest {
    private String dirpath="/u01/repository/github/boluogan/domain/domain-tool/src/test/resources";
    private ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
    //@Test
    public void testUser(){
        try {

            User user = mapper.readValue(new File(dirpath,"user.json"), User.class);
            System.out.println("user.gender():"+user.getGender());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void testQueryRecord(){
        try {
            //File file = new File(dirpath,"queryrecord.json");

            File file = new File("/u01/repository/github/boluogan/domain/domain-tool/target/datas/20150112091420241.json");
            Map<String,?> map = mapper.readValue(file,Map.class);

            System.out.println("name:"+map.get("name"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testWriteQueryRecord(){
        try {
            File source = new File(dirpath,"temp.json");
            String filename=String.format("%s.json", MyDateHelper.dateToString(new Date(), "yyyyMMddHHmmssSSS"));
            File file = new File("/u01/repository/github/boluogan/domain/domain-tool/target/datas",filename);
            String content = FileUtils.readFileToString(source);
            FileUtils.writeStringToFile(file,content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
