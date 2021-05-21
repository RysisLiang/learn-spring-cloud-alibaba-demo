package com.example.order.rpc.impl;

import com.example.order.dao.IPayOrderDao;
import com.example.order.entity.PayOrder;
import com.example.order.rpc.PayOrderService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * PayOrderServiceImpl
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/6 15:08
 */
@DubboService(version = "1.0.0")
public class PayOrderServiceImpl implements PayOrderService {

    @Resource
    private IPayOrderDao payOrderDao;


    @Override
    public PayOrder updateByAccountId(Integer orderId, Integer payAmount) {
        return null;
    }

    @Override
    public PayOrder save(PayOrder payOrder) {
        return payOrderDao.save(payOrder);
    }

    @Override
    public List<PayOrder> getAll() {
        return payOrderDao.findAll();
    }

    @Override
    public PayOrder findOneById(Long id) {
        return payOrderDao.findById(id).orElse(null);
    }
}
