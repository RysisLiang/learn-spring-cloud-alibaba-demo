package com.example.main.controller;

import com.example.common.entity.User;
import com.example.main.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

/**
 * UserController
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 15:11
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @GetMapping(value = "/status")
    public String status() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "ok");
        return "ok";
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
        User user = new User();
        user.setUserName("abc123");
        user.setPassword("123456");
        user.setName("李雷" + r);
        user.setAge(r);
        user.setAddress("测试数据地址" + r);
        user.setCreateTime(LocalDateTime.now());
        return loginService.signUp(user);
    }

    /**
     * 登录
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/sign-in")
    public Object signIn(Long id) {
        System.out.println(LocalDateTime.now() + " - LoginController.signIn:id=" + id);
        return loginService.signIn(id);
    }

    @GetMapping(value = "/findAll")
    public Object findAll() {
        System.out.println(LocalDateTime.now() + " - LoginController.findAll:");
        return loginService.findALl();
    }


}
