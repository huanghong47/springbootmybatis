package cn.hhfarcry.springbootmybatis.common.base.entity;

import cn.hhfarcry.springbootmybatis.common.base.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @program: springbootmybatis
 * @description: 常用字段
 * @author: huanghong
 * @date: 2019-01-10 10:27
 */
public class BaseEntity extends PageVO {

    /**
     * 主键采用自增id还是uuid根据业务场景而定
     */
    @Setter@Getter
    private Integer id;

    /**
     * 1:有效数据;-1:无效/被删除的数据
     */
    @Setter@Getter
    private Integer isdeleted;

    /**
     * 备注
     */
    @Setter@Getter
    private String comment;

    /**
     * 创建人id
     */
    @Setter@Getter
    private Integer createuserid;

    /**
     * 创建时间
     */
    @Setter@Getter
    private Date createtime;

    /**
     * 修改人id
     */
    @Setter@Getter
    private Integer updateuserid;

    /**
     * 修改时间
     */
    @Setter@Getter
    private Date  updatetime;

}
