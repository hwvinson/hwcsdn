package com.hw.csdn.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "personal_article")
public class PersonalArticle {
    @Id
    @GeneratedValue
    private Integer paid;
    @Column(name = "pic_Id", nullable = true, length = 20)
    private String picId;   //图片id
    @Column(name = "word_Id", nullable = true, length = 20)
    private String wordId;  //文件id
    @Column(name = "user_Id", nullable = true, length = 20)
    private Integer userId;  //用户id
    @Column(name = "tatil", nullable = true, length = 100)
    private String tatil;  //标题
    @Column(name = "content", nullable = true)
    private String content; //内容
    @Column(name = "create_date", nullable = true)
    private Date createDate;
    @Column(name = "update_date", nullable = true)
    private Date updateDate;
    @Column(name = "user_Name", nullable = true,length = 20)
    private String userName;
    @Column(name = "state", nullable = true,length = 2)
    private String state;
}
