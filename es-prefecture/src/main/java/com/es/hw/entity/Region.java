package com.es.hw.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "region")
public class Region {
    @Id
    private int id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Integer)
    private String pid;
    @Field(type = FieldType.Integer)
    private String sort;
    @Field(type = FieldType.Integer)
    private String level;
    @Field(type = FieldType.Text)
    private String longCode;
    @Field(type = FieldType.Text)
    private String code;

}
