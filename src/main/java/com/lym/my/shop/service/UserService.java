package com.lym.my.shop.service;

import com.lym.my.shop.entity.User;

/**业务逻辑
 * @author liuyumeng3
 * @date 2019/11/26 19:25
 */

public interface UserService {
    /**
     * 登录  调用数据访问层的UserDao【根据邮箱和密码获取用户信息】
     * @param email
     * @param password
     * @return
     */
    //业务逻辑
    public User login(String email, String password);
}
