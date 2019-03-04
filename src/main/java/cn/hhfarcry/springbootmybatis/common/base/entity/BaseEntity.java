package cn.hhfarcry.springbootmybatis.common.base.entity;

import cn.hhfarcry.springbootmybatis.common.base.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value="主键id",name="id",example="1")
    @Setter@Getter
    private Integer id;

    @ApiModelProperty(value="uuid",name="uuid",example="1")
    @Setter@Getter
    private String uuid;

    @ApiModelProperty(value="0:有效数据;-1:无效/被删除的数据",name="isDeleted",example="-1")
    @Setter@Getter
    private int isDeleted;

    @ApiModelProperty(value="状态",name="status",example="0")
    @Setter@Getter
    private Integer status;

    @ApiModelProperty(value="备注",name="comment",example="xxx")
    @Setter@Getter
    private String comment;

    @ApiModelProperty(value="创建人id",name="createUserUuid",example="xxx")
    @Setter@Getter
    private String createUserUuid;

    @ApiModelProperty(value="创建时间",name="createTime",example="2018-01-01 00:00:00")
    @Setter@Getter
    private Date createTime;

    @ApiModelProperty(value="修改人id",name="createUserUuid",example="xxx")
    @Setter@Getter
    private String updateUserUuid;

    @ApiModelProperty(value="修改时间",name="createTime",example="2018-01-01 00:00:00")
    @Setter@Getter
    private Date  updateTime;

}
