package cn.hhfarcry.springbootmybatis.example.service;

import cn.hhfarcry.springbootmybatis.example.entity.RoleEntity;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 11:21
 */
public interface IRoleService {
    /**
     * 新增或编辑角色
     * @param param
     * @return
     */
    String insertRole(RoleEntity param);

    /**
     * 角色查询
     * @param param
     * @return
     */
    Page<RoleEntity> getPage(Map<String, Object> param);

    /**
     * 绑定角色资源
     * @param roleId
     * @param resourceIds
     * @return
     */
    String bindroleResources(String roleId, List<String> resourceIds);

    /**
     * 根据用户id获取角色列表
     * @param userId
     * @return
     */
    List<RoleEntity>getRolesByUserId(String userId);
}
