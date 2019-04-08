package cn.hhfarcry.springbootmybatis.common.security;


import cn.hhfarcry.springbootmybatis.common.base.utils.IPUtil;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.redis.Constant;
import cn.hhfarcry.springbootmybatis.common.security.jwt.JwtUtil;
import cn.hhfarcry.springbootmybatis.example.dao.UserDao;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-18 14:19
 */
@Service
public class SecurityService {

    @Autowired
    private  HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    @Autowired
    private UserDao userDao;

    public  String getToken(){
        return request.getHeader("Authorization");
    }

    //获取当前操作用户
    public UserEntity getUser(){
        String token = getToken();
        UserEntity curUser = null;
        if(ParamUtils.isNotBlank(token)){
            String userName = JwtUtil.getClaim(token, Constant.JWTACCOUNT);
            Map<String,Object> params = new HashMap<>();
            params.put("userName",userName);
            List<UserEntity> userEntitys = userDao.selectByEntity(params);
            curUser = userEntitys.get(0);
        }
        return curUser;
    }

    public void loginSetToken(String token){
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
    }

    //获取ip
    public String getIP(){
        return IPUtil.getIpAddr(request);
    }
}
