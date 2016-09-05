package com.zach.netty.user;

import com.google.protobuf.ByteString;
import com.zach.netty.protobuf.*;

/**
 * Created by Administrator on 2016-9-3.
 */
public class UserService {
    public void save() throws Exception {
        //创建user
        UserProBuf.User.Builder user = UserProBuf.User.newBuilder();
        user.setId(1);
        user.setPhonie("13800000000");
        user.setUserName("xiaoming");

        //创建消息
        RequestMsgProtoBuf.RequestMsg.Builder requestMsg = RequestMsgProtoBuf.RequestMsg.newBuilder();
        requestMsg.setCmd("saveUser");
        requestMsg.setRequestParam(user.build().toByteString());


        //调用client
        ResponseMsgProtoBuf.ResponseMsg response = (ResponseMsgProtoBuf.ResponseMsg)PfClient.startClient(requestMsg);
        System.out.println(response);



    }

    public ResponseMsgProtoBuf.ResponseMsg getEmail() throws Exception {
        UserProBuf.User.Builder user = UserProBuf.User.newBuilder();
        user.setId(1);
        user.setPhonie("13800000000");
        user.setUserName("xiaoming");


        //放进一个email去
        EmailProBuf.Email.Builder email = EmailProBuf.Email.newBuilder();
        email.setId(1);
        email.setFromUser("张三");
        email.setSubject("test");
        email.setContent("test content");

        //创建消息
        RequestMsgProtoBuf.RequestMsg.Builder requestMsg = RequestMsgProtoBuf.RequestMsg.newBuilder();
        requestMsg.setCmd("saveUser");
        requestMsg.setRequestParam(email.build().toByteString());

        ResponseMsgProtoBuf.ResponseMsg responseMsg = PfClient.startClient(requestMsg);

        return responseMsg;
    }

}
