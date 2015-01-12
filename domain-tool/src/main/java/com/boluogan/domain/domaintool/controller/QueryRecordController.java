package com.boluogan.domain.domaintool.controller;

import com.boluogan.domain.domaintool.model.QueryRecordFile;
import com.boluogan.domain.domaintool.model.ResponseData;
import com.boluogan.domain.domaintool.service.QueryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by boluogan.com on 1/10/15.
 */
@RestController
public class QueryRecordController {
    @Autowired
    private QueryRecordService queryRecordService;

    @RequestMapping(value = "/queries", method = RequestMethod.GET)
    public List<QueryRecordFile> queries() {
        return queryRecordService.queries();

    }


    @RequestMapping(value = "/savequeries", method = RequestMethod.POST)
    public ResponseData<String> savequeries(@RequestBody String datas) {
        
        return queryRecordService.savequeries(datas);
    }

    @RequestMapping(value = "/datas/{filename}", method = RequestMethod.GET)
    public String queryRecord(@PathVariable String filename) {
        String content = queryRecordService.readQueryRecord(filename);
        return content;

        //return new ResponseData<String>(ResponseStatus.)
    }


    /*public QueryRecordFile queryRecordFile(){

    }*/
}
