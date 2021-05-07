package com.es.hw.service;

import com.es.hw.dto.RegionInfoResp;
import com.es.hw.entity.Region;

import java.util.List;
import java.util.Map;

public interface PreFectureInfoService {

    List<Region> queryPreInfo(String codeId);

    void syncData();

    List<Region> queryPreInfoByEs(String addess);
}
