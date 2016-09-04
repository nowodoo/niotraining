package com.zach.netty.user;

import com.zach.netty.media.Remote;
import com.zach.netty.protobuf.EmailProBuf;
import com.zach.netty.protobuf.UserProBuf;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2016-9-4.
 */
@Controller
public class UserController {
    @Remote("saveUser")
    public void saveUser(){

    }

    @Remote("getEmailByUser")
    public Object getEmailByUser(UserProBuf.User user) {
        EmailProBuf.Email.Builder email = EmailProBuf.Email.newBuilder().setContent("test").setFromUser("zhangsan").setId(12);  //这里fromUser只是他的一个属性

        return email.build();
    }
}
