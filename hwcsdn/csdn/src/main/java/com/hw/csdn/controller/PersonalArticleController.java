package com.hw.csdn.controller;

import com.hw.csdn.dao.entity.PersonalArticle;
import com.hw.csdn.dao.service.PersonalArticleService;
import com.hw.csdn.util.EmptyUtil;
import com.hw.csdn.util.JsonUtil;
import com.hw.csdn.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/personalArticle")
public class PersonalArticleController {

    @Autowired
    PersonalArticleService personalArticleService;
    Logger logger= LoggerFactory.getLogger(PersonalArticleController.class);
    @RequestMapping(value = "/updateArticle",method = RequestMethod.POST)
    public Message updateArticle(@RequestParam String personalArticle){
        logger.info(personalArticle);
        Message msg=new Message();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try{
            PersonalArticle personalArticlel =(PersonalArticle) JsonUtil.convertJsonToObject(personalArticle,PersonalArticle.class);
            if (EmptyUtil.isEmpty(personalArticlel.getPaid())){
                throw new Exception("文章id不能为空");
            }
            personalArticleService.update(personalArticlel);
            msg.setCode("personalArticle");
            msg.setSuccess("0000");
            msg.setData("修改成功");
        }catch (Exception e){
            msg.setCode("personalArticle");
            msg.setSuccess("8888");
            msg.setData(e+";"+e.getMessage());
        }
        return msg;
    }

    @RequestMapping(value = "/saveArticle",method = RequestMethod.POST)
    public Message saveArticle(@RequestParam String personalArticle){
        logger.info(personalArticle);
        Message msg=new Message();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try{
            PersonalArticle personalArticlel =(PersonalArticle)JsonUtil.convertJsonToObject(personalArticle,PersonalArticle.class);
            personalArticlel.setCreateDate(new Date());
            personalArticlel.setUpdateDate(new Date());
            personalArticleService.save(personalArticlel);
            msg.setCode("personalArticle");
            msg.setSuccess("0000");
            msg.setData("新增成功");
        }catch (Exception e){
            msg.setCode("personalArticle");
            msg.setSuccess("8888");
            msg.setData(e+";"+e.getMessage());
        }
        return msg;
    }

}
