package com.example.main.rpc.fallback;


import com.example.common.framework.rpc.RPCResult;
import com.example.main.rpc.remote.PayOrderRemoteService;
import com.example.order.entity.PayOrder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * PayOrderRemoteServiceFallback
 * 默认的失败接口
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/21 23:04
 */
@Component
public class PayOrderRemoteServiceFallback implements PayOrderRemoteService {


    @Override
    public RPCResult<PayOrder> save(PayOrder payOrder) {
        return null;
    }

    @Override
    public RPCResult<PayOrder> findOneById(Long id) {
        return null;
    }

    @Override
    public RPCResult<Integer> updateByAccountId(Long id, Integer payAmount) {
        return null;
    }

    @Override
    public RPCResult<List<PayOrder>> getAll() {
        return null;
    }
}
