package com.example.account.rpc.provider;

import com.example.account.dao.IAccountDao;
import com.example.account.entity.Account;
import com.example.account.rpc.IAccountRPC;
import com.example.common.framework.rpc.RPCResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * AccountProvider
 * 用于提供账户服务的类
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/6 15:08
 */
@RestController
public class AccountProvider implements IAccountRPC {

    @Resource
    private IAccountDao accountDao;

    /**
     * 新增账户
     *
     * @param account 账户数据
     * @return
     */
    @Override
    public RPCResult<Account> save(@RequestBody Account account) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 AccountProvider.save:name=" + account.getName());
        Account data = accountDao.save(account);
        return RPCResult.buildSuccess(data);
    }

    /**
     * 根据指定id查询账户
     *
     * @param id 账户id
     * @return
     */
    @Override
    public RPCResult<Account> findOneById(@PathVariable("id") Long id) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 AccountProvider.findOneById:id=" + id);
        Optional<Account> optional = accountDao.findById(id);
        Optional<Account> optional1 = optional.map(i -> {
            i.setAddress("user-server-0");
            return i;
        });
        return RPCResult.buildSuccess(optional1.orElse(null));
    }

    /**
     * 获取全部账户
     *
     * @return
     */
    @Override
    public RPCResult<List<Account>> getAll() {
        System.out.println(LocalDateTime.now() + " - 1.0.0 AccountProvider.getAll");
        return RPCResult.buildSuccess(accountDao.findAll());
    }

    /**
     * 根据账户名称和密码查询账户
     *
     * @param username 账户名称
     * @param pwd 密码
     * @return
     */
    @Override
    public RPCResult<Account> findOneByUsernameAndPwd(@RequestParam("username") String username,
                                                      @RequestParam("pwd") String pwd) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 AccountProvider.findOneById:username=" + username);
        Optional<Account> optional = accountDao.findOneByUsernameAndPwd(username, pwd);
        Optional<Account> optional1 = optional.map(i -> {
            i.setAddress("user-server-0");
            return i;
        });
        return RPCResult.buildSuccess(optional1.orElse(null));
    }

    @Override
    @Transactional
    public RPCResult<Account> debitById(Long id, Integer money) {
        System.out.printf("AccountProvider.debitById:id=%d, money=%d %n", id, money);
        accountDao.debitById(id, money);
        Optional<Account> optional = accountDao.findById(id);
        return RPCResult.buildSuccess(optional.orElse(null));
    }
}
