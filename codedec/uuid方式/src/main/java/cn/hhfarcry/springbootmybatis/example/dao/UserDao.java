package cn.hhfarcry.springbootmybatis.example.dao;


import cn.hhfarcry.springbootmybatis.common.base.dao.BaseDao;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-08 13:16
 */
@Repository
@Mapper
public interface UserDao extends BaseDao<UserEntity> {
    /**
     * 批量删除
     * @param ids
     */
    void deleteinbatch(List<Integer> ids);
}
