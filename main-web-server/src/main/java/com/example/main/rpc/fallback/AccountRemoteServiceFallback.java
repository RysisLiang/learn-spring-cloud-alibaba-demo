package com.example.main.rpc.fallback;


import com.example.account.entity.Account;
import com.example.common.framework.rpc.RPCResult;
import com.example.main.rpc.remote.AccountRemoteService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AccountRemoteServiceFallback
 * 默认的失败接口
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/21 23:04
 */
@Component
public class AccountRemoteServiceFallback implements AccountRemoteService {

    @Override
    public RPCResult<Account> save(Account account) {
        return RPCResult.buildError("fallback");
    }

    @Override
    public RPCResult<Account> findOneById(Long id) {
        return RPCResult.buildError("fallback");
    }

    @Override
    public RPCResult<List<Account>> getAll() {
        return RPCResult.buildError("fallback");
    }

    @Override
    public RPCResult<Account> findOneByUsernameAndPwd(String username, String pwd) {
        return RPCResult.buildError("fallback");
    }

    @Override
    public RPCResult<Account> debitById(Long id, Integer money) {
        return RPCResult.buildError("fallback");
    }
}
