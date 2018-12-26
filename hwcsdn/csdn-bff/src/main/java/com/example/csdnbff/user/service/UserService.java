package com.example.csdnbff.user.service;


import com.example.csdnbff.resq.UserInfo;
import com.example.csdnbff.util.Message;

import java.util.List;

public interface UserService {
    public Message save(String requestBody) throws Exception;
    public void delete(Integer id);
    public List<UserInfo> findAll();
    public Message userLogin(String requestBody) throws Exception;
}
