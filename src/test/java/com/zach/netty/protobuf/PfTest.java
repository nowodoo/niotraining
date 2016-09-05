package com.zach.netty.protobuf;

import com.zach.netty.user.UserService;
import org.junit.Test;

/**
 * Created by Administrator on 2016-9-3.
 */
public class PfTest {
    @Test
    public void save() throws Exception {
        UserService userService = new UserService();
        userService.save();
    }

    @Test
    public void getEmail() throws Exception {
        UserService userService = new UserService();
        ResponseMsgProtoBuf.ResponseMsg responseMsg = userService.getEmail();

        EmailProBuf.Email email = EmailProBuf.Email.parseFrom(responseMsg.getResponse());

        System.out.println(email);

    }

}
