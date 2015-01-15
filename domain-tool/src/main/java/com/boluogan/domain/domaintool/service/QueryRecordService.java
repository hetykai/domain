package com.boluogan.domain.domaintool.service;

import com.boluogan.domain.domaintool.constant.ResponseStatus;
import com.boluogan.domain.domaintool.model.QueryRecordFile;
import com.boluogan.domain.domaintool.model.ResponseData;
import com.boluogan.domain.domaintool.utils.MyIOUtils;
import com.boluogan.util.date.MyDateHelper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by boluogan.com on 1/10/15.
 */
@Service
public class QueryRecordService {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private  ObjectMapper objectMapper = new ObjectMapper();

    /*{
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true) ;
    }*/
    private final File datasDirectory = MyIOUtils.createDatasDirectory();


    private File createBaseDirectory(){
        String classpath = QueryRecordService.class.getResource("/").getPath();
        String dirpath=classpath+"/target";

        System.out.println("classpath:"+classpath);//file:/u01/repository/github/boluogan/domain/domain-tool/target/domain-tool.jar!/
        if(classpath.indexOf("file:")>=0){
            try {

                dirpath = classpath.split("!")[0];
                int end = dirpath.lastIndexOf("/");
                dirpath=dirpath.substring(5 ,end);
                System.out.println("dirpath:"+dirpath);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        try {
            File mydir = new File(dirpath,"datas");
            if(!mydir.exists()){
                mydir.mkdir();
            }
            return mydir;
        } catch (Exception e) {
            System.out.println("exception-->dirpath:"+dirpath);
            e.printStackTrace();
        }

        return new File(System.getProperty("user.datasDirectory"));


        //return new File(System.getProperty("user.datasDirectory"));
    }

    public List<QueryRecordFile> queries(){
        List<QueryRecordFile> list = new ArrayList<QueryRecordFile>();
        System.out.println("datasDirectory.path:" + datasDirectory.getAbsolutePath());
        File[] files = datasDirectory.listFiles();

        for (File file:files){
            if(file.isFile()&&file.getName().indexOf(".json")>=0){
                try {

                    Map<String,?> map = objectMapper.readValue(file,Map.class);

                    String name =(String)map.get("name");
                    System.out.println("name:"+name);
                    String url = String.format("/datas/%s",file.getName());
                    QueryRecordFile queryRecordFile= new QueryRecordFile();
                    queryRecordFile.setName(name);
                    queryRecordFile.setUrl(url);
                    list.add(queryRecordFile);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return list;

    }

    public ResponseData<String> savequeries(String datas){
        try {
            System.out.println("datas:"+datas);
            System.out.println("datasDirectory.path:" + datasDirectory.getAbsolutePath());
            String filename=String.format("%s.json", MyDateHelper.dateToString(new Date(), "yyyyMMddHHmmssSSS"));
            System.out.println("filename:"+filename);
            File file = new File(datasDirectory,filename);

            FileUtils.writeStringToFile(file,datas,UTF8);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseStatus.FAILED.toResponseData();
        }

        return ResponseStatus.SUCCESS.toResponseData();
    }

    public String readQueryRecord(String filename){
        try {
            System.out.println("datasDirectory.getAbsolutePath():"+datasDirectory.getAbsolutePath());
            File file = new File(datasDirectory,filename+".json");
            return FileUtils.readFileToString(file,UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "404-File Not Found.";
    }


}
