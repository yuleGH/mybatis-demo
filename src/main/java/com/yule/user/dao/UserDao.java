package com.yule.user.dao;

import com.yule.user.entity.User;

import java.util.List;

/**
 * 用户 Dao 层
 *
 * @author yule
 * @date 2018/8/6 22:06
 */
public interface UserDao {
    /**
     * 查询所有的用户
     * @return
     */
    List<User> queryUserList();

    User queryUserByName(String name);
}
