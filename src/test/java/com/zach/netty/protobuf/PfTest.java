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
}
