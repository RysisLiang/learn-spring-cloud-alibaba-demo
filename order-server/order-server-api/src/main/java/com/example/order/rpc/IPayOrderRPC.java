package com.example.order.rpc;

import com.example.common.framework.rpc.RPCResult;
import com.example.order.entity.PayOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * IPayOrderRPC
 * 用于定义支付订单服务的API接口
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 14:46
 */
@RequestMapping(value = "/v1/api/pay-order")
public interface IPayOrderRPC {

    /**
     * 新增支付订单
     *
     * @param payOrder 支付订单
     * @return
     */
    @PostMapping("")
    RPCResult<PayOrder> save(@RequestBody PayOrder payOrder);

    /**
     * 根据指定id查询账户
     *
     * @param id 主键id
     * @return
     */
    @GetMapping("/{id}")
    RPCResult<PayOrder> findOneById(@PathVariable("id") Long id);

    /**
     * 根据订单id查询订单并更新支付金额
     *
     * @param id 主键id
     * @param payAmount 支付金额
     * @return
     */
    @PutMapping("/{id}")
    RPCResult<Integer> updateByAccountId(@PathVariable("id") Integer id,
                                         @RequestParam("payAmount") Integer payAmount);

    /**
     * 获取全部订单
     *
     * @return
     */
    @GetMapping("")
    RPCResult<List<PayOrder>> getAll();

}
