package com.example.userserver.config;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * DubboGlobalFallbackHandler
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/23 17:20
 */
@Component
public class DubboGlobalFallbackHandler {

    @PostConstruct
    public void init() {
        // 自定义统一的降级策略 todo
        DubboAdapterGlobalConfig.setProviderFallback(((invoker, invocation, ex) -> {
            ex.printStackTrace();
            return AsyncRpcResult.newDefaultAsyncResult(new RuntimeException("user-server 服务器处理不过来啦"), invocation);
        }));
    }


}
