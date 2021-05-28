package com.example.main.service;

import com.example.account.entity.Account;
import com.example.account.rpc.IAccountRPC;
import com.example.common.framework.rpc.RPCResult;
import com.example.order.entity.PayOrder;
import com.example.order.rpc.IPayOrderRPC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * BusinessService
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/5/28 15:35
 */
@Service
public class BusinessService {

    @Resource
    private IAccountRPC accountRPC;
    @Resource
    private IPayOrderRPC payOrderRPC;

    /**
     * 采购
     *
     * @param userId 用户主键
     * @param commodityCode 商品编码
     * @param orderCount 订单数目
     */
    public void purchase(Long userId, String commodityCode, int orderCount) {
        // 1. 用户余额借出
        RPCResult<Account> accountRPCResult = accountRPC.debitById(userId, orderCount);
        // 账户信息
        Account account = accountRPCResult.getResult();

        // 2. 创建支付订单
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderId(LocalDateTime.now().toString());
        payOrder.setOrderTitle("GoodId-" + commodityCode);
        payOrder.setAccountId(account.getId());
        payOrder.setUsername(account.getUsername());
        payOrder.setPayAmount(orderCount);
        payOrder.setCreateTime(LocalDateTime.now());
        payOrderRPC.save(payOrder);
    }
}
