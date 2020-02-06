package com.lym.my.shop.web.interceptor;

import com.lym.my.shop.commons.constant.ConstantUtils;
import com.lym.my.shop.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuyumeng3
 * @date 2020/2/5 14:36
 */

public class PermissionInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
       //以login结尾的请求
        if(modelAndView.getViewName().endsWith("login")){
            User user = (User) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
            if(user != null){
                httpServletResponse.sendRedirect("/main");
            }
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
