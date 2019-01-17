package cn.hhfarcry.springbootmybatis.example.service;

import cn.hhfarcry.springbootmybatis.example.entity.ResourceEntity;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 11:21
 */
public interface IResourceService {
    String insertResource(ResourceEntity param);
    Page<ResourceEntity> getPage(Map<String,Object> param);


    List<ResourceEntity> getResourcesByRoleId(Integer roleId);
    List<ResourceEntity>getResourcesByUserId(Integer userId);
}
