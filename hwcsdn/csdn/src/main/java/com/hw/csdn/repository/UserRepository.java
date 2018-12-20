package com.hw.csdn.repository;

import com.hw.csdn.dao.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserInfo,Integer> {
    @Query(value = "select * from user_info where user_Name= :uname and user_Password= :upass",nativeQuery = true)
    UserInfo findUserInfoByUserName(@Param("uname") String uname, @Param("upass") String upass);
}
