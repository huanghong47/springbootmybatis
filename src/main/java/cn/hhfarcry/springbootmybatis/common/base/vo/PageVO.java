package cn.hhfarcry.springbootmybatis.common.base.vo;

import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import lombok.Data;

import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-10 11:03
 */
@Data
public class PageVO {
    public static final String PARAMNAME_FIELD = "field";
    public static final String PARAMNAME_ORDER = "order";
    public static final String PARAMNAME_PAGENUM = "pagenum";
    public static final String PARAMNAME_PAGESIZE = "pagesize";


    public static final String DEFAULT_FIELD = "create_time";
    public static final String DEFAULT_ORDER = "asc";
    public static final int DEFAULT_PAGENUM = 1;
    public static final int DEFAULT_PAGESIZE = 10;


    private String field;

    private String order;

    private Integer pagenum;

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
