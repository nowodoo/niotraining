package com.zach.netty.user;

import com.zach.netty.media.Remote;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2016-9-4.
 */
@Controller
public class UserController {
    @Remote("saveUser")
    public void saveUser(){

    }
}
