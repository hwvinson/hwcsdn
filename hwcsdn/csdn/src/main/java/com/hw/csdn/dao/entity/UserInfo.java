package com.hw.csdn.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue
    private Integer userId;
    @Column(name = "user_Name", nullable = true, length = 20)
    private String userName;
    @Column(name = "user_Password", nullable = true, length = 255)
    @JsonIgnore
    private String userPassword;
    @Column(name = "qq", nullable = true, length = 20)
    private String qq;
    @Column(name = "wechat", nullable = true, length = 20)
    private String wechat;
    @Column(name = "salt", nullable = true, length = 50)
    @JsonIgnore
    private String salt;
}
