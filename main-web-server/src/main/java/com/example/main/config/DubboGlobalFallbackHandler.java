package com.example.main.config;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreaker;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.EventObserverRegistry;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.util.TimeUtil;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * DubboGlobalFallbackHandler
 * sentinel 实现 dubbo filter BlockException异常执行定义
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/23 17:20
 */
@Configuration
public class DubboGlobalFallbackHandler {

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig config = new ConsumerConfig();
        config.setFilter("sentinel.dubbo.my.consumer,default,-sentinel.dubbo.my.consumer");
        return config;
    }

    /**
     * - com.alibaba.csp.sentinel.slots.block.degrade.DegradeException - sentinel熔断异常
     * - com.alibaba.csp.sentinel.slots.block.flow.FlowException - sentinel降级异常
     */
    @PostConstruct
    public void init() {
        // 自定义统一的降级策略
        DubboAdapterGlobalConfig.setConsumerFallback(((invoker, invocation, ex) -> {
            System.err.println("DubboGlobalFallbackHandler = " + ex.getMessage());
            if (ex instanceof DegradeException) { // 熔断异常的返回处理
                // 用于sentinel记录异常数据
//                Tracer.trace(ex);
//                // 抛出dubbo - RpcException 触发dubbo的重试机制
//                throw new RpcException("main-web-server-0 消费熔断处理");
                System.out.println("main-web-server-0 消费降级处理");

                return AsyncRpcResult.newDefaultAsyncResult(invocation);
            } else if (ex instanceof FlowException) { // 降级异常的返回处理
                // 返回一个限流的值
                return AsyncRpcResult.newDefaultAsyncResult(new RuntimeException("main-web-server-0 消费限流处理"), invocation);
            }
            // 默认的处理
            return AsyncRpcResult.newDefaultAsyncResult(new RuntimeException("main-web-server-0 消费未知异常"), invocation);
        }));

        // 熔断开关监听
        EventObserverRegistry.getInstance().addStateChangeObserver("logging",
                (prevState, newState, rule, snapshotValue) -> {
                    if (newState == CircuitBreaker.State.OPEN) {
                        // 变换至 OPEN state 时会携带触发时的值
                        System.err.printf("%s -> OPEN at %d, snapshotValue=%.2f%n", prevState.name(), TimeUtil.currentTimeMillis(), snapshotValue);
                    } else {
                        System.err.printf("%s -> %s at %d%n", prevState.name(), newState.name(), TimeUtil.currentTimeMillis());
                    }
                });
    }


}
