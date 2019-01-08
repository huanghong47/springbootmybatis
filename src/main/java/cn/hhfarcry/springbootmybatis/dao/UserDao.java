package cn.hhfarcry.springbootmybatis.dao;


import cn.hhfarcry.springbootmybatis.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-08 13:16
 */
@Mapper
public interface UserDao {

    int insertUser(Map<String,String> param);
    List<UserEntity>getlist(Map<String,String> param);
}
