package com.example.order.rpc.provider;

import com.example.common.framework.rpc.RPCResult;
import com.example.order.dao.IPayOrderDao;
import com.example.order.entity.PayOrder;
import com.example.order.rpc.IPayOrderRPC;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * PayOrderProvider
 * 用于提供支付订单服务的类
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/6 15:08
 */
@RestController
public class PayOrderProvider implements IPayOrderRPC {

    @Resource
    private IPayOrderDao payOrderDao;

    @Override
    public RPCResult<PayOrder> save(PayOrder payOrder) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 PayOrderProvider.save:" + payOrder);
        PayOrder data = payOrderDao.save(payOrder);
        return RPCResult.buildSuccess(data);
    }

    @Override
    public RPCResult<PayOrder> findOneById(Long id) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 PayOrderProvider.findOneById:" + id);
        Optional<PayOrder> data = payOrderDao.findById(id);
        return RPCResult.buildSuccess(data.orElse(null));
    }

    @Override
    public RPCResult<Integer> updateByAccountId(Long id, Integer payAmount) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 PayOrderProvider.save:" + id + "-" + payAmount);
        Integer data = payOrderDao.updateByAccountId(id, payAmount);
        return RPCResult.buildSuccess(data);
    }

    @Override
    public RPCResult<List<PayOrder>> getAll() {
        System.out.println(LocalDateTime.now() + " - 1.0.0 PayOrderProvider.getAll:");
        return RPCResult.buildSuccess(payOrderDao.findAll());
    }
}
