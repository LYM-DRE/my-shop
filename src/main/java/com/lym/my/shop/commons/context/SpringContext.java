package com.lym.my.shop.commons.context;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author liuyumeng3
 * @date 2019/11/26 19:33
 */

//不适合被继承  final
    //spring启动时，单例
    //实现ApplicationContextAware, DisposableBean 这两个接口
@Component
public  class SpringContext implements ApplicationContextAware, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(SpringContext.class);

    private static ApplicationContext applicationContext;
    //applicationContext 是静态属性，不依赖对象，所以this. 点不出applicationContext

    /**
     * 获取存储在静态变量中的 ApplicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 从静态变量 applicationContext 中获取 Bean，自动转型成所赋值对象的类型
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name){
        //断言SpringContext被注入进来了
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 使用ApplicationContext，根据class获取实例
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        //断言SpringContext被注入进来了
        assertContextInjected();
        return applicationContext.getBean(clazz);
    }

    //销毁
    public void destroy() throws Exception {
        logger.debug("清空 ApplicationContext",applicationContext);
        applicationContext = null;
    }

    //applicationContext 是静态属性，不依赖对象，所以this. 点不出applicationContext
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;

    }

    // 断言 Context 已经注入
    //引用一个  Commons-lang 3
     private static void assertContextInjected(){
         Validate.validState(applicationContext != null,"你还没有在 spring-context.xml中配置SpringContext 对象");
     }
}
