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

    private String name;
    private int age;
    private String address;

    public User() {
    }

    public User(Long id, String name, int age, String address) {
        super.setId(id);
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
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
