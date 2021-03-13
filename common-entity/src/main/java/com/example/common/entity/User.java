package com.example.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User
 * 用户实体类
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 14:46
 */
@Entity
@Table(name = "t_user")
public class User extends BaseModule implements Serializable {

    /**
     * 账户名称
     */
    private String userName;
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
    private int age;
    /**
     * 用户地址
     */
    private String address;

    public User() {
    }

    public User(String userName, String password, String name, int age, String address) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
