package com.example.csdnbff.user.controller;

import com.example.common.util.Message;
import com.example.csdnbff.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public Message userLogin(@RequestBody String requestBody) {
        Message msg = new Message();
        try {
            System.out.println(requestBody);
            msg = userService.userLogin(requestBody);
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
}
