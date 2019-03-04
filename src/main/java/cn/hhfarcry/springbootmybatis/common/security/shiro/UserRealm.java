package cn.hhfarcry.springbootmybatis.common.security.shiro;

import cn.hhfarcry.springbootmybatis.common.redis.Constant;
import cn.hhfarcry.springbootmybatis.common.security.jwt.JwtUtil;
import cn.hhfarcry.springbootmybatis.common.redis.JedisUtil;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.example.dao.ResourceDao;
import cn.hhfarcry.springbootmybatis.example.dao.RoleDao;
import cn.hhfarcry.springbootmybatis.example.dao.UserDao;
import cn.hhfarcry.springbootmybatis.example.entity.ResourceEntity;
import cn.hhfarcry.springbootmybatis.example.entity.RoleEntity;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hhfarcry.springbootmybatis.common.security.jwt.JwtToken;
import org.springframework.util.CollectionUtils;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 16:14
 */
@Service
public class UserRealm extends AuthorizingRealm {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final ResourceDao resourceDao;

    @Autowired
    public UserRealm(UserDao userDao, RoleDao roleDao, ResourceDao resourceDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.resourceDao = resourceDao;
    }

    /**
     * 大坑，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String userName = JwtUtil.getClaim(principals.toString(), Constant.JWTACCOUNT);

        Map<String,Object>params = new HashMap<>();
        params.put("userName",userName);
        List<UserEntity> userEntitys = userDao.selectByEntity(params);

        List<RoleEntity>roles = roleDao.getRolesByUserId(userEntitys.get(0).getId());
        for (RoleEntity role : roles) {
            simpleAuthorizationInfo.addRole(role.getRoleName());
        }
        List<ResourceEntity> resources = resourceDao.getResourcesByUserId(userEntitys.get(0).getId());
        for (ResourceEntity resource : resources) {
            simpleAuthorizationInfo.addStringPermission(resource.getResourceName());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得account，用于和数据库进行对比
        String userName = JwtUtil.getClaim(token, Constant.JWTACCOUNT);
        // 帐号为空
        if (ParamUtils.isBlank(userName)) {
            throw new AuthenticationException("Token中帐号为空(The account in Token is empty.)");
        }
        // 查询用户是否存在
        Map<String,Object>params = new HashMap<>();
        params.put("userName",userName);
        List<UserEntity> userEntitys = userDao.selectByEntity(params);
        if (CollectionUtils.isEmpty(userEntitys)) {
            throw new AuthenticationException("该帐号不存在(The account does not exist.)");
        }
        // 开始认证，要AccessToken认证通过，且Redis中存在RefreshToken，且两个Token时间戳一致
        if (JwtUtil.verify(token) && JedisUtil.exists(Constant.PREFIX_SHIRO_REFRESH_TOKEN + userName)) {
            // 获取RefreshToken的时间戳
            String currentTimeMillisRedis = JedisUtil.getObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + userName).toString();
            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            if (JwtUtil.getClaim(token, Constant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
                return new SimpleAuthenticationInfo(token, token, "userRealm");
            }
        }
        throw new AuthenticationException("Token已过期(Token expired or incorrect.)");
    }
}
