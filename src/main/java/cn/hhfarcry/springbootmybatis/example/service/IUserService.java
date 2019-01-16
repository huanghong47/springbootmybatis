package cn.hhfarcry.springbootmybatis.example.service;

import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-08 13:16
 */
public interface IUserService {
    String insertUser(UserEntity param);
    List<UserEntity> getlist(Map<String,Object> param);
    Page<UserEntity> getPage(Map<String,Object> param);

}
