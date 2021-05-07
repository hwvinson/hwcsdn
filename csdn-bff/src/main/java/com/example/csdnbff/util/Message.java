package com.example.csdnbff.util;

import lombok.Data;

@Data
public class Message {
    private String code;  //领域
    private String success; //是否成功
    private Object data;   //返回数据
}
