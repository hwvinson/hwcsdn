package com.es.hw.util;

import com.es.hw.entity.Region;
import com.google.common.collect.Lists;
import io.searchbox.core.SearchResult;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ArrayConvert {

    public static<T> List<T> iterableToList(Iterable<T> iterable){
        List<T> list = StreamSupport
                .stream(iterable.spliterator(),false)
                .collect(Collectors.toList());
        return list;
    }
    public static<T> List<T> hitListToList(List<SearchResult.Hit<T, Void>> hitList){
        List<T> list = Lists.newArrayList();
        hitList.stream().map((hit)-> hit.source).forEach((article)->{
            list.add(article);
        });
        return list;
    }
}
