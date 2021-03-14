package com.example.main.service;

import com.example.common.api.AccountService;
import com.example.common.entity.Account;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * TestService
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 15:14
 */
@Service
public class TestService {

    @DubboReference(loadbalance = "roundrobin", version = "1.0.0")
    private AccountService accountService;

    /**
     * 注册
     *
     * @param account
     * @return
     */
    public Optional<Account> signUp(Account account) {
        Account save = accountService.save(account);
        return Optional.ofNullable(save);
    }

    /**
     * 登录
     *
     * @param userName 用户名称
     * @param password 密码
     * @return
     */
    public Optional<Account> signIn(String userName, String password) {
        Account account = accountService.findOneByUsernameAndPwd(userName, password);
        return Optional.ofNullable(account);
    }

    /**
     * 查询全部用户
     *
     * @return
     */
    public List<Account> findALl() {
        List<Account> all = null;
        try {
            all = accountService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return
     */
    public Optional<Account> findById(Long id) {
        Account account = accountService.findOneById(id);
        return Optional.ofNullable(account);
    }
}
