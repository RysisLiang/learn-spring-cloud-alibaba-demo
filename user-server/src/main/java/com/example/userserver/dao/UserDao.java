package com.example.userserver.dao;

import com.example.common.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserDao
 *
 * @author liangxiao
 * @version 1.00
 * @Date 2020/9/8 17:51
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {

//    private static final List<User> USER_LIST;
//
//    static {
//        User user1 = new User(1L, "王小明", 25, "上海市");
//        User user2 = new User(2L, "韩梅梅", 20, "广东省广州市");
//        User user3 = new User(3L, "韩梅梅", 20, "广东省广州市");
//        User user4 = new User(4L, "韩梅梅", 20, "广东省广州市");
//        User user5 = new User(5L, "韩梅梅", 20, "广东省广州市");
//        User user6 = new User(6L, "韩梅梅", 20, "广东省广州市");
//        User user7 = new User(7L, "韩梅梅", 20, "广东省广州市");
//        User user8 = new User(8L, "韩梅梅", 20, "广东省广州市");
//        User user9 = new User(9L, "韩梅梅", 20, "广东省广州市");
//        User user10 = new User(10L, "韩梅梅", 20, "广东省广州市");
//        USER_LIST = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
//    }

//    public List<User> findAll() {
//        return USER_LIST;
//    }
//
//    public User findOneById(Long id) {
//        return USER_LIST.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
//    }
}
