package cn.hhfarcry.springbootmybatis.example.dao;

import cn.hhfarcry.springbootmybatis.example.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program: emsog
 * @description:
 * @author: huanghong
 * @date: 2019-01-15 14:32
 */
@Repository
@Mapper
public interface UserRoleDao {
    int insertUserRole(UserRoleEntity param);
    List<UserRoleEntity> selectByEntity(Map<String, String> param);
    int insertUserRoles(List<UserRoleEntity> userRoles);
    int deleteUserRoles(Integer userId);
}
