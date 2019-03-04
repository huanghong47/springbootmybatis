package cn.hhfarcry.springbootmybatis.common.loginstorage.mymanager;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-18 10:42
 */
public class LogInfoEntity {

    @Setter@Getter
    private Integer id;
    @Setter@Getter
    private String userId; // 操作用户
    @Setter@Getter
    private String userName;
    @Setter@Getter
    private String ip;//请求ip
    @Setter@Getter
    private String opModel; // 操作模块
    @Setter@Getter
    private String opEvent; // 操作时间
    @Setter@Getter
    private Date createDate; // 操作时间
    @Setter@Getter
    private String opContent; // 操作内容
    @Setter@Getter
    private String description; // 操作描述
    @Setter@Getter
    private String opResult; // 操作结果

}
