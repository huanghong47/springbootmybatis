package cn.hhfarcry.springbootmybatis.common.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-24 09:07
 */
@NoArgsConstructor
public class HttpClientResult implements Serializable {
    /**
     * 响应状态码
     */
    @Setter@Getter
    private int code;

    /**
     * 响应数据
     */
    @Setter@Getter
    private String content;

    public HttpClientResult(int code) {
        this.code = code;
    }

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }
}
