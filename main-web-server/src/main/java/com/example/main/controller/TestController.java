package com.example.main.controller;

import com.example.account.entity.Account;
import com.example.main.service.TestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

/**
 * TestController
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 15:11
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping(value = "/status")
    public Object status() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "ok");
        return map;
    }

    /**
     * 注册
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/sign-up")
    public Object signUp(Long id) {
        System.out.println(LocalDateTime.now() + " - LoginController.signUp:id=" + id);
        int r = new Random().nextInt(20);
        Account account = new Account();
        account.setUsername("hahaR" + r);
        account.setPassword("123456");
        account.setName("李雷" + r);
        account.setAge(r);
        account.setAddress("测试数据地址" + r);
        account.setCreateTime(LocalDateTime.now());
        return testService.signUp(account);
    }

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @GetMapping(value = "/sign-in")
    public Object signIn(String userName, String password) {
        System.out.printf("%s - LoginController.signIn:userName=%s, password=%s %n", LocalDateTime.now(), userName, password);
        return testService.signIn(userName, password);
    }

    @GetMapping(value = "/findAll")
    public Object findAll() {
        System.out.printf("%s - LoginController.findAll %n", LocalDateTime.now());
        return testService.findALl();
    }

    @GetMapping(value = "/find/{id}")
    public Object findById(@PathVariable("id") Long id) {
        System.out.printf("%s - LoginController.findById:id=%d %n", LocalDateTime.now(), id);
        return testService.findById(id);
    }


}
