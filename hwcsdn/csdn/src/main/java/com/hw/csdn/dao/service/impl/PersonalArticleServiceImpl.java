package com.hw.csdn.dao.service.impl;

import com.hw.csdn.dao.entity.PersonalArticle;
import com.hw.csdn.dao.service.PersonalArticleService;
import com.hw.csdn.repository.PersonalArticleRepository;
import com.hw.csdn.util.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("personalArticleServiceImpl")
public class PersonalArticleServiceImpl implements PersonalArticleService {
    @Autowired
    PersonalArticleRepository personalArticleRepository;
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    Logger logger = LoggerFactory.getLogger(PersonalArticleServiceImpl.class);

    @Override
    public void save(PersonalArticle personalArticle) {
        personalArticleRepository.save(personalArticle);
    }

    @Override
    public void update(PersonalArticle personalArticle) {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateSql="update PersonalArticle p set";
        updateSql+=" p.updateDate='"+sdf.format(date)+"'";
        if (EmptyUtil.isNotEmpty(personalArticle.getUserName())){
            updateSql+=",p.userName='"+personalArticle.getUserName()+"'";
        }
        if (EmptyUtil.isNotEmpty(personalArticle.getContent())){
            updateSql+=",p.content='"+personalArticle.getContent()+"'";
        }
        if (EmptyUtil.isNotEmpty(personalArticle.getPicId())){
            updateSql+=",p.picId='"+personalArticle.getPicId()+"'";
        }
        if (EmptyUtil.isNotEmpty(personalArticle.getState())){
            updateSql+=",p.state='"+personalArticle.getState()+"'";
        }
        if (EmptyUtil.isNotEmpty(personalArticle.getTatil())){
            updateSql+=",p.tatil='"+personalArticle.getTatil()+"'";
        }
        if (EmptyUtil.isNotEmpty(personalArticle.getWordId())){
            updateSql+=",p.wordId='"+personalArticle.getWordId()+"'";
        }
        updateSql+=" where p.paid='"+personalArticle.getPaid()+"'";
        EntityManager em=entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query=em.createQuery(updateSql);
        int flag=query.executeUpdate();
        em.getTransaction().commit();
        logger.info(flag+"");
    }
}
