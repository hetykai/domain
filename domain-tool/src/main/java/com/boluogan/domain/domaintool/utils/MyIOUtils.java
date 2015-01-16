package com.boluogan.domain.domaintool.utils;

import com.boluogan.domain.domaintool.service.QueryRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by boluogan.com on 1/12/15.
 */
public class MyIOUtils {
    private static Logger logger = LoggerFactory.getLogger(MyIOUtils.class);
    private static final  File rootDirectory=createRootDirectory();
    private static final String DATAS_DIRECTORY_NAME="datas";


    private static File createRootDirectory(){

        String classpath = MyIOUtils.class.getResource("/").getPath();
        String dirpath=System.getProperty("user.dir");

        System.out.println("classpath:"+classpath);//file:/u01/repository/github/boluogan/domain/domain-tool/target/domain-tool.jar!/
        if(classpath.indexOf("file:")>=0){
            try {

                dirpath = classpath.split("!")[0];
                int end = dirpath.lastIndexOf("/");
                dirpath=dirpath.substring(5 ,end);

            } catch (Exception e) {
                logger.error("createRootDirectory:",e);
            }

        }
        File file = new File(dirpath);
        return file;
    }

    public static String getDatasDirectoryName(){
        return String.format("%s/%s",rootDirectory.getAbsolutePath(),DATAS_DIRECTORY_NAME);
    }


    public static File createDatasDirectory(){
        File rootDir = createRootDirectory();
        File mydir = new File(rootDir,DATAS_DIRECTORY_NAME);
        try {

            if(!mydir.exists()){
                mydir.mkdir();
            }
            return mydir;
        } catch (Exception e) {

            logger.error("createDatasDirectory:",e);
        }
        return mydir;
    }
}
