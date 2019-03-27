package cn.hhfarcry.springbootmybatis.example.service;

import cn.hhfarcry.springbootmybatis.example.entity.ResourceEntity;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 11:21
 */
public interface IResourceService {
    /**
     * 添加资源
     * @param param
     * @return
     */
    String insertResource(ResourceEntity param);

    /**
     * 资源列表
     * @param param
     * @return
     */
    Page<ResourceEntity> getPage(Map<String, Object> param);

    /**
     * 根据角色查询资源
     * @param roleId
     * @return
     */
    List<ResourceEntity> getResourcesByRoleId(String roleId);

    /**
     * 根据用户查询资源
     * @param userId
     * @return
     */
    List<ResourceEntity>getResourcesByUserId(String userId);

    /**
     * 绑定前后端资源
     * @param frontId
     * @param backIds
     * @return
     */
    String bindfbresources(Integer frontId, List<Integer> backIds);
}
