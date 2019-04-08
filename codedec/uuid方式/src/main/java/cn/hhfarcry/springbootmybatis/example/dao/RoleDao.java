package cn.hhfarcry.springbootmybatis.example.dao;

import cn.hhfarcry.springbootmybatis.common.base.dao.BaseDao;
import cn.hhfarcry.springbootmybatis.example.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-15 14:32
 */
@Repository
@Mapper
public interface RoleDao extends BaseDao<RoleEntity> {
    /**
     * 根据用户id获取角色列表
     * @param userId
     * @return
     */
    List<RoleEntity>getRolesByUserId(Integer userId);

    /**
     * 根据多个角色id查询角色
     * @param roleids
     * @return
     */
    List<RoleEntity>getRolesByRoleids(List<Integer> roleids);
}
