package com.example.main.rpc.remote;

import com.example.account.rpc.IAccountRPC;
import com.example.main.rpc.fallback.AccountRemoteServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * AccountRemoteService
 * 用于定义用户服务的API-远程实现
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 14:46
 */
@FeignClient(value = "user-server", fallback = AccountRemoteServiceFallback.class)
public interface AccountRemoteService extends IAccountRPC {

}
