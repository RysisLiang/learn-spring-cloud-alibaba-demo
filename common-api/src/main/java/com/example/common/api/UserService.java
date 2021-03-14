package com.example.common.api;

import com.example.common.entity.User;

/**
 * UserService
 * 用于定义用户的服务API
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/5 14:46
 */
public interface UserService extends CRUDService<User> {

    /**
     * 根据用户名和密码查询账户信息
     *
     * @param username 用户名
     * @param pwd 密码
     * @return
     */
    User findOneByUsernameAndPwd(String username, String pwd);

}
