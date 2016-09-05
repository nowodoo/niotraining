package com.zach.netty.http;

/**
 * Created by Administrator on 2016-9-5.
 */
public class RequestParam {
    private String command;
    private Object parameter;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
}
