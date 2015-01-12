package com.boluogan.domain.domaintool.service;

import com.boluogan.domain.domaintool.constant.ResponseStatus;
import com.boluogan.domain.domaintool.model.ResponseData;
import com.boluogan.domain.domaintool.model.WhoisRecord;
import com.boluogan.domain.domaintool.utils.WhoisRecordBuilder;
import com.boluogan.domain.whois.DomainWhoisInfo;
import com.boluogan.domain.whois.WhoisQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by boluogan.com on 1/5/15.
 */
@Service
public class BulkQueryService {
    private SimpMessagingTemplate template;
    private ExecutorService executorService= Executors.newSingleThreadExecutor();
    @Autowired
    public BulkQueryService(SimpMessagingTemplate template){
        this.template=template;
    }
    public void bulkQuery(final List<Map<String,?>> whoisRecordList){
        try {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2*1000l);

                        for(Map<String,?> map:whoisRecordList){
                            try {
                                Integer sid = (Integer) map.get("sid");
                                String domainname = (String)map.get("domainname");

                                DomainWhoisInfo domainWhoisInfo = WhoisQuery.parsedWhoisInfo(domainname);
                                WhoisRecord whoisRecord= WhoisRecordBuilder.build(domainWhoisInfo);
                                whoisRecord.setSid(sid);
                                template.convertAndSend("/topic/bulkquery",new ResponseData<WhoisRecord>(ResponseStatus.QUERYING,whoisRecord));
                                Thread.sleep(50l);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage()+":\n"+whoisRecordList.get(0));
                    }

                    template.convertAndSend("/topic/bulkquery",new ResponseData<String>(ResponseStatus.DONE,"批量查询结束."));
                }
            };

            executorService.execute(runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
