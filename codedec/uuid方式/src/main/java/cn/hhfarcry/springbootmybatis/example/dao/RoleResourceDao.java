package cn.hhfarcry.springbootmybatis.example.dao;


import cn.hhfarcry.springbootmybatis.example.entity.RoleResourceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-15 14:32
 */
@Repository
@Mapper
public interface RoleResourceDao {
    int insertRoleResource(RoleResourceEntity param);
    List<RoleResourceEntity> selectByEntity(Map<String, String> param);
    List<Integer> getlistByresourceid(Integer resourceid);
    int insertRoleResources(List<RoleResourceEntity> roleResources);
    int deleteRoleResources(Integer roleId);
    int deleteInbatchRoleResources(List<Integer> roleId);

}
