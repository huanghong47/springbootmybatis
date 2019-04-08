package cn.hhfarcry.springbootmybatis.common.base.vo;

import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-10 11:03
 */
public class PageVO {
    public static final String PARAMNAME_FIELD = "field";
    public static final String PARAMNAME_ORDER = "order";
    public static final String PARAMNAME_PAGENUM = "pagenum";
    public static final String PARAMNAME_PAGESIZE = "pagesize";


    public static final String DEFAULT_FIELD = "create_time";
    public static final String DEFAULT_ORDER = "asc";
    public static final int DEFAULT_PAGENUM = 1;
    public static final int DEFAULT_PAGESIZE = 10;


    @ApiModelProperty(value="排序字段(默认create_time)",name="field",example="1")
    @Setter@Getter
    private String field;

    @ApiModelProperty(value="排序规则(默认升序:asc)",name="order",example="1")
    @Setter@Getter
    private String order;

    @ApiModelProperty(value="页码(默认1)",name="pagenum",example="1")
    @Setter@Getter
    private Integer pagenum;

    @ApiModelProperty(value="每页数量(默认10)",name="pagenum",example="1")
    @Setter@Getter
    private Integer pagesize;

    //构建分页参数
    public static PageVO getPageVO(Map<String,Object>params){
        PageVO vo = new PageVO();
        vo.setField(ParamUtils.isBlank(params.get(PageVO.PARAMNAME_FIELD)) ? PageVO.DEFAULT_FIELD : params.get(PageVO.PARAMNAME_FIELD).toString());
        vo.setOrder(ParamUtils.isBlank(params.get(PageVO.PARAMNAME_ORDER)) ? PageVO.DEFAULT_ORDER : params.get(PageVO.PARAMNAME_ORDER).toString());
        vo.setPagenum(ParamUtils.isBlank(params.get(PageVO.PARAMNAME_PAGENUM)) ? PageVO.DEFAULT_PAGENUM : ParamUtils.strTIntger(params.get(PageVO.PARAMNAME_PAGENUM)));
        vo.setPagesize(ParamUtils.isBlank(params.get(PageVO.PARAMNAME_PAGESIZE)) ? PageVO.DEFAULT_PAGESIZE : ParamUtils.strTIntger(params.get(PageVO.PARAMNAME_PAGESIZE)));
        return vo;
    }

}
