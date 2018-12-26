package com.hw.csdn.dao.service.impl;


import com.hw.csdn.dao.entity.UserInfo;
import com.hw.csdn.dao.service.UserService;
import com.hw.csdn.repository.UserRepository;
import com.hw.csdn.util.BaseResultCodeConstant;
import com.hw.csdn.util.MD5Util;
import com.hw.csdn.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Override
    public Message save(UserInfo userInfo) throws Exception{
        Message msg=new Message();
            String sql="select * from user_info where user_Name = '"+userInfo.getUserName()+"'";
            EntityManager em=entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
            Query query=em.createNativeQuery(sql,UserInfo.class);
            List<UserInfo> userInfos=(List<UserInfo>)query.getResultList();
            if(userInfos.size()>0){
                throw new Exception("此用户已被注册，请换用户名！！！");
            }
            userRepository.save(userInfo);
            em.close();
            msg.setCode("user/insert");
            msg.setSuccess(BaseResultCodeConstant.SUCCESS);
            msg.setData("注册成功");
        return msg;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserInfo> findAll() {
        return (List<UserInfo>)userRepository.findAll();
    }

    @Override
    public Message userLogin(String uname, String upass) throws Exception{
        Message msg=new Message();
        String sql="select * from user_info where user_Name = '"+uname+"'";
        EntityManager em=entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
        Query query=em.createNativeQuery(sql,UserInfo.class);
        List<UserInfo> userInfos=(List<UserInfo>)query.getResultList();
        if(userInfos.size()==0){
            throw new Exception("用户名或密码错误");
        }
        for (UserInfo userInfo:userInfos ) {
            if(!userInfo.getUserPassword().equals(MD5Util.getMd5(upass)+ userInfo.getSalt())){
                throw new Exception("用户名或密码错误");
            }
        }
        em.close();
        if (userInfos.size()>0){
            msg.setCode("user/login");
            msg.setSuccess(BaseResultCodeConstant.SUCCESS);
            msg.setData(userInfos.get(0));
        }
        return msg;
    }
}
