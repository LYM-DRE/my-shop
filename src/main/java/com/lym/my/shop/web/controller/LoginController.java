package com.lym.my.shop.web.controller;


import com.lym.my.shop.commons.constant.ConstantUtils;
import com.lym.my.shop.entity.User;
import com.lym.my.shop.service.UserService;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author liuyumeng3
 * @date 2019/11/26 19:41
 */

@Controller
public class LoginController  {

    //注入userservice
    @Autowired
    private UserService userService;

    /**
     * 跳转登录页面
     * @return
     */
    //方法
    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 登录逻辑
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest httpServletRequest){
        User user = userService.login(email, password);

        //登录失败
        if(user == null){
           return login();
        }
        //登陆成功
        else {
            //将登录信息放入会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,user);
            return "redirect:/main";
        }


    }

}
