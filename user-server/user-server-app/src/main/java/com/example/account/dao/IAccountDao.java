package com.example.account.dao;

import com.example.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * 根据指定id进行借出余额操作
     *
     * @param id 账户id
     * @param money 金额
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Account a SET a.balance = a.balance - :money WHERE a.id = :id and a.balance >= :money")
    int debitById(@Param("id") Long id, @Param("money") Integer money);


}
