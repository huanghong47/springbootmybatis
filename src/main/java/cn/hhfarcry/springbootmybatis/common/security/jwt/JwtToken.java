package cn.hhfarcry.springbootmybatis.common.security.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @program: springbootmybatis
 * @description: 自定义jwt过滤器
 * @author: huanghong
 * @date: 2019-01-16 15:05
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
