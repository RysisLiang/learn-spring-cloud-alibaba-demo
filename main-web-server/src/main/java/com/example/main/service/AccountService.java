package com.example.main.service;

import com.example.account.entity.Account;
import com.example.account.rpc.IAccountRPC;
import com.example.common.framework.rpc.RPCResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * AccountService
 * 账户业务类
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 15:14
 */
@Service
public class AccountService {

    @Resource
    private IAccountRPC accountRPC;

    /**
     * 注册
     *
     * @param account 账户信息
     * @return
     */
    public Optional<Account> signUp(Account account) {
        RPCResult<Account> rpc = accountRPC.save(account);
        return Optional.ofNullable(rpc.getResult());
    }

    /**
     * 登录
     *
     * @param userName 用户名称
     * @param password 密码
     * @return
     */
    public Optional<Account> signIn(String userName, String password) {
        RPCResult<Account> rpc = accountRPC.findOneByUsernameAndPwd(userName, password);
        return Optional.ofNullable(rpc.getResult());
    }

    /**
     * 查询全部用户
     *
     * @return
     */
    public List<Account> findALl() {
        try {
            RPCResult<List<Account>> rpc = accountRPC.getAll();
            return rpc.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return
     */
    public Optional<Account> findById(Long id) {
        RPCResult<Account> rpc = accountRPC.findOneById(id);
        return Optional.ofNullable(rpc.getResult());
    }
}
