package com.es.hw.dao;

import com.es.hw.entity.Region;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RegionEsDao extends ElasticsearchRepository<Region,Object> {
}
