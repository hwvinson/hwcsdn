package com.hw.csdn.dao.service;

import com.hw.csdn.dao.entity.UserInfo;
import com.hw.csdn.util.Message;

import java.util.List;

public interface UserService {
    public Message save(UserInfo userInfo) throws Exception;
    public void delete(Integer id);
    public List<UserInfo> findAll();
    public Message userLogin(String uname, String upass) throws Exception;
}
