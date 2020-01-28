package com.lym.my.shop.web.controller;

import com.lym.my.shop.commons.context.SpringContext;
import com.lym.my.shop.commons.utils.CookieUtils;
import com.lym.my.shop.entity.User;
import com.lym.my.shop.service.UserService;
import com.lym.my.shop.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

/**
 * @author liuyumeng3
 * @date 2019/11/26 19:41
 */

public class LoginController extends HttpServlet {

    //写且只写一次，封装cookieName
    private static  final String COOKIE_NAME_USER_INFO = "userInfo";


    //去spring-context.xml中注入Service，<bean id = "userService" class = "com.lym.my.shop.service.impl.UserServiceImpl"/> 将控制权交给Spring，控制反转开始了
    private UserService userService = SpringContext.getBean("userService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userInfo = CookieUtils.getCookieValue(req, COOKIE_NAME_USER_INFO );

        if (!StringUtils.isBlank(userInfo)){
            String[] userInfoArray = userInfo.split(":");
            String email = userInfoArray[0];
            String password = userInfoArray[1];
            req.setAttribute("email",email);
            req.setAttribute("password",password);
            req.setAttribute("isRemember",true);
        }


        System.out.println(userInfo);
        req.getRequestDispatcher("/login.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean isRemember = req.getParameter("isRemember") == null ? false : true;

        //用户选择不记住
        if (!isRemember){
            CookieUtils.deleteCookie(req,resp,COOKIE_NAME_USER_INFO);
        }

//        String  isRemember = req.getParameter("isRemember");


//        SpringContext context = new SpringContext();
//        UserService userService = (UserService) context.getBean("userService");
        User admin = userService.login(email,password);

        //登录成功
        if(admin != null){
            if(isRemember){
                //用户信息存储一周
                CookieUtils.setCookie(req,resp,COOKIE_NAME_USER_INFO ,String.format("%s:%s",email,password),7*24*60*60);
            }

            resp.sendRedirect("/main.jsp");
        }
        //登录失败
        else{
            req.setAttribute("message","用户名或密码错误 ");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }

    }
}
