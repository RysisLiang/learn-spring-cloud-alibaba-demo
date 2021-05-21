package com.example.order.entity;

import com.example.common.framework.entity.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * PayOrder
 * 支付订单
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/5/21 14:46
 */
@Entity
@Table(name = "t_pay_order")
public class PayOrder extends BaseModel implements Serializable {
    /**
     * 账户id
     */
    private Integer accountId;
    /**
     * 账户名称
     */
    private String username;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单标题
     */
    private String orderTitle;
    /**
     * 支付金额
     */
    private Integer payAmount;

    @Override
    public String toString() {
        return "PayOrder{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderTitle='" + orderTitle + '\'' +
                ", payAmount=" + payAmount +
                '}';
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }
}
