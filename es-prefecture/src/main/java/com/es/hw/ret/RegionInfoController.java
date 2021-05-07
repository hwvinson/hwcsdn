package com.es.hw.ret;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.es.hw.entity.Region;
import com.es.hw.service.PreFectureInfoService;
import com.es.hw.util.EmptyUtil;
import com.es.hw.util.Message;
import org.elasticsearch.action.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionInfoController {

    @Autowired
    PreFectureInfoService preFectureInfoService;

    @PostMapping(value = "/queryRegionInfo")
    public Message queryRegionInfo(@RequestBody String reqJson){
        Message message = new Message();
        JSONObject object = JSON.parseObject(reqJson);
        String codeId = object.getString("codeId");
        if (EmptyUtil.isEmpty(codeId)){
            message.setCode("1");
            message.setData("codeId为必传");
        }
        List<Region> regionList = preFectureInfoService.queryPreInfo(codeId);
        message.setCode("0");
        message.setData(regionList);
        return message;
    }

    @GetMapping(value = "/syncData")
    public void syncData(){

        preFectureInfoService.syncData();
    }

    @PostMapping(value = "/queryRegionInfoByEs")
    public Message queryRegionInfoByEs(@RequestBody String reqJson){
        Message message = new Message();
        JSONObject object = JSON.parseObject(reqJson);
        String codeId = object.getString("addess");
        if (EmptyUtil.isEmpty(codeId)){
            message.setCode("1");
            message.setData("addess为必传");
        }
        List<Region> regionList = preFectureInfoService.queryPreInfoByEs(codeId);
        message.setCode("0");
        message.setData(regionList);
        return message;
    }
}
