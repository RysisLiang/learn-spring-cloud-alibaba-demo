package com.example.userserver.service.impl;

import com.example.common.api.UserService;
import com.example.common.entity.User;
import com.example.userserver.dao.UserDao;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * UserService
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/6 15:08
 */
@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User save(User user) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 UserServiceImpl.save:name=" + user.getName());
        return userDao.save(user);
    }

    @Override
    public List<User> getAll() {
        System.out.println(LocalDateTime.now() + " - 1.0.0 UserServiceImpl.getAll");
        return (List<User>) userDao.findAll();
    }

    @Override
    public User findOneById(Long id) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 UserServiceImpl.findOneById:id=" + id);
        Optional<User> optional = userDao.findById(id);
        Optional<User> optional1 = optional.map(i -> {
            i.setAddress("user-server-0");
            return i;
        });
        return optional1.orElse(null);
    }

    @Override
    public User findOneByUsernameAndPwd(String username, String pwd) {
        System.out.println(LocalDateTime.now() + " - 1.0.0 UserServiceImpl.findOneById:username=" + username);
        Optional<User> optional = userDao.findOneByUsernameAndPwd(username, pwd);
        Optional<User> optional1 = optional.map(i -> {
            i.setAddress("user-server-0");
            return i;
        });
        return optional1.orElse(null);
    }
}
