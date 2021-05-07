package com.es.hw.mapper;

import com.es.hw.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RegionMapper {

    List<Region> queryRegion(@Param("codeId") String codeId);

    int queryRegionCount(@Param("codeId") String codeId);

    List<Region> queryRegionLimit(@Param("pageNum") int pageNum
            , @Param("pageSize")int pageSize);
}
