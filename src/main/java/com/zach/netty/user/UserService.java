package com.zach.netty.user;

import com.zach.netty.protobuf.PfClient;
import com.zach.netty.protobuf.RequestMsgProtoBuf;
import com.zach.netty.protobuf.UserProBuf;

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
        UserProBuf.User returnUser = (UserProBuf.User)PfClient.startClient(requestMsg);
        System.out.println(returnUser);



    }
}
