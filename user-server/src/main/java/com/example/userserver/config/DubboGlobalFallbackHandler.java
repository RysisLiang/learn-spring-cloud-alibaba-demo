package com.example.userserver.config;

import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * DubboGlobalFallbackHandler
 * sentinel 实现 dubbo filter BlockException异常执行定义
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/23 17:20
 */
@Component
public class DubboGlobalFallbackHandler {

    /**
     * - com.alibaba.csp.sentinel.slots.block.degrade.DegradeException - sentinel熔断异常
     * - com.alibaba.csp.sentinel.slots.block.flow.FlowException - sentinel降级异常
     */
    @PostConstruct
    public void init() {
        // 自定义统一的降级策略
        DubboAdapterGlobalConfig.setProviderFallback(((invoker, invocation, ex) -> {
            ex.printStackTrace();
            if (ex instanceof DegradeException) { // 熔断异常的返回处理
                // 用于sentinel记录异常数据
                Tracer.trace(ex);
                // 抛出dubbo - RpcException 触发dubbo的重试机制
                throw new RpcException("user-server-0 服务器熔断处理");
            } else if (ex instanceof FlowException) { // 降级异常的返回处理
                // 返回一个限流的值
                return AsyncRpcResult.newDefaultAsyncResult(new RuntimeException("user-server-0 服务器处理不过来啦"), invocation);
            }
            // 默认的处理
            return AsyncRpcResult.newDefaultAsyncResult(new RuntimeException("user-server-0 服务器未知异常"), invocation);
        }));
    }


}
