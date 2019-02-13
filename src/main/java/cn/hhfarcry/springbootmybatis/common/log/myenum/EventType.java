package cn.hhfarcry.springbootmybatis.common.log.myenum;

import java.sql.SQLOutput;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-18 10:31
 */
public enum  EventType {
    DEFAULT("默认", "default"), ADD("新增", "add"), UPDATE("修改", "update"), DELETE_SINGLE("删除", "delete"),
    LOGIN("登录","login"),LOGIN_OUT("注销","login_out");

    private EventType(String index, String name){
        this.name = name;
        this.event = index;
    }
    private String event;
    private String name;
    public String getEvent(){
        return this.event;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        System.out.println(EventType.LOGIN.getEvent());
    }

}
