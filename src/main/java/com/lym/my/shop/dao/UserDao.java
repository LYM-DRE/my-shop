package com.lym.my.shop.dao;

import com.lym.my.shop.entity.User;

/**数据库
 * @author liuyumeng3
 * @date 2019/11/26 19:10
 */

public interface UserDao {
    /**
     * 根据邮箱和密码获取用户信息
     * @param email
     * @param password
     * @return  用户
     */
    //登录，访问数据库，获得email和password
    //方法名不要太长  getUserByEmailAndPassword
    //通过get知道获取一个对象
    public User getUser(String email, String password);
}
