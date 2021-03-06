package com.zach.netty.user;

import com.hzins.thrift.demo.Content;
import com.zach.netty.media.Remote;
import com.zach.netty.protobuf.EmailProBuf;
import com.zach.netty.protobuf.ResponseMsgProtoBuf;
import com.zach.netty.protobuf.UserProBuf;
import com.zach.utils.JsonUtils;
import org.springframework.stereotype.Controller;

import java.util.Map;

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
        EmailProBuf.Email.Builder email = EmailProBuf.Email.newBuilder().setContent("test").setFromUser("zhangsan").setId(12).setSubject("test");  //这里fromUser只是他的一个属性

        //以为在定义的时候， response是byteString，所以在这里直接就是byteString。
        ResponseMsgProtoBuf.ResponseMsg response = ResponseMsgProtoBuf.ResponseMsg.newBuilder().setResponse(email.build().toByteString()).build();

        return response;
    }

    @Remote("httpGetEmailByUser")
    public Object httpGetEmailByUser(Object email) {

        Map<String, Object> map = JsonUtils.jsonToBean(email.toString(), Map.class);

        email = email.toString() + "hhhh";

        return email;
    }

    @Remote("ThriftGetEmailByContent")
    public Object getEmail(Content content){
        System.out.println(content.getPhone());
        content.setId(1);
        content.setPhone("15626519062");
        content.setIdIsSet(true);
        return content;
    }
}
