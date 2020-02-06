package com.lym.my.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liuyumeng3
 * @date 2020/2/4 22:45
 */
@Controller
public class MainController {

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }
}
