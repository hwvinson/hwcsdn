package com.es.hw.ret;

import com.es.hw.service.SyncPrefectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SyncPreController {

    @Autowired
    SyncPrefectureService syncPrefectureService;

    @GetMapping(value = "/test")
    public String testSycn() throws Exception{
        return syncPrefectureService.syncPreInDb();
    }

}
