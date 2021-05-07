package com.hw.csdn.repository;

import com.hw.csdn.dao.entity.PersonalArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalArticleRepository extends JpaRepository<PersonalArticle,Integer> {
}
