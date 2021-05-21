package com.example.order.rpc;

import com.example.common.framework.service.CRUDService;
import com.example.order.entity.PayOrder;

/**
 * PayOrderService
 * 支付订单API
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 14:46
 */
public interface PayOrderService extends CRUDService<PayOrder> {

    /**
     * 根据订单id查询订单并更新支付金额
     *
     * @param orderId 订单id
     * @param payAmount 支付金额
     * @return
     */
    PayOrder updateByAccountId(Integer orderId, Integer payAmount);

}
