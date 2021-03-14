package com.example.userserver.dao;

import com.example.common.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserDao
 *
 * @author liangxiao
 * @version 1.00
 * @Date 2020/9/8 17:51
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {

    /**
     * 根据用户名和密码查询
     *
     * @param username 用户名
     * @param pwd 密码
     * @return
     */
    @Query(value = "from User where username=:username and password=:pwd")
    Optional<User> findOneByUsernameAndPwd(String username, String pwd);


}
