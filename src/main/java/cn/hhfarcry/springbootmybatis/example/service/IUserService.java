package cn.hhfarcry.springbootmybatis.example.service;

import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-08 13:16
 */
public interface IUserService {
    /**
     * 新增用户
     * @param param
     * @return
     */
    String insertUser(UserEntity param);

    /**
     * 修改用户
     * @param param
     * @return
     */
    String updateUser(UserEntity param);

    /**
     * 重置密码
     * @param id
     * @return
     */
    String reset(Integer id);

    /**
     * 修改密码
     * @param id
     * @param newpw
     * @param oldpw
     * @return
     */
    String changepw(Integer id, String newpw, String oldpw);

    /**
     * 删除用户
     * @param userids
     * @return
     */
    String delete(List<String> userids);

    /**
     * 查询用户
     * @param param
     * @return
     */
    Page<UserEntity> getPage(Map<String, Object> param);

    /**
     * 绑定用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    String bindUserRoles(String userId, List<String> roleIds);

    /**
     * 登录
     * @param param
     * @return
     */
    String loginUser(UserEntity param);

}
