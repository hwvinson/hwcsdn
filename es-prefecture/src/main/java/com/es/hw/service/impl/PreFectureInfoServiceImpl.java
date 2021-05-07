package com.es.hw.service.impl;

import com.alibaba.fastjson.JSON;
import com.es.hw.conf.ESDataSourceConfig;
import com.es.hw.dao.RegionEsDao;
import com.es.hw.dto.RegionInfoResp;
import com.es.hw.entity.Region;
import com.es.hw.mapper.RegionMapper;
import com.es.hw.service.PreFectureInfoService;
import com.es.hw.util.ArrayConvert;
import com.google.common.collect.Lists;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class PreFectureInfoServiceImpl implements PreFectureInfoService {
    @Autowired
    RegionMapper regionMapper;

    @Autowired
    RegionEsDao regionEsDao;

    @Autowired
    private ESDataSourceConfig jestClient;

    @Override
    public List<Region> queryPreInfo(String codeId) {
        List<Region> regions = regionMapper.queryRegion(codeId);
        return regions;
    }

    @Override
    public void syncData() {
        long begin = System.currentTimeMillis();
        log.info("删除数据====");
        regionEsDao.deleteAll(); //先清空所有
        log.info("删除数据完成====");
        log.info("全量导入数据====");
        int num = 10000;
        int count = regionMapper.queryRegionCount(null);
        int times = count / num;
        if (count % num != 0) {
            times = times + 1;
        }
        int pageSize = 0;
        for (int i = 1; i <= times; i++) {
            List<Region> regions = regionMapper.queryRegionLimit((i-1)*num,num);//全量同步数据到es
            insertIntoEs(regions);
        }
        long end = System.currentTimeMillis();
        log.info("全量导入数据结束====");
        log.info("共计：{}秒",(end-begin)/1000);
    }

    @Async
    public void insertIntoEs(List<Region> regions){
        regionEsDao.saveAll(regions);
    }

    @Override
    public List<Region> queryPreInfoByEs(String addess) {
        List<Region> regions = null;
        try {
            log.info(regionEsDao.count()+"");
            SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
            FuzzyQueryBuilder matchQueryBuilder = QueryBuilders.fuzzyQuery("name",addess);
            searchSourceBuilder.query(matchQueryBuilder)
                    .from(0)  //页码
                    .size(50);
            Search search = new Search.Builder(searchSourceBuilder.toString())
                    .build();
            SearchResult result = jestClient.getClient()
                    .execute(search);
            List<SearchResult.Hit<Region, Void>> hitList = result.getHits(Region.class);
            regions = ArrayConvert.hitListToList(hitList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return regions;
    }


}
