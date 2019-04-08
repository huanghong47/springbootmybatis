package cn.hhfarcry.springbootmybatis.common.base.vo;

import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import com.github.pagehelper.Page;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: boccasecurityofproductionplatformbackside
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-04-01 16:14
 */
@Data
@NoArgsConstructor
public class ResponseResult{
    /**
     * 返回码
     */
    private int code;

    /**
     * 操作信息
     */
    private String msg;

    /**
     * 异常信息
     */
    private String errormsg;

    /**
     * 条数
     */
    private Long count;

    /**
     * 数据
     */
    @Getter@Setter
    private Object data;

    /**
     * 时间戳
     */
    private Long currenttime;



    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.errormsg = "";
        this.count = -1L;
        this.data = new ArrayList<>();
        this.currenttime = System.currentTimeMillis();
    }

    public ResponseResult(int code, String msg, String errormsg) {
        this.code = code;
        this.msg = msg;
        this.errormsg = errormsg;
        this.count = -1L;
        this.data = new ArrayList<>();
        this.currenttime = System.currentTimeMillis();
    }

    public ResponseResult(int code, String msg, String errormsg, Long count, Object data, Long currenttime) {
        this.code = code;
        this.msg = ParamUtils.isBlank(msg)?"":msg;
        this.errormsg = ParamUtils.isBlank(errormsg)?"":errormsg;
        this.count = ParamUtils.isBlank(count)?-1L:count;
        this.data = ParamUtils.isBlank(data)?new ArrayList<>():data;
        this.currenttime = System.currentTimeMillis();
    }

    public ResponseResult(String msg) {
        switch (msg){
            case "操作成功":
                this.code = CodeMsgVo.SUCCESS.getCode();
                this.msg = msg;
                this.errormsg = "";
                this.data = new ArrayList<>();
                this.count = -1L;
                this.currenttime = System.currentTimeMillis();
                break;
        }
    }

    public ResponseResult(Object data){
        this.code = CodeMsgVo.SUCCESS.getCode();
        this.msg = CodeMsgVo.SUCCESS.getMsg();
        this.errormsg = "";
        this.count = ParamUtils.isBlank(data)?0L:1L;
        this.data = ParamUtils.isBlank(data)?new ArrayList<>():data;;
        this.currenttime = System.currentTimeMillis();
    }

    public ResponseResult(Page<? extends Object>page){
        this.code = CodeMsgVo.SUCCESS.getCode();
        this.msg = CodeMsgVo.SUCCESS.getMsg();
        this.errormsg = "";
        this.count = CollectionUtils.isEmpty(page)?0L:page.getTotal();
        this.data = CollectionUtils.isEmpty(page)?new Page<>():page;
        this.currenttime = System.currentTimeMillis();
    }

    public ResponseResult(List<? extends Object> list){
        this.code = CodeMsgVo.SUCCESS.getCode();
        this.msg = CodeMsgVo.SUCCESS.getMsg();
        this.errormsg = "";
        this.count = CollectionUtils.isEmpty(list)?0L:list.size();
        this.data = CollectionUtils.isEmpty(list)?new ArrayList<>():list;
        this.currenttime = System.currentTimeMillis();
    }
}
