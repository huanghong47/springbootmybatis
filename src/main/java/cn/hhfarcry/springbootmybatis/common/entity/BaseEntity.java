package cn.hhfarcry.springbootmybatis.common.entity;

import cn.hhfarcry.springbootmybatis.common.vo.PageVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-10 10:27
 */
public class BaseEntity extends PageVO {

    /**
     * 主键采用自增id还是uuid根据业务场景而定
     */
    @Setter@Getter
    private Integer id;

    @Setter@Getter
    private String uuid;

    //(0:有效数据  -1:无效/被删除的数据)
    @Setter@Getter
    private int isDeleted;

    //(0:可见 -q)
    @Setter@Getter
    private Integer status;

    @Setter@Getter
    private String comment;

    @Setter@Getter
    private String createUserUuid;

    @Setter@Getter
    private Date createTime;

    @Setter@Getter
    private String updateUserUuid;


    @Setter@Getter
    private Date  updateTime;

}
