package cn.hhfarcry.springbootmybatis.example.dao;


import cn.hhfarcry.springbootmybatis.common.dao.BaseDao;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-08 13:16
 */
@Repository
@Mapper
public interface UserDao extends BaseDao<UserEntity> {

}
