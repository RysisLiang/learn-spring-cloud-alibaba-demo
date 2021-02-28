package com.example.main.service;

import com.example.common.api.UserService;
import com.example.common.entity.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * LoginService
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 15:14
 */
@Service
public class LoginService {

    @DubboReference(loadbalance = "roundrobin", version = "1.0.0")
    private UserService userService;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    public Optional<User> signUp(User user) {
        User save = userService.save(user);
        return Optional.ofNullable(save);
    }

    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     */
    public Optional<User> signIn(Long id) {
        User user = userService.findOneById(id);
        return Optional.ofNullable(user);
    }

    /**
     * 查询全部用户
     *
     * @return
     */
    public List<User> findALl() {
        List<User> all = null;
        try {
            all = userService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }
}
