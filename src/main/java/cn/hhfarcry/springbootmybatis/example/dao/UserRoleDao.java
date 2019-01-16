package cn.hhfarcry.springbootmybatis.example.dao;

import cn.hhfarcry.springbootmybatis.example.entity.UserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description:
 * @author: huanghong
 * @date: 2019-01-15 14:32
 */
public interface UserRoleDao {
    int insertUserRole(UserRoleEntity param);
    List<UserRoleEntity> selectByEntity(Map<String,String> param);
}
