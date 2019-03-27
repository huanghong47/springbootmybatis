package cn.hhfarcry.springbootmybatis.example.service.impl;

import cn.hhfarcry.springbootmybatis.common.base.service.BaseService;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.redis.Constant;
import cn.hhfarcry.springbootmybatis.common.redis.JedisUtil;
import cn.hhfarcry.springbootmybatis.common.security.SecurityService;
import cn.hhfarcry.springbootmybatis.common.security.jwt.JwtUtil;
import cn.hhfarcry.springbootmybatis.common.security.utils.AesCipherUtil;
import cn.hhfarcry.springbootmybatis.example.dao.RoleDao;
import cn.hhfarcry.springbootmybatis.example.dao.UserDao;
import cn.hhfarcry.springbootmybatis.example.dao.UserRoleDao;
import cn.hhfarcry.springbootmybatis.example.entity.RoleEntity;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import cn.hhfarcry.springbootmybatis.example.entity.UserRoleEntity;
import cn.hhfarcry.springbootmybatis.example.service.IUserService;
import com.github.pagehelper.Page;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: emsog
 * @description:
 * @author: huanghong
 * @date: 2019-01-08 13:16
 */
@Service
@Transactional
public class UserService  extends BaseService<UserEntity> implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SecurityService securityService;

    @Override
    @PostConstruct
    public void init(){
        super.baseDao = userDao;
    }

    @Override
    //@LogEvent(module = ModuleType.USER,event = EventType.ADD, desc = "新增用户")
    public String insertUser(UserEntity param) {
        Map<String,Object>params = new HashMap<>();
        params.put("userName",param.getUserName());
        //判读当前账号是否存在
        List<UserEntity>existUser = userDao.selectByEntity(params);
        if(CollectionUtils.isNotEmpty(existUser)){
            return "账号已存在";
        }
        //密码进行AES加密
        String key = AesCipherUtil.enCrypto(param.getPassword());
        super.buildUser(param);
        param.setPassword(key);
        int result = userDao.insertOrUpdateByEntity(param);
        if(result>0){
            return  "ok";
        }else{
            return "注册新用户失败";
        }
    }

    @Override
    public String updateUser(UserEntity param) {
        Map<String,Object>params = new HashMap<>();
        params.put("userName",param.getUserName());
        //判读当前账号是否存在
        List<UserEntity>existUser = userDao.selectByEntity(params);
        if(CollectionUtils.isNotEmpty(existUser) && existUser.get(0).getId().intValue() != param.getId().intValue()){
            return "账号已存在";
        }
        super.buildUser(param);
        userDao.updateByEntity(param);
        return "ok";
    }

    @Override
    public String reset(Integer id) {
        UserEntity existUser = userDao.getOne(id);
        existUser.setPassword(AesCipherUtil.enCrypto("123456"));
        super.buildUser(existUser);
        userDao.updateByEntity(existUser);
        return "ok";
    }

    @Override
    public String changepw(Integer id, String newpw, String oldpw) {
        UserEntity existUser = userDao.getOne(id);
        String oldkey =  AesCipherUtil.deCrypto(existUser.getPassword());
        if(!oldkey.equals(oldpw)){
            return "原密码错误";
        }
        existUser.setPassword(AesCipherUtil.enCrypto(newpw));
        super.buildUser(existUser);
        userDao.updateByEntity(existUser);
        return "ok";
    }

    @Override
    public String delete(List<String> userids) {
        List<Integer>ids = new ArrayList<>();
        for (String userid : userids) {
            ids.add(Integer.valueOf(userid));
        }
        userDao.deleteinbatch(ids);
        return "ok";
    }

    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;


    @Override
//    @LogEvent(module = ModuleType.USER,event = EventType.LOGIN, desc = "登录")
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
        if(key.equals(param.getPassword())){
            // 清除可能存在的Shiro权限信息缓存
            if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + param.getUserName())) {
                JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + param.getUserName());
            }
            // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + param.getUserName(), currentTimeMillis, Integer.parseInt(refreshTokenExpireTime));
            UserEntity userEntity = existUser.get(0);
            String token = JwtUtil.sign(userEntity.getUserName(),userEntity.getId()+"", currentTimeMillis);
            //由于乱码问题，先处理============================================
            String token1 = token.substring(0,token.indexOf(".")+1);
            String token2 = token.substring(token.indexOf(".")+1,token.lastIndexOf("."));
            String token3 = token.substring(token.lastIndexOf("."),token.length());
            String payload = new String(Base64.decodeBase64(token2),StandardCharsets.UTF_8);
            String token22 = Base64.encodeBase64String(payload.getBytes(StandardCharsets.UTF_8));
            String newtoken = token1+token22+token3;
            //由于乱码问题，先处理============================================

            securityService.loginSetToken(newtoken);
            return "ok";
        }else{
            return "账号或密码错误";
        }
    }

    @Override
    public Page<UserEntity> getPage(Map<String, Object> param) {
        Page<UserEntity> result = super.getPage(param);
        for (UserEntity userEntity : result) {
            List<RoleEntity>roles = roleDao.getRolesByUserId(userEntity.getId());
            userEntity.setUserRole(roles);
        }
        return result;
    }


    @Override
    public String bindUserRoles(String userId, List<String> roleIds) {
        UserEntity existuser = userDao.getOne(ParamUtils.strTIntger(userId));
        if(ParamUtils.isBlank(existuser)){
            return "用户不存在";
        }
        //删除该用户现有的角色
        userRoleDao.deleteUserRoles(ParamUtils.strTIntger(userId));
        //查询roleIds内的角色（防止添加了一个id不存在的角色）
        List<RoleEntity>existRoles = roleDao.getRolesByRoleids(ParamUtils.strlisttointlist(roleIds));
        List<UserRoleEntity>params = new ArrayList<>();
        for (RoleEntity roleEntity : existRoles) {
            UserRoleEntity param = new UserRoleEntity();
            param.setRoleId(roleEntity.getId());
            param.setUserId(ParamUtils.strTIntger(userId));
            params.add(param);
        }
        if(CollectionUtils.isNotEmpty(params)){
            userRoleDao.insertUserRoles(params);
        }
        return "ok";
    }
}
