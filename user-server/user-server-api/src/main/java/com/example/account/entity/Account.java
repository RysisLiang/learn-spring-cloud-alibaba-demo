package com.example.account.entity;

import com.example.common.framework.entity.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Account
 * 用户实体类
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 14:46
 */
@Entity
@Table(name = "t_account")
public class Account extends BaseModel implements Serializable {

    /**
     * 账户名称
     */
    private String username;
    /**
     * 账户密码
     */
    private String password;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 账户余额
     * - 单位是：分
     */
    private Integer balance;

    public Account() {
    }

    public Account(String username, String password, String name, Integer age, String address, Integer balance) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.address = address;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
