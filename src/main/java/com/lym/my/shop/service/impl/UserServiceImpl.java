package com.lym.my.shop.service.impl;

import com.lym.my.shop.commons.context.SpringContext;
import com.lym.my.shop.dao.UserDao;
import com.lym.my.shop.entity.User;
import com.lym.my.shop.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author liuyumeng3
 * @date 2019/11/26 19:28
 */
@Service
public class UserServiceImpl implements UserService {
//逻辑错误
    //所想非所得
//去spring-context.xml中注入Dao，<bean id = "userDao" class = "com.lym.my.shop.dao.impl.UserDaoImpl"/>  将控制权交给Spring，控制反转开始了
    private UserDao userDao = SpringContext.getBean("userDao");

    public User login(String email, String password) {
//        SpringContext context = new SpringContext();
//        UserDao userDao = (UserDao) context.getBean("userDao");
        return userDao.getUser(email,password);
    }
}
