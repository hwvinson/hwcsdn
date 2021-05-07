package com.example.csdnbff.user.controller;

import com.example.csdnbff.resq.UserInfo;
import com.example.csdnbff.user.service.UserService;
import com.example.csdnbff.util.BaseResultCodeConstant;
import com.example.csdnbff.util.JsonUtil;
import com.example.csdnbff.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/userInfo")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public Message userLogin(@RequestBody String requestBody, HttpServletRequest request) {
        Message msg = new Message();
        try {
            System.out.println(requestBody);
            msg = userService.userLogin(requestBody);
            if (BaseResultCodeConstant.SUCCESS.equals(msg.getSuccess())){
                HttpSession session=request.getSession();
                UserInfo userInfo= JsonUtil.toBean(JsonUtil.convertObjectToJSON(msg.getData()),UserInfo.class);
                session.setAttribute("userInfo",userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public Message toRegister(@RequestBody String requestBody) {
        Message msg = new Message();
        try {
            System.out.println(requestBody);
            msg = userService.save(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
    @RequestMapping(value = "/tologin", method = RequestMethod.GET)
    public ModelAndView tologin() {
        ModelAndView mv=new ModelAndView("user/tologin");
        return mv;
    }
    @RequestMapping(value = "/toindex", method = RequestMethod.GET)
    public ModelAndView toindex() {
        ModelAndView mv=new ModelAndView("index1");
        return mv;
    }
}
