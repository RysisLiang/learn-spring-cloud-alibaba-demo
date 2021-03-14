package com.example.userserver.service.impl;

import com.example.common.api.AccountService;
import com.example.common.entity.Account;
import com.example.userserver.dao.AccountDao;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * AccountServiceImpl
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/6 15:08
 */
@DubboService(version = "1.0.0")
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public Account save(Account account) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 AccountServiceImpl.save:name=" + account.getName());
        return accountDao.save(account);
    }

    @Override
    public List<Account> getAll() {
        System.out.println(LocalDateTime.now() + " - 1.0.0 AccountServiceImpl.getAll");
        return (List<Account>) accountDao.findAll();
    }

    @Override
    public Account findOneById(Long id) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 AccountServiceImpl.findOneById:id=" + id);
        Optional<Account> optional = accountDao.findById(id);
        Optional<Account> optional1 = optional.map(i -> {
            i.setAddress("user-server-0");
            return i;
        });
        return optional1.orElse(null);
    }

    @Override
    public Account findOneByUsernameAndPwd(String username, String pwd) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 AccountServiceImpl.findOneById:username=" + username);
        Optional<Account> optional = accountDao.findOneByUsernameAndPwd(username, pwd);
        Optional<Account> optional1 = optional.map(i -> {
            i.setAddress("user-server-0");
            return i;
        });
        return optional1.orElse(null);
    }
}
