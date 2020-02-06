package com.lym.my.shop.web.interceptor;

import com.lym.my.shop.commons.constant.ConstantUtils;
import com.lym.my.shop.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author liuyumeng3
 * @date 2020/2/5 11:52
 */

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("LoginInterceptor");

        User user = (User) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);

        //未登录
        if(user == null){
            httpServletResponse.sendRedirect("/login");
        }


        //旅行
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println(modelAndView.getViewName());
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
