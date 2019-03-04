package cn.hhfarcry.springbootmybatis.example.service.impl;

import cn.hhfarcry.springbootmybatis.common.redis.Constant;
import cn.hhfarcry.springbootmybatis.common.loginstorage.myannotation.LogEnable;
import cn.hhfarcry.springbootmybatis.common.loginstorage.myannotation.LogEvent;
import cn.hhfarcry.springbootmybatis.common.loginstorage.myenum.EventType;
import cn.hhfarcry.springbootmybatis.common.loginstorage.myenum.ModuleType;
import cn.hhfarcry.springbootmybatis.common.security.SecurityService;
import cn.hhfarcry.springbootmybatis.common.security.jwt.JwtUtil;
import cn.hhfarcry.springbootmybatis.common.redis.JedisUtil;
import cn.hhfarcry.springbootmybatis.common.base.service.BaseService;
import cn.hhfarcry.springbootmybatis.common.security.utils.AesCipherUtil;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.example.dao.UserDao;
import cn.hhfarcry.springbootmybatis.example.dao.UserRoleDao;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import cn.hhfarcry.springbootmybatis.example.entity.UserRoleEntity;
import cn.hhfarcry.springbootmybatis.example.service.IUserService;
import com.github.pagehelper.Page;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description:
 * @author: huanghong
 * @date: 2019-01-08 13:16
 */
@Service
@Transactional
@LogEnable // 启动日志拦截
public class UserService  extends BaseService<UserEntity> implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private SecurityService securityService;

    @Override
    @PostConstruct
    public void init(){
        super.baseDao = userDao;
    }

    @Override
    @LogEvent(module = ModuleType.USER,event = EventType.ADD, desc = "新增用户")
    public String insertUser(UserEntity param) {
        Map<String,Object>params = new HashMap<>();
        params.put("userName",param.getUserName());
        //判读当前账号是否存在
        List<UserEntity>existUser = userDao.selectByEntity(params);
        if(CollectionUtils.isNotEmpty(existUser)){
            return "账号已存在";
        }
        //密码以账号+密码进行AES加密
        String key = AesCipherUtil.enCrypto(param.getUserName() + param.getPassword());
        super.buildUser(param);
        param.setPassword(key);
        int result = userDao.insertOrUpdateByEntity(param);
        if(result>0){
            return  "ok";
        }else{
            return "注册新用户失败";
        }
    }


    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;


    @Override
    @LogEvent(module = ModuleType.USER,event = EventType.LOGIN, desc = "登录")
    public String  loginUser(UserEntity param) {
        Map<String,Object>params = new HashMap<>();
        params.put("userName",param.getUserName());
        //判读当前账号是否存在
        List<UserEntity>existUser = userDao.selectByEntity(params);
        if(CollectionUtils.isEmpty(existUser)){
            return "账号不存在";
        }
        //正确密码解密
        String key = AesCipherUtil.deCrypto(existUser.get(0).getPassword());
        if(key.equals(param.getUserName()+param.getPassword())){
            // 清除可能存在的Shiro权限信息缓存
            if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + param.getUserName())) {
                JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + param.getUserName());
            }
            // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + param.getUserName(), currentTimeMillis, Integer.parseInt(refreshTokenExpireTime));

            String token = JwtUtil.sign(param.getUserName(), currentTimeMillis);
            securityService.loginSetToken(token);
            return "ok";
        }else{
            return "账号或密码错误";
        }
    }

    @Override
    public Page<UserEntity> getPage(Map<String, Object> param) {
        return super.getPage(param);
    }


    @Override
    public String bindUserRoles(String userId, List<String> roleIds) {
        userRoleDao.deleteUserRoles(ParamUtils.strTIntger(userId));
        List<UserRoleEntity>params = new ArrayList<>();
        for (String roleId : roleIds) {
            UserRoleEntity param = new UserRoleEntity();
            param.setRoleId(ParamUtils.strTIntger(roleId));
            param.setUserId(ParamUtils.strTIntger(userId));
            params.add(param);
        }
        if(CollectionUtils.isNotEmpty(params)){
            userRoleDao.insertUserRoles(params);
        }
        return "ok";
    }
}
