package com.example.account.dao;

import com.example.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * AccountDao
 *
 * @author liangxiao
 * @version 1.00
 * @Date 2020/9/8 17:51
 */
@Repository
public interface IAccountDao extends JpaRepository<Account, Long> {

    /**
     * 根据用户名和密码查询
     *
     * @param username 用户名
     * @param pwd 密码
     * @return
     */
    @Query(value = "from Account where username=:username and password=:pwd")
    Optional<Account> findOneByUsernameAndPwd(String username, String pwd);


}
