package com.boluogan.domain.domaintool.controller;

import com.boluogan.domain.domaintool.model.ResponseData;
import com.boluogan.domain.domaintool.constant.ResponseStatus;
import com.boluogan.domain.domaintool.model.WhoisRecord;
import com.boluogan.domain.domaintool.service.BulkQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by boluogan.com on 12/31/14.
 */
@RestController
public class BulkQueryController {
    @Autowired
    private BulkQueryService bulkQueryService;

    @MessageMapping("/domainscan")
    @SendTo("/topic/bulkquery")
    public ResponseData bulkQuery(@RequestBody List<Map<String,?>> whoisRecordMaps){
        bulkQueryService.bulkQuery(whoisRecordMaps);
        return new ResponseData<String>(ResponseStatus.BEGIN,"正在查询中....");
    }
}
