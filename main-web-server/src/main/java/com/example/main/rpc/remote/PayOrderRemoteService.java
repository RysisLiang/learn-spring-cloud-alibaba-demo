package com.example.main.rpc.remote;

import com.example.main.rpc.fallback.PayOrderRemoteServiceFallback;
import com.example.order.rpc.IPayOrderRPC;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * PayOrderRemoteService
 * 用于定义支付订单服务的API-远程实现
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 14:46
 */
@FeignClient(value = "order-server", fallback = PayOrderRemoteServiceFallback.class)
public interface PayOrderRemoteService extends IPayOrderRPC {

}
