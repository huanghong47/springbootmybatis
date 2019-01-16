package cn.hhfarcry.springbootmybatis.example.dao;


import cn.hhfarcry.springbootmybatis.example.entity.RoleResourceEntity;

import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-15 14:32
 */
public interface RoleResourceDao {
    int insertRoleResource(RoleResourceEntity param);
    List<RoleResourceEntity> selectByEntity(Map<String,String> param);
}
