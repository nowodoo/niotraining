package com.zach.netty.media;

import com.google.protobuf.ByteString;
import com.zach.netty.protobuf.RequestMsgProtoBuf;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Media {
	
	public static Map<String,MethodBean> methodBeans = new HashMap<String,MethodBean>();

    /**
     * 主要的执行方法，按照参数找到相应的方法去执行
     */
	public static Object execute(RequestMsgProtoBuf.RequestMsg requestMsg) {
        Object response = null;
        try {
            //首先初始化上面的map，then the code blow will work.
            String cmd = requestMsg.getCmd();
            MethodBean methodBean = methodBeans.get(cmd);
            Method method = methodBean.getMethod();
            Object bean = methodBean.getBean();

            //获取目标方法的参数类型
            Class parameterType = method.getParameterTypes()[0];
            //找到目标方法的所有的构造方法
            Constructor[] constructors = parameterType.getConstructors();
            Constructor c = null;  //获取这个类的构造方法，然后刁颖newInstance方法就可以构造这个类
            //找到所有的构造器
            for (Constructor constructor : constructors) {
                if (constructor.getParameterTypes()[0].getName().equals("boolean")) {   //因为有一个构造器的参数是boolean的，所以使用这种方法找到就好了，自己可以debug一下就好了
                    c = constructor;
                }
            }

            if (c != null) {
                c.setAccessible(true);
            }
            //初始化参数,然而这个参数是通过requestMsg传递进来的
            Object parameterObj = c.newInstance(true);
            ByteString requestParam = requestMsg.getRequestParam();    //在这里我们看一下UserController的类型，是一个protobuffer对象。
            Method parameterMethod = parameterType.getMethod("parseFrom", ByteString.class);   //在这里只是调用了parseFrom方法
            parameterObj = parameterMethod.invoke(parameterObj, requestParam); //这个就是返回来的对象了
            response = method.invoke(bean, parameterObj); //这个才是最终的调用的业务逻辑方法
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
