package com.lym.my.shop.dao.impl;

import com.lym.my.shop.dao.UserDao;
import com.lym.my.shop.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


//实现UserDao接口
/**
 * @author liuyumeng3
 * @date 2019/11/26 19:20
 */
@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public User getUser(String email, String password) {

        //埋点
        logger.debug("调用getUser(),email:{} password:{}", email,password);


        //这个是类
        User user = null;

        if("admin@lym.com".equals(email)){
            if ("admin".equals(password)){
                user = new User();
                //造数据
                user.setEmail("admin@lym.com");
                user.setUsername("LYM");

                logger.info("成功获取\"{}\"的用户信息",user.getUsername());
            }
        }else {
            logger.warn("获取\"{}\"的用户信息失败",email);
        }
        return user ;
    }
}
