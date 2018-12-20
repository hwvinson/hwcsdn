package com.hw.csdn.controller;

import com.alibaba.fastjson.JSONObject;
import com.hw.csdn.dao.entity.UserInfo;
import com.hw.csdn.dao.service.UserService;
import com.hw.csdn.util.EmptyUtil;
import com.hw.csdn.util.MD5Util;
import com.hw.csdn.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/userInfo")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @RequestMapping(value = "/insetUser",method = RequestMethod.POST)
    public Message insetUser(@RequestBody String userInfoStr){
        Message msg=new Message();
        logger.info("【注册接口】请求报文："+userInfoStr);
        JSONObject json = JSONObject.parseObject(userInfoStr);
        String userName=(String)json.get("userName");
        String userPassword=(String) json.get("userPassword");
        String qq=(String) json.get("qq");
        String wechat=(String) json.get("wechat");
        try{
            if(EmptyUtil.isEmpty(userName)){
                throw new Exception("用户名不能为空");
            }
            if(EmptyUtil.isEmpty(userPassword)){
                throw new Exception("密码不能为空");
            }
            String salt = UUID.randomUUID().toString();
            UserInfo userInfo=new UserInfo();
            if (EmptyUtil.isNotEmpty(userPassword)){
                userInfo.setUserPassword(MD5Util.getMd5(userPassword)+salt); //加密
                userInfo.setSalt(salt);
            }else{
                throw new Exception("密码不能为空");
            }
            userInfo.setUserName(userName);
            userInfo.setQq(qq);
            userInfo.setWechat(wechat);
            msg=userService.save(userInfo);
        }catch (Exception e){
            msg.setSuccess("8888");
            msg.setCode("user/insert");
            if (EmptyUtil.isEmpty(e.getMessage())){
                msg.setData(e);
            }else{
                msg.setData(e.getMessage());
            }
        }
        logger.info("【注册接口】响应报文："+msg.toString());
        return msg;
    }

    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public Message userLogin(@RequestBody String requestBody){
        logger.info("【登录接口】请求报文："+ requestBody);
        JSONObject json = JSONObject.parseObject(requestBody);
        String uname=(String)json.get("uname");
        String upass=(String)json.get("upass");
        Message msg=new Message();
        try {
            if (EmptyUtil.isEmpty(uname)){
                throw new Exception("用户名不能为空");
            }
            if (EmptyUtil.isEmpty(upass)){
                throw new Exception("密码不能为空");
            }
            msg=userService.userLogin(uname,upass);
        }catch (Exception e){
            msg.setSuccess("8888");
            msg.setCode("user/login");
            if (EmptyUtil.isEmpty(e.getMessage())){
                msg.setData(e);
            }else{
                msg.setData(e.getMessage());
            }
        }
        logger.info("【登录接口】响应报文："+msg.toString());
        return msg;
    }

}
