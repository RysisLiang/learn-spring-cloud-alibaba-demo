package com.example.order.dao;

import com.example.order.entity.PayOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * IPayOrderDao
 *
 * @author liangxiao
 * @version 1.00
 * @Date 2020/9/8 17:51
 */
@Repository
public interface IPayOrderDao extends JpaRepository<PayOrder, Long> {

    /**
     * 根据订单id查询订单并更新支付金额
     *
     * @param orderId 订单id
     * @param payAmount 支付金额
     * @return
     */
    @Modifying
    @Query("UPDATE PayOrder p SET p.payAmount = :payAmount WHERE p.id = :orderId")
    int updateByAccountId(@Param("orderId") Long orderId, @Param("payAmount") Integer payAmount);

}
