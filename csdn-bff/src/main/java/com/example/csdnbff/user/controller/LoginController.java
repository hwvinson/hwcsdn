package com.example.csdnbff.user.controller;

import com.example.csdnbff.resq.UserInfo;
import com.example.csdnbff.user.service.UserService;
import com.example.csdnbff.util.JsonUtil;
import com.example.csdnbff.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userLogin")
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/tologin", method = RequestMethod.POST)
    public String tologin(@RequestBody String requestBody, HttpServletRequest request){
        try{
            Message msg = userService.userLogin(requestBody);
            if("0000".equals(msg.getSuccess())){
                HttpSession session=request.getSession();//获取session并将userName存入session对象
                UserInfo userInfo= JsonUtil.toBean(JsonUtil.convertObjectToJSON(msg.getData()),UserInfo.class);
                session.setAttribute("userInfo", userInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }
}
