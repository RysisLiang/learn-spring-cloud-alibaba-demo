package com.example.account.rpc;

import com.example.account.entity.Account;
import com.example.common.framework.rpc.RPCResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AccountRemoteService
 * 用于定义用户服务的API接口
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 14:46
 */
@RequestMapping(value = "/v1/api/accounts")
public interface IAccountRPC {

    /**
     * 新增账户
     *
     * @param account 账户数据
     * @return
     */
    @PostMapping("")
    RPCResult<Account> save(@RequestBody Account account);

    /**
     * 根据指定id查询账户
     *
     * @param id 账户id
     * @return
     */
    @GetMapping("/{id}")
    RPCResult<Account> findOneById(@PathVariable("id") Long id);

    /**
     * 获取全部账户
     *
     * @return
     */
    @GetMapping("")
    RPCResult<List<Account>> getAll();

    /**
     * 根据账户名称和密码查询账户
     *
     * @param username 账户名称
     * @param pwd 密码
     * @return
     */
    @GetMapping("/unpwd")
    RPCResult<Account> findOneByUsernameAndPwd(@RequestParam("username") String username,
                                               @RequestParam("pwd") String pwd);

    /**
     * 根据指定id进行借出余额操作，并返回新的用户数据
     *
     * @param id 账户id
     * @param money 金额
     * @return
     */
    @PostMapping("/{id}")
    RPCResult<Account> debitById(@PathVariable("id") Long id, @RequestParam("money") Integer money);

}
