package com.hw.csdn.resq;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserInfo {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String qq;
    private String wechat;
    @JsonIgnore
    private String salt;
}
