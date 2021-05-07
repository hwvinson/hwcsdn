package com.example.csdnbff.user.service.impl;


import com.example.csdnbff.resq.UserInfo;
import com.example.csdnbff.user.service.UserService;
import com.example.csdnbff.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Value("${csdn.url}")
    private String userServicePath;

    private String url = "http://localhost:9093";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Message save(String requestBody) throws Exception {
        return  this.restTemplate.postForObject(url+"/userInfo/insetUser",requestBody, Message.class);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<UserInfo> findAll() {
        return null;
    }

    @Override
    public Message userLogin(String requestBody) throws Exception {
        return this.restTemplate.postForObject(url+"/userInfo/userLogin",requestBody,Message.class);
    }
}
