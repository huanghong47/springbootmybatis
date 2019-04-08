package cn.hhfarcry.springbootmybatis.common.base.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-24 09:07
 */
@NoArgsConstructor
@Data
public class HttpClientResult implements Serializable {
    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult(int code) {
        this.code = code;
    }

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }
}
