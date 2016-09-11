package com.zach.netty.media;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2016-9-4.
 */
@Component
public class InitMedia implements ApplicationListener<ContextRefreshedEvent>,Ordered {

    /**
     *  在容器启动的时候将所有的bean放在一个map里面
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //根据相应的注解获取bean，获取controlelr
        Map<String, Object> beanMap = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(Controller.class);

        for (String key : beanMap.keySet()) {
            Object bean = beanMap.get(key);
            Method[] methods = bean.getClass().getDeclaredMethods();
            if (methods == null || methods.length == 0) {
                continue;
            }

            for (Method m : methods) {
                if (m.isAnnotationPresent(Remote.class)) {
                    Remote r = m.getAnnotation(Remote.class);
                    String cmd = r.value();
                    MethodBean methodBean = new MethodBean();
                    methodBean.setBean(bean);
                    methodBean.setMethod(m);
                    Media.methodBeans.put(cmd, methodBean);
                    System.out.println("cmd===" + cmd);
                }
            }
        }
    }

    /**
     * 这个必须要首先进行初始化，因为需要往全局的map里面添加方法
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
