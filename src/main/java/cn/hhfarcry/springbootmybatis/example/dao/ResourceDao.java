package cn.hhfarcry.springbootmybatis.example.dao;

import cn.hhfarcry.springbootmybatis.common.base.dao.BaseDao;
import cn.hhfarcry.springbootmybatis.example.entity.ResourceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-15 14:32
 */
@Repository
@Mapper
public interface ResourceDao extends BaseDao<ResourceEntity> {

    List<ResourceEntity>getResourcesByRoleId(Integer roleId);
    List<ResourceEntity>getResourcesByUserId(Integer userId);
}
