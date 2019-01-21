package cn.hhfarcry.springbootmybatis.common.log.myenum;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-18 10:31
 */
public enum  ModuleType {
    DEFAULT("默认模块"), // 默认值
    USER("用户模块"),// 用户模块
    XXX("xxx模块"); //XXX模块

    private ModuleType(String index){
        this.module = index;
    }

    private String module;

    public String getModule(){
        return this.module;
    }
}
